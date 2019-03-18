package com.tangdi.common.service;


import com.tangdi.common.model.po.PubLog;
import org.aspectj.lang.ProceedingJoinPoint;

public interface PubLogService {

    /**
     * 保存日志
     */
    void add(ProceedingJoinPoint joinPoint, PubLog pubLog);
}
