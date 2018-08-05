package com.sun.fighter.study.system.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 系统菜单sys_menu, 资源树，按钮
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @TableId("sys_menu_id")
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


    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return this.sysMenuId;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", sysMenuId=" + sysMenuId +
        ", name=" + name +
        ", parentId=" + parentId +
        ", type=" + type +
        ", url=" + url +
        ", icon=" + icon +
        ", showFlag=" + showFlag +
        ", permission=" + permission +
        ", sort=" + sort +
        ", status=" + status +
        ", remark=" + remark +
        ", createId=" + createId +
        ", updateId=" + updateId +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
