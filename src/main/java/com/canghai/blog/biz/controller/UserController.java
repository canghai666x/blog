package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.service.UserService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findByName")
    public GeneralResponse findByName(@RequestParam String username){
        return new GeneralResponse<>(userService.findByName(username));
    }

    public GeneralResponse findById(@PathVariable Long id){
        return new GeneralResponse<>(userService.findById(id));
    }

    @PostMapping
    @Log("新增用户")
    public GeneralResponse add(@RequestBody User user){
        try {
            userService.add(user);
            return new GeneralResponse();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新用户")
    public GeneralResponse update(@RequestBody User user){
        try {
            userService.update(user);
            return new GeneralResponse<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping("/changePass")
    @Log("修改密码")
    public GeneralResponse updatePass(@RequestBody User user){
        try {
            userService.updatePass(user);
            return new GeneralResponse<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除用户")
    public GeneralResponse delete(@PathVariable Long id){
        try {
            userService.delete(id);
            return new GeneralResponse<>();
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
