package com.platform.controller.app;

import com.platform.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/regulation/list")
    @ResponseBody
    public R regulationList(){

        return R.succeed();
    }

    @RequestMapping("/regulation/details")
    @ResponseBody
    public R regulationDetail(){

        return R.succeed();
    }
}
