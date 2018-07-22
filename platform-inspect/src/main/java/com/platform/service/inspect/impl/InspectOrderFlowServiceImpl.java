package com.platform.service.inspect.impl;

import com.platform.dao.inspect.InspectOrderFlowDao;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.service.inspect.InspectOrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 巡检工单处理流程Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Service("inspectOrderFlowService")
public class InspectOrderFlowServiceImpl implements InspectOrderFlowService {
    @Autowired
    private InspectOrderFlowDao inspectOrderFlowDao;

    @Override
    public InspectOrderFlowEntity queryObject(Integer id) {
        return inspectOrderFlowDao.queryObject(id);
    }

    @Override
    public List<InspectOrderFlowEntity> queryList(Map<String, Object> map) {
        return inspectOrderFlowDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return inspectOrderFlowDao.queryTotal(map);
    }

    @Override
    public int save(InspectOrderFlowEntity inspectOrderFlow) {
        return inspectOrderFlowDao.save(inspectOrderFlow);
    }

    @Override
    public int update(InspectOrderFlowEntity inspectOrderFlow) {
        return inspectOrderFlowDao.update(inspectOrderFlow);
    }

    @Override
    public int delete(Integer id) {
        return inspectOrderFlowDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return inspectOrderFlowDao.deleteBatch(ids);
    }
}
