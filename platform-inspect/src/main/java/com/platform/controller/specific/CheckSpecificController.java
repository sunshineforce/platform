package com.platform.controller.specific;

import com.alibaba.fastjson.JSON;
import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.service.specific.CheckSpecificItemService;
import com.platform.service.specific.CheckSpecificService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.collections.map.HashedMap;
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
 * 检查规范表Controller
 *
 * @author admin
 *  
 * @date 2018-07-23 20:03:40
 */
@Controller
@RequestMapping("checkspecific")
public class CheckSpecificController extends AbstractController {
    @Autowired
    private CheckSpecificService checkSpecificService;

    @Autowired
    private CheckSpecificItemService checkSpecificItemService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("checkspecific:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (user != null && user.getEnterpriseId() != null){
            params.put("enterpriseId",user.getEnterpriseId());
        }
        //查询列表数据
        Query query = new Query(params);

        List<CheckSpecificEntity> checkSpecificList = checkSpecificService.queryList(query);
        int total = checkSpecificService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(checkSpecificList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("checkspecific:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        CheckSpecificEntity checkSpecific = checkSpecificService.queryObject(id);
        Map<String, Object> params = new HashedMap();
        params.put("specificId",checkSpecific.getId());
        List<CheckSpecificItemEntity> items = checkSpecificItemService.queryList(params);
        checkSpecific.setSpecificItems(items);
        return R.ok().put("checkSpecific", checkSpecific);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("checkspecific:save")
    @ResponseBody
    public R save(@RequestBody CheckSpecificEntity checkSpecific) {
        SysUserEntity user = getUser();
        if (null != user){
            checkSpecific.setCreatorId(user.getUserId());
            checkSpecific.setCreator(user.getUsername());
            checkSpecific.setEnterpriseId(user.getEnterpriseId());
        }
        Date time = new Date();
        checkSpecific.setCreateTime(time);

        checkSpecificService.save(checkSpecific);

        if (StringUtils.isNotBlank(checkSpecific.getSpecificItemsJson())){
            List<CheckSpecificItemEntity> items = JSON.parseArray(checkSpecific.getSpecificItemsJson(), CheckSpecificItemEntity.class);
            for (CheckSpecificItemEntity item : items) {
                item.setSpecificId(checkSpecific.getId());
                item.setDataStatus(0);
                checkSpecificItemService.save(item);
            }
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("checkspecific:update")
    @ResponseBody
    public R update(@RequestBody CheckSpecificEntity checkSpecific) {
        SysUserEntity user = getUser();
        if (null != user){
            checkSpecific.setUpdatorId(user.getUserId());
            checkSpecific.setUpdator(user.getUsername());
        }
        Date time = new Date();
        checkSpecific.setUpdateTime(time);
        checkSpecificService.update(checkSpecific);

        if (StringUtils.isNotBlank(checkSpecific.getSpecificItemsJson())){
           // checkSpecificItemService.deleteBySpecId(checkSpecific.getId());
            Map<String, Object> params = new HashedMap();
            params.put("specificId",checkSpecific.getId());
            List<CheckSpecificItemEntity> itemEntities = checkSpecificItemService.queryList(params);

            List<CheckSpecificItemEntity> items = JSON.parseArray(checkSpecific.getSpecificItemsJson(), CheckSpecificItemEntity.class);
            List<CheckSpecificItemEntity> delList = new ArrayList<>();
            List<CheckSpecificItemEntity> addList = new ArrayList<>();
            List<CheckSpecificItemEntity> updateList = new ArrayList<>();
            for (CheckSpecificItemEntity item : items) {
                if (item.getId() == null || (item.getId() != null && item.getId().intValue() == 0)) {
                    item.setDataStatus(0);
                    item.setSpecificId(checkSpecific.getId());
                    addList.add(item);
                }
            }
            if (null != itemEntities && itemEntities.size() > 0){
                for (CheckSpecificItemEntity itemEntity : itemEntities) {
                    boolean isIn = false;
                    for (CheckSpecificItemEntity item : items) {
                        if (item.getId() != null && item.getId().intValue() == itemEntity.getId().intValue()){
                            isIn = true;
                            updateList.add(item);
                        }
                    }
                  if (!isIn){
                      itemEntity.setDataStatus(1); // 删除
                      delList.add(itemEntity);
                  }
                }
            }
            //删除
            if(delList.size() > 0){
                for (CheckSpecificItemEntity entity : delList) {
                    checkSpecificItemService.update(entity);
                }
            }
            //修改
            if (updateList.size() > 0){
                for (CheckSpecificItemEntity entity : updateList) {
                    checkSpecificItemService.update(entity);
                }
            }

            //增加
            if (addList.size() > 0){
                for (CheckSpecificItemEntity entity : addList) {
                    checkSpecificItemService.save(entity);
                }
            }

        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("checkspecific:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        checkSpecificService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CheckSpecificEntity> list = checkSpecificService.queryList(params);

        return R.ok().put("list", list);
    }
}
