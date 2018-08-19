package com.platform.dao.inspect;

import com.platform.dao.BaseDao;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.inspect.vo.AnomalyFlowVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 巡检工单处理流程Dao
 * @author admin
 * @date 2018-07-22 21:48:11
 */

@Repository
public interface InspectOrderFlowDao extends BaseDao<InspectOrderFlowEntity> {
    /**
     * 查询异常历史记录
     * @param params
     * @return
     */
    List<AnomalyFlowVo> queryAnomalyList(Map<String,Object> params);
}
