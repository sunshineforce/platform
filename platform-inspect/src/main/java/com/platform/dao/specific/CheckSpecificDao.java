package com.platform.dao.specific;

import com.platform.dao.BaseDao;
import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.vo.CheckSpecificVo;

import java.util.List;

/**
 * 检查规范表Dao
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
public interface CheckSpecificDao extends BaseDao<CheckSpecificEntity> {
    List<CheckSpecificVo> queryAllCheckSpecific();
}
