package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.Tag;
import com.canghai.blog.biz.mapper.TagMapper;
import com.canghai.blog.common.utils.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagMapper tagmapper;
    @Autowired
    private ArticleTagService articleTagService;

    public List<Tag> list(Tag tag, QueryPage queryPage){
        IPage<Tag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(tag.getName()),Tag::getName,tag.getName());
        queryWrapper.orderByDesc(Tag::getId);
        return tagmapper.selectList(queryWrapper);
    }

    public List<Tag> list(Tag tag){
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(tag.getName()),Tag::getName,tag.getName());
        queryWrapper.orderByDesc(Tag::getId);
        return tagmapper.selectList(queryWrapper);
    }

    @Transactional
    public void add(Tag tag){
        if (!exist(tag)){
            tagmapper.insert(tag);
        }
    }

    private boolean exist(Tag tag){
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getName,tag.getName());
        return tagmapper.selectList(queryWrapper).size()>0;
    }

    @Transactional
    public void update(Tag tag){
        tagmapper.updateById(tag);
    }

    public void delete(Long id){
        tagmapper.deleteById(id);
        //删除相关联的
        articleTagService.deleteByArticleId(id);
    }
}
