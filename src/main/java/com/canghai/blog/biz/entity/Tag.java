package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField(exist = false)
    private Long count;
    public Tag(){
    }
    public Tag(String name){
        this.name=name;
    }
}
