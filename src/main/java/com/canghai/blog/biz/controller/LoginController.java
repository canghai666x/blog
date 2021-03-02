package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.LoginLog;
import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.entity.UserLogin;
import com.canghai.blog.biz.service.LoginLogService;
import com.canghai.blog.biz.service.UserService;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.exception.GlobalException;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.HttpContextUtil;
import com.canghai.blog.common.utils.IPUtil;
import com.canghai.blog.common.utils.Md5Util;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(CommonConstant.BASE_API)
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private Md5Util md5Util;

    @Autowired
    private LoginLogService loginLogService;


    @PostMapping("/login")
    public GeneralResponse login(@RequestBody UserLogin userLogin){
        Subject subject=getSubject();
        String encryptPassword = md5Util.encryptPassword(userLogin.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), encryptPassword);
        try {
            subject.login(token);
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            LoginLog log = new LoginLog();
            String ip = IPUtil.getIpAddr(request);
            log.setIp(ip);
            log.setUsername(super.getCurrentUser().getUsername());
            log.setCreateTime(new Date());
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            log.setDevice(browser.getName()+"--"+operatingSystem.getName());
            loginLogService.saveLog(log);
            return new GeneralResponse<>(this.getToken());
        }catch (Exception e){
            e.printStackTrace();
            return new GeneralResponse<>(e);
        }
    }

    @PostMapping("/register")
    public GeneralResponse save(User user){
        try {
            userService.add(user);
            return new GeneralResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new GeneralResponse();
        }
    }

    @GetMapping("/logout")
    public GeneralResponse logout(){
        Subject subject = getSubject();
        subject.logout();
        return new GeneralResponse();
    }


}
