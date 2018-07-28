package com.platform.dao;


import com.platform.entity.SysRegionEntity;
import com.platform.vo.TreeVo;

import java.util.List;

/**
 * @author admin
 *
 * @date 2017-11-04 11:19:31
 */
public interface SysRegionDao extends BaseDao<SysRegionEntity> {
    List<TreeVo> queryAllRegion();
}
