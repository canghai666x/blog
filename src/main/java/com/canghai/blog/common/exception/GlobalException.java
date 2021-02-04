package com.canghai.blog.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GlobalException extends RuntimeException{

    @Setter
    @Getter
    private String msg;

    public GlobalException(String message){
        super(message);
        this.msg=message;
    }
}
