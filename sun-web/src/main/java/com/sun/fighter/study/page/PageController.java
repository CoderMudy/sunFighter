package com.sun.fighter.study.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/29
 * @描述
 */
@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("main")
    public String main(){
        return "main/main";
    }

    @GetMapping("treeTable")
    public String treeTable(){
        return "demo/treetable";
    }

    @GetMapping("treeGrid")
    public String treeGrid(){
        return "demo/treegrid";
    }

    @GetMapping("userList")
    public String userList(){
        return "system/userList";
    }

    @GetMapping("toMenuList")
    public String toSyeMenu(){
        return "system/menuList";
    }

}
