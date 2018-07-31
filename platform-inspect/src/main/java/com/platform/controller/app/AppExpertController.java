package com.platform.controller.app;

import com.platform.service.expert.IExpertService;
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
 * CreateTime : 2018/7/31 20:49
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@Controller
@RequestMapping("/app")
public class AppExpertController {

    @Autowired
    private IExpertService expertService;


    @RequestMapping("/expert/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok().put("page", expertService.search(params));
    }
}
