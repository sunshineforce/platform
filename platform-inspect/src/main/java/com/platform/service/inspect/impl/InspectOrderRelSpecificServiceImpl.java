package com.platform.service.inspect.impl;

import com.platform.dao.inspect.InspectOrderRelSpecificDao;
import com.platform.entity.inspect.InspectOrderRelSpecificEntity;
import com.platform.service.inspect.InspectOrderRelSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 工单管理检查项表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Service("inspectOrderRelSpecificService")
public class InspectOrderRelSpecificServiceImpl implements InspectOrderRelSpecificService {
    @Autowired
    private InspectOrderRelSpecificDao inspectOrderRelSpecificDao;

    @Override
    public InspectOrderRelSpecificEntity queryObject(Integer id) {
        return inspectOrderRelSpecificDao.queryObject(id);
    }

    @Override
    public List<InspectOrderRelSpecificEntity> queryList(Map<String, Object> map) {
        return inspectOrderRelSpecificDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return inspectOrderRelSpecificDao.queryTotal(map);
    }

    @Override
    public int save(InspectOrderRelSpecificEntity inspectOrderRelSpecific) {
        return inspectOrderRelSpecificDao.save(inspectOrderRelSpecific);
    }

    @Override
    public int update(InspectOrderRelSpecificEntity inspectOrderRelSpecific) {
        return inspectOrderRelSpecificDao.update(inspectOrderRelSpecific);
    }

    @Override
    public int delete(Integer id) {
        return inspectOrderRelSpecificDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return inspectOrderRelSpecificDao.deleteBatch(ids);
    }
}
