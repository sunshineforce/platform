package com.platform.dao.inspect;

import com.platform.dao.BaseDao;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.vo.AnomalyVo;

import java.util.List;
import java.util.Map;

/**
 * 安全巡检异常工单表Dao
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public interface InspectOrderDao extends BaseDao<InspectOrderEntity> {

    List<AnomalyVo> search(Map<String,Object> queryParams);

    Integer searchTotal(Map<String,Object> queryParams);
}
