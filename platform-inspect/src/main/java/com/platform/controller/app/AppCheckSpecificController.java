package com.platform.controller.app;

import com.platform.entity.specific.CheckSpecificEntity;
import com.platform.entity.specific.CheckSpecificItemEntity;
import com.platform.service.specific.CheckSpecificItemService;
import com.platform.service.specific.CheckSpecificService;
import com.platform.utils.R;
import com.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/4 11:47
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class AppCheckSpecificController {

    @Autowired
    private CheckSpecificService checkSpecificService;

    @Autowired
    private CheckSpecificItemService checkSpecificItemService;


    @RequestMapping(value = "/check/specific/list")
    public R query(@RequestParam Map<String,Object> params){
        if (!checkQueryParams(params)) {
            return R.paramsIllegal();
        }
        return R.succeed().put("page",checkSpecificService.search(params));
    }

    @RequestMapping(value = "/check/specific/create",method = RequestMethod.POST)
    public R create(@RequestParam CheckSpecificEntity entity){
        boolean valid = checkParams(entity);
        if (!valid) {
            return R.paramsIllegal();
        }
        checkSpecificService.save(entity);
        List<CheckSpecificItemEntity> itemList = entity.getSpecificItems();
        for (CheckSpecificItemEntity specificItem : itemList) {
            specificItem.setSpecificId(entity.getId());

            checkSpecificItemService.save(specificItem);
        }

        return R.succeed();
    }

    //检查参数合法性
    private boolean checkParams(CheckSpecificEntity entity){
        if (entity == null) {
            return false;
        }
        if (StringUtils.isNullOrEmpty(entity.getSpecificName())) {
            return false;
        }
        return true;
    }

    private boolean checkQueryParams(Map<String,Object> params){
        if (params == null) {
            return false;
        }
        if (StringUtils.isNullOrEmpty(params.get("materialTypeId"))) {
            return false;
        }
        return true;
    }

}
