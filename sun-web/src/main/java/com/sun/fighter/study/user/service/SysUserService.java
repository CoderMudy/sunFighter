package com.sun.fighter.study.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.sun.fighter.study.domain.SysUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author chengyin
 * @since 2018-07-23
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过登录账号查询用户信息
     * @param userName
     * @return
     */
    public SysUser findByUserName(String userName);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    public SysUser insertSysUser(SysUser sysUser);
}
