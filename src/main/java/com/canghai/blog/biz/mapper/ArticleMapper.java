package com.canghai.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canghai.blog.biz.entity.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> findByCateGory(Long id);
    List<Article> findByTag(Long id);
}