package com.platform.service.inspect.impl;

import com.platform.constants.CommonConstant;
import com.platform.dao.AppUserDao;
import com.platform.dao.inspect.InspectOrderDao;
import com.platform.dao.inspect.InspectOrderFlowDao;
import com.platform.dao.material.MaterialDao;
import com.platform.dao.notice.NoticeDao;
import com.platform.entity.AppUserEntity;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.inspect.vo.AnomalyVo;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.notice.NoticeEntity;
import com.platform.service.common.CommonService;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.utils.DateUtils;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.StringUtils;
import com.platform.utils.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
    private CommonService commonService;

    @Autowired
    private InspectOrderFlowDao inspectOrderFlowDao;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private NoticeDao noticeDao;

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
        Map<String,Object> queryParams = new HashMap<String, Object>();
        for (AnomalyVo anomalyVo : resultList) {
            if (StringUtils.isNotEmpty(anomalyVo.getMaterialUrl())) {
                String[] urls = anomalyVo.getMaterialUrl().split(",");
                anomalyVo.setMaterialUrl(urls[0]);
            }
            anomalyVo.setChiefName(commonService.getChiefNames(anomalyVo.getChiefIds()));
            queryParams.put("orderId",anomalyVo.getId());

            anomalyVo.setAnomalys(inspectOrderFlowDao.queryAnomalyList(queryParams));
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
    public int processAnomaly(InspectOrderFlowEntity params) {
        Integer orderId = params.getOrderId();
        Integer materialId = params.getMaterialId();
        String descr = params.getDescr();
        String photos = params.getPhotos();

        //记录最终异常状态
        int er = saveOrUpdateInspectOrder(orderId,materialId);

        //保存异常流水
        int effectRows = saveInspectOrderFlow(orderId, photos, descr,"", CommonConstant.ANOMALY_PROCESS);

        return (effectRows + er);
    }

    @Override
    public int report(InspectOrderFlowEntity queryParams) {
        Integer orderId = queryParams.getOrderId();
        String descr = queryParams.getDescr();
        String ids = queryParams.getChiefIds();

        //保存异常记录
        int effectRows = saveOrUpdateInspectOrder(orderId,null);

        int flow = saveInspectOrderFlow(orderId,"",descr,ids,CommonConstant.ANOMALY_REPORT);

        return (effectRows+flow);
    }

    @Override
    public int materialCheck(InspectOrderEntity inspectOrder) {
        //保存异常信息
        setInspectOrder(inspectOrder);
        if (inspectOrder.getInspectStatus().intValue() == InspectStatusEnum.NORMAL.getCode().intValue()) {
            inspectOrder.setStatus(InspectOrderStatusEnum.FINISHED.getCode());
        }
        //保存异常记录
        int effectRows = save(inspectOrder);
        if (inspectOrder.getInspectStatus().intValue() == InspectStatusEnum.ABNORMAL.getCode().intValue()) {
            InspectOrderFlowEntity orderFlow = new InspectOrderFlowEntity();
            orderFlow.setOrderId(inspectOrder.getId());
            orderFlow.setMaterialId(inspectOrder.getMaterialId());
            orderFlow.setType(1); //上报上级
//            AppUserEntity appUser = commonService.getCurrentLoginUser();
//            orderFlow.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
//            orderFlow.setUserName(appUser.getRealname());

            orderFlow.setUserId(11);
            orderFlow.setUserName("曹操");
            orderFlow.setCreateTime(new Date());
            orderFlow.setDescr(inspectOrder.getDescr());
            orderFlow.setPhotos(inspectOrder.getPhotos());
            orderFlow.setChiefIds(inspectOrder.getChiefIds());
            if (StringUtils.isNotEmpty(inspectOrder.getChiefIds())) {
                orderFlow.setChiefNames(commonService.getChiefNames(inspectOrder.getChiefIds()));
            }
            orderFlow.setDataStatus(DataStatusEnum.NORMAL.getCode());

            inspectOrderFlowDao.save(orderFlow);

            //发送任务通知
            NoticeEntity notice = new NoticeEntity();
            if (StringUtils.isNotEmpty(inspectOrder.getChiefIds())) {
                Date date = new Date();
                notice.setUserIds(inspectOrder.getChiefIds());
                notice.setStatus(NoticeStatusEnum.UNREAD.getCode());
                MaterialEntity material = materialDao.queryObject(inspectOrder.getMaterialId());
                notice.setCreateTime(date);
                notice.setName("异常-"+material.getMaterialName()+DateUtils.format(date,DateUtils.DATE_TIME_PATTERN));

                noticeDao.save(notice);
            }
        }
        return effectRows;
    }

    private void setInspectOrder(InspectOrderEntity entity){
        Date date = new Date();
        entity.setInspectTime(date);
//        AppUserEntity appUser = commonService.getCurrentLoginUser();
//        entity.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
//        entity.setUserName(appUser.getRealname());

        entity.setUserId(11);
        entity.setUserName("曹操");
        entity.setChiefName(commonService.getChiefNames(entity.getChiefIds()));
        entity.setCreateTime(date);
        entity.setDataStatus(DataStatusEnum.NORMAL.getCode());

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
//        AppUserEntity appUser =commonService.getCurrentLoginUser();
//        anomalyItem.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
//        anomalyItem.setUserName(appUser.getRealname());
        anomalyItem.setUserId(11);
        anomalyItem.setUserName("小六");
        if (StringUtils.isNotEmpty(ids)) {
            anomalyItem.setChiefIds(ids);
            anomalyItem.setChiefNames(commonService.getChiefNames(ids));
        }

        //处理异常
        return inspectOrderFlowDao.save(anomalyItem);
    }

    private int saveOrUpdateInspectOrder(Integer orderId,Integer materialId){
        InspectOrderEntity inspectOrder = inspectOrderDao.queryObject(orderId);
        int effectRows =0;
        Date currentDate = new Date();
        if (inspectOrder == null) {
            InspectOrderEntity entity = new InspectOrderEntity();
            entity.setMaterialId(materialId);
            MaterialEntity material = materialDao.queryObject(materialId);
            entity.setRegionId(material.getRegionId());
            entity.setStatus(InspectOrderStatusEnum.PENDING.getCode());
            entity.setInspectTime(currentDate);

//            AppUserEntity appUser = commonService.getCurrentLoginUser();
//
//            entity.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
//            entity.setUserName(appUser.getRealname());

            entity.setUserId(11);
            entity.setUserName("小六");
            entity.setInspectStatus(MaterialStatusEnum.ANOMALY.getCode());
            entity.setCreateTime(currentDate);
            entity.setDataStatus(DataStatusEnum.NORMAL.getCode());
            effectRows = inspectOrderDao.save(entity);
        }else {
            inspectOrder.setStatus(InspectOrderStatusEnum.REVIEW.getCode());
//            AppUserEntity appUser = commonService.getCurrentLoginUser();
            AppUserEntity appUser = appUserDao.queryObject(9L);

            inspectOrder.setUserId(Integer.valueOf(String.valueOf(appUser.getId())));
            inspectOrder.setUserName(appUser.getRealname());
            inspectOrder.setUpdateTime(currentDate);


            try {
                effectRows = inspectOrderDao.update(inspectOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return effectRows;
    }

}
