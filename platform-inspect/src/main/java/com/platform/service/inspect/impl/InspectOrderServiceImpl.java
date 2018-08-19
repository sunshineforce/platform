package com.platform.service.inspect.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.AppUserDao;
import com.platform.dao.inspect.InspectOrderDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.inspect.vo.AnomalyVo;
import com.platform.entity.notice.NoticeEntity;
import com.platform.service.AppUserService;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.inspect.InspectOrderFlowService;
import com.platform.service.material.MaterialService;
import com.platform.utils.*;
import com.platform.utils.enums.DataStatusEnum;
import com.platform.utils.enums.InspectStatusEnum;
import com.platform.utils.enums.MaterialStatusEnum;
import com.platform.utils.enums.TaskStatusEnum;
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
    private AppUserDao appUserDao;

    @Autowired
    private InspectOrderFlowService inspectOrderFlowService;

    @Autowired
    private MaterialService materialService;

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
        Integer materialId = Integer.valueOf(String.valueOf(map.get("materialId")));
        String descr = String.valueOf(map.get("descr"));
        String photos = String.valueOf(map.get("photos"));

        //记录最终异常状态
        int er = saveOrUpdateInspectOrder(orderId,materialId);

        //保存异常流水
        int effectRows = saveInspectOrderFlow(orderId, photos, descr,"", CommonConstant.ANOMALY_PROCESS);

        return (effectRows + er);
    }

    @Override
    public int report(Map<String, Object> map) {
        Integer orderId = Integer.valueOf(String.valueOf(map.get("orderId")));
        String descr = String.valueOf(map.get("descr"));
        String ids = String.valueOf(map.get("chiefIds"));

        //保存异常记录
        int effectRows = saveOrUpdateInspectOrder(orderId,null);

        int flow = saveInspectOrderFlow(orderId,"",descr,ids,CommonConstant.ANOMALY_REPORT);

        return (effectRows+flow);
    }

    private String chiefName(String chiefIds){
        if (StringUtils.isNullOrEmpty(chiefIds)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] ids = chiefIds.split(",");

        for (String id : ids) {
            AppUserEntity appUser = appUserDao.queryObject(Long.valueOf(id));
            builder.append(appUser.getRealname());
            builder.append(",");
        }
        if (builder.length()>0) {
            builder.setLength(builder.length()-1);
        }

        return builder.toString();
    }

    //保存物品工单
    private int saveInspectOrderFlow(Integer orderId,String photos,String descr,String ids,int type){
        InspectOrderFlowEntity anomalyItem = new InspectOrderFlowEntity();
        anomalyItem.setType(type); //处理异常
        anomalyItem.setOrderId(orderId);
        anomalyItem.setDescr(descr);
        anomalyItem.setCreateTime(new Date());
        if (StringUtils.isNotEmpty(photos)) {
            anomalyItem.setPhotos(photos);
        }

//        Subject subject = ShiroUtils.getSubject();
//        AppUserEntity appUser = (AppUserEntity) subject.getPrincipal();

//        anomalyItem.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
//        anomalyItem.setUserName(appUser.getRealname());
        anomalyItem.setUserId(11);
        anomalyItem.setUserName("小六");
        if (StringUtils.isNotEmpty(ids)) {
            anomalyItem.setChiefIds(ids);
            anomalyItem.setChiefNames(chiefName(ids));
        }

        //处理异常
        return inspectOrderFlowService.save(anomalyItem);
    }

    private int saveOrUpdateInspectOrder(Integer orderId,Integer materialId){
        InspectOrderEntity inspectOrder = inspectOrderDao.queryObject(orderId);
        int effectRows =0;
        Date currentDate = new Date();
        if (inspectOrder == null) {
            InspectOrderEntity entity = new InspectOrderEntity();
            entity.setMaterialId(materialId);

            entity.setStatus(InspectStatusEnum.PENDING.getCode());
            entity.setInspectTime(currentDate);
            entity.setUserId(11);
            entity.setUserName("小六");
            entity.setInspectStatus(MaterialStatusEnum.ANOMALY.getCode());
            entity.setCreateTime(currentDate);
            entity.setDataStatus(DataStatusEnum.NORMAL.getCode());
            effectRows = inspectOrderDao.save(entity);
        }else {
            inspectOrder.setStatus(InspectStatusEnum.REVIEW.getCode());
            inspectOrder.setUserId(11);
            inspectOrder.setUserName("小六");
            inspectOrder.setUpdateTime(currentDate);

            effectRows = inspectOrderDao.update(inspectOrder);
        }
        return effectRows;
    }

}
