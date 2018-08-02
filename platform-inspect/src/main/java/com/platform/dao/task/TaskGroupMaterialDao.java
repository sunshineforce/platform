package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.task.TaskGroupMaterialEntity;
import com.platform.entity.task.vo.TaskStatisticsVo;

import java.util.List;
import java.util.Map;

/**
 * 任务组物料信息表Dao
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskGroupMaterialDao extends BaseDao<TaskGroupMaterialEntity> {

    /**
     * 分页查询组物料信息
     * @param map
     * @return
     */
    List<TaskGroupMaterialEntity> selectTaskGroupMaterialList(Map<String, Object> map);

    /**
     * 条数
     * @param map
     * @return
     */
    int selectTaskGroupMaterialTotal(Map<String, Object> map);

    /**
     * 查询任务下物品类型的完成数量
     * @param taskId
     * @return
     */
    List<TaskStatisticsVo> selectMaterialStatusCount(Long taskId);

    /**
     * 移除
     * @param taskGroupMaterialEntity
     * @return
     */
    int remove(TaskGroupMaterialEntity taskGroupMaterialEntity);
}
