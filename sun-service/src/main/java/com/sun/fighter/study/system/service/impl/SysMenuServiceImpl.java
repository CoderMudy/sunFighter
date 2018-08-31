package com.sun.fighter.study.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.fighter.study.common.PageResult;
import com.sun.fighter.study.system.dao.SysMenuDao;
import com.sun.fighter.study.system.domain.SysMenu;
import com.sun.fighter.study.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author chengyin
 * @since 2018-08-31
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public PageResult<SysMenu> listMenus() {
        List<SysMenu> sysMenuList = selectList(new EntityWrapper<SysMenu>());
        return new PageResult<SysMenu>(sysMenuList);
    }
}
