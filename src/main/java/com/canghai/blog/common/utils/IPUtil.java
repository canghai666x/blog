package com.canghai.blog.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
    private static final String UNKNOWN="unkhown";

    public static String getIpAddr(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null||ip.length()==0||UNKNOWN.equalsIgnoreCase(UNKNOWN)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||UNKNOWN.equalsIgnoreCase(UNKNOWN)) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||UNKNOWN.equalsIgnoreCase(UNKNOWN)){
            ip=request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
    }
}
