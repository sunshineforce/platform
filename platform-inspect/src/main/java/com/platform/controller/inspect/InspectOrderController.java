package com.platform.controller.inspect;

import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.inspect.InspectOrderFlowService;
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
 * 安全巡检异常工单表Controller
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Controller
@RequestMapping("inspectorder")
public class InspectOrderController {
    @Autowired
    private IInspectOrderService inspectOrderService;

    @Autowired
    private InspectOrderFlowService inspectOrderFlowService;



    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inspectorder:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<InspectOrderEntity> inspectOrderList = inspectOrderService.queryList(query);
        int total = inspectOrderService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(inspectOrderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("inspectorder:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        InspectOrderEntity inspectOrder = inspectOrderService.queryObject(id);

        return R.ok().put("inspectOrder", inspectOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("inspectorder:save")
    @ResponseBody
    public R save(@RequestBody InspectOrderEntity inspectOrder) {
        inspectOrderService.save(inspectOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("inspectorder:update")
    @ResponseBody
    public R update(@RequestBody InspectOrderEntity inspectOrder) {
        inspectOrderService.update(inspectOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inspectorder:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        inspectOrderService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<InspectOrderEntity> list = inspectOrderService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/queryDetails")
    @ResponseBody
    public R queryDetails(@RequestBody InspectOrderEntity inspectOrder) {



        return R.ok().put("inspectOrder", inspectOrder);
    }
}
