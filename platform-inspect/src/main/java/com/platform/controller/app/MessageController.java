package com.platform.controller.app;

import com.platform.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/24 20:30
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RequestMapping("/app")
public class MessageController {

    @RequestMapping("/notice")
    @ResponseBody
    public R noticeList(){
        return R.succeed();
    }
}
