package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.canghai.blog.biz.entity.ArticleTag;
import com.canghai.blog.biz.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Transactional
    public void add(ArticleTag articleTag){
        if(!exist(articleTag))
            articleTagMapper.insert(articleTag);
    }

    private boolean exist(ArticleTag articleTag) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,articleTag.getArticleId());
        queryWrapper.eq(ArticleTag::getTagId,articleTag.getTagId());
        return articleTagMapper.selectList(queryWrapper).size()>0;
    }

    @Transactional
    public void deleteByArticleId(Long id){
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,id);
        articleTagMapper.delete(queryWrapper);
    }

    @Transactional
    public void deleteByTagId(Long id){
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId,id);
        articleTagMapper.delete(queryWrapper);
    }
}
