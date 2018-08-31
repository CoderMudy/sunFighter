package com.sun.fighter.study.controller.login;

import com.sun.fighter.common.JsonData;
import com.sun.fighter.study.controller.common.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/1
 * @描述
 */
@Slf4j
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    @ApiOperation("登录")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String userName, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        subject.login(token);
        return  REDIRECT + "/";
    }
}
