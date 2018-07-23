package com.platform.dao.inspect;

import com.platform.dao.BaseDao;
import com.platform.entity.inspect.InspectOrderRelSpecificEntity;

import java.util.List;

/**
 * 工单管理检查项表Dao
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
public interface InspectOrderRelSpecificDao extends BaseDao<InspectOrderRelSpecificEntity> {

    /**
     * 查询工单检查项
     * @param orderId
     * @return
     */
    List<InspectOrderRelSpecificEntity> selctOrderSpecific(Integer orderId);

}
