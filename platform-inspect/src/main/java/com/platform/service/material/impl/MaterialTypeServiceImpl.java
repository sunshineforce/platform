package com.platform.service.material.impl;

import com.platform.dao.material.MaterialTypeDao;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.entity.vo.MaterialTypeVo;
import com.platform.service.material.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 物品类型表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
@Service("materialTypeService")
public class MaterialTypeServiceImpl implements MaterialTypeService {
    @Autowired
    private MaterialTypeDao materialTypeDao;

    @Override
    public MaterialTypeEntity queryObject(Integer id) {
        return materialTypeDao.queryObject(id);
    }

    @Override
    public List<MaterialTypeEntity> queryList(Map<String, Object> map) {
        return materialTypeDao.queryList(map);
    }

    @Override
    public List<MaterialTypeVo> loadAllMaterialType() {
        return materialTypeDao.queryAllMaterialType();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return materialTypeDao.queryTotal(map);
    }

    @Override
    public int save(MaterialTypeEntity materialType) {
        return materialTypeDao.save(materialType);
    }

    @Override
    public int update(MaterialTypeEntity materialType) {
        return materialTypeDao.update(materialType);
    }

    @Override
    public int delete(Integer id) {
        return materialTypeDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return materialTypeDao.deleteBatch(ids);
    }
}
