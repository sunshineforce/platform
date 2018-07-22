package com.platform.controller.inspect;

import java.util.List;
import java.util.Map;

import com.platform.entity.inspect.InspectOrderRelSpecificEntity;
import com.platform.service.inspect.InspectOrderRelSpecificService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 工单管理检查项表Controller
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Controller
@RequestMapping("inspectorderrelspecific")
public class InspectOrderRelSpecificController {
    @Autowired
    private InspectOrderRelSpecificService inspectOrderRelSpecificService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inspectorderrelspecific:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<InspectOrderRelSpecificEntity> inspectOrderRelSpecificList = inspectOrderRelSpecificService.queryList(query);
        int total = inspectOrderRelSpecificService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(inspectOrderRelSpecificList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("inspectorderrelspecific:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        InspectOrderRelSpecificEntity inspectOrderRelSpecific = inspectOrderRelSpecificService.queryObject(id);

        return R.ok().put("inspectOrderRelSpecific", inspectOrderRelSpecific);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("inspectorderrelspecific:save")
    @ResponseBody
    public R save(@RequestBody InspectOrderRelSpecificEntity inspectOrderRelSpecific) {
        inspectOrderRelSpecificService.save(inspectOrderRelSpecific);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("inspectorderrelspecific:update")
    @ResponseBody
    public R update(@RequestBody InspectOrderRelSpecificEntity inspectOrderRelSpecific) {
        inspectOrderRelSpecificService.update(inspectOrderRelSpecific);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inspectorderrelspecific:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        inspectOrderRelSpecificService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<InspectOrderRelSpecificEntity> list = inspectOrderRelSpecificService.queryList(params);

        return R.ok().put("list", list);
    }
}
