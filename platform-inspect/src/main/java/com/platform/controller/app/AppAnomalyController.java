package com.platform.controller.app;

import com.platform.utils.R;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/app")
public class AppAnomalyController {

    @RequestMapping("/anomaly/list")
    @ResponseBody
    public R anomalyList(){

        return R.succeed();
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
