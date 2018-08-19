package com.platform.entity.stat;

import java.io.Serializable;


/**
 * 异常处理统计实体
 * 表名 sta_exception_day
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
public class StaExceptionDayEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //统计日期
    private Integer statDate;
    //省份
    private Integer provinceId;
    //城市
    private Integer cityId;
    //区县id
    private Integer districtId;
    //待处理
    private Integer pendingNum = 0;
    //待复查数量
    private Integer reviewNum = 0;
    //已完成
    private Integer finishNum = 0;

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：统计日期
     */
    public void setStatDate(Integer statDate) {
        this.statDate = statDate;
    }

    /**
     * 获取：统计日期
     */
    public Integer getStatDate() {
        return statDate;
    }
    /**
     * 设置：待处理
     */
    public void setPendingNum(Integer pendingNum) {
        this.pendingNum = pendingNum;
    }

    /**
     * 获取：待处理
     */
    public Integer getPendingNum() {
        return pendingNum;
    }
    /**
     * 设置：待复查数量
     */
    public void setReviewNum(Integer reviewNum) {
        this.reviewNum = reviewNum;
    }

    /**
     * 获取：待复查数量
     */
    public Integer getReviewNum() {
        return reviewNum;
    }
    /**
     * 设置：已完成
     */
    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    /**
     * 获取：已完成
     */
    public Integer getFinishNum() {
        return finishNum;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
