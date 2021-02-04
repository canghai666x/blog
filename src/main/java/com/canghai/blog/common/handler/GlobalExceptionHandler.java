package com.canghai.blog.common.handler;

import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value= Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public GeneralResponse exception(Exception e){
        log.error("内部错误,{}",e.getMessage());
        e.printStackTrace();
        return new GeneralResponse(e);
    }

    @ExceptionHandler(value = GlobalException.class)
    public GeneralResponse globalExceptionHandle(GlobalException e){
        log.error("全局异常,{}",e.getMessage());
        e.printStackTrace();
        return new GeneralResponse(e);
    }

//    public GeneralResponse handleUnAuthorizedException(UnAuthorizedException e){
//
//    }
}
