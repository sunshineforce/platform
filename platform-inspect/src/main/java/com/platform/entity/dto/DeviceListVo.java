package com.platform.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/8/25 15:54
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */


public class DeviceListVo implements Serializable {

    private static final long serialVersionUID = -2068255861107400778L;

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;
    private List<DeviceVo> records;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public List<DeviceVo> getRecords() {
        return records;
    }

    public void setRecords(List<DeviceVo> records) {
        this.records = records;
    }
}
