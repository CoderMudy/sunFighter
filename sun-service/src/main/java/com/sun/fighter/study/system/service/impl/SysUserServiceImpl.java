package com.sun.fighter.study.system.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.fighter.study.system.dao.SysUserDao;
import com.sun.fighter.study.system.domain.SysUser;
import com.sun.fighter.study.system.service.SysUserService;
import com.sun.fighter.study.util.EndecryptUtils;
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

    @Override
    public SysUser insertSysUser(SysUser sysUser) {
        EndecryptUtils.md5Password(sysUser);
        insert(sysUser);
        return sysUser;
    }
}
