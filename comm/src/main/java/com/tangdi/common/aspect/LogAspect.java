package com.tangdi.common.aspect;

import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficRuntimeException;
import com.tangdi.common.model.po.PubLog;
import com.tangdi.common.service.PubLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jie
 * @date 2018-11-24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private PubLogService pubLogService;

    private long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.tangdi.common.aop.log.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint){
        Object result = null;
        currentTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        }
        PubLog pubLog = new PubLog();
        pubLog.setLogType("INFO");
        pubLog.setAddTime(new Date());
        pubLogService.add(joinPoint, pubLog);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        PubLog pubLog = new PubLog();
        pubLog.setLogType("ERROR");
        pubLog.setAddTime(new Date());
        pubLogService.add((ProceedingJoinPoint)joinPoint, pubLog);
    }
}
