package com.tangdi.common.service.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficRuntimeException;
import com.tangdi.common.mapper.PubEmailConfigMapper;
import com.tangdi.common.model.po.PubEmailConfig;
import com.tangdi.common.model.vo.EmailVo;
import com.tangdi.common.service.EmailService;
import com.tangdi.common.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author jie
 * @date 2018-12-26
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Autowired
    private PubEmailConfigMapper pubEmailConfigMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PubEmailConfig update(PubEmailConfig emailConfig, PubEmailConfig old) {
        try {
            if(!emailConfig.getPass().equals(old.getPass())){
                // 对称加密
                emailConfig.setPass(EncryptUtils.desEncrypt(emailConfig.getPass()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pubEmailConfigMapper.insert(emailConfig);
        return emailConfig;
    }

    @Override
    public PubEmailConfig find() {
        return new PubEmailConfig();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(EmailVo emailVo, PubEmailConfig emailConfig){
        if(emailConfig == null){
//            throw new BadRequestException("请先配置，再操作");
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        }
        /**
         * 封装
         */
        MailAccount account = new MailAccount();
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        try {
            // 对称解密
            account.setPass(EncryptUtils.desDecrypt(emailConfig.getPass()));
        } catch (Exception e) {
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        }
        account.setFrom(emailConfig.getUser()+"<"+emailConfig.getFromUser()+">");
        //ssl方式发送
        account.setStartttlsEnable(true);
        String content = emailVo.getContent();
        /**
         * 发送
         */
        try {
            MailUtil.send(account,
                          emailVo.getTos(),
                          emailVo.getSubject(),
                          content,
                          true);
        }catch (Exception e){
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        }
    }
}
