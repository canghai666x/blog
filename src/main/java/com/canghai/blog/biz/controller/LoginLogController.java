package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.LoginLog;
import com.canghai.blog.biz.service.LoginLogService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/list")
    public GeneralResponse findByPage(@RequestBody LoginLog loginLog, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(loginLogService.list(loginLog,queryPage)));
    }

    @Log("删除登陆日志")
    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        try {
            loginLogService.delete(id);
            return new GeneralResponse<>();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

}
