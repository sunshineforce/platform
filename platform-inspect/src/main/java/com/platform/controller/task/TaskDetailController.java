package com.platform.controller.task;

import com.platform.entity.task.TaskDetailEntity;
import com.platform.service.task.TaskDetailService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 任务详情表Controller
 *
 * @author admin
 *  
 * @date 2018-08-21 16:48:06
 */
@Controller
@RequestMapping("taskdetail")
public class TaskDetailController {
    @Autowired
    private TaskDetailService taskDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("taskdetail:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TaskDetailEntity> taskDetailList = taskDetailService.queryList(query);
        int total = taskDetailService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(taskDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("taskdetail:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TaskDetailEntity taskDetail = taskDetailService.queryObject(id);

        return R.ok().put("taskDetail", taskDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("taskdetail:save")
    @ResponseBody
    public R save(@RequestBody TaskDetailEntity taskDetail) {
        taskDetailService.save(taskDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("taskdetail:update")
    @ResponseBody
    public R update(@RequestBody TaskDetailEntity taskDetail) {
        taskDetailService.update(taskDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("taskdetail:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        taskDetailService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TaskDetailEntity> list = taskDetailService.queryList(params);

        return R.ok().put("list", list);
    }
}
