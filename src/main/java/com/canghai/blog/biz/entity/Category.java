package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Category {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
}
