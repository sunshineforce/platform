package com.platform.controller.app;

import com.platform.controller.AbstractController;
import com.platform.entity.SysRegionEntity;
import com.platform.service.SysRegionService;
import com.platform.service.material.MaterialTypeService;
import com.platform.service.specific.CheckSpecificService;
import com.platform.utils.R;
import com.platform.vo.SelectVo;
import com.platform.vo.TreeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 14:57
 * ModifyUser: bjlixiaopeng
 * Class Description: 公共Controller，包括查询物品类型、区域树等等
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class CommonController extends AbstractController {
    public static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private MaterialTypeService materialTypeService;

    @Autowired
    private CheckSpecificService checkSpecificService;

    @Autowired
    private SysRegionService regionService;

    @RequestMapping(value = "/material/type/list")
    public R queryMaterialTypeList(){
        List<TreeVo> list = materialTypeService.loadAllMaterialType();
        return R.succeed().put("list",list);
    }

    @RequestMapping(value = "/check/specific//list")
    public R queryCheckSpecificList(){
        List<SelectVo> list = checkSpecificService.loadAllCheckSpecific();
        return R.succeed().put("list",list);
    }

    @RequestMapping(value = "/enterprise/info")
    public R queryEnterpriseByRegionId(@RequestParam("regionId") Long regionId){
        if (regionId == null) {
            return R.paramsIllegal();
        }
        List<SysRegionEntity> list = regionService.queryList(null);
        return R.succeed().put("list",list);
    }

}
