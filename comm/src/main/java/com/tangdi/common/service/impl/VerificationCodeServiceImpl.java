package com.tangdi.common.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficRuntimeException;
import com.tangdi.common.mapper.PubVerifyCodeMapper;
import com.tangdi.common.model.po.PubVerifyCode;
import com.tangdi.common.model.vo.EmailVo;
import com.tangdi.common.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @date 2018-12-26
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private PubVerifyCodeMapper pubVerifyCodeMapper;

    private Integer expiration = 2;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EmailVo sendEmail(PubVerifyCode code) {
        EmailVo emailVo = null;
        String content = "";
        Map<String, Object> qryMap = new HashMap<>();
        qryMap.put("Scenes", code.getScenes());
        qryMap.put("Typ", code.getType());
        qryMap.put("Value", code.getValue());
        PubVerifyCode verificationCode = pubVerifyCodeMapper.findByCondition(qryMap);
        // 如果不存在有效的验证码，就创建一个新的
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email/email.ftl");
        if(verificationCode == null){
            code.setCode(RandomUtil.randomNumbers (6));
            content = template.render(Dict.create().set("code",code.getCode()));
            emailVo = new EmailVo(Arrays.asList(code.getValue()),"eladmin后台管理系统",content);
            pubVerifyCodeMapper.insert(code);
            timedDestruction(code);
        // 存在就再次发送原来的验证码
        } else {
            content = template.render(Dict.create().set("code",verificationCode.getCode()));
            emailVo = new EmailVo(Arrays.asList(verificationCode.getValue()),"eladmin后台管理系统",content);
        }
        return emailVo;
    }

    @Override
    public void validated(PubVerifyCode code) {
        Map<String, Object> qryMap = new HashMap<>();
        qryMap.put("Scenes", code.getScenes());
        qryMap.put("Typ", code.getType());
        qryMap.put("Value", code.getValue());
        PubVerifyCode verificationCode = pubVerifyCodeMapper.findByCondition(qryMap);
        if(verificationCode == null || !verificationCode.getCode().equals(code.getCode())){
            // throw new BadRequestException("无效验证码");
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        } else {
            verificationCode.setStatus(false);
            pubVerifyCodeMapper.updateByPrimaryKey(verificationCode);
        }
    }

    /**
     * 定时任务，指定分钟后改变验证码状态
     * @param verifyCode
     */
    private void timedDestruction(PubVerifyCode verifyCode) {
        //以下示例为程序调用结束继续运行
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        try {
            executorService.schedule(() -> {
                verifyCode.setStatus(false);
                pubVerifyCodeMapper.updateByPrimaryKey(verifyCode);
            }, expiration * 60 * 1000L, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
