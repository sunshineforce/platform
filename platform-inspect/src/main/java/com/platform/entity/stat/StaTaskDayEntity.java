package com.platform.entity.stat;

import java.io.Serializable;


/**
 * 按天统计任务执行情况实体
 * 表名 sta_task_day
 *
 * @author admin
 *  
 * @date 2018-08-06 19:30:48
 */
public class StaTaskDayEntity implements Serializable {
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

    //待执行数量
    private Integer pendingNum;
    //执行中
    private Integer executingNum;
    //已完成
    private Integer finishNum;
    //超时数量
    private Integer timeoutNum;

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
     * 设置：待执行数量
     */
    public void setPendingNum(Integer pendingNum) {
        this.pendingNum = pendingNum;
    }

    /**
     * 获取：待执行数量
     */
    public Integer getPendingNum() {
        return pendingNum;
    }
    /**
     * 设置：执行中
     */
    public void setExecutingNum(Integer executingNum) {
        this.executingNum = executingNum;
    }

    /**
     * 获取：执行中
     */
    public Integer getExecutingNum() {
        return executingNum;
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
    /**
     * 设置：超时数量
     */
    public void setTimeoutNum(Integer timeoutNum) {
        this.timeoutNum = timeoutNum;
    }

    /**
     * 获取：超时数量
     */
    public Integer getTimeoutNum() {
        return timeoutNum;
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
