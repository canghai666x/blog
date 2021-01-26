package com.canghai.blog.biz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.mapper.UserMapper;
import com.canghai.blog.common.utils.Md5Util;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Md5Util md5Util;

    public User findByName(String username){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        List<User> users = userMapper.selectList(queryWrapper);
        return users.size()>0?users.get(0):null;
    }

    @Transactional
    public void add(User user){
        String encryptPassword = md5Util.encryptPassword(user.getPassword());
        user.setPassword(encryptPassword);
        user.setAvatar("/img/avatar/default.jpg");
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

    @Transactional
    public void update(User user){
        user.setPassword(null);
        userMapper.updateById(user);
    }

    @Transactional
    public void updatePass(User user){
        User newUser= new User().setId(user.getId()).setPassword(user.getPassword());
        userMapper.updateById(newUser);
    }

    @Transactional
    public void delete(Long id){
        userMapper.deleteById(id);
    }
}
