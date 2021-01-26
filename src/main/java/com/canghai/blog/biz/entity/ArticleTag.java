package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTag implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("article_id")
    private Long articleId;
    @TableField("tag_id")
    private Long tagId;

    public ArticleTag(Long articleId,Long tagId){
        this.articleId=articleId;
        this.tagId=tagId;
    }
}
