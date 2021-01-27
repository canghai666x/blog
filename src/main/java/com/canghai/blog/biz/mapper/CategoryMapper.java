package com.canghai.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.canghai.blog.biz.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> findCategoryByArticleId(long id);
}
