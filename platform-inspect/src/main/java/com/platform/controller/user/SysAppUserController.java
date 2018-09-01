package com.platform.controller.user;

import com.platform.annotation.SysLog;
import com.platform.cache.RegionCacheUtil;
import com.platform.controller.AbstractController;
import com.platform.entity.AppUserEntity;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.AppUserService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
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
public class SysAppUserController extends AbstractController {

    @Autowired
    private AppUserService appUserService;



    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("appUser:list")
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        if (null != params.get("regionId") && org.apache.commons.lang.StringUtils.isNotBlank(String.valueOf(params.get("regionId")))){
            Integer regionId = Integer.parseInt(String.valueOf(params.get("regionId")));
            SysRegionEntity region = RegionCacheUtil.getAreaByAreaId(regionId);
            List<Integer> regionIdList = RegionCacheUtil.getRegionIdList(region.getId(), region.getType());
            params.put("regionIdList",regionIdList);
        }
        params.put("regionId",null);
        if(null != params.get("identify") && StringUtils.isBlank(String.valueOf(params.get("identify")))){
            params.put("identify",null);
        }

        if (null != params.get("enterpriseIds") && StringUtils.isNotBlank(String.valueOf(params.get("enterpriseIds")))){
            String[] enterpriseIdss = String.valueOf(params.get("enterpriseIds")).split(",");
            params.put("enterpriseList",enterpriseIdss);
        }

        //查询列表数据
        Query query = new Query(params);
        List<AppUserEntity> userList = appUserService.queryList(query);
        int total = appUserService.queryTotal(query);

        List<AppUserEntity> allAppUsers = appUserService.queryList(new HashMap<String, Object>());
        setSuperior(allAppUsers,userList);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    ///格式化领导字符串
    private void setSuperior(List<AppUserEntity> allAppUsers, List<AppUserEntity> userList){

        for (AppUserEntity user : userList) {
            if(null != user.getRegionId()){
                user.setRegionName(RegionCacheUtil.getAreaNameByAreaId(user.getRegionId()));
            }

            if (StringUtils.isNotBlank(user.getSuperior())){
                String superiorStrArr [] = user.getSuperior().split(",");
                StringBuffer superiorStr = new StringBuffer("");

                for (String s : superiorStrArr) {
                    if (null != allAppUsers && allAppUsers.size() > 0){
                        for (AppUserEntity allAppUser : allAppUsers) {
                            if (allAppUser.getId() != null && s.equals(String.valueOf(allAppUser.getId()))) {
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
     * 根据app用户身份获取下拉列表信息
     * @param identify
     * @return
     */
    @RequestMapping("/appUserListByIdentify/{identify}")
    public R appUserListByIdentify(@PathVariable("identify") Integer identify) {
        return R.ok().put("list", getList(identify,null));
    }

    @RequestMapping("/appUserList")
    public R appUserList(AppUserEntity user) {
        return R.ok().put("list", getList(user.getIdentify(),user.getEnterpriseId()));
    }

    private List<Map<String,Object>> getList(Integer identify,Integer enterpriseId){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        SysUserEntity user = getUser();
        ///如果是企业用户登录，查询该企业下的用户
        if (null != user){
            if (null != user.getEnterpriseId()){
                params.put("enterpriseId",user.getEnterpriseId());
            }else{
                if (null != enterpriseId){
                    params.put("enterpriseId",enterpriseId);
                }
            }

        }
        if (null != identify){
            params.put("identify",identify);
        }
        List<AppUserEntity> userList = appUserService.queryList(params);
        if (null != userList && userList.size() > 0){
            Map<String,Object> superiorMap = null;
            for (AppUserEntity sysUserEntity : userList) {
                superiorMap = new HashMap<>();
                superiorMap.put("id",sysUserEntity.getId());
                superiorMap.put("name",sysUserEntity.getRealname());
                list.add(superiorMap);
            }
        }
        return list;
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
        AppUserEntity user = appUserService.queryObject(userId);


        List<Map<String,Object>> superiorList = getList(1,null); // 领导
        user.setSuperiorList(superiorList);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("appUser:save")
    public R save(@RequestBody AppUserEntity user) {
        //ValidatorUtils.validateEntity(user, AddGroup.class);
        SysUserEntity sysUserEntity = getUser();
        if (null != sysUserEntity){
            user.setCreateUserId(sysUserEntity.getUserId());
        }
        user.setCreateTime(new Date());
        user.setCreateUserId(getUserId());
        user.setUpdateTime(new Date());
        user.setUpdateUserId(getUserId());
        appUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("appUser:update")
    public R update(@RequestBody AppUserEntity user) {
       // ValidatorUtils.validateEntity(user, UpdateGroup.class);

        SysUserEntity sysUserEntity = getUser();
        if (null != sysUserEntity){
            user.setUpdateUserId(sysUserEntity.getUserId());
        }
        user.setUpdateTime(new Date());
        appUserService.update(user);

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

        appUserService.deleteBatch(userIds);

        return R.ok();
    }
}
