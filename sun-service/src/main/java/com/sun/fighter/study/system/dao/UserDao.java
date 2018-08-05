package com.sun.fighter.study.system.dao;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.sun.fighter.study.common.SuperDao;
import com.sun.fighter.study.system.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */
public interface UserDao extends SuperDao<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     */
    @SqlParser(filter = true)
    @Select("select test_id as id, name, age, test_type from user")
    List<User> selectListBySQL();
}
