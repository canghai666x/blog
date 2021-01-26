package com.canghai.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canghai.blog.biz.entity.ArticleTag;
import com.canghai.blog.biz.entity.Tag;

import java.util.List;

public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    List<Tag> findByArticleId(long articleId);
}
