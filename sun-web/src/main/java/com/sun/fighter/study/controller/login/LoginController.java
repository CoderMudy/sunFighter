package com.sun.fighter.study.controller.login;

import com.sun.fighter.common.JsonData;
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
public class LoginController {

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
        ModelMap modelMap = new ModelMap();
        JsonData result = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            log.info("登录成功:{}",userName);
            log.info("登录token:{}",subject.getSession().getId());
            result = JsonData.success("登录成功");
        } catch (IncorrectCredentialsException e){
            log.info("密码错误:{}",userName);
            result = JsonData.fail("密码错误");
        } catch (LockedAccountException e){
            log.info("登录失败，该用户已被冻结:{}",userName);
            result = JsonData.fail("登录失败，该用户已被冻结");
        }catch (AuthenticationException e) {
            log.info("该用户不存在:{}",userName);
            result = JsonData.fail("该用户不存在");
        } finally {
            modelMap.addAttribute("jsonData",result);
            if(result.isRet()){
                return  "redirect:index";
            }else{
                return "login";
            }
        }
    }
}
