package com.canghai.blog.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPage implements Serializable {
    //当前页
    private int page;
    //每页记录数
    private int limit;
}
