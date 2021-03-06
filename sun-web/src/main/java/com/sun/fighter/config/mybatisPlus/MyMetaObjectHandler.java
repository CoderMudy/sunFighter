package com.sun.fighter.config.mybatisPlus;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.sun.fighter.SunFighterApplication;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @创建人 chengyin
 * @创建时间 2018/6/19
 * @描述  注入公共字段自动填充,任选注入方式即可
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(SunFighterApplication.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("新增的时候干点不可描述的事情");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
    }
}
