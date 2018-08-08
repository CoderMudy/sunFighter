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
 * 角色权限表
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_permission")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统资源id
     */
    @TableId(value = "sys_role_permission_id",type = IdType.AUTO)
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

   @Override
    protected Serializable pkVal() {
        return this.sysRolePermissionId;
    }

}
