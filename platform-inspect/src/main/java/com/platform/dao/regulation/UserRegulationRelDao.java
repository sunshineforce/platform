package com.platform.dao.regulation;

import com.platform.dao.BaseDao;
import com.platform.entity.regulation.UserRegulationRelEntity;
import com.platform.entity.regulation.vo.KnowledgeCollectVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author admin
 *  
 * @date 2018-08-19 17:34:20
 */

@Repository
public interface UserRegulationRelDao extends BaseDao<UserRegulationRelEntity> {

    List<KnowledgeCollectVo> selectKnowledgeCollectList(Map<String,Object> params);

    int selectKnowledgeCollectTotal(Map<String,Object> params);
}
