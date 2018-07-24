package com.platform.controller.regulation;

import com.platform.entity.regulation.RegulationEntity;
import com.platform.service.regulation.RegulationService;
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
 * 法规管理表Controller
 *
 * @author admin
 *  
 * @date 2018-07-24 10:28:40
 */
@Controller
@RequestMapping("regulation")
public class RegulationController {
    @Autowired
    private RegulationService regulationService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("regulation:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<RegulationEntity> regulationList = regulationService.queryList(query);
        int total = regulationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(regulationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("regulation:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        RegulationEntity regulation = regulationService.queryObject(id);

        return R.ok().put("regulation", regulation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("regulation:save")
    @ResponseBody
    public R save(@RequestBody RegulationEntity regulation) {
        regulationService.save(regulation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("regulation:update")
    @ResponseBody
    public R update(@RequestBody RegulationEntity regulation) {
        regulationService.update(regulation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("regulation:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        regulationService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<RegulationEntity> list = regulationService.queryList(params);

        return R.ok().put("list", list);
    }
}
