package com.platform.controller.material;

import com.platform.cache.RegionCacheUtil;
import com.platform.controller.AbstractController;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.material.MaterialEntity;
import com.platform.service.material.MaterialService;
import com.platform.service.task.TaskGroupMaterialService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
public class MaterialController extends AbstractController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private TaskGroupMaterialService taskGroupMaterialService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("material:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {

        if (null != params.get("regionId") && org.apache.commons.lang.StringUtils.isNotBlank(String.valueOf(params.get("regionId")))){
            Integer regionId = Integer.parseInt(String.valueOf(params.get("regionId")));
            SysRegionEntity region = RegionCacheUtil.getAreaByAreaId(regionId);
            List<Integer> regionIdList = RegionCacheUtil.getRegionIdList(region.getId(), region.getType());
            params.put("regionIdList",regionIdList);
        }
        params.put("regionId",null);

        if (null != params.get("enterpriseIds") && StringUtils.isNotBlank(String.valueOf(params.get("enterpriseIds")))){
            String[] enterpriseIdss = String.valueOf(params.get("enterpriseIds")).split(",");
            params.put("enterpriseList",enterpriseIdss);
        }

        //查询列表数据
        Query query = new Query(params);

        List<MaterialEntity> materialList = materialService.queryList(query);
        int total = materialService.queryTotal(query);
        Integer taskGroupId = null;
        if (params.get("taskGroupId") != null && !"null".equals(String.valueOf(params.get("taskGroupId")))){
           taskGroupId =  Integer.parseInt(String.valueOf(params.get("taskGroupId")));
        }
        syncTaskGroupStatus(materialList,taskGroupId);
        PageUtils pageUtil = new PageUtils(materialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    
    private void syncTaskGroupStatus(List<MaterialEntity> materialList,Integer taskGroupId ){
        if (null == materialList){
            return;
        }
        Map<String, Object> params = null;
        if (null != taskGroupId){
            for (MaterialEntity materialEntity : materialList) {
                params = new HashMap<>();
                params.put("taskGroupId",taskGroupId);
                params.put("materialId",materialEntity.getId());
                int l = taskGroupMaterialService.queryTotal(params);
                if (l > 0 ){
                    materialEntity.setTaskGroupStatus(1);
                }else{
                    materialEntity.setTaskGroupStatus(0);
                }
            }
        }
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
        SysUserEntity user = getUser();
        if (null != user){
            material.setCreatorId(user.getUserId());
            material.setCreator(user.getUsername());
        }
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
        SysUserEntity user = getUser();
        if (null != user){
            material.setUpdatorId(user.getUserId());
            material.setUpdator(user.getUsername());
        }
        materialService.update(material);

        return R.ok();
    }

    /**
     * 报废
     * @param material
     * @return
     */
    @RequestMapping("/giveUp")
    @RequiresPermissions("material:giveUp")
    @ResponseBody
    public R giveUp(@RequestBody MaterialEntity material) {
        SysUserEntity user = getUser();
        if (null != user){
            material.setUpdatorId(user.getUserId());
            material.setUpdator(user.getUsername());
        }
        material.setMaterialStatus(3);//报废
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
