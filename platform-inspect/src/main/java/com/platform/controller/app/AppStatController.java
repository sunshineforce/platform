package com.platform.controller.app;

import com.platform.cache.RegionCacheUtil;
import com.platform.entity.SysRegionEntity;
import com.platform.service.SysRegionService;
import com.platform.service.stat.IStatService;
import com.platform.utils.R;
import com.platform.vo.WeixinTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * app端统计
 *
 * @author bjsonghongxu
 * @create 2018-08-19 9:56
 **/
@Controller
@RequestMapping("/app/stat")
public class AppStatController {



    @Autowired
    private IStatService iStatService;

    @Autowired
    private SysRegionService sysRegionService;


    /**
     *  统计获取统计工单和异常数据
     */
    @RequestMapping("/statTask")
    @ResponseBody
    public R statTaskAndOrder(@RequestParam Map<String, Object> params) {
        return iStatService.stat(params).put("code",200);
    }

    /**
     * 查询所有城市
     *
     * @return
     */
    @RequestMapping("/getAllCity")
    public R getAllCity(@RequestParam(name = "areaId" , required = false) Integer areaId) {
        SysRegionEntity regionEntity = new SysRegionEntity();
        // regionEntity.setParentId(provinceId);
        regionEntity.setType(1);
        List<WeixinTreeVo> weixinTreeVos = sysRegionService.buildCascadeTree(regionEntity);
        return R.succeed().put("list", weixinTreeVos);
    }
}
