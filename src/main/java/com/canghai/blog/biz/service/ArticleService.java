package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.*;
import com.canghai.blog.biz.mapper.ArticleMapper;
import com.canghai.blog.common.utils.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleCatrgoryService articleCatrgoryService;
    @Autowired
    private ArticleTagService articleTagService;

    public List<Article> findByCategory(Long id){
        return articleMapper.findByCateGory(id);
    }
    public List<Article> findByTag(Long id){
        return articleMapper.findByTag(id);
    }

    public IPage<Article> list(Article article, QueryPage queryPage){
        IPage<Article> page = new Page<>(queryPage.getPage(),queryPage.getLimit());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getId);
        queryWrapper.like(StringUtils.isNotBlank(article.getTitle()),Article::getTitle,article.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(article.getAuthor()),Article::getAuthor,article.getAuthor());
        IPage<Article> selectPage = articleMapper.selectPage(page,queryWrapper);
        findInit(selectPage.getRecords());
        return selectPage;
    }

    private void findInit(List<Article> records) {
        if (!records.isEmpty()){
            records.forEach(article -> {
                List<Category> categoryList = categoryService.findByArticleId(article.getId());
                if (categoryList.size()>0){
                    article.setCategory(categoryList.get(0));
                }
                List<Tag> tagList = tagService.findByArticleId(article.getId());
                article.setTags(tagList);
            });
        }
    }

    public Article findById(Long id){
        Article article = articleMapper.selectById(id);
        if (article!=null){
            List<Article> articles = new ArrayList<>();
            articles.add(article);
            findInit(articles);
            return article;
        }
        return null;
    }

    @Transactional
    public void add(Article article){
        article.setCreateTime(new Date());
        articleMapper.insert(article);
        this.updateArticleCategoryTags(article);
    }

    private void updateArticleCategoryTags(Article article) {
        if (article.getId()!=null){
            if (article.getCategory()!=null){
                articleCatrgoryService.deleteByCategoryId(article.getId());
                Category category = categoryService.findById(article.getCategory());
                if (category!=null){
                    articleCatrgoryService.add(new ArticleCategory(article.getId(),category.getId()));
                }
            }
            if (article.getTags()!=null&&article.getTags().size()>0){
                articleTagService.deleteByArticleId(article.getId());
                article.getTags().forEach(tag -> {
                    articleTagService.add(new ArticleTag(article.getId(),tag.getId()));
                });
            }
        }
    }

    @Transactional
    public void update(Article article){
        articleMapper.updateById(article);
        updateArticleCategoryTags(article);
    }

    @Transactional
    public void delete(Long id){
        if (id!=null&&id!=0){
            articleMapper.deleteById(id);
            articleCatrgoryService.deleteByArticleId(id);
            articleTagService.deleteByArticleId(id);
        }
    }
}
