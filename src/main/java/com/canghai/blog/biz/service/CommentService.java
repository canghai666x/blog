package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.Comment;
import com.canghai.blog.biz.mapper.CommentMapper;
import com.canghai.blog.common.utils.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> findByArticleId(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.orderByDesc(Comment::getId);
        return commentMapper.selectList(queryWrapper);
    }

    public Comment findById(Long id){
        return commentMapper.selectById(id);
    }

    public IPage<Comment> list(Comment comment, QueryPage queryPage){
        IPage<Comment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(comment.getContent()),Comment::getContent,comment.getContent());
        queryWrapper.orderByDesc(Comment::getId);
        return commentMapper.selectPage(page,queryWrapper);
    }

    @Transactional
    public void add(Comment comment){
        commentMapper.insert(comment);
    }

    @Transactional
    public void delete(Long id){
        commentMapper.deleteById(id);
    }


}
