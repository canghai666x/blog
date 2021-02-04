package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.SysLog;
import com.canghai.blog.biz.service.LogService;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/log")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @PostMapping("/list")
    public GeneralResponse list(@RequestBody SysLog log, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(logService.list(log,queryPage)));
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        try {
            logService.delete(id);
            return new GeneralResponse();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
