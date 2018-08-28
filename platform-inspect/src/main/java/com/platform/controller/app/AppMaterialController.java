package com.platform.controller.app;

import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.material.MaterialService;
import com.platform.utils.R;
import com.platform.utils.StringUtils;
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

    @Autowired
    private IInspectOrderService inspectOrderService;

    @RequestMapping("/material/details")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        if (!checkParams(params)) {
            return R.paramsIllegal();
        }
        Integer id = Integer.valueOf(String.valueOf(params.get("id")));
        return R.succeed().put("list",materialService.queryMaterialById(id));
    }

    @RequestMapping("/material/info")
    @ResponseBody
    public R MaterialEntity(@RequestParam String qrCode) {
        if (StringUtils.isNullOrEmpty(qrCode)) {
            return R.paramsIllegal();
        }
        return R.succeed().put("list",materialService.materialInfo(qrCode));
    }

    @RequestMapping("/material/history")
    @ResponseBody
    public R history(@RequestParam Map<String, Object> params) {
        if (StringUtils.isNullOrEmpty(params.get("id"))) {
            return R.paramsIllegal();
        }
        Integer id = Integer.valueOf(String.valueOf(params.get("id")));
        params.put("materialId",id);
        return R.succeed().put("page",inspectOrderService.search(params));
    }

    private boolean checkParams(Map<String, Object> params){
        if (StringUtils.isNullOrEmpty(params.get("id"))) {
            return false;
        }
        return true;
    }
}
