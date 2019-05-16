package cn.lanaya.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);
}
