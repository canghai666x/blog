package com.canghai.blog.common.auth;

import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.service.UserService;
import com.canghai.blog.common.constants.CommonEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (username==null){
            throw new AuthenticationException(CommonEnum.TOKEN_ERROR.getMsg());
        }
        User user = userService.findByName(username);
        String password = user.getPassword();
        if (user==null){
            throw new AuthenticationException(CommonEnum.LOGIN_ERROR.getMsg());
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
