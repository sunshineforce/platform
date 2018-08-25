package com.platform.controller.stat;

import com.platform.controller.AbstractController;
import com.platform.entity.SysUserEntity;
import com.platform.service.stat.IStatService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 异常处理统计Controller
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Controller
@RequestMapping("stat")
public class StatController extends AbstractController {


    @Autowired
    private IStatService iStatService;

    /**
     *  统计获取统计工单和异常数据
     */
    @RequestMapping("/statTaskAndOrder")
    @ResponseBody
    public R statTaskAndOrder(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();
        if (null == user){
            return R.error("用户未登录！");
        }
        params.put("enterpriseId",user.getEnterpriseId()); // 企业id
        return iStatService.stat(params);
    }




}
