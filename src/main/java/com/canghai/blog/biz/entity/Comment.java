package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("article_id")
    private Long articleId;
    @TableField("nick_name")
    private String nickName;
    private String content;
    private String email;
    private String ip;
    private String device;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date crateTime;
}
