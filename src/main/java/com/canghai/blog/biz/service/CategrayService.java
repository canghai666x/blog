package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.Category;
import com.canghai.blog.biz.mapper.CategoryMapper;
import com.canghai.blog.common.utils.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategrayService {
    @Autowired
    private CategoryMapper categoryMapper;

    public IPage<Category> list(Category category, QueryPage queryPage) {
        IPage<Category> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(category.getName()), Category::getName, category.getName());
        queryWrapper.orderByDesc(Category::getId);
        return categoryMapper.selectPage(page, queryWrapper);
    }

    public List<Category> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(category.getName()), Category::getName, category.getName());
        queryWrapper.orderByDesc(Category::getId);
        return categoryMapper.selectList(queryWrapper);
    }

    @Transactional
    public void add(Category category) {
        if (!exist(category)) {
            categoryMapper.insert(category);
        }
    }

    private boolean exist(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName, category.getName());
        return categoryMapper.selectList(queryWrapper).size() > 0 ? true : false;
    }

    @Transactional
    public void update(Category category){
        categoryMapper.updateById(category);
    }

    public void delete(Long id){
        categoryMapper.deleteById(id);
        //删除与文章相关的Category

    }

    public List<Category> findByArticleId(Long id){
        return categoryMapper.findCategoryByArticleId(id);
    }
}
