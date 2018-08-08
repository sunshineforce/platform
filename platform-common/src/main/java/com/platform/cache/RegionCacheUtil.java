package com.platform.cache;

import com.platform.dao.SysRegionDao;
import com.platform.entity.SysRegionEntity;
import com.platform.utils.SpringContextUtils;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author bjsonghongxu
 * @date 2017-11-04 11:19:31
 */
public class RegionCacheUtil implements InitializingBean {

    public static List<SysRegionEntity> sysRegionEntityList;

    public static final int COUNTRY_TYPE = 0; //国家类型
    public static final int PROVINCE_TYPE = 1; //省份类型
    public static final int CITY_TYPE = 2; //城市类型
    public static final int DISTRICT_TYPE = 3; //区县类型



    public static void init() {
        SysRegionDao regionDao = SpringContextUtils.getBean(SysRegionDao.class);
        if (null != regionDao) {
            sysRegionEntityList = regionDao.queryList(new HashMap<String, Object>());
        }
    }


    /**
     * 根据类型及所传id，取出Ids
     * @param regionId
     * @param type
     * @return
     */
    public static List<Integer> getRegionIdList(Integer regionId , int type){
        List<Integer> list = new ArrayList<>();
        List<SysRegionEntity> regionList = getSysRegionEntityList(regionId, type);
        if (null != regionId && regionList.size() > 0){
            for (SysRegionEntity region : regionList) {
                list.add(region.getId());
            }
        }
        return list;
    }

    /**
     * 根据类型及所传id，取出集合
     * @param regionId
     * @param type
     * @return
     */
    public static List<SysRegionEntity> getSysRegionEntityList( Integer regionId , int type){
        List<SysRegionEntity> regionList = new ArrayList<>();
        SysRegionEntity regionEntity = getAreaByAreaId(regionId);
        regionList.add(regionEntity);

        if (RegionCacheUtil.COUNTRY_TYPE == type){getRegionListByProvinceType(regionList,regionId,type);}
        if (RegionCacheUtil.PROVINCE_TYPE == type){getRegionListByCityType(regionList,regionId,type);}
        if (RegionCacheUtil.CITY_TYPE == type){getRegionListByDistrictType(regionList,regionId,type);}
        if (RegionCacheUtil.DISTRICT_TYPE == type){getRegionListByType(regionList,regionId,type);}
        return regionList;
    }


    private static void getRegionListByProvinceType( List<SysRegionEntity> regionList , Integer regionId , int type){
        if (RegionCacheUtil.COUNTRY_TYPE == type){ // 国家
            List<SysRegionEntity> provinceList = getAllProviceByParentId(regionId);
            if (null != provinceList && provinceList.size() > 0){
                regionList.addAll(provinceList);
                for (SysRegionEntity province : provinceList) {
                    getRegionListByCityType(regionList,province.getId(),RegionCacheUtil.PROVINCE_TYPE);
                }
            }
        }
    }

    private static void getRegionListByCityType( List<SysRegionEntity> regionList , Integer regionId , int type){
        if (RegionCacheUtil.PROVINCE_TYPE == type){
            // 城市
            List<SysRegionEntity> cityList = getChildrenCity(regionId);
            if (null != cityList){
                regionList.addAll(cityList);
                for (SysRegionEntity city : cityList) {
                    getRegionListByDistrictType(regionList,city.getId(),RegionCacheUtil.CITY_TYPE);
                }
            }

        }
    }

    private static void getRegionListByDistrictType( List<SysRegionEntity> regionList , Integer regionId , int type){
        if (RegionCacheUtil.CITY_TYPE == type){
            // 区县
            List<SysRegionEntity> districtList = getChildrenDistrict(regionId);
            if (null != districtList && districtList.size() > 0){
                regionList.addAll(districtList);
                for (SysRegionEntity district : districtList) {
                    getRegionListByType(regionList,district.getId(),RegionCacheUtil.DISTRICT_TYPE);
                }
            }

        }
    }

    private static void getRegionListByType( List<SysRegionEntity> regionList , Integer regionId , int type){
        if (RegionCacheUtil.DISTRICT_TYPE == type) {
            List<SysRegionEntity> areas = getChildrenByParentId(regionId);
            if (null != areas && areas.size() > 0) {
                regionList.addAll(areas);
            }
        }

    }

    /**
     * 获取所有国家
     *
     * @return
     */
    public static List getAllCountry() {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(0)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取全部省份
     *
     * @return
     */
    public static List getAllProvice() {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(1)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取所有城市
     *
     * @return
     */
    public static List<SysRegionEntity> getAllCity() {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(2)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据国家获取全部省份
     *
     * @return
     */
    public static List<SysRegionEntity> getAllProviceByParentId(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(1) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public static List getChildrenCity(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public static List getChildrenCity(String proviceName) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == proviceName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && proviceName.equals(areaVo.getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenDistrict(Integer areaId) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenDistrict(String provinceName, String cityName) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == provinceName || null == cityName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3)
                        && cityName.equals(areaVo.getParentName())
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && provinceName.equals(getAreaByAreaId(areaVo.getParentId()).getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }


    /**
     * 获取区域
     *
     * @return
     */
    public static List<SysRegionEntity> getChildrenByParentId(Integer parentId) {
        List<SysRegionEntity> resultObj = new ArrayList();
        if (null == parentId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && parentId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区域名称
     *
     * @return
     */
    public static String getAreaNameByAreaId(Integer areaId) {
        if (null == areaId) {
            return "";
        }
        String resultObj = areaId.toString();
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo.getName();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static SysRegionEntity getAreaByAreaId(Integer areaId) {
        SysRegionEntity resultObj = null;
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo;
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getProvinceIdByName(String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 1 && areaVo.getName().equals(areaName)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getCityIdByName(Integer provinceId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 2 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }


    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getDistrictIdByName(Integer provinceId, Integer cityId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionEntity areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 3 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(cityId)
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && null != getAreaByAreaId(areaVo.getParentId()).getParentId()
                        && getAreaByAreaId(areaVo.getParentId()).getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}