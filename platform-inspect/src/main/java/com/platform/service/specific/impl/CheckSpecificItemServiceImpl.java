package com.platform.service.specific.impl;

import com.platform.dao.specific.CheckSpecificItemDao;
import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.service.specific.CheckSpecificItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 规范项条目表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Service("checkSpecificItemService")
public class CheckSpecificItemServiceImpl implements CheckSpecificItemService {
    @Autowired
    private CheckSpecificItemDao checkSpecificItemDao;

    @Override
    public CheckSpecificItemEntity queryObject(Integer id) {
        return checkSpecificItemDao.queryObject(id);
    }

    @Override
    public List<CheckSpecificItemEntity> queryList(Map<String, Object> map) {
        return checkSpecificItemDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return checkSpecificItemDao.queryTotal(map);
    }

    @Override
    public int save(CheckSpecificItemEntity checkSpecificItem) {
        return checkSpecificItemDao.save(checkSpecificItem);
    }

    @Override
    public int update(CheckSpecificItemEntity checkSpecificItem) {
        return checkSpecificItemDao.update(checkSpecificItem);
    }

    @Override
    public int delete(Integer id) {
        return checkSpecificItemDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return checkSpecificItemDao.deleteBatch(ids);
    }

    @Override
    public int deleteBySpecId(Integer specificId) {
        return checkSpecificItemDao.deleteBySpecId(specificId);
    }
}
