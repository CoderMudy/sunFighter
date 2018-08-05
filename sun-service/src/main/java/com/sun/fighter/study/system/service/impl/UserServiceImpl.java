package com.sun.fighter.study.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.fighter.study.system.dao.UserDao;
import com.sun.fighter.study.system.domain.User;
import com.sun.fighter.study.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public boolean deleteAll() {
        return retBool(baseMapper.deleteAll());
    }

    @Override
    public List<User> selectListBySQL() {
        return baseMapper.selectListBySQL();
    }

}
