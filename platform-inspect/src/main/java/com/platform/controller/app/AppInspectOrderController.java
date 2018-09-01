package com.platform.controller.app;

import com.platform.entity.AppUserEntity;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.service.common.CommonService;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/21 19:35
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@RestController
@RequestMapping("/app")
public class AppInspectOrderController {

    @Autowired
    private IInspectOrderService inspectOrderService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/material/check",method = RequestMethod.POST)
    public R materialCheck(@ModelAttribute InspectOrderEntity entity){
        try {
            inspectOrderService.materialCheck(entity);
            return R.succeed();
        } catch (Exception e) {
            return R.failure();
        }
    }

    /**
     * 任务详情
     * @return
     */
    @RequestMapping(value = "/task/detail")
    public R taskDetail(){
        try {
            AppUserEntity appUser = commonService.getCurrentLoginUser();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("userId",appUser.getId());
            Query query = new Query(map);
            List<InspectOrderEntity> list = inspectOrderService.queryList(map);
            Integer total = inspectOrderService.queryTotal(query);
            return R.succeed().put("page",new PageUtils(list, total, query.getLimit(), query.getPage()));
        } catch (Exception e) {
            return R.failure();
        }
    }

}
