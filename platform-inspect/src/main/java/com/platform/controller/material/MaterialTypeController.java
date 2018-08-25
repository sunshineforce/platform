package com.platform.controller.material;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.material.MaterialTypeEntity;
import com.platform.service.material.MaterialTypeService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 物品类型表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 11:11:45
 */
@Controller
@RequestMapping("materialtype")
public class MaterialTypeController extends AbstractController {
    @Autowired
    private MaterialTypeService materialTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }

        //查询列表数据
        Query query = new Query(params);

        List<MaterialTypeEntity> materialTypeList = materialTypeService.queryList(query);
        int total = materialTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(materialTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("materialtype:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        MaterialTypeEntity materialType = materialTypeService.queryObject(id);

        return R.ok().put("materialType", materialType);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("materialtype:save")
    @ResponseBody
    public R save(@RequestBody MaterialTypeEntity materialType) {
        SysUserEntity user = getUser();
        if (null != user){
            materialType.setCreatorId(user.getUserId());
            materialType.setCreator(user.getUsername());
            materialType.setEnterpriseId(user.getEnterpriseId());
        }

        Date time = new Date();
        materialType.setUpdateTime(time);
        materialType.setCreateTime(time);

        materialTypeService.save(materialType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("materialtype:update")
    @ResponseBody
    public R update(@RequestBody MaterialTypeEntity materialType) {
        SysUserEntity user = getUser();
        if (null != user){
            materialType.setUpdatorId(user.getUserId());
            materialType.setUpdator(user.getUsername());
        }
        Date time = new Date();
        materialType.setUpdateTime(time);
        materialTypeService.update(materialType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("materialtype:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        materialTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MaterialTypeEntity> list = materialTypeService.queryList(params);

        return R.ok().put("list", list);
    }
}
