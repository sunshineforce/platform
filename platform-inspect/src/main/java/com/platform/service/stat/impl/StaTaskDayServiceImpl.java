package com.platform.service.stat.impl;

import com.platform.dao.stat.StaTaskDayDao;
import com.platform.entity.stat.StaTaskDayEntity;
import com.platform.service.stat.StaTaskDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 按天统计任务执行情况Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Service("staTaskDayService")
public class StaTaskDayServiceImpl implements StaTaskDayService {
    @Autowired
    private StaTaskDayDao staTaskDayDao;

    @Override
    public StaTaskDayEntity queryObject(Integer id) {
        return staTaskDayDao.queryObject(id);
    }

    @Override
    public List<StaTaskDayEntity> queryList(Map<String, Object> map) {
        return staTaskDayDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return staTaskDayDao.queryTotal(map);
    }

    @Override
    public int save(StaTaskDayEntity staTaskDay) {
        return staTaskDayDao.save(staTaskDay);
    }

    @Override
    public int update(StaTaskDayEntity staTaskDay) {
        return staTaskDayDao.update(staTaskDay);
    }

    @Override
    public int delete(Integer id) {
        return staTaskDayDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return staTaskDayDao.deleteBatch(ids);
    }
}
