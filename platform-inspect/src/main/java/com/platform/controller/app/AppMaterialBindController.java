package com.platform.controller.app;

import com.platform.controller.AbstractController;
import com.platform.entity.dto.CustomerVo;
import com.platform.entity.enterprise.EnterpriseEntity;
import com.platform.entity.material.MaterialEntity;
import com.platform.service.enterprise.IEnterpriseService;
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

    @Autowired
    private IEnterpriseService enterpriseService;

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
            return R.succeed().put("exists",false);
        }else {
            return R.succeed().put("exists",true);
        }
    }

    @RequestMapping(value = "/customer/bind",method = RequestMethod.POST)
    public R materialBindBatch(@ModelAttribute CustomerVo customer){
        //绑定客户
        enterpriseService.enterpriseBind(customer);
        //绑定设备
        materialService.materialBindBatch(customer);
        return R.succeed();
    }
}
