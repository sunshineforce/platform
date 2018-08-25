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
    //企业Id
    private Integer enterpriseId;
    //申请批次号
    private String batchNo;
    //二维码生成数量
    private Integer quantity;
    //二维前缀
    private String prefix;
    //申请人
    private Long applicant;
    //申请时间
    private Date applyDate;
    //状态（0：审核中；1：已发放 2 已驳回）
    private Integer qrCodeStatus;
    //驳回原因
    private String rejectReason;
    //生成人员
    private Long generateMan;
    //生成时间
    private Date generateDate;
    //创建时间
    private Date createDate;

    private String applicantName;

    private String enterpriseName;

    private Integer isGenerated; // 是否已生成  0 为生成 1 已生成


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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getApplicant() {
        return applicant;
    }

    public void setApplicant(Long applicant) {
        this.applicant = applicant;
    }

    public Long getGenerateMan() {
        return generateMan;
    }

    public void setGenerateMan(Long generateMan) {
        this.generateMan = generateMan;
    }

    public Integer getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(Integer isGenerated) {
        this.isGenerated = isGenerated;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
