package com.sun.fighter.study.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/1
 * @描述
 */
@RestController
public class LoginController {

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
