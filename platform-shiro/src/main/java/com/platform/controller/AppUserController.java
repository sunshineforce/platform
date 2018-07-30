package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.utils.Constant;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.validator.ValidatorUtils;
import com.platform.validator.group.AddGroup;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * APP用户
 * @author admin
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/app/user")
public class AppUserController extends SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("appUser:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryAllAppUser(query);
        int total = sysUserService.queryAppUserTotal(query);

        Map<String, Object> params1 = new HashMap<>();
//        Query query1 = new Query(params1);
        List<SysUserEntity> allAppUsers = sysUserService.queryAllAppUser(params1);
        setSuperior(allAppUsers,userList);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    ///格式化领导字符串
    private void setSuperior(List<SysUserEntity> allAppUsers, List<SysUserEntity> userList){
        for (SysUserEntity user : userList) {
            if (StringUtils.isNotBlank(user.getSuperior())){
                String superiorStrArr [] = user.getSuperior().split(",");
                StringBuffer superiorStr = new StringBuffer("");

                for (String s : superiorStrArr) {
                    if (null != allAppUsers && allAppUsers.size() > 0){
                        for (SysUserEntity allAppUser : allAppUsers) {
                            if (allAppUser.getUserId() != null && s.equals(String.valueOf(allAppUser.getUserId()))) {
                                if (superiorStr.length() > 0){
                                    superiorStr.append(",").append(allAppUser.getRealname());
                                }else {
                                    superiorStr.append(allAppUser.getRealname());
                                }
                                break;
                            }
                        }
                    }

                }

                user.setSuperiorStr(superiorStr.toString());

            }

        }
    }

    /**
     * 领导下拉选项
     * @param
     * @return
     */
    @RequestMapping("/superiorList/{userId}")
    public R superiorList(@PathVariable("userId") Long userId) {


        List<Map<String,Object>> superiorList = getSuperiorList(userId);

        return R.ok().put("superiorList", superiorList);
    }

    private List<Map<String,Object>> getSuperiorList(Long userId){
        Map<String, Object> params = new HashMap<>();
        // Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryAllAppUser(params);

        List<Map<String,Object>> superiorList = new ArrayList<>();
        if (null != userList && userList.size() > 0){
            Map<String,Object> superiorMap = null;
            for (SysUserEntity sysUserEntity : userList) {
                if (userId == null || (userId != null && userId.intValue() != sysUserEntity.getUserId().intValue())) { //刨除自身
                    superiorMap = new HashMap<>();
                    superiorMap.put("id",sysUserEntity.getUserId());
                    superiorMap.put("name",sysUserEntity.getRealname());
                    superiorList.add(superiorMap);
                }
            }
        }
        return superiorList;
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/appUser:info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("appUser:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
//        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
//        user.setRoleIdList(roleIdList);
        List<Map<String,Object>> superiorList = getSuperiorList(userId);
        user.setSuperiorList(superiorList);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("appUser:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        SysUserEntity sysUserEntity = getUser();
        if (null != user){
            user.setCreateUserId(sysUserEntity.getUserId());
        }
        user.setCreateTime(new Date());
        user.setCreateUserId(getUserId());
        user.setRoleId(2);
        user.setUpdateTime(new Date());
        user.setUpdateUserId(getUserId());
        List<Long> roleIdList = new ArrayList<Long>();
        roleIdList.add(2L);
        user.setRoleIdList(roleIdList);
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("appUser:update")
    public R update(@RequestBody SysUserEntity user) {
       // ValidatorUtils.validateEntity(user, UpdateGroup.class);

        SysUserEntity sysUserEntity = getUser();
        if (null != user){
            user.setUpdateUserId(sysUserEntity.getUserId());
        }
        user.setUpdateTime(new Date());
        sysUserService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("appUser:delete")
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
