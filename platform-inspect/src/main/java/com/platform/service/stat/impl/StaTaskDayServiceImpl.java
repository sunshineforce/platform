package com.platform.service.stat.impl;

import com.platform.cache.RegionCacheUtil;
import com.platform.dao.stat.StaTaskDayDao;
import com.platform.dao.task.TaskDao;
import com.platform.dao.task.TaskDetailDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.dto.StatDto;
import com.platform.entity.stat.StaTaskDayEntity;
import com.platform.service.stat.StaTaskDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 按天统计任务执行情况Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Service("staTaskDayService")
public class StaTaskDayServiceImpl implements StaTaskDayService {
    @Autowired
    private StaTaskDayDao staTaskDayDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskDetailDao taskDetailDao;

    @Override
    public StaTaskDayEntity queryObject(Integer id) {
        return staTaskDayDao.queryObject(id);
    }

    @Override
    public List<StaTaskDayEntity> queryList(Map<String, Object> map) {
        return staTaskDayDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return staTaskDayDao.queryTotal(map);
    }

    @Override
    public int save(StaTaskDayEntity staTaskDay) {
        return staTaskDayDao.save(staTaskDay);
    }

    @Override
    public int update(StaTaskDayEntity staTaskDay) {
        return staTaskDayDao.update(staTaskDay);
    }

    @Override
    public int delete(Integer id) {
        return staTaskDayDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return staTaskDayDao.deleteBatch(ids);
    }

    @Override
    public List<StaTaskDayEntity> statTask(Map<String, Object> map,List<SysRegionEntity> districtList) {
        List<StaTaskDayEntity> list = null;
        List<StatDto> statDtos = taskDetailDao.statTask(map);
        if (statDtos != null) {
            list = new ArrayList<>();

            for (SysRegionEntity region : districtList) {
                StaTaskDayEntity st = new StaTaskDayEntity();
                st.setCityId(Integer.parseInt(String.valueOf(map.get("cityId"))));
                st.setDistrictId(region.getId());
                for (StatDto statDto : statDtos) {
                    if (null != statDto.getRegionId() && inRegion(region.getId().intValue(),statDto.getRegionId().intValue())){
                        if (null != statDto.getStatus()){
                            switch (statDto.getStatus().intValue()){
                                case 0 : st.setPendingNum(statDto.getNum()); break;
                                case 1 : st.setExecutingNum(statDto.getNum()); break;
                                case 2 : st.setFinishNum(statDto.getNum()); break;
                                case 3 : st.setTimeoutNum(statDto.getNum()); break;
                            }
                        }
                    }
                }
                list.add(st);
            }

        }

        return list;
    }

    private boolean inRegion(int districtId, int regionId ){
        boolean rs = false;
        List<Integer> regionEntities = RegionCacheUtil.getRegionIdList(districtId,RegionCacheUtil.DISTRICT_TYPE);
        if (null != regionEntities && regionEntities.size() > 0){
            for (Integer id : regionEntities) {
                if (regionId == id.intValue()){
                    rs = true;
                    break;
                }
            }
        }
        return rs;
    }
}
