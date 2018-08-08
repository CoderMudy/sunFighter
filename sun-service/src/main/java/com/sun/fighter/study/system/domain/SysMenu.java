package com.sun.fighter.study.system.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 系统菜单sys_menu, 资源树，按钮
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @TableId(value = "sys_menu_id",type = IdType.AUTO)
    private Long sysMenuId;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 菜单类型，button或者menu
     */
    private String type;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否展示
     */
    @TableField("show_flag")
    private String showFlag;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 删除标记Y：正常；N：删除；A：审核
     */
    private String status;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 创建者id
     */
    @TableField("create_id")
    private Long createId;
    /**
     * 修改者id
     */
    @TableField("update_id")
    private Long updateId;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 修改时间
     */
    @TableField("update_date")
    private Date updateDate;

    @Override
    protected Serializable pkVal() {
        return this.sysMenuId;
    }

}
