package com.platform.service.common;

import com.platform.dao.SysRegionDao;
import com.platform.entity.SysRegionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonService {
    @Autowired
    private SysRegionDao regionDao;

    /**
     * 获取某个区域的全名，自动拼接上上级区域名称
     * @return
     */
    public String getRegionName(Integer regionId) {
        String regionName = "";
        if (regionId == null) {
            return regionName;
        }
        SysRegionEntity region = regionDao.queryObject(regionId);

        if(region != null) {
            if (region.getParentId() != 0) {
                regionName =  getRegionName(region.getParentId()) + region.getName();  //  递归调用方法getRegionString(Long regionId)，停止条件设为regionId==null为真
            }
        }
        regionName = regionName.replace("市辖区","");
        return regionName;
    }
}
