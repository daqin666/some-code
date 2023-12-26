package com.example.somecode.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class AopLog {
    //线程局部的变量,解决多线程中相同变量的访问冲突问题。
    //定义切点 1
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.example..*.*(..))")
    public void aopWebLog() {
    }
    //定义切点 2
    @Pointcut("execution(public * com.example..*.*(..))")
    public void myPointcut() {
    }

    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP方法 : " + request.getMethod());
        log.info("IP地址 : " + request.getRemoteAddr());
        log.info("类的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        log.info("参数 : " + request.getQueryString());
    }

    @AfterReturning(pointcut = "aopWebLog()",returning = "retObject")
    public void doAfterReturning(Object retObject) throws Throwable {
        // 处理完请求，返回内容
        log.info("应答值 : " + retObject);
        log.info("费时: " + (System.currentTimeMillis() - startTime.get()));
    }

    //抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
    @AfterThrowing(pointcut = "aopWebLog()", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        log.error("执行 " + " 异常", ex);
    }


    @Around("myPointcut()")
    public Object mylogger (ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] arry = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();
//        log.info("调用前："+className+":"+methodName+"传递的参数为："+mapper.writeValueAsString(arry));
        Object obj = pjp.proceed();
//        log.info("调用后"+className+":"+methodName+"返回值为："+mapper.writeValueAsString(obj));
        return obj;
    }

}
