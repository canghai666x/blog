package com.canghai.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("article_category")
public class ArticleCategory implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("article_id")
    private Long articleId;
    @TableField("category_id")
    private Long categoryId;
    public ArticleCategory(Long articleId,Long categoryId){
        this.articleId=articleId;
        this.categoryId=categoryId;
    }
}
