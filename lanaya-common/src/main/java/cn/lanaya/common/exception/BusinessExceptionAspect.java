package cn.lanaya.common.exception;

import cn.lanaya.common.format.R;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class BusinessExceptionAspect {
    private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionAspect.class);

    @Autowired
    private BusinessExceptionHandler handler;

    @Pointcut("execution(* cn.lanaya.*.web.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = atts.getRequest();
        logger.info("url=[{}]", request.getRequestURL());
        logger.info("method=[{}]", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}, {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
    }

    @Around("pointCut()")
    public R<?> doAround(ProceedingJoinPoint joinPoint){
        return null;
    }

    @AfterReturning(value = "pointCut()", returning = "object")
    public void doAfterReturning(Object object){
        logger.info("response={}", object.toString());
    }

}
