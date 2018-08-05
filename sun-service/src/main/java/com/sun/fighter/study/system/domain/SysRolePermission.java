package com.sun.fighter.study.system.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@TableName("sys_role_permission")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统资源id
     */
    @TableId("sys_role_permission_id")
    private Long sysRolePermissionId;
    /**
     * 资源id
     */
    @TableField("sys_menu_id")
    private Long sysMenuId;
    /**
     * 角色id
     */
    @TableField("sys_role_id")
    private Long sysRoleId;
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


    public Long getSysRolePermissionId() {
        return sysRolePermissionId;
    }

    public void setSysRolePermissionId(Long sysRolePermissionId) {
        this.sysRolePermissionId = sysRolePermissionId;
    }

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.sysRolePermissionId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        ", sysRolePermissionId=" + sysRolePermissionId +
        ", sysMenuId=" + sysMenuId +
        ", sysRoleId=" + sysRoleId +
        ", createId=" + createId +
        ", updateId=" + updateId +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
