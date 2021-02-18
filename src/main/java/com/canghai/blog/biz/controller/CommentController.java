package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.Comment;
import com.canghai.blog.biz.service.CommentService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.IPUtil;
import com.canghai.blog.common.utils.QueryPage;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/admin")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/findByArticleId/{id}")
    public GeneralResponse findByArticleId(@PathVariable Long id){
        return new GeneralResponse<>(commentService.findByArticleId(id));
    }

    @GetMapping("/list")
    public GeneralResponse list(@RequestBody Comment comment, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(commentService.list(comment,queryPage)));
    }

    public GeneralResponse findById(Long id){
        return new GeneralResponse<>(commentService.findById(id));
    }

    @PostMapping
    @Log("添加评论")
    public GeneralResponse save(@RequestBody Comment comment, HttpServletRequest request){
        try {
            String ip= IPUtil.getIpAddr(request);
            comment.setCrateTime(new Date());
            comment.setIp(ip);
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            comment.setDevice(browser.name()+" "+operatingSystem.getName());
            commentService.add(comment);
            return new GeneralResponse();
        }catch (Exception e){
            //  封装全局异常处理
            System.out.println(e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除评论")
    public GeneralResponse delete(@PathVariable Long id){
        try {
            commentService.delete(id);
            return new GeneralResponse();
        }catch (Exception e){
            throw e;
        }
    }
}
