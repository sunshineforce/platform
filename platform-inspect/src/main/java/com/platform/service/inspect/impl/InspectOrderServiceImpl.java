package com.platform.service.inspect.impl;

import com.platform.dao.inspect.InspectOrderDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.inspect.vo.AnomalyVo;
import com.platform.entity.notice.NoticeEntity;
import com.platform.service.AppUserService;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.inspect.InspectOrderFlowService;
import com.platform.utils.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 安全巡检异常工单表Service实现类
 *
 * @author admin
 *  
 * @date 2018-07-22 21:48:11
 */
@Service("inspectOrderService")
public class InspectOrderServiceImpl implements IInspectOrderService {

    @Autowired
    private InspectOrderDao inspectOrderDao;

    @Autowired
    private InspectOrderFlowService inspectOrderFlowService;

    @Autowired
    private AppUserService userService;

    @Override
    public InspectOrderEntity queryObject(Integer id) {
        return inspectOrderDao.queryObject(id);
    }

    @Override
    public List<InspectOrderEntity> queryList(Map<String, Object> map) {
        return inspectOrderDao.queryList(map);
    }

    @Override
    public PageUtils search(Map<String, Object> map) {
        Query query = new Query(map);
        List<AnomalyVo> resultList = inspectOrderDao.search(map);
        for (AnomalyVo anomalyVo : resultList) {
            if (StringUtils.isNotEmpty(anomalyVo.getMaterialUrl())) {
                String[] urls = anomalyVo.getMaterialUrl().split(",");
                anomalyVo.setMaterialUrl(urls[0]);
            }
            anomalyVo.setChiefName(chiefName(anomalyVo.getChiefIds()));
        }
        int total = inspectOrderDao.searchTotal(map);

        PageUtils pageUtil = new PageUtils(resultList, total, query.getLimit(), query.getPage());
        return pageUtil;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return inspectOrderDao.queryTotal(map);
    }

    @Override
    public int save(InspectOrderEntity inspectOrder) {
        return inspectOrderDao.save(inspectOrder);
    }

    @Override
    public int update(InspectOrderEntity inspectOrder) {
        return inspectOrderDao.update(inspectOrder);
    }

    @Override
    public int delete(Integer id) {
        return inspectOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return inspectOrderDao.deleteBatch(ids);
    }

    @Override
    public int processAnomaly(Map<String, Object> map) {
        Integer orderId = Integer.valueOf(String.valueOf(map.get("orderId")));
        String descr = String.valueOf(map.get("descr"));
        String photos = String.valueOf(map.get("photos"));

        InspectOrderFlowEntity anomalyItem = new InspectOrderFlowEntity();
        anomalyItem.setType(0);
        anomalyItem.setOrderId(orderId);
        anomalyItem.setDescr(descr);
        anomalyItem.setCreateTime(new Date());
        anomalyItem.setPhotos(photos);

        Subject subject = ShiroUtils.getSubject();
        AppUserEntity appUser = (AppUserEntity) subject.getPrincipal();

        anomalyItem.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
        anomalyItem.setUserName(appUser.getRealname());

        //处理异常
        int effectRows = inspectOrderFlowService.update(anomalyItem);
        //生成下级待办事项（即通知）
        return inspectOrderFlowService.update(anomalyItem);
    }

    @Override
    public int report(Map<String, Object> map) {
        Integer orderId = Integer.valueOf(String.valueOf(map.get("orderId")));
        String descr = String.valueOf(map.get("descr"));
        String ids;
        if (!StringUtils.isNullOrEmpty(map.get("ids"))) {
            ids = String.valueOf(map.get("ids"));
            String[] arr = ids.split(",");
            for (String s : arr) {
                InspectOrderFlowEntity anomalyItem = new InspectOrderFlowEntity();
                anomalyItem.setOrderId(orderId);
                anomalyItem.setDescr(descr);
                anomalyItem.setType(1);
//                anomalyItem.setUserId();
                inspectOrderFlowService.save(anomalyItem);
            }

            //上报上级
            NoticeEntity noticeEntity = new NoticeEntity();
        }


        return 0;
    }

    private String chiefName(String chiefIds){
        if (StringUtils.isNullOrEmpty(chiefIds)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] ids = chiefIds.split(",");

        for (String id : ids) {
            AppUserEntity appUser = userService.queryObject(Long.valueOf(id));
            builder.append(appUser.getRealname());
            builder.append(",");
        }
        if (builder.length()>0) {
            builder.setLength(builder.length()-1);
        }

        return builder.toString();
    }
}
