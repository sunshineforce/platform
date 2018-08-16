package com.platform.dao.material;

import com.platform.dao.BaseDao;
import com.platform.entity.material.MaterialEntity;
import org.springframework.stereotype.Repository;

/**
 * 物品表Dao
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */

@Repository
public interface MaterialDao extends BaseDao<MaterialEntity> {
    MaterialEntity queryMaterialByQrCode(String qrCode);
}
