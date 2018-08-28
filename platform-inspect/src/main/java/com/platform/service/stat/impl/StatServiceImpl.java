package com.platform.service.stat.impl;

import com.platform.cache.RegionCacheUtil;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.stat.StaExceptionDayEntity;
import com.platform.entity.stat.StaTaskDayEntity;
import com.platform.service.stat.IStatService;
import com.platform.service.stat.StaExceptionDayService;
import com.platform.service.stat.StaTaskDayService;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2018-08-19 10:02
 **/
@Service
public class StatServiceImpl implements IStatService {

    @Autowired
    private StaExceptionDayService staExceptionDayService;

    @Autowired
    private StaTaskDayService staTaskDayService;


    @Override
    public R stat(Map<String, Object> params) {
        String startTime = String.valueOf(params.get("startTime"));
        String endTime = String.valueOf(params.get("endTime"));
        int regionId = 12718; // 默认是杭州市
        int type = RegionCacheUtil.CITY_TYPE; //默认城市类型
        String regions = null;
        List<Integer> regionIdList = null;
        List<SysRegionEntity> districtList = null;
        if (null != params.get("regions")){
            regions =String.valueOf(params.get("regions"));
            String[] regionArr = regions.split(",");
            regionId = Integer.parseInt(regionArr[regionArr.length - 1]);
            switch (regionArr.length){
                case 1 :
                    districtList = RegionCacheUtil.getChildrenDistrict(regionId);
                    type = RegionCacheUtil.CITY_TYPE; break;
                case 2 :
                    districtList = RegionCacheUtil.getChildrenByParentId(regionId);
                    type = RegionCacheUtil.DISTRICT_TYPE; break;
                case 3 :
                    districtList = new ArrayList<>();
                    districtList.add(RegionCacheUtil.getAreaByAreaId(regionId));
                    type = 4; break; //自身
            }

            if (type == 4){
                regionIdList = new ArrayList<>();
                regionIdList.add(regionId);
            }else {
                regionIdList = RegionCacheUtil.getRegionIdList(regionId, type);
            }
        }


        List<StaTaskDayEntity> tasks = null;
        List<StaExceptionDayEntity> orders = null;


        if (null != districtList && districtList.size() > 0){
            params.put("cityId",regionId);
            if (null != regionIdList){params.put("regionIdList",regionIdList);}
            params.put("startTime", startTime);
            params.put("endTime",endTime);
            orders = staExceptionDayService.statExceptionOrder(params,districtList);
            tasks = staTaskDayService.statTask(params,districtList);
        }


        List<Map<String,Object>> tasksSeries = getTaskSeries(districtList,tasks);
        List<Map<String,Object>> pendingData = getOrderSeries(0,districtList,orders);
        List<Map<String,Object>> reviewData = getOrderSeries(1,districtList,orders);
        List<Map<String,Object>> finishData = getOrderSeries(2,districtList,orders);

        return R.ok()
                .put("tasksSeries", tasksSeries)
                .put("pendingData",pendingData)
                .put("reviewData",reviewData)
                .put("finishData",finishData)
                .put("xData",getXDatas(districtList));
    }

    private List<Integer> getIds(List<SysRegionEntity> districtList){
        List<Integer> ids = new ArrayList<>();
        for (SysRegionEntity sysRegionEntity : districtList) {
            ids.add(sysRegionEntity.getId());
        }
        return ids;
    }


    private List<String> getXDatas(List<SysRegionEntity> districtList){
        List<String> list = new ArrayList<>();
        if (null != districtList && districtList.size() > 0 ){
            for (SysRegionEntity r : districtList) {
                list.add(r.getName());
            }
        }
        return list;
    }


    private List<Map<String,Object>> getOrderSeries(int type , List<SysRegionEntity> districtList ,List<StaExceptionDayEntity> orders){
        List<Map<String,Object>> orderSeries = new ArrayList<>();
        if (null != orders && orders.size() > 0 && null != districtList && districtList.size() > 0){
            Map<String,Object> m  = null;
            for (SysRegionEntity r : districtList) {
                m  = new HashMap<>();
                int num = 0;
                m.put("name",r.getName());
               if (null != orders){
                   for (StaExceptionDayEntity order : orders) {
                       if (order.getDistrictId() != null &&  r.getId().intValue() == order.getDistrictId().intValue()){
                           switch (type){
                               case 0 : num += order.getPendingNum(); break;
                               case 1 : num += order.getReviewNum();break;
                               case 2 : num += order.getFinishNum();break;
                           }



                       }
                   }
               }
                m.put("value",num);


                orderSeries.add(m);
            }
        }
        return orderSeries;
    }


    private List<Map<String,Object>> getTaskSeries(List<SysRegionEntity> districtList , List<StaTaskDayEntity> tasks){
        List<Map<String,Object>> tasksSeries = new ArrayList<>();
        if (null != tasks && tasks.size() > 0 && null != districtList && districtList.size() > 0){
            Map<String,Object> m1 = new HashMap<>();
            Map<String,Object> m2 = new HashMap<>();
            Map<String,Object> m3 = new HashMap<>();
            Map<String,Object> m4 = new HashMap<>();

            int []d1 = new int[districtList.size()];
            int []d2 = new int[districtList.size()];
            int []d3 = new int[districtList.size()];
            int []d4 = new int[districtList.size()];

            for (int i = 0 ; i < districtList.size(); i++) {
                boolean has = false;
                for (StaTaskDayEntity  task : tasks) {
                    if (task.getDistrictId() != null && districtList.get(i).getId().intValue() == task.getDistrictId().intValue()){
                        d1[i] = task.getPendingNum() != null ? task.getPendingNum() : 0 ;
                        d2[i] = task.getExecutingNum() != null ? task.getExecutingNum() : 0 ;
                        d3[i] = task.getFinishNum() != null ? task.getFinishNum() : 0;
                        d4[i] = task.getTimeoutNum() != null ? task.getTimeoutNum() : 0;
                        has = true;
                        break;
                    }

                }

                if (!has){
                    d1[i] = 0;
                    d2[i] = 0;
                    d3[i] = 0;
                    d4[i] = 0;
                }
            }

            //'待执行', '执行中', '已完成', '已超时'
            m1.put("name","待执行");
            m1.put("type","bar");
            m1.put("data",d1);

            m2.put("name","执行中");
            m2.put("type","bar");
            m2.put("data",d2);

            m3.put("name","已完成");
            m3.put("type","bar");
            m3.put("data",d3);

            m4.put("name","已超时");
            m4.put("type","bar");
            m4.put("data",d4);

            tasksSeries.add(m1);
            tasksSeries.add(m2);
            tasksSeries.add(m3);
            tasksSeries.add(m4);

        }
        return tasksSeries;
    }
}
