package com.platform.service.task.impl;

import com.platform.dao.task.IntegratedTaskDao;
import com.platform.entity.task.IntegratedTaskEntity;
import com.platform.service.task.IntegratedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 综合任务Service实现类
 *
 * @author admin
 *  
 * @date 2018-09-01 10:28:46
 */
@Service("integratedTaskService")
public class IntegratedTaskServiceImpl implements IntegratedTaskService {
    @Autowired
    private IntegratedTaskDao integratedTaskDao;

    @Override
    public IntegratedTaskEntity queryObject(Integer id) {
        return integratedTaskDao.queryObject(id);
    }

    @Override
    public List<IntegratedTaskEntity> queryList(Map<String, Object> map) {
        return integratedTaskDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return integratedTaskDao.queryTotal(map);
    }

    @Override
    public int save(IntegratedTaskEntity integratedTask) {
        return integratedTaskDao.save(integratedTask);
    }

    @Override
    public int update(IntegratedTaskEntity integratedTask) {
        return integratedTaskDao.update(integratedTask);
    }

    @Override
    public int delete(Integer id) {
        return integratedTaskDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return integratedTaskDao.deleteBatch(ids);
    }
}
