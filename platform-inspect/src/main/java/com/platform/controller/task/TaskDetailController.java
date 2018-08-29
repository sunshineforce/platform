package com.platform.controller.task;

import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.task.TaskDetailEntity;
import com.platform.entity.task.vo.TaskDetailStatVo;
import com.platform.entity.task.vo.TaskDetailVo;
import com.platform.service.AppUserService;
import com.platform.service.task.TaskDetailService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
public class TaskDetailController extends AbstractController {
    @Autowired
    private TaskDetailService taskDetailService;

    @Autowired
    private AppUserService appUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {

        Map<String, Object> params1 = new HashMap<>();
        SysUserEntity user = getUser();
        ///如果是企业用户登录，查询该企业下的用户
        if (null != user && null != user.getEnterpriseId()){
            params.put("enterpriseId",user.getEnterpriseId());
            params1.put("enterpriseId",user.getEnterpriseId());
        }
        params1.put("identify",0);
        List<AppUserEntity> userList = appUserService.queryList(params1); //查询安全列表


        //查询列表数据
        Query query = new Query(params);
        List<TaskDetailVo> taskDetailList = taskDetailService.queryTaskDetailList(query);
        int total = taskDetailService.queryTaskDetailToatal(query);

        setAppuserNames(taskDetailList,userList);
        PageUtils pageUtil = new PageUtils(taskDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    private void setAppuserNames( List<TaskDetailVo> taskDetailList, List<AppUserEntity> userList){
        if (taskDetailList == null || userList == null) {
            return;
        }
        for (TaskDetailVo taskEntity : taskDetailList) {
            String userIds  =taskEntity.getUserIds();
            if (StringUtils.isNotBlank(userIds)){
                String [] ids = userIds.split(",");
                StringBuffer sb = new StringBuffer("");
                for (String id : ids) {
                    for (AppUserEntity user : userList) {
                        if (user.getId().intValue() == Integer.parseInt(id)){
                            if (sb.length() > 0){
                                sb.append(",").append(user.getRealname());
                            }else {
                                sb.append(user.getRealname());
                            }

                        }
                    }
                }
                taskEntity.setUserNames(sb.toString());
            }

        }
    }

    /**
     * 详情统计
     * @param params
     * @return
     */
    @RequestMapping("/detailStat")
    @ResponseBody
    public R detailStat(@RequestParam Map<String, Object> params) {
        if (params.get("id") != null){
            Long id = Long.parseLong(String.valueOf(params.get("id")));
            TaskDetailEntity taskDetail = taskDetailService.queryObject(id);
            if (taskDetail != null){
                params.put("taskGroupId",taskDetail.getTaskGroupId());
                params.put("taskDetailId",taskDetail.getId());
            }
        }
        //查询列表数据
        Query query = new Query(params);
        List<TaskDetailStatVo> statVoList = taskDetailService.statTaskDetail(query);
        int total = taskDetailService.statTaskDetailTotal(query);
        PageUtils pageUtil = new PageUtils(statVoList, total, query.getLimit(), query.getPage());

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
