package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
}
