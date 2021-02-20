package com.canghai.blog.common.auth;

import com.canghai.blog.common.properties.BlogProperties;
import com.canghai.blog.common.properties.ShiroProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ShiroConfig {

    @Autowired
    private BlogProperties blogProperties;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroProperties shiroProperties = blogProperties.getShiroProperties();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        Map<String,String> chain = new LinkedHashMap<>();
        String[] urls = shiroProperties.getAnonUrl().split(",");
        for (String url:urls){
            chain.put(url,"anon");
        }
        chain.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chain);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(securityManager);
        securityManager.setCacheManager(cacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        ShiroProperties shiroProperties = blogProperties.getShiroProperties();
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiroProperties.getCookieTimeout());
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        ShiroProperties shiroProperties = blogProperties.getShiroProperties();
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode(shiroProperties.getCipherKey()));
        return cookieRememberMeManager;
    }

    @Bean
    public CacheManager cacheManager(){
        return new EhCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public SessionManager sessionManager(){
        AuthSessionManager sessionManager = new AuthSessionManager();
        Collection<SessionListener> sessionListeners = new ArrayList<>();
        sessionListeners.add(new ShiroSessionListener());
        sessionManager.setGlobalSessionTimeout(blogProperties.getShiroProperties().getSessionTimeout()*1000L);
        sessionManager.setSessionListeners(sessionListeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
}
