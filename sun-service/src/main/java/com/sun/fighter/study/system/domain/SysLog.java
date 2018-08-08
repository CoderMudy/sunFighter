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
 * 系统日志记录表
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(value = "sys_log_id",type = IdType.AUTO)
    private Long sysLogId;
    /**
     * 模块名称
     */
    @TableField("module_name")
    private String moduleName;
    /**
     * 全类名或者操作url
     */
    @TableField("class_name")
    private String className;
    /**
     * ip
     */
    private String ip;
    /**
     * 信息
     */
    private String msg;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 用户登录名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 日志来源类型
     */
    @TableField("log_type")
    private String logType;
    /**
     * 使用时长 毫米单位
     */
    private Long times;
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
    /**
     * 备注信息
     */
    private String remark;

    @Override
    protected Serializable pkVal() {
        return this.sysLogId;
    }

}
