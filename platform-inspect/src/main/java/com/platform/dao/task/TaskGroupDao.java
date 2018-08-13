package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.MaterialDetailsVo;
import com.platform.entity.task.vo.TaskStatisticsVo;
import com.platform.entity.task.vo.TaskVo;
import com.platform.vo.SelectVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 任务组表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */

@Repository
public interface TaskGroupDao extends BaseDao<TaskGroupEntity> {
    List<SelectVo> queryAllTaskGroup();

    List<SelectVo> queryAllTaskGroupMembers();

    List<TaskVo> selectTaskGroupSimple(Map<String, Object> map);

    TaskStatisticsVo selectTaskGroupProcessRate(Map<String, Object> map);

    Integer selectTaskGroupSimpleTotal(Map<String, Object> map);

    List<TaskStatisticsVo> selectTaskGroupMaterialType(Map<String, Object> map);

    Integer selectTaskGroupMaterialTypeTotal(Map<String, Object> map);

    List<TaskStatisticsVo> selectTaskGroupRegion(Map<String, Object> map);

    Integer selectTaskGroupRegionTotal(Map<String, Object> map);

    List<MaterialDetailsVo> selectMaterialDetails(Map<String, Object> map);

    Integer selectMaterialDetailsTotal(Map<String, Object> map);

    List<MaterialDetailsVo> selectTaskGroupRegionDetails(Map<String, Object> map);

    Integer selectTaskGroupRegionDetailsTotal(Map<String, Object> map);

    List<SelectVo> selectTaskByTaskGroupId(Integer id);
}
