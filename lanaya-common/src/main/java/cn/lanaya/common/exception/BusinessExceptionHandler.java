package cn.lanaya.common.exception;

import cn.lanaya.common.format.MessageEnum;
import cn.lanaya.common.format.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

    @ExceptionHandler
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
