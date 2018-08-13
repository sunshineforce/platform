package com.platform.dao.specific;

import com.platform.dao.BaseDao;
import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.vo.SelectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 规范项条目表Dao
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */

@Repository
public interface CheckSpecificItemDao extends BaseDao<CheckSpecificItemEntity> {

    /**
     * 通过主表id删除
     * @param specificId
     * @return
     */
    int deleteBySpecId(Integer specificId);

    List<SelectVo> queryListSimple(Long specificId);
}
