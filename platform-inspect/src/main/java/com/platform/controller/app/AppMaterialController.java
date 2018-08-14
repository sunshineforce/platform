package com.platform.controller.app;

import com.platform.service.material.MaterialService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppMaterialController {
    @Autowired
    private MaterialService materialService;

    @RequestMapping("/material/details")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        if (!checkParams(params)) {
            return R.paramsIllegal();
        }
        Integer id = Integer.valueOf(String.valueOf(params.get("id")));
        return R.succeed().put("list",materialService.queryMaterialById(id));
    }

    private boolean checkParams(Map<String, Object> params){
        Integer id = Integer.valueOf(String.valueOf(params.get("id")));
        if (id == null || id ==0) {
            return false;
        }
        return true;
    }
}
