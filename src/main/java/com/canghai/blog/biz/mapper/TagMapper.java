package com.canghai.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canghai.blog.biz.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findByArticleId(Long articleId);
}
