package com.sun.fighter.study.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.sun.fighter.study.domain.User;
import com.sun.fighter.study.enums.AgeEnum;
import com.sun.fighter.study.enums.PhoneEnum;
import com.sun.fighter.study.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页 PAGE
     */
    @ApiOperation("测试")
    @GetMapping("/test")
    public Page<User> test() {
        return userService.selectPage(new Page<User>(0, 12));
    }

    /**
     * AR 部分测试
     */
    @GetMapping("/test1")
    public Page<User> test1() {
        User user = User.builder().name("testAr").age(AgeEnum.ONE).role((long) 1).build();
        System.err.println("删除所有：" + user.delete(null));
        user.setRole(111L);
        user.setTestDate(new Date());
        user.setPhone(PhoneEnum.CMCC);
        user.insert();
        System.err.println("查询插入结果：" + user.selectById().toString());
        user.setName("mybatis-plus-ar");
        System.err.println("更新：" + user.updateById());
        return user.selectPage(new Page<User>(0, 12), null);
    }

    /**
     * 增删改查 CRUD
     */
    @GetMapping("/test2")
    public User test2() {
        System.err.println("删除一条数据：" + userService.deleteById(1L));
        System.err.println("deleteAll：" + userService.deleteAll());
        System.err.println("插入一条数据：" + userService.insert(User.builder().id(1L).name("张三").age(AgeEnum.TWO).role(1L).build()));
        User user = User.builder().name("张三").age(AgeEnum.TWO).role((long) 1).build();
        boolean result = userService.insert(user);
        // 自动回写的ID
        Long id = user.getId();
        System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
        System.err.println("查询：" + userService.selectById(id).toString());
        System.err.println("更新一条数据：" + userService.updateById(User.builder().id(1L).name("三毛").age(AgeEnum.TWO).role(1L).build()));
        for (int i = 0; i < 5; ++i) {
            userService.insert(User.builder().id(Long.valueOf(100 + i)).name("张三").age(AgeEnum.TWO).role(1L).build());
        }
        Page<User> userListPage = userService.selectPage(new Page<User>(1, 5), new EntityWrapper<>(User.builder().build()));
        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());
        return userService.selectById(1L);
    }

    /**
     * 插入 OR 修改
     */
    @GetMapping("/test3")
    public User test3() {
        User user = User.builder().id(1L).name("王五").age(AgeEnum.TWO).role(1L).build();
        user.setPhone(PhoneEnum.CT);
        System.out.println("插入前：" + user.toString());
        userService.insertOrUpdate(user);
        user = userService.selectById(1L);
        System.out.println("更新后：" + user.toString());
        return user;
    }


    /**
     * 测试实体注解注入条件 LIKE 查询
     */
    @GetMapping("/like")
    public Object like() {
        JSONObject result = new JSONObject();
        User user = User.builder().name("三").build();
        result.put("result", userService.selectList(new EntityWrapper<User>(user)));
        return result;
    }

    @GetMapping("/add")
    public Object addUser() {
        User user = User.builder().name("张三'特殊`符号").age(AgeEnum.TWO).role(1L).build();
        user.setPhone(PhoneEnum.CUCC);
        JSONObject result = new JSONObject();
        result.put("result", userService.insert(user));
        return result;
    }

    @GetMapping("/selectsql")
    public Object getUserBySql() {
        JSONObject result = new JSONObject();
        result.put("records", userService.selectListBySQL());
        return result;
    }

    /**
     * 7、分页 size 一页显示数量  current 当前页码
     * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
     * 方式二：http://localhost:8080/user/pagehelper?size=1&current=1<br>
     */

    // 参数模式分页
    @GetMapping("/page")
    public Object page(Page page) {
        return userService.selectPage(page);
    }

    // ThreadLocal 模式分页
    @GetMapping("/pagehelper")
    public Object pagehelper(Page page) {
        PageHelper.setPagination(page);
        page.setRecords(userService.selectList(null));
        page.setTotal(PageHelper.freeTotal());//获取总数并释放资源 也可以 PageHelper.getTotal()
        return page;
    }


    /**
     * 测试事物
     * http://localhost:8080/user/test_transactional<br>
     * 访问如下并未发现插入数据说明事物可靠！！<br>
     * http://localhost:8080/user/test<br>
     * <br>
     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
     * 需要事物的方法加上 @Transactional 必须的哦！！
     */
    @Transactional
    @GetMapping("/test_transactional")
    public void testTransactional() {
        userService.insert(User.builder().id(1000L).name("测试事物").age(AgeEnum.TWO).role(1L).build());
        System.out.println(" 这里手动抛出异常，自动回滚数据");
        throw new RuntimeException();
    }
}
