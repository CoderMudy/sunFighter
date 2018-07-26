package com.sun.fighter.study.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author chengyin
 * @since 2018-07-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;
    /**
     * 登录账号
     */
    @TableField("user_name")
    @NotBlank(message = "登录账号不能为空")
    @Length(min = 6,max = 15,message = "登录账号长度需要在2-15个字之间")
    private String userName;
    /**
     * 用户昵称
     */
    @TableField("nick_name")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
    private String userType;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * APP会话token
     */
    private String token;
    /**
     * 客户端唯一标识
     */
    private String uuid;
    /**
     * 个性签名
     */
    @TableField("personal_sign")
    private String personalSign;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 最后登陆时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("login_date")
    private Date loginDate;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
