package com.platform.service.task.impl;

import com.platform.dao.task.TaskGroupDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.entity.task.vo.TaskGroupVo;
import com.platform.entity.task.vo.TaskVo;
import com.platform.service.SysRegionService;
import com.platform.service.task.TaskGroupService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
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
    private SysRegionService regionService;

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
        List<TaskVo> taskGroupList = taskGroupDao.selectTaskGroupSimple(map);
        for (TaskVo taskVo : taskGroupList) {
            taskVo.setLocation(getRegionName(taskVo.getRegionId()).concat(taskVo.getEnterpriseName()));
            taskVo.setTaskStatus(TaskStatusEnum.getDesc(taskVo.getStatus()));
            taskVo.setProgressRate(calcProgressRate(taskVo.getId(),Integer.valueOf(status)));
        }
        int total = taskGroupDao.selectTaskGroupSimpleTotal(query);

        return new PageUtils(taskGroupList, total, query.getLimit(), query.getPage());
    }

    /**
     * 计算任务完成进度
     * @return
     */
    private String calcProgressRate(Long taskId,Integer type){
        String progressRate = "0/0";
        if (taskId == null) {
            return progressRate;
        }

        return progressRate;
    }

    /**
     * 获取某个区域的全名，自动拼接上上级区域名称
     * @return
     */
    public String getRegionName(Integer regionId) {
        String regionName = "";
        if (regionId == null) {
            return regionName;
        }
        SysRegionEntity region = regionService.queryObject(regionId);

        if(region != null) {
            if (region.getParentId() != 0) {
                regionName =  getRegionName(region.getParentId()) + region.getName();  //  递归调用方法getRegionString(Long regionId)，停止条件设为regionId==null为真
            }
        }
        regionName = regionName.replace("市辖区","");
        return regionName;
    }

}
