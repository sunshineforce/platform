package com.platform.service.inspect.impl;

import com.platform.dao.inspect.InspectOrderDao;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.service.inspect.IInspectOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 安全巡检异常工单表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Service("inspectOrderService")
public class InspectOrderServiceImpl implements IInspectOrderService {
    @Autowired
    private InspectOrderDao inspectOrderDao;

    @Override
    public InspectOrderEntity queryObject(Integer id) {
        return inspectOrderDao.queryObject(id);
    }

    @Override
    public List<InspectOrderEntity> queryList(Map<String, Object> map) {
        return inspectOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return inspectOrderDao.queryTotal(map);
    }

    @Override
    public int save(InspectOrderEntity inspectOrder) {
        return inspectOrderDao.save(inspectOrder);
    }

    @Override
    public int update(InspectOrderEntity inspectOrder) {
        return inspectOrderDao.update(inspectOrder);
    }

    @Override
    public int delete(Integer id) {
        return inspectOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return inspectOrderDao.deleteBatch(ids);
    }
}
