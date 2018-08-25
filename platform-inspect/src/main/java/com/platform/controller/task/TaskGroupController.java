package com.platform.controller.task;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.task.TaskGroupEntity;
import com.platform.service.task.TaskGroupService;
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
 * 任务组表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Controller
@RequestMapping("taskgroup")
public class TaskGroupController extends AbstractController {
    @Autowired
    private TaskGroupService taskGroupService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        //查询列表数据
        Query query = new Query(params);

        List<TaskGroupEntity> taskGroupList = taskGroupService.queryList(query);
        int total = taskGroupService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(taskGroupList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("taskgroup:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TaskGroupEntity taskGroup = taskGroupService.queryObject(id);

        return R.ok().put("taskGroup", taskGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("taskgroup:save")
    @ResponseBody
    public R save(@RequestBody TaskGroupEntity taskGroup) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
          taskGroup.setEnterpriseId(user.getEnterpriseId());
        }
        Date time = new Date();
        taskGroup.setCreateTime(time);
        taskGroup.setUpdateTime(time);
        taskGroupService.save(taskGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("taskgroup:update")
    @ResponseBody
    public R update(@RequestBody TaskGroupEntity taskGroup) {
        Date time = new Date();
        taskGroup.setUpdateTime(time);
        taskGroupService.update(taskGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("taskgroup:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        taskGroupService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        List<TaskGroupEntity> list = taskGroupService.queryList(params);

        return R.ok().put("list", list);
    }
}
