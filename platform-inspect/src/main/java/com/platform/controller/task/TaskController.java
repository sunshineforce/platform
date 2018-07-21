package com.platform.controller.task;

import com.platform.constants.CommonConstant;
import com.platform.entity.task.TaskEntity;
import com.platform.service.task.TaskService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Controller
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("task:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        params.put("dataStatus",CommonConstant.USEABLE_STATUS);
        //查询列表数据
        Query query = new Query(params);

        List<TaskEntity> taskList = taskService.queryTaskList(query);
        int total = taskService.queryTaskTotal(query);

        PageUtils pageUtil = new PageUtils(taskList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("task:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TaskEntity task = taskService.queryObject(id);

        return R.ok().put("task", task);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("task:save")
    @ResponseBody
    public R save(@RequestBody TaskEntity task) {
        Date time = new Date();
        task.setUpdateTime(time);
        task.setCreateTime(time);
        task.setDataStatus(CommonConstant.USEABLE_STATUS);
        taskService.save(task);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("task:update")
    @ResponseBody
    public R update(@RequestBody TaskEntity task) {
        Date time = new Date();
        task.setUpdateTime(time);
        task.setDataStatus(CommonConstant.USEABLE_STATUS);
        taskService.update(task);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("task:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        //taskService.deleteBatch(ids);
        if (ids.length > 0 ){
            TaskEntity task = null;
            for (Integer id : ids) {
                task = new TaskEntity();
                task.setId(id);
                Date time = new Date();
                task.setUpdateTime(time);
                task.setDataStatus(CommonConstant.UN_USEABLE_STATUS);
                taskService.update(task);
            }
        }

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TaskEntity> list = taskService.queryList(params);

        return R.ok().put("list", list);
    }
}
