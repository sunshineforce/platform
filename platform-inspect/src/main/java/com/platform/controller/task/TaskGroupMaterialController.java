package com.platform.controller.task;

import com.platform.entity.material.MaterialEntity;
import com.platform.entity.task.TaskGroupMaterialEntity;
import com.platform.service.material.MaterialService;
import com.platform.service.task.TaskGroupMaterialService;
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
 * 任务组物料信息表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 10:58:54
 */
@Controller
@RequestMapping("taskgroupmaterial")
public class TaskGroupMaterialController {
    @Autowired
    private TaskGroupMaterialService taskGroupMaterialService;

    @Autowired
    private MaterialService materialService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("taskgroupmaterial:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TaskGroupMaterialEntity> taskGroupMaterialList = taskGroupMaterialService.queryTaskGroupMaterialList(query);
        int total = taskGroupMaterialService.queryTaskGroupMaterialTotal(query);

        PageUtils pageUtil = new PageUtils(taskGroupMaterialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("taskgroupmaterial:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TaskGroupMaterialEntity taskGroupMaterial = taskGroupMaterialService.queryObject(id);

        return R.ok().put("taskGroupMaterial", taskGroupMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody TaskGroupMaterialEntity taskGroupMaterial) {
        taskGroupMaterialService.save(taskGroupMaterial);
        return R.ok();
    }

    /**
     * 关联某一条件下
     * @return
     */
    @RequestMapping("/saveAll")
    @ResponseBody
    public R saveAll(@RequestParam Map<String, Object> params) {
        List<MaterialEntity> list = materialService.queryList(params);
        Integer taskGroupId = params.get("taskGroupId") == null ? null : Integer.parseInt(String.valueOf(params.get("taskGroupId")));
        if (taskGroupId != null && list != null && list.size() > 0) {
            for (MaterialEntity materialEntity : list) {
                TaskGroupMaterialEntity taskGroupMaterial = new TaskGroupMaterialEntity();
                taskGroupMaterial.setMaterialId(materialEntity.getId());
                taskGroupMaterial.setTaskGroupId(taskGroupId);
                taskGroupMaterialService.save(taskGroupMaterial);
            }
        }
        return R.ok();
    }


    /**
     * 移除
     * @param taskGroupMaterial
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody TaskGroupMaterialEntity taskGroupMaterial) {
        taskGroupMaterialService.remove(taskGroupMaterial);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("taskgroupmaterial:update")
    @ResponseBody
    public R update(@RequestBody TaskGroupMaterialEntity taskGroupMaterial) {
        taskGroupMaterialService.update(taskGroupMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("taskgroupmaterial:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        taskGroupMaterialService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TaskGroupMaterialEntity> list = taskGroupMaterialService.queryList(params);

        return R.ok().put("list", list);
    }
}
