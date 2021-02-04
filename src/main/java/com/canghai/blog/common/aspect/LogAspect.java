package com.canghai.blog.common.aspect;

import com.canghai.blog.biz.entity.SysLog;
import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.service.LogService;
import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.HttpContextUtil;
import com.canghai.blog.common.utils.IPUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.canghai.blog.common.annotation.Log)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object result = null;
        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user!=null){
            long beginTime = System.currentTimeMillis();
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            String ip= IPUtil.getIpAddr(request);
            long time = System.currentTimeMillis()-beginTime;
            SysLog log = new SysLog();
            log.setIp(ip);
            log.setTime(time);
            log.setUsername(user.getUsername());
            logService.saveLog(proceedingJoinPoint,log);
        }
        return result;
    }
}
