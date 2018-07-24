package com.platform.entity.material;

import java.io.Serializable;
import java.util.Date;


/**
 * 二维码申请表实体
 * 表名 qr_code_apply
 *
 * @author admin
 *  
 * @date 2018-07-24 10:41:09
 */
public class QrCodeApplyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键Id
    private Integer id;
    //二维码类型（0：物品二维码；1：企业二维码）
    private Integer qrCodeType;
    //申请批次号
    private String batchNo;
    //二维码生成数量
    private Integer quantity;
    //二维前缀
    private String prefix;
    //申请人
    private Integer applicant;
    //申请时间
    private Date applyDate;
    //状态（0：审核中；1：已发放）
    private Integer qrCodeStatus;
    //驳回原因
    private String rejectReason;
    //生成人员
    private Integer generateMan;
    //生成时间
    private Date generateDate;
    //绑定人员
    private Integer bindMan;
    //绑定时间
    private Date bindDate;
    //物料Id
    private Integer matetialId;
    //物品类型
    private Integer materialTypeId;
    //创建时间
    private Date createDate;

    /**
     * 设置：主键Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键Id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：二维码类型（0：物品二维码；1：企业二维码）
     */
    public void setQrCodeType(Integer qrCodeType) {
        this.qrCodeType = qrCodeType;
    }

    /**
     * 获取：二维码类型（0：物品二维码；1：企业二维码）
     */
    public Integer getQrCodeType() {
        return qrCodeType;
    }
    /**
     * 设置：申请批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 获取：申请批次号
     */
    public String getBatchNo() {
        return batchNo;
    }
    /**
     * 设置：二维码生成数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取：二维码生成数量
     */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     * 设置：二维前缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取：二维前缀
     */
    public String getPrefix() {
        return prefix;
    }
    /**
     * 设置：申请人
     */
    public void setApplicant(Integer applicant) {
        this.applicant = applicant;
    }

    /**
     * 获取：申请人
     */
    public Integer getApplicant() {
        return applicant;
    }
    /**
     * 设置：申请时间
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取：申请时间
     */
    public Date getApplyDate() {
        return applyDate;
    }
    /**
     * 设置：状态（0：审核中；1：已发放）
     */
    public void setQrCodeStatus(Integer qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    /**
     * 获取：状态（0：审核中；1：已发放）
     */
    public Integer getQrCodeStatus() {
        return qrCodeStatus;
    }
    /**
     * 设置：驳回原因
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    /**
     * 获取：驳回原因
     */
    public String getRejectReason() {
        return rejectReason;
    }
    /**
     * 设置：生成人员
     */
    public void setGenerateMan(Integer generateMan) {
        this.generateMan = generateMan;
    }

    /**
     * 获取：生成人员
     */
    public Integer getGenerateMan() {
        return generateMan;
    }
    /**
     * 设置：生成时间
     */
    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    /**
     * 获取：生成时间
     */
    public Date getGenerateDate() {
        return generateDate;
    }
    /**
     * 设置：绑定人员
     */
    public void setBindMan(Integer bindMan) {
        this.bindMan = bindMan;
    }

    /**
     * 获取：绑定人员
     */
    public Integer getBindMan() {
        return bindMan;
    }
    /**
     * 设置：绑定时间
     */
    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }

    /**
     * 获取：绑定时间
     */
    public Date getBindDate() {
        return bindDate;
    }
    /**
     * 设置：物料Id
     */
    public void setMatetialId(Integer matetialId) {
        this.matetialId = matetialId;
    }

    /**
     * 获取：物料Id
     */
    public Integer getMatetialId() {
        return matetialId;
    }
    /**
     * 设置：物品类型
     */
    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    /**
     * 获取：物品类型
     */
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }
}
