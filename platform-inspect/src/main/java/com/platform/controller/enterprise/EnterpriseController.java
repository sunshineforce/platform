package com.platform.controller.enterprise;

import com.platform.cache.RegionCacheUtil;
import com.platform.constants.CommonConstant;
import com.platform.controller.AbstractController;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.service.enterprise.IEnterpriseService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业信息表Controller
 *
 * @author admin
 *  
 * @date 2018-07-21 14:32:11
 */
@Controller
@RequestMapping("enterprise")
public class EnterpriseController extends AbstractController {
    
    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("id",user.getEnterpriseId());
        }
        if (null != params.get("regionId") && StringUtils.isNotBlank(String.valueOf(params.get("regionId")))){
            Integer regionId = Integer.parseInt(String.valueOf(params.get("regionId")));
            SysRegionEntity region = RegionCacheUtil.getAreaByAreaId(regionId);
            List<Integer> regionIdList = RegionCacheUtil.getRegionIdList(region.getId(), region.getType());
            params.put("regionIdList",regionIdList);
        }
        params.put("regionId",null);

        //查询列表数据
        Query query = new Query(params);

        List<EnterpriseEntity> enterpriseList = enterpriseService.queryList(query);
        int total = enterpriseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(enterpriseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("enterprise:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        EnterpriseEntity enterprise = enterpriseService.queryObject(id);

        return R.ok().put("enterprise", enterprise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:save")
    @ResponseBody
    public R save(@RequestBody EnterpriseEntity enterprise) {
        enterprise.setEnabled(1); // 正常
        SysUserEntity user = getUser();

        Date time = new Date();
        //新增企业管理员用户
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername(enterprise.getAccount());
        sysUserEntity.setRealname(enterprise.getOwner());
        sysUserEntity.setPassword(enterprise.getPassword());
        sysUserEntity.setMobile(enterprise.getMobile());
        sysUserEntity.setRegionId(enterprise.getRegionId());
        sysUserEntity.setStatus(enterprise.getEnabled());
        if (null != user){
            sysUserEntity.setCreateUserId(user.getUserId());
            sysUserEntity.setCreateTime(time);
            enterprise.setCreator(user.getRealname());
            enterprise.setCreateTime(time);
        }
        //设置企业用户角色
        List<Long> roleList = new ArrayList<>();
        roleList.add(CommonConstant.COMPANY_ROLE_ID);
        sysUserEntity.setRoleIdList(roleList);
        sysUserService.save(sysUserEntity);
        //管理用户表
        enterprise.setUserId(sysUserEntity.getUserId());
        //添加企业信息
        enterpriseService.save(enterprise);

        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setUserId(sysUserEntity.getUserId());
        userEntity.setEnterpriseId(enterprise.getId());
        sysUserService.update(userEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:update")
    @ResponseBody
    public R update(@RequestBody EnterpriseEntity enterprise) {
        enterprise.setEnabled(1); // 正常
        SysUserEntity user = getUser();
        Date time = new Date();
        EnterpriseEntity enterpriseEntity = enterpriseService.queryObject(enterprise.getId());
        if (null != enterpriseEntity){
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setUsername(enterprise.getAccount());
            sysUserEntity.setRealname(enterprise.getOwner());
            sysUserEntity.setPassword(enterprise.getPassword());
            sysUserEntity.setMobile(enterprise.getMobile());
            sysUserEntity.setRegionId(enterprise.getRegionId());
            sysUserEntity.setStatus(enterprise.getEnabled());
            if (null != user){
                sysUserEntity.setUpdateUserId(user.getUserId());
                sysUserEntity.setUpdateTime(time);
                enterprise.setUpdateTime(time);
                enterprise.setUpdator(user.getRealname());
            }
            sysUserService.update(sysUserEntity);
        }
        enterpriseService.update(enterprise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        enterpriseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<EnterpriseEntity> list = enterpriseService.queryList(params);

        return R.ok().put("list", list);
    }
}
