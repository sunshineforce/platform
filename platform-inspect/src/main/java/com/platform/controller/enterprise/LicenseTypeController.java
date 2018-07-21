package com.platform.controller.enterprise;

import java.util.List;
import java.util.Map;

import com.platform.entity.enterprise.LicenseTypeEntity;
import com.platform.service.enterprise.ILicenseTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 证照类型Controller
 * @author admin
 * @date 2018-07-21 15:28:08
 */
@Controller
@RequestMapping("licenseType")
public class LicenseTypeController {
    @Autowired
    private ILicenseTypeService licenseTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("licenseType:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<LicenseTypeEntity> licenseTypeList = licenseTypeService.queryList(query);
        int total = licenseTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(licenseTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("licenseType:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        LicenseTypeEntity licenseType = licenseTypeService.queryObject(id);

        return R.ok().put("licenseType", licenseType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("licenseType:save")
    @ResponseBody
    public R save(@RequestBody LicenseTypeEntity licenseType) {
        licenseTypeService.save(licenseType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("licenseType:update")
    @ResponseBody
    public R update(@RequestBody LicenseTypeEntity licenseType) {
        licenseTypeService.update(licenseType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("licenseType:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        licenseTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<LicenseTypeEntity> list = licenseTypeService.queryList(params);

        return R.ok().put("list", list);
    }
}
