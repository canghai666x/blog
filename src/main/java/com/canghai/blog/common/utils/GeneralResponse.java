package com.canghai.blog.common.utils;

import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.constants.CommonEnum;
import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@AllArgsConstructor
public class GeneralResponse<T> implements Serializable {
    @Getter
    @Setter
    private int code = CommonConstant.SUCCESS;

    @Getter
    @Setter
    private Object msg = "success";

    @Getter
    @Setter
    private T data;

    public GeneralResponse() {
        super();
    }

    public GeneralResponse(T data) {
        super();
        this.data = data;
    }

    public GeneralResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GeneralResponse(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public GeneralResponse(CommonEnum commonEnum) {
        this.code = commonEnum.getCode();
        this.msg = commonEnum.getMsg();
    }

    public GeneralResponse(Throwable e) {
        super();
        this.code = CommonConstant.ERROR;
        this.msg = e.getMessage();
    }
}
