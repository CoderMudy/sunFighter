package com.sun.fighter.study.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.fighter.study.system.dao.SysLogDao;
import com.sun.fighter.study.system.domain.SysLog;
import com.sun.fighter.study.system.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志记录表 服务实现类
 * </p>
 *
 * @author chengyin
 * @since 2018-08-05
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

}
