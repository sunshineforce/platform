package com.platform.controller.inspect;

import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.inspect.InspectOrderRelSpecificEntity;
import com.platform.entity.material.MaterialEntity;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.inspect.InspectOrderFlowService;
import com.platform.service.inspect.InspectOrderRelSpecificService;
import com.platform.service.material.MaterialService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.collections.map.HashedMap;
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

    @Autowired
    private MaterialService materialService;

    @Autowired
    private InspectOrderRelSpecificService inspectOrderRelSpecificService;


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

    @RequestMapping("/queryDetail")
    @ResponseBody
    public R queryDetail(@RequestBody InspectOrderEntity inspectOrder) {
        ///加载检查信息
        InspectOrderEntity order = inspectOrderService.queryObject(inspectOrder.getId());
        List<InspectOrderRelSpecificEntity> orderRelSpecificEntityList = inspectOrderRelSpecificService.queryOrderSpecific(order.getId());
        order.setSpecificList(orderRelSpecificEntityList);
        ///加载物品详情
        MaterialEntity material = materialService.queryObject(order.getMaterialId());
        order.setMaterial(material);
        ///加载异常处理流程
        Map<String, Object> params = new HashedMap();
        params.put("orderId",order.getId());
        List<InspectOrderFlowEntity> flows = inspectOrderFlowService.queryList(params);
        order.setOrderFlows(flows);

        return R.ok().put("order", order);
    }
}
