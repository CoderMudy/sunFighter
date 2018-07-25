package com.sun.fighter.study.user.controller;


import com.sun.fighter.study.domain.SysUser;
import com.sun.fighter.study.user.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author chengyin
 * @since 2018-07-23
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("新增用户")
    @PostMapping
    public void insertSysUser(SysUser sysUser){
        sysUserService.insertSysUser(sysUser);
    }

}

