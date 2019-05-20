package cn.lanaya.common.exception;

import cn.lanaya.common.format.MessageEnum;
import cn.lanaya.common.format.R;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.TimeUnit;

@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public R<?> handleServiceException(BusinessException be){
        R<?> r = null;
        try {
        } catch (Throwable throwable) {
        }
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handleException(Exception e) {
        R<?> r = null;
        if(e.getClass().isAssignableFrom(BusinessException.class)){
            BusinessException b = (BusinessException) e;
            r = new R<>(100, b.getMessage(), null);
        }else if(e instanceof RuntimeException){
            r = new R<>(MessageEnum.UNKNOW_ERROR, null);
        }else{
            r = new R<>(MessageEnum.UNKNOW_ERROR, null);
        }
        return r;
    }
}
