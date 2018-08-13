package com.platform.dao.specific;

import com.platform.dao.BaseDao;
import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.specific.vo.CheckSpecificVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 检查规范表Dao
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Repository
public interface CheckSpecificDao extends BaseDao<CheckSpecificEntity> {
    List<CheckSpecificVo> queryListSimple(Map<String, Object> map);

    Integer queryTotalSimple(Map<String, Object> map);
}
