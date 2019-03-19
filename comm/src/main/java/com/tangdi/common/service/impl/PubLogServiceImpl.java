package com.tangdi.common.service.impl;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.tangdi.common.aop.log.Log;
import com.tangdi.common.common.Constants;
import com.tangdi.common.mapper.PubLogMapper;
import com.tangdi.common.model.po.PubLog;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.common.utils.RequestHolder;
import com.tangdi.common.utils.StringUtils;
import com.tangdi.common.service.PubLogService;
import com.tangdi.common.service.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author ron
 * @date 2018/12/26 11:02
 */
@Service
public class PubLogServiceImpl implements PubLogService {

    @Autowired
    private PubLogMapper pubLogMapper;
    @Autowired
    private RedisService redisService;

    private final String LOGINPATH = "login";



    @Override
    public void add(ProceedingJoinPoint joinPoint, PubLog pubLog) {
        // 获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log aopLog = method.getAnnotation(Log.class);
        // 描述
        if (pubLog != null) {
            pubLog.setDescription(aopLog.value());
        }
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用户名
        String username = "";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }
        // 获取IP地址
        pubLog.setRequestIp(StringUtils.getIP(request));

        if(!LOGINPATH.equals(signature.getName())){
            String token = request.getHeader(Constants.TOKEN);
            String userJson = redisService.getByDecompress(token);
            JSONObject userInfo = JSON.parseObject(userJson,JSONObject.class);
            username = userInfo.getStr("userName");
        } else {
            try {
                JSONObject jsonObject = new JSONObject(argValues[0]);
                username = jsonObject.get("userName").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        pubLog.setLogId(IdGenUtils.getUUID());
        pubLog.setMethod(methodName);
        pubLog.setUsername(username);
        pubLog.setParams(params + " }");
        pubLogMapper.insert(pubLog);
    }
}
