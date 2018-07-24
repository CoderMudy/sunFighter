package com.sun.fighter.study.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.sun.fighter.study.domain.User;

import java.util.List;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */

public interface UserService  extends IService<User> {

    boolean deleteAll();

    List<User> selectListBySQL();
}