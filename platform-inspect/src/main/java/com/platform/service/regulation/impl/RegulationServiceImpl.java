package com.platform.service.regulation.impl;

import com.platform.dao.regulation.RegulationDao;
import com.platform.entity.regulation.RegulationEntity;
import com.platform.service.regulation.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 法规管理表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-24 10:28:40
 */
@Service("regulationService")
public class RegulationServiceImpl implements RegulationService {
    @Autowired
    private RegulationDao regulationDao;

    @Override
    public RegulationEntity queryObject(Long id) {
        return regulationDao.queryObject(id);
    }

    @Override
    public List<RegulationEntity> queryList(Map<String, Object> map) {
        return regulationDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return regulationDao.queryTotal(map);
    }

    @Override
    public int save(RegulationEntity regulation) {
        return regulationDao.save(regulation);
    }

    @Override
    public int update(RegulationEntity regulation) {
        return regulationDao.update(regulation);
    }

    @Override
    public int delete(Long id) {
        return regulationDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return regulationDao.deleteBatch(ids);
    }
}
