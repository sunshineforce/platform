package com.platform.service.task;


import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.TaskVo;
import com.platform.utils.PageUtils;
import com.platform.vo.SelectVo;

import java.util.List;
import java.util.Map;

/**
 * 任务组表Service接口
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
public interface TaskGroupService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TaskGroupEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TaskGroupEntity> queryList(Map<String, Object> map);

    /**
     * 获取所有的任务组
     * @return
     */
    List<SelectVo> queryAllTaskGroup();

    /**
     * 查询所有任务组人员
     * @return
     */
    List<SelectVo> queryAllTaskGroupMembers();

    PageUtils queryTaskGroup(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param taskGroup 实体
     * @return 保存条数
     */
    int save(TaskGroupEntity taskGroup);

    /**
     * 根据主键更新实体
     *
     * @param taskGroup 实体
     * @return 更新条数
     */
    int update(TaskGroupEntity taskGroup);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);



    /**********************************************APP 接口部分 **************************************/

    /**
     * 加载所有任务组（下拉列表）
     * @return
     */
    PageUtils queryAllTaskGroupForApp();

    /**
     * 根据任务Id查询物品各类统计数据
     * @return
     */
    PageUtils queryMaterialTypeByTaskId(Map<String, Object> map);

    /**
     * 根据任务Id查询企业统计数据
     * @return
     */
    PageUtils queryRegionByTaskId(Map<String, Object> map);

    PageUtils queryTaskGroupMaterialDetails(Map<String, Object> map);

    PageUtils queryTaskGroupRegionDetails(Map<String, Object> map);
}
