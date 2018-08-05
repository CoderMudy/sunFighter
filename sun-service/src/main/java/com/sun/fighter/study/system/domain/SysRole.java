package com.sun.fighter.study.system.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_role_id", type = IdType.AUTO)
    private Integer sysRoleId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色描述
     */
    private String desc;
    /**
     * 角色编号
     */
    private String code;
    /**
     * 创建者id
     */
    @TableField("create_id")
    private Integer createId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改者id
     */
    @TableField("update_id")
    private Date updateId;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Date updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.sysRoleId;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        ", sysRoleId=" + sysRoleId +
        ", roleName=" + roleName +
        ", desc=" + desc +
        ", code=" + code +
        ", createId=" + createId +
        ", createTime=" + createTime +
        ", updateId=" + updateId +
        ", updateTime=" + updateTime +
        "}";
    }
}
