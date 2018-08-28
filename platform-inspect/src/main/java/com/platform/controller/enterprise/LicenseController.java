package com.platform.controller.enterprise;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.enterprise.LicenseEntity;
import com.platform.entity.enterprise.LicenseTypeEntity;
import com.platform.entity.enterprise.LicenseVo;
import com.platform.service.enterprise.ILicenseService;
import com.platform.service.enterprise.ILicenseTypeService;
import com.platform.utils.DateUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证照表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 15:28:08
 */
@Controller
@RequestMapping("license")
public class LicenseController   extends AbstractController {

    @Autowired
    private ILicenseService licenseService;

    @Autowired
    private ILicenseTypeService licenseTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("license:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        if (null != params.get("startTime") && StringUtils.isNotBlank(String.valueOf(params.get("startTime")))){
            params.put("startTime",
                    DateUtils.formAtTime(String.valueOf(params.get("startTime")),DateUtils.DATE_PATTERN));
        }else {
            params.put("startTime",null);
        }
        if (null != params.get("endTime") && StringUtils.isNotBlank(String.valueOf(params.get("endTime")))){
            params.put("endTime",
                    DateUtils.formAtTime(String.valueOf(params.get("endTime")),DateUtils.DATE_PATTERN));
        }else {
            params.put("endTime",null);
        }
        if (null != params.get("licenseTypeId") && StringUtils.isBlank(String.valueOf(params.get("licenseTypeId")))){
            params.put("licenseTypeId",null);
        }
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
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            license.setEnterpriseId(user.getEnterpriseId());
        }
        license.setCreateTime(new Date());
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

    private Map<String,Integer> loadLicenseType(){
        List<LicenseTypeEntity> list = licenseTypeService.queryList(null);
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (LicenseTypeEntity licenseType : list) {
            map.put(licenseType.getLicenseType(),licenseType.getId());
        }
        return map;
    }
}
