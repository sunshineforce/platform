package com.platform.controller.app;

import com.platform.controller.AbstractController;
import com.platform.entity.material.MaterialEntity;
import com.platform.service.material.MaterialService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/7/28 17:12
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class AppMaterialBindController extends AbstractController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/material/bind",method = RequestMethod.POST)
    @ResponseBody
    public R materialBind(@RequestBody MaterialEntity entity){
         materialService.save(entity);
        return R.succeed();
    }

    @RequestMapping(value = "/material/exists",method = RequestMethod.POST)
    @ResponseBody
    public R checkExists(@RequestParam String qrCode){
        MaterialEntity material = materialService.queryMaterialByQrCode(qrCode);
        if (material == null) {
            return R.succeed().put("data",new String[]{});
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/material/check/qrcode",method = RequestMethod.POST)
    @ResponseBody
    public R checkQrCodeType(@RequestParam String url){
        String[] prefix = "http://i.0t.com.cn/nb/d/,http://i.0t.com.cn/nb/s".split(",");

        return null;
    }
}
