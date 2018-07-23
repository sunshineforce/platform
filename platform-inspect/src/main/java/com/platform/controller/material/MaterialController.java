package com.platform.controller.material;

import com.platform.entity.material.MaterialEntity;
import com.platform.service.material.MaterialService;
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
 * 物品表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
@Controller
@RequestMapping("material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("material:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<MaterialEntity> materialList = materialService.queryList(query);
        int total = materialService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(materialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("material:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        MaterialEntity material = materialService.queryObject(id);

        return R.ok().put("material", material);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("material:save")
    @ResponseBody
    public R save(@RequestBody MaterialEntity material) {
        materialService.save(material);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("material:update")
    @ResponseBody
    public R update(@RequestBody MaterialEntity material) {
        materialService.update(material);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("material:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        materialService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MaterialEntity> list = materialService.queryList(params);

        return R.ok().put("list", list);
    }
}
