package com.sun.fighter.user;

import com.alibaba.fastjson.JSONObject;
import com.sun.fighter.SunFighterApplication;
import com.sun.fighter.base.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述  使用rest-assured框架，测试controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SunFighterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCtrlTest extends TestBase {

    /**
     * sample：
     * <p>
     * 使用rest-assured框架，测试/user/test 这个URL的结果是否正确
     */
    @Test
    public void testUserListPage() {
        JSONObject result = httpGet("/user/test");
        Integer total = result.getInteger("total");
        Assert.assertTrue(0 != total);
        Assert.assertNotNull(result.get("records"));
        System.out.println(result);
    }
}
