package com.sun.fighter.study.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.sun.fighter.study.common.PageResult;
import com.sun.fighter.study.system.domain.SysMenu;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author chengyin
 * @since 2018-08-31
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     *
     * @return
     */
    PageResult<SysMenu> listMenus();
}
