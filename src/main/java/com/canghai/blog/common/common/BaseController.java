package com.canghai.blog.common.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.canghai.blog.biz.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected static Subject getSubject(){return SecurityUtils.getSubject();
    }
    protected User getCurrentUser(){
        return (User) getSubject().getPrincipal();
    }
    protected Session getSession(){
        return getSubject().getSession();
    }
    protected Map<String,Object> getToken(){
        Map<String,Object> map = new HashMap<>();
        map.put("token",getSession().getId());
        return map;
    }
    protected Map<String,Object> getDate(IPage<?> iPage){
        Map<String,Object> data = new HashMap<>();
        data.put("rows",iPage.getRecords());
        data.put("total",iPage.getTotal());
        return data;
    }
}
