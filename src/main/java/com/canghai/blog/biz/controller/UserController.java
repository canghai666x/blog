package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.service.UserService;
import com.canghai.blog.common.constants.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/user")
public class UserController {
    @Autowired
    private UserService userService;

}
