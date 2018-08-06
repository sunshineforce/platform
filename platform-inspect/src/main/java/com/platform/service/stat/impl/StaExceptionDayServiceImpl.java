package com.platform.service.stat.impl;

import com.platform.dao.stat.StaExceptionDayDao;
import com.platform.entity.stat.StaExceptionDayEntity;
import com.platform.service.stat.StaExceptionDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 异常处理统计Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Service("staExceptionDayService")
public class StaExceptionDayServiceImpl implements StaExceptionDayService {
    @Autowired
    private StaExceptionDayDao staExceptionDayDao;

    @Override
    public StaExceptionDayEntity queryObject(Integer id) {
        return staExceptionDayDao.queryObject(id);
    }

    @Override
    public List<StaExceptionDayEntity> queryList(Map<String, Object> map) {
        return staExceptionDayDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return staExceptionDayDao.queryTotal(map);
    }

    @Override
    public int save(StaExceptionDayEntity staExceptionDay) {
        return staExceptionDayDao.save(staExceptionDay);
    }

    @Override
    public int update(StaExceptionDayEntity staExceptionDay) {
        return staExceptionDayDao.update(staExceptionDay);
    }

    @Override
    public int delete(Integer id) {
        return staExceptionDayDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return staExceptionDayDao.deleteBatch(ids);
    }
}
