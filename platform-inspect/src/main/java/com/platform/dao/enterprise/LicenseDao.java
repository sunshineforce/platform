package com.platform.dao.enterprise;

import com.platform.dao.BaseDao;
import com.platform.entity.enterprise.LicenseEntity;
import com.platform.entity.enterprise.LicenseVo;

import java.util.List;
import java.util.Map;

/**
 * 证照表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
public interface LicenseDao extends BaseDao<LicenseEntity> {
    List<LicenseVo> selectList(Map<String, Object> map);
}
