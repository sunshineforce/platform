package com.platform.service.stat.impl;

import com.platform.dao.inspect.InspectOrderDao;
import com.platform.dao.stat.StaExceptionDayDao;
import com.platform.entity.SysRegionEntity;
import com.platform.entity.dto.StatDto;
import com.platform.entity.stat.StaExceptionDayEntity;
import com.platform.service.stat.StaExceptionDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * 异常处理统计Service实现类
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
@Service("staExceptionDayService")
public class StaExceptionDayServiceImpl implements StaExceptionDayService {
    @Autowired
    private StaExceptionDayDao staExceptionDayDao;

    @Autowired
    private InspectOrderDao inspectOrderDao;

    @Override
    public StaExceptionDayEntity queryObject(Integer id) {
        return staExceptionDayDao.queryObject(id);
    }

    @Override
    public List<StaExceptionDayEntity> queryList(Map<String, Object> map) {
        return staExceptionDayDao.queryList(map);
    }

    @Override
    public List<StaExceptionDayEntity> statExceptionOrder(Map<String, Object> map, List<SysRegionEntity> districtList) {
        List<StaExceptionDayEntity> list = null;
        List<StatDto> statDtos = inspectOrderDao.statExceptionOrder(map);
        if (statDtos != null) {
            list = new ArrayList<>();
            for (SysRegionEntity region : districtList) {
                StaExceptionDayEntity st = new StaExceptionDayEntity();
                st.setCityId(Integer.parseInt(String.valueOf(map.get("cityId"))));
                st.setDistrictId(region.getId());
                for (StatDto statDto : statDtos) {
                    if (null != statDto.getRegionId() && statDto.getRegionId().intValue() == region.getId().intValue()){
                        if (null != statDto.getStatus()){
                            switch (statDto.getStatus().intValue()){
                                case 0 : st.setPendingNum(statDto.getNum()); break;
                                case 1 : st.setReviewNum(statDto.getNum()); break;
                                case 2 : st.setFinishNum(statDto.getNum()); break;
                            }
                        }


                    }

                }
                list.add(st);
            }
        }

        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return staExceptionDayDao.queryTotal(map);
    }

    @Override
    public int save(StaExceptionDayEntity staExceptionDay) {
        return staExceptionDayDao.save(staExceptionDay);
    }

    @Override
    public int update(StaExceptionDayEntity staExceptionDay) {
        return staExceptionDayDao.update(staExceptionDay);
    }

    @Override
    public int delete(Integer id) {
        return staExceptionDayDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return staExceptionDayDao.deleteBatch(ids);
    }
}
