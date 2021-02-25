package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canghai.blog.biz.entity.LoginLog;
import com.canghai.blog.biz.mapper.LoginLogMapper;
import com.canghai.blog.common.utils.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    public IPage<LoginLog> list(LoginLog log, QueryPage queryPage){
        IPage<LoginLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<LoginLog> queryWrapper = new QueryWrapper<LoginLog>().lambda();
        queryWrapper.like(StringUtils.isNotBlank(log.getLocation()),LoginLog::getLocation,log.getLocation());
        queryWrapper.orderByDesc(LoginLog::getCreateTime);
        return loginLogMapper.selectPage(page,queryWrapper);
    }

    @Transactional
    public void delete(Long id){
        loginLogMapper.deleteById(id);
    }

    @Transactional
    public void saveLog(LoginLog log){
        loginLogMapper.insert(log);
    }
}
