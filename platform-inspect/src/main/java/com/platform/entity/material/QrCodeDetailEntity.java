package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;


/**
 * 二维码详情表实体
 * 表名 qr_code_detail
 *
 * @author admin
 *  
 * @date 2018-08-04 10:17:20
 */
public class QrCodeDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //申请id
    private Integer applyId;
    //物品id
    private Integer matetialId;
    //物品类型id
    private Integer materialTypeId;
    //绑定人id
    private Integer bindUserId;
    //绑定事件
    private Date bindTime;
    //二维码
    private String qrCode;
    //识别码
    private String idCode;


    //绑定人姓名
    private String bindUserName;

    //物品名称
    private String  matetialName;

    //物品类型名称
    private String materialTypeName;

    //申请批次号
    private String batchNo;

    //生成人员
    private String generateUserName;

    //生成时间
    private Date generateTime;



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
     * 设置：申请id
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取：申请id
     */
    public Integer getApplyId() {
        return applyId;
    }
    /**
     * 设置：物品id
     */
    public void setMatetialId(Integer matetialId) {
        this.matetialId = matetialId;
    }

    /**
     * 获取：物品id
     */
    public Integer getMatetialId() {
        return matetialId;
    }
    /**
     * 设置：物品类型id
     */
    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    /**
     * 获取：物品类型id
     */
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }
    /**
     * 设置：绑定人id
     */
    public void setBindUserId(Integer bindUserId) {
        this.bindUserId = bindUserId;
    }

    /**
     * 获取：绑定人id
     */
    public Integer getBindUserId() {
        return bindUserId;
    }
    /**
     * 设置：绑定事件
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    /**
     * 获取：绑定事件
     */
    public Date getBindTime() {
        return bindTime;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getBindUserName() {
        return bindUserName;
    }

    public void setBindUserName(String bindUserName) {
        this.bindUserName = bindUserName;
    }

    public String getMatetialName() {
        return matetialName;
    }

    public void setMatetialName(String matetialName) {
        this.matetialName = matetialName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getGenerateUserName() {
        return generateUserName;
    }

    public void setGenerateUserName(String generateUserName) {
        this.generateUserName = generateUserName;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
}
