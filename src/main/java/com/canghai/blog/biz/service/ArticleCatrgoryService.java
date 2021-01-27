package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.canghai.blog.biz.entity.ArticleCategory;
import com.canghai.blog.biz.mapper.ArticleCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleCatrgoryService {
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Transactional
    public void add(ArticleCategory articleCategory){
        if (!exist(articleCategory)){
            articleCategoryMapper.insert(articleCategory);
        }
    }

    private boolean exist(ArticleCategory articleCategory) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId,articleCategory.getArticleId());
        queryWrapper.eq(ArticleCategory::getCategoryId,articleCategory.getCategoryId());
        return articleCategoryMapper.selectList(queryWrapper).size()>0?true:false;
    }

    @Transactional
    public void deleteByArticleId(Long id){
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId,id);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Transactional
    public void deleteByCategoryId(Long id){
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getCategoryId,id);
        articleCategoryMapper.delete(queryWrapper);
    }

}
