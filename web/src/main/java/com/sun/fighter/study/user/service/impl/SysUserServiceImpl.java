package com.sun.fighter.study.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.fighter.study.dao.SysUserDao;
import com.sun.fighter.study.domain.SysUser;
import com.sun.fighter.study.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author chengyin
 * @since 2018-07-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Override
    public SysUser findByUserName(String userName) {
        SysUser sysUser = SysUser.builder().userName(userName).build();
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>(sysUser);
        return selectOne(entityWrapper);
    }
}
