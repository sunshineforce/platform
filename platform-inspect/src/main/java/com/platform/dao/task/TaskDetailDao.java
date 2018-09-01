package com.platform.dao.task;

import com.platform.dao.BaseDao;
import com.platform.entity.dto.StatDto;
import com.platform.entity.task.TaskDetailEntity;
import com.platform.entity.task.vo.TaskDetailStatVo;
import com.platform.entity.task.vo.TaskDetailVo;
import com.platform.entity.task.vo.TaskRelVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 任务详情表Dao
 *
 * @author admin
 *  
 * @date 2018-08-21 16:48:06
 */
@Repository
public interface TaskDetailDao extends BaseDao<TaskDetailEntity> {


    /**
     * 通过taskid 删除
     * @param taskId
     * @return
     */
    int  deleteByTaskId(Integer taskId);


    /**
     * 统计任务
     * @param map
     * @return
     */
    List<StatDto> statTask(Map<String, Object> map);

    /**
     * 统计大任务
     * @param map
     * @return
     */
    List<TaskRelVo> statIntegratedTask(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int statIntegratedTaskTotal(Map<String, Object> map);

    /**
     * 统计具体任务执行情况
     * @param map
     * @return
     */
    List<TaskDetailStatVo> statTaskDetail(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int statTaskDetailTotal(Map<String, Object> map);


    /**
     * 任务转化为开始状态
     * @return
     */
    int  startTask();

    /**
     * 任务过期扫描
     * @return
     */
    int  taskTimeOut();

    /**
     * 同步任务状态
     * @return
     */
    int syncTask();

    /**
     * 查询任务详情信息
     * @param map
     * @return
     */
    List<TaskDetailVo> selectTaskDetailList(Map<String, Object> map);

    /**
     * 总条数
     * @param map
     * @return
     */
    int selectTaskDetailToatal(Map<String, Object> map);
}
