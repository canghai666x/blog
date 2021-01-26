package com.canghai.blog.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonEnum {
    LOGIN_ERROR(500, "用户名或密码错误"),
    TOKEN_ERROR(401, "Token已失效，请重新登录"),
    PARAM_ERROR(401, "参数错误"),
    USER_ERROR(500, "获取用户信息失败");

    private final int code;
    private final String msg;
}
