package com.sun.fighter.study.system.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 系统日志记录表
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId("sys_log_id")
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


    public Long getSysLogId() {
        return sysLogId;
    }

    public void setSysLogId(Long sysLogId) {
        this.sysLogId = sysLogId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.sysLogId;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        ", sysLogId=" + sysLogId +
        ", moduleName=" + moduleName +
        ", className=" + className +
        ", ip=" + ip +
        ", msg=" + msg +
        ", params=" + params +
        ", userName=" + userName +
        ", logType=" + logType +
        ", times=" + times +
        ", createId=" + createId +
        ", updateId=" + updateId +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", remark=" + remark +
        "}";
    }
}
