package com.platform.controller.app;

import com.platform.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 21:06
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RequestMapping("/app")
public class AnomalyController {

    @RequestMapping("/anomaly/list")
    @ResponseBody
    public R anomalyList(){

        return R.ok();
    }

    @RequestMapping("/anomaly/details")
    @ResponseBody
    public R anomalyDetails(){

        return R.ok();
    }


    @RequestMapping("/anomaly/histories")
    @ResponseBody
    public R anomalyHistories(){

        return R.ok();
    }

}
