package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.SysLog;
import com.canghai.blog.biz.mapper.LogMapper;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.utils.QueryPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public IPage<SysLog> list(SysLog sysLog, QueryPage queryPage){
        IPage<SysLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysLog::getId);
        queryWrapper.like(StringUtils.isNotBlank(sysLog.getUsername()), SysLog::getUsername, sysLog.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(sysLog.getIp()), SysLog::getIp, sysLog.getIp());
        return logMapper.selectPage(page,queryWrapper);
    }

    public void delete(Long id){
        logMapper.deleteById(id);
    }

    public void saveLog(ProceedingJoinPoint proceedingJoinPoint, SysLog sysLog)throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        if (annotation!=null){
            sysLog.setOperation(annotation.value());
        }
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName=signature.getName();
        sysLog.setMethod(className+" "+methodName);
        Object[] args = proceedingJoinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer d = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = d.getParameterNames(method);
        if (args!=null&&parameterNames!=null){
            StringBuilder params = new StringBuilder();
            params = handleParams(params,args, Arrays.asList(parameterNames));
            String str = params.toString();
            if (str.length()>=100){
                str= str.substring(0,80)+"...";
                sysLog.setParams(str);
            }
        }
        sysLog.setCreateTime(new Date());
        logMapper.insert(sysLog);
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List<String> paramNames) throws JsonProcessingException {
        for (int i=0;i< args.length;i++){
            if (args[i] instanceof Map){
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList();
                for (Object key:set){
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params,list.toArray(),paramList);
            }else{
                if (args[i] instanceof Serializable){
                    Class<?> clazz = args[i].getClass();
                    try {
                        clazz.getDeclaredMethod("toString",new Class[]{null});
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                }else if (args[i] instanceof MultipartFile){
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                }else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }


}
