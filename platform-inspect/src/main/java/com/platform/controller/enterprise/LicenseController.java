package com.platform.controller.enterprise;

import java.util.List;
import java.util.Map;

import com.platform.entity.enterprise.LicenseEntity;
import com.platform.entity.enterprise.LicenseVo;
import com.platform.service.enterprise.ILicenseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 证照表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
@Controller
@RequestMapping("license")
public class LicenseController {
    @Autowired
    private ILicenseService licenseService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("license:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<LicenseVo> licenseList = licenseService.selectList(query);
        int total = licenseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(licenseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("license:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        LicenseEntity license = licenseService.queryObject(id);

        return R.ok().put("license", license);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("license:save")
    @ResponseBody
    public R save(@RequestBody LicenseEntity license) {
        licenseService.save(license);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("license:update")
    @ResponseBody
    public R update(@RequestBody LicenseEntity license) {
        licenseService.update(license);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("license:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        licenseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<LicenseEntity> list = licenseService.queryList(params);

        return R.ok().put("list", list);
    }
}
