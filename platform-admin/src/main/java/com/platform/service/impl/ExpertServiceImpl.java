package com.platform.service.impl;

import com.platform.dao.ExpertDao;
import com.platform.entity.ExpertEntity;
import com.platform.service.IExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 专家库表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-20 18:08:44
 */
@Service("tExpertService")
public class ExpertServiceImpl implements IExpertService {
    @Autowired
    private ExpertDao tExpertDao;

    @Override
    public ExpertEntity queryObject(Integer id) {
        return tExpertDao.queryObject(id);
    }

    @Override
    public List<ExpertEntity> queryList(Map<String, Object> map) {
        return tExpertDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tExpertDao.queryTotal(map);
    }

    @Override
    public int save(ExpertEntity tExpert) {
        return tExpertDao.save(tExpert);
    }

    @Override
    public int update(ExpertEntity tExpert) {
        return tExpertDao.update(tExpert);
    }

    @Override
    public int delete(Integer id) {
        return tExpertDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return tExpertDao.deleteBatch(ids);
    }
}
