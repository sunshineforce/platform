package com.platform.service.task.impl;

import com.platform.dao.task.TaskGroupDao;
import com.platform.entity.SysUserEntity;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.MaterialDetailsVo;
import com.platform.entity.task.vo.TaskGroupVo;
import com.platform.entity.task.vo.TaskStatisticsVo;
import com.platform.entity.task.vo.TaskVo;
import com.platform.service.SysUserService;
import com.platform.service.common.CommonService;
import com.platform.service.task.TaskGroupService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.enums.MaterialStatusEnum;
import com.platform.utils.enums.TaskStatusEnum;
import com.platform.vo.SelectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 任务组表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Service("taskGroupService")
public class TaskGroupServiceImpl implements TaskGroupService {

    @Autowired
    private TaskGroupDao taskGroupDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysUserService userService;

    @Override
    public TaskGroupEntity queryObject(Integer id) {
        return taskGroupDao.queryObject(id);
    }

    @Override
    public List<TaskGroupEntity> queryList(Map<String, Object> map) {
        return taskGroupDao.queryList(map);
    }

    @Override
    public List<SelectVo> queryAllTaskGroup() {
        return taskGroupDao.queryAllTaskGroup();
    }

    @Override
    public List<SelectVo> queryAllTaskGroupMembers() {
        return taskGroupDao.queryAllTaskGroupMembers();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return taskGroupDao.queryTotal(map);
    }

    @Override
    public int save(TaskGroupEntity taskGroup) {
        return taskGroupDao.save(taskGroup);
    }

    @Override
    public int update(TaskGroupEntity taskGroup) {
        return taskGroupDao.update(taskGroup);
    }

    @Override
    public int delete(Integer id) {
        return taskGroupDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return taskGroupDao.deleteBatch(ids);
    }

    @Override
    public PageUtils queryAllTaskGroupForApp() {
        Query query = new Query(new HashMap<String,Object>());
        List<SelectVo> list = queryAllTaskGroup();
        List<SelectVo> taskList;
        List<TaskGroupVo> taskGroupList = new ArrayList<TaskGroupVo>();
        for (SelectVo selectVo : list) {
            TaskGroupVo taskGroupVo = new TaskGroupVo();
            BeanUtils.copyProperties(selectVo,taskGroupVo);

            taskList = taskGroupDao.selectTaskByTaskGroupId(taskGroupVo.getId());
            taskGroupVo.setTaskList(taskList);

            taskGroupList.add(taskGroupVo);
        }
        int total = taskGroupDao.queryTotal();
        return new PageUtils(taskGroupList, total, query.getLimit(), query.getPage());
    }

    /************************ APP 查询 *****************************/
    @Override
    public PageUtils queryTaskGroup(Map<String, Object> map) {
        Query query = new Query(map);
        String status = String.valueOf(map.get("status"));
        List<TaskVo> taskGroupList = taskGroupDao.selectTaskGroupSimple(query);
        Map<String, Object> processRateQueryMap = new HashMap<String, Object>();
        processRateQueryMap.put("status",status);

        SysUserEntity sysUser;
        for (TaskVo taskVo : taskGroupList) {
            taskVo.setLocation(commonService.getRegionName(taskVo.getRegionId()).concat(taskVo.getEnterpriseName()));
            taskVo.setTaskStatus(TaskStatusEnum.getDesc(taskVo.getStatus()));
            processRateQueryMap.put("taskGroupId",taskVo.getTaskGroupId());
            processRateQueryMap.put("taskId",taskVo.getTaskId());
            taskVo.setProgressRate(calcProgressRate(processRateQueryMap));
            sysUser = userService.queryObject(taskVo.getCheckUserId());
            taskVo.setCheckUser(sysUser.getUsername());
        }
        Integer total = taskGroupDao.selectTaskGroupSimpleTotal(query);

        return new PageUtils(taskGroupList, total, query.getLimit(), query.getPage());
    }

    /**
     * 计算任务完成进度
     * @return
     */
    private String calcProgressRate(Map<String, Object> map){
        Integer total=0;
        Integer finished=0;
        String progressRate = "0/0";
        if (map == null) {
            return progressRate;
        }

        TaskStatisticsVo taskStatistics = taskGroupDao.selectTaskGroupProcessRate(map);
        if (taskStatistics != null) {
            total = taskStatistics.getTotal();
            finished = taskStatistics.getFinish();
        }
        progressRate = (total + "/" + finished);
        return progressRate;
    }

    @Override
    public PageUtils queryMaterialTypeByTaskId(Map<String, Object> map) {
        Query query = new Query(map);
        List<TaskStatisticsVo> list = taskGroupDao.selectTaskGroupMaterialType(query);
        Integer total = taskGroupDao.selectTaskGroupMaterialTypeTotal(query);
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    @Override
    public PageUtils queryRegionByTaskId(Map<String, Object> map) {
        Query query = new Query(map);

        List<TaskStatisticsVo> list = taskGroupDao.selectTaskGroupRegion(query);
        Integer total = taskGroupDao.selectTaskGroupRegionTotal(query);
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    @Override
    public PageUtils queryTaskGroupMaterialDetails(Map<String, Object> map) {
        Query query = new Query(map);
        List<MaterialDetailsVo> list = taskGroupDao.selectMaterialDetails(query);
        for (MaterialDetailsVo materialDetails : list) {
            materialDetails.setLocation(commonService.getRegionName(materialDetails.getRegionId()));
            materialDetails.setMaterialStatus(MaterialStatusEnum.getDesc(materialDetails.getStatus()));
        }
        Integer total = taskGroupDao.selectMaterialDetailsTotal(query);

        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    @Override
    public PageUtils queryTaskGroupRegionDetails(Map<String, Object> map) {
        Query query = new Query(map);
        List<MaterialDetailsVo> list = taskGroupDao.selectTaskGroupRegionDetails(query);
        for (MaterialDetailsVo materialDetails : list) {
            materialDetails.setLocation(commonService.getRegionName(materialDetails.getRegionId()));
            materialDetails.setMaterialStatus(MaterialStatusEnum.getDesc(materialDetails.getStatus()));
        }
        Integer total = taskGroupDao.selectTaskGroupRegionDetailsTotal(query);

        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }
}
