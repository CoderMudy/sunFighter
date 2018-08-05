package com.sun.fighter.study.common;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.*;

import java.io.Serializable;

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
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId("test_id")
    private Long id;
    private Long tenantId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
