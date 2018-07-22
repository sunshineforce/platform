package com.platform.controller.inspect;

import java.util.List;
import java.util.Map;

import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.service.inspect.InspectOrderFlowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 巡检工单处理流程Controller
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Controller
@RequestMapping("inspectorderflow")
public class InspectOrderFlowController {
    @Autowired
    private InspectOrderFlowService inspectOrderFlowService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<InspectOrderFlowEntity> inspectOrderFlowList = inspectOrderFlowService.queryList(query);
        int total = inspectOrderFlowService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(inspectOrderFlowList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        InspectOrderFlowEntity inspectOrderFlow = inspectOrderFlowService.queryObject(id);

        return R.ok().put("inspectOrderFlow", inspectOrderFlow);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("inspectorderflow:save")
    @ResponseBody
    public R save(@RequestBody InspectOrderFlowEntity inspectOrderFlow) {
        inspectOrderFlowService.save(inspectOrderFlow);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("inspectorderflow:update")
    @ResponseBody
    public R update(@RequestBody InspectOrderFlowEntity inspectOrderFlow) {
        inspectOrderFlowService.update(inspectOrderFlow);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inspectorderflow:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        inspectOrderFlowService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<InspectOrderFlowEntity> list = inspectOrderFlowService.queryList(params);

        return R.ok().put("list", list);
    }
}
