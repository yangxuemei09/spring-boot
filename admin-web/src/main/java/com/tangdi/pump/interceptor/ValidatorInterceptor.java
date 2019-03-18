package com.tangdi.pump.interceptor;

import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Method;

/**
 * @author ron
 * @date 2018/12/26 18:09
 */
@Slf4j
@Aspect
@Component
public class ValidatorInterceptor {
    @Pointcut("execution(* com.tangdi.pump.controller..*(..)) && @within(org.springframework.web.bind.annotation.RequestMapping)")
    public void validatorMethodPointcut() {
    }


    @Around("validatorMethodPointcut()")
    public Object aroundAdvisor(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (proceedingJoinPoint.getArgs() != null) {
            String errorMsg = validArgs(proceedingJoinPoint.getArgs());
            if (errorMsg != null && !"".equals(errorMsg)) {
                return ApiResponse.buildErrorResult(new TrafficRuntimeException(CodeEnum.INVALID_PARAM.getCode(), CodeEnum.INVALID_PARAM.getMessage() + " : " + errorMsg));
            }
        }

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("Invoke method {}", method.getName(), e);

            return ApiResponse.buildErrorResult(e);
        }

        return result;
    }

    /**
     * 校验参数是否合法
     *
     * @param args
     * @return
     */
    private String validArgs(Object[] args) {
        String errorMessages = null;
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult != null && bindingResult.hasErrors()) {
                    StringBuilder sb = new StringBuilder();
                    for (ObjectError error : bindingResult.getAllErrors()) {
                        sb.append(error.getDefaultMessage()).append(",");
                    }
                    errorMessages = sb.toString();

                    if (errorMessages.contains(",")) {
                        errorMessages = errorMessages.substring(0, errorMessages.length() - 1);
                    }
                }

                break;
            }
        }

        return errorMessages;
    }
}
