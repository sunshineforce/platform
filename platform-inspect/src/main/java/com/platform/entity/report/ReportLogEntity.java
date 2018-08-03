package com.platform.entity.report;

import java.io.Serializable;
import java.util.Date;


/**
 * 上报记录表实体
 * 表名 report_log
 *
 * @author admin
 *  
 * @date 2018-07-24 10:14:47
 */
public class ReportLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Long id;
    //事件编号
    private String eventNo;
    //推送结果(0:成功;1:失败;3:异常)
    private Integer result;
    //告警位置
    private String alarmLocation;
    //位置标签
    private String localtionTag;
    //设备类型
    private Integer deviceType;
    //客户id
    private Integer customerId;
    //发送时间/告警时间
    private Date sendTime;
    //所在楼层
    private String alarmFloor;
    //安装位置
    private String installLocal;
    //发起内容
    private String sendText;
    //上报类型
    private Integer classify;
    //创建时间
    private Date createTime;

    private String userName;

    private String mobile;

    private String materialTypeName;

    /**
     * 设置：主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：事件编号
     */
    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    /**
     * 获取：事件编号
     */
    public String getEventNo() {
        return eventNo;
    }
    /**
     * 设置：推送结果(0:成功;1:失败;3:异常)
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取：推送结果(0:成功;1:失败;3:异常)
     */
    public Integer getResult() {
        return result;
    }
    /**
     * 设置：告警位置
     */
    public void setAlarmLocation(String alarmLocation) {
        this.alarmLocation = alarmLocation;
    }

    /**
     * 获取：告警位置
     */
    public String getAlarmLocation() {
        return alarmLocation;
    }
    /**
     * 设置：位置标签
     */
    public void setLocaltionTag(String localtionTag) {
        this.localtionTag = localtionTag;
    }

    /**
     * 获取：位置标签
     */
    public String getLocaltionTag() {
        return localtionTag;
    }
    /**
     * 设置：设备类型
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取：设备类型
     */
    public Integer getDeviceType() {
        return deviceType;
    }
    /**
     * 设置：客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取：客户id
     */
    public Integer getCustomerId() {
        return customerId;
    }
    /**
     * 设置：发送时间/告警时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取：发送时间/告警时间
     */
    public Date getSendTime() {
        return sendTime;
    }
    /**
     * 设置：所在楼层
     */
    public void setAlarmFloor(String alarmFloor) {
        this.alarmFloor = alarmFloor;
    }

    /**
     * 获取：所在楼层
     */
    public String getAlarmFloor() {
        return alarmFloor;
    }
    /**
     * 设置：安装位置
     */
    public void setInstallLocal(String installLocal) {
        this.installLocal = installLocal;
    }

    /**
     * 获取：安装位置
     */
    public String getInstallLocal() {
        return installLocal;
    }
    /**
     * 设置：发起内容
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    /**
     * 获取：发起内容
     */
    public String getSendText() {
        return sendText;
    }
    /**
     * 设置：上报类型
     */
    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    /**
     * 获取：上报类型
     */
    public Integer getClassify() {
        return classify;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}
