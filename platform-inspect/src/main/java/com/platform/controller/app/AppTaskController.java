package com.platform.controller.app;

import com.alibaba.druid.util.StringUtils;
import com.platform.entity.task.TaskEntity;
import com.platform.service.task.TaskGroupService;
import com.platform.service.task.TaskService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:32
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class AppTaskController {

    @Resource
    private TaskService taskService;

    @Autowired
    private TaskGroupService taskGroupService;

    @RequestMapping(value = "/task/list",method = RequestMethod.POST)
    public R taskList(@RequestBody HashMap<String,Object> params){
        //查询列表数据
        Query query = new Query(params);
        if (params == null) {
            return R.paramsIllegal();
        }
        if (StringUtils.isEmpty(String.valueOf(params.get("status")))) {
            return R.paramsIllegal();
        }

        List<TaskEntity> taskList = taskService.queryList(params);
        int total = taskService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(taskList, total, query.getLimit(), query.getPage());

        return R.succeed().put("page", pageUtil);
    }

    @RequestMapping(value = "/task/create",method = RequestMethod.POST)
    public R createTask(@RequestBody TaskEntity taskEntity){
        if (taskEntity == null) {
            return R.succeed();
        }
        taskService.save(taskEntity);
        return R.succeed();
    }

    /**
     * 加载所有的任务组
     * @return
     */
    @RequestMapping("/task/group/list")
    public R queryAllTaskGroup(){
        return R.succeed().put("list",taskGroupService.queryAllTaskGroup());
    }

    /**
     * 加载任务组人员
     * @return
     */
    @RequestMapping("/task/group/members")
    public R queryTaskGroupMembers(){
        return R.succeed().put("list",taskGroupService.queryAllTaskGroupMembers());
    }
}