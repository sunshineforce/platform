package com.platform.service.material.impl;

import com.platform.dao.material.MaterialDao;
import com.platform.dao.material.MaterialTypeDao;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 物品表Service实现类
 * @author admin
 * @date 2018-07-23 11:11:45
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Override
    public MaterialEntity queryObject(Integer id) {
        MaterialEntity materialEntity = materialDao.queryObject(id);
        if (null != materialEntity){
            MaterialTypeEntity materialTypeEntity = materialTypeDao.queryObject(materialEntity.getMaterialTypeId());
            if (null != materialTypeEntity){
                materialEntity.setMaterialTypeName(materialTypeEntity.getName());
            }
        }
        return materialEntity;
    }

    @Override
    public List<MaterialEntity> queryList(Map<String, Object> map) {
        return materialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return materialDao.queryTotal(map);
    }

    @Override
    public int save(MaterialEntity material) {
        return materialDao.save(material);
    }

    @Override
    public int update(MaterialEntity material) {
        return materialDao.update(material);
    }

    @Override
    public int delete(Integer id) {
        return materialDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return materialDao.deleteBatch(ids);
    }
}
