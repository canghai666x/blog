package com.canghai.blog.common.auth;

import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.constants.CommonEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class AuthSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //ServletRequest 转为httpServletRequest，获取authorization请求头
        String token = WebUtils.toHttp(request).getHeader(CommonConstant.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return token;
        }else{
            //无法获取authorization请求头则从cookies中查找
            return super.getSessionId(request, response);
        }
    }

}
