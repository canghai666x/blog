package com.canghai.blog.common.properties;

import lombok.Data;

@Data
public class ShiroProperties {
    private Long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String cipherKey;
}
