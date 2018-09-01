package com.platform.controller.task;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.task.TaskRelEntity;
import com.platform.entity.task.vo.TaskRelVo;
import com.platform.service.task.TaskDetailService;
import com.platform.service.task.TaskRelService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author admin
 *  
 * @date 2018-09-01 10:28:46
 */
@Controller
@RequestMapping("taskrel")
public class TaskRelController extends AbstractController {
    @Autowired
    private TaskRelService taskRelService;

    @Autowired
    private TaskDetailService taskDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        ///如果是企业用户登录，查询该企业下的用户
        if (null != user && null != user.getEnterpriseId()){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        //查询列表数据
        Query query = new Query(params);

        List<TaskRelVo> taskRelList = taskDetailService.statIntegratedTask(query);
        int total = taskDetailService.queryTaskDetailToatal(query);

        PageUtils pageUtil = new PageUtils(taskRelList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TaskRelEntity taskRel = taskRelService.queryObject(id);

        return R.ok().put("taskRel", taskRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody TaskRelEntity taskRel) {
        taskRelService.save(taskRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody TaskRelEntity taskRel) {
        taskRelService.update(taskRel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        taskRelService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TaskRelEntity> list = taskRelService.queryList(params);

        return R.ok().put("list", list);
    }
}
