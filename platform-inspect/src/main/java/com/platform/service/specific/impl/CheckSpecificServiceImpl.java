package com.platform.service.specific.impl;

import com.platform.dao.specific.CheckSpecificDao;
import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.vo.CheckSpecificVo;
import com.platform.service.specific.CheckSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 检查规范表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Service("checkSpecificService")
public class CheckSpecificServiceImpl implements CheckSpecificService {
    @Autowired
    private CheckSpecificDao checkSpecificDao;

    @Override
    public CheckSpecificEntity queryObject(Integer id) {
        return checkSpecificDao.queryObject(id);
    }

    @Override
    public List<CheckSpecificEntity> queryList(Map<String, Object> map) {
        return checkSpecificDao.queryList(map);
    }

    @Override
    public List<CheckSpecificVo> loadAllCheckSpecific() {
        return checkSpecificDao.queryAllCheckSpecific();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return checkSpecificDao.queryTotal(map);
    }

    @Override
    public int save(CheckSpecificEntity checkSpecific) {
        return checkSpecificDao.save(checkSpecific);
    }

    @Override
    public int update(CheckSpecificEntity checkSpecific) {
        return checkSpecificDao.update(checkSpecific);
    }

    @Override
    public int delete(Integer id) {
        return checkSpecificDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return checkSpecificDao.deleteBatch(ids);
    }
}
