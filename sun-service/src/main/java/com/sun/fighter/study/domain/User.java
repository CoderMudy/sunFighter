package com.sun.fighter.study.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.mapper.SqlCondition;
import com.sun.fighter.study.common.SuperEntity;
import com.sun.fighter.study.enums.AgeEnum;
import com.sun.fighter.study.enums.PhoneEnum;
import lombok.*;

import java.util.Date;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends SuperEntity<User> {

    /**
     * 名称 , condition 属性设置注入
     * 等效于 SQL 为：WHERE name LIKE CONCAT('%',s值,'%')
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * update 时候注入年龄 + 1
     * 等效于 SQL 为： update user set age=age+1
     */
    @TableField(update = "%s+1")
    private AgeEnum age;
    /**
     * 这里故意演示注解可无
     */
    @TableField("test_type")
    @TableLogic
    private Integer testType;

    /**
     * 注入更新内容【可无】
     */
    @TableField(update = "now()")
    private Date testDate;

    private Long role;
    private PhoneEnum phone;

    @Builder
    public User(Long id, Long tenantId, String name, AgeEnum age, Integer testType, Date testDate, Long role, PhoneEnum phone) {
        super(id, tenantId);
        this.name = name;
        this.age = age;
        this.testType = testType;
        this.testDate = testDate;
        this.role = role;
        this.phone = phone;
    }
}
