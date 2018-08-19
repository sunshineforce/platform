package com.platform.controller.app;

import com.platform.entity.regulation.RegulationEntity;
import com.platform.service.regulation.RegulationService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 21:15
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@Controller
@RequestMapping("/app")
public class AppRegulationController {

    @Autowired
    private RegulationService regulationService;

    @RequestMapping("/regulation/list")
    @ResponseBody
    public R regulationList(@RequestParam Map<String, Object> params){
       //查询列表数据
        Query query = new Query(params);

        List<RegulationEntity> regulationList = regulationService.queryList(query);
        int total = regulationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(regulationList, total, query.getLimit(), query.getPage());

        return R.succeed().put("page", pageUtil);
    }

    @RequestMapping("/regulation/details")
    @ResponseBody
    public R regulationDetail(@RequestParam(name = "id" , required = false) Long id){

        RegulationEntity regulation = regulationService.queryObject(id);

        return R.succeed().put("regulation", regulation);
    }
}
