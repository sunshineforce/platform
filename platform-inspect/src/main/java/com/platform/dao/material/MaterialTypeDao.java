package com.platform.dao.material;

import com.platform.dao.BaseDao;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.entity.vo.MaterialTypeVo;

import java.util.List;

/**
 * 物品类型表Dao
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
public interface MaterialTypeDao extends BaseDao<MaterialTypeEntity> {
    List<MaterialTypeVo> queryAllMaterialType();
}
