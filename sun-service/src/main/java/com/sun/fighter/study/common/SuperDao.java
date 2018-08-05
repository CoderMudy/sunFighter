package com.sun.fighter.study.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述  mapper 父类，注意这个类不要让 mp 扫描到！！
 */
public interface SuperDao<T> extends BaseMapper<T> {

}
