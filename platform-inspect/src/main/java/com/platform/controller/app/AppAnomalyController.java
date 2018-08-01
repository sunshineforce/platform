package com.platform.controller.app;

import com.platform.service.inspect.IInspectOrderService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 21:06
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */
@Controller
@RequestMapping("/app")
public class AppAnomalyController {

    @Autowired
    private IInspectOrderService orderService;

    @RequestMapping("/anomaly/list")
    @ResponseBody
    public R anomalyList(@RequestParam Map<String, Object> queryParams){

        return R.succeed().put("page",orderService.search(queryParams));
    }

    @RequestMapping("/anomaly/details")
    @ResponseBody
    public R anomalyDetails(){

        return R.succeed();
    }


    @RequestMapping("/anomaly/histories")
    @ResponseBody
    public R anomalyHistories(){

        return R.succeed();
    }

}
