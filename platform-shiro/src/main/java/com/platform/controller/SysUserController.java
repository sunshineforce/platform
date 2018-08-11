package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.cache.RegionCacheUtil;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysRoleEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysRoleService;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.utils.*;
import com.platform.validator.Assert;
import com.platform.validator.ValidatorUtils;
import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;
import org.apache.commons.lang.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @author admin
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        if (null != params.get("regionId") && org.apache.commons.lang.StringUtils.isNotBlank(String.valueOf(params.get("regionId")))){
            Integer regionId = Integer.parseInt(String.valueOf(params.get("regionId")));
            SysRegionEntity region = RegionCacheUtil.getAreaByAreaId(regionId);
            List<Integer> regionIdList = RegionCacheUtil.getRegionIdList(region.getId(), region.getType());
            params.put("regionIdList",regionIdList);
        }
        params.put("regionId",null);

        List<SysRoleEntity> roleList = sysRoleService.queryList(new HashMap<String, Object>());
        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);
        adduserRoleInfos(userList,roleList);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    private void adduserRoleInfos(List<SysUserEntity> userList,List<SysRoleEntity> roleList){
        if (userList == null) {
            return;
        }

        for (SysUserEntity sysUserEntity : userList) {
            if(null != sysUserEntity.getRegionId()){
                sysUserEntity.setRegionName(RegionCacheUtil.getAreaNameByAreaId(sysUserEntity.getRegionId()));
            }
            List<Long> roleIdList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
            if (roleList != null && roleIdList != null && roleIdList.size() > 0) {
                String roleNames = "";
                for (Long roleId : roleIdList) {
                    for (SysRoleEntity roleEntity : roleList) {
                       if (roleId.equals(roleEntity.getRoleId())){
                           if (roleNames.length() > 0){
                               roleNames += ","+roleEntity.getRoleName();
                           }else {
                               roleNames += roleEntity.getRoleName();
                           }
                       }
                    }
                }
              sysUserEntity.setRoleNames(roleNames);
            }
        }

    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }

        //退出
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return R.ok();
    }
}
