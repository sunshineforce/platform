package com.platform.controller.task;

import com.platform.constants.CommonConstant;
import com.platform.entity.task.IntegratedTaskEntity;
import com.platform.service.task.IntegratedTaskService;
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
 * 综合任务Controller
 *
 * @author admin
 *  
 * @date 2018-09-01 10:28:46
 */
@Controller
@RequestMapping("integratedtask")
public class IntegratedTaskController {
    @Autowired
    private IntegratedTaskService integratedTaskService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("integratedtask:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<IntegratedTaskEntity> integratedTaskList = integratedTaskService.queryList(query);
        int total = integratedTaskService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(integratedTaskList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("integratedtask:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        IntegratedTaskEntity integratedTask = integratedTaskService.queryObject(id);

        return R.ok().put("integratedTask", integratedTask);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("integratedtask:save")
    @ResponseBody
    public R save(@RequestBody IntegratedTaskEntity integratedTask) {
        integratedTask.setDataStatus(CommonConstant.USEABLE_STATUS);
        integratedTask.setPublishTime(new Date());
        integratedTaskService.save(integratedTask);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("integratedtask:update")
    @ResponseBody
    public R update(@RequestBody IntegratedTaskEntity integratedTask) {
        integratedTaskService.update(integratedTask);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("integratedtask:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        integratedTaskService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<IntegratedTaskEntity> list = integratedTaskService.queryList(params);

        return R.ok().put("list", list);
    }
}
