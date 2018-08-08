package com.sun.fighter.aspect;

import com.alibaba.fastjson.JSON;
import com.sun.fighter.study.system.domain.SysLog;
import com.sun.fighter.study.system.service.SysLogService;
import com.sun.fighter.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/25
 * @描述 Web层日志切面
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class WebLogAspect {

    @Autowired
    private SysLogService sysLogService;

//    ThreadLocal<Long> startTime = new ThreadLocal<>();

//    @Pointcut("execution(public * com.didispace.web..*.*(..))")
    @Pointcut("execution(public * com.sun.fighter.study.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public void doAround(ProceedingJoinPoint point) throws Throwable{
        long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        // 请求的参数
        Object[] args = point.getArgs();
        String params = "";
        if(args != null && args.length>0){
            params = JSON.toJSONString(args[0]);
        }


        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + methodName);
        log.info("IP : " + IPUtils.getIpAddr());
        log.info("CLASS_METHOD : " + className + "." + methodName);
        log.info("ARGS : " + Arrays.toString(args));



        // 处理完请求，返回内容
        log.info("RESPONSE : " + point.proceed());
        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime));
        SysLog sysLog = SysLog.builder()
                .className(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName())
                .ip(IPUtils.getIpAddr())
                .params(Arrays.toString(args))
                .times(System.currentTimeMillis() - startTime)
                .createDate(new Date()).build();
        sysLogService.insert(sysLog);
    }

//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        startTime.set(System.currentTimeMillis());
//
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 记录下请求内容
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        log.info("RESPONSE : " + ret);
//        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
//    }

}
