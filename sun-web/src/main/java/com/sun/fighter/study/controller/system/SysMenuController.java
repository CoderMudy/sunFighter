package com.sun.fighter.study.controller.system;

import com.sun.fighter.study.common.PageResult;
import com.sun.fighter.study.system.domain.SysMenu;
import com.sun.fighter.study.system.service.SysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("获取菜单")
    @GetMapping("/list")
    public PageResult<SysMenu> list(){
        return sysMenuService.listMenus();
    }
}
