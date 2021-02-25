package com.canghai.blog.biz.entity;

import lombok.Data;

@Data
public class UserLogin {
    private String username;
    private String password;
    private Boolean isRememberMe;
}
