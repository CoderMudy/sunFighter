package com.sun.fighter.study.login;

import com.alibaba.fastjson.JSONObject;
import com.sun.fighter.study.domain.SysUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/1
 * @描述
 */
@Slf4j
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(String userName,String password){
        JSONObject result = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            result.put("tokent",subject.getSession().getId());
            result.put("msg","登录成功");
            log.info("登录成功:{}",userName);
        } catch (IncorrectCredentialsException e){
            result.put("msg", "密码错误");
            log.info("密码错误:{}",userName);
        } catch (LockedAccountException e){
            result.put("msg", "登录失败，该用户已被冻结");
            log.info("登录失败，该用户已被冻结:{}",userName);
        }catch (AuthenticationException e) {
            result.put("msg", "该用户不存在");
            log.info("该用户不存在:{}",userName);
        } finally {
            return result;
        }
    }
}
