package com.neusoft.ssm.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VisitItem {
    private Integer id;

    private Integer medicalRecordInfoId;

    private String type;

    private String purposeRequirement;

    private String status;

    private Date applicationTime;

    private Integer applicationDoctorId;

    private String feeStatus;

    private String executionStatus;

    private BigDecimal fee;

    public VisitItem(Integer id, Integer medicalRecordInfoId, String type, String purposeRequirement, String status, Date applicationTime, Integer applicationDoctorId, String feeStatus, String executionStatus, BigDecimal fee) {
        this.id = id;
        this.medicalRecordInfoId = medicalRecordInfoId;
        this.type = type;
        this.purposeRequirement = purposeRequirement;
        this.status = status;
        this.applicationTime = applicationTime;
        this.applicationDoctorId = applicationDoctorId;
        this.feeStatus = feeStatus;
        this.executionStatus = executionStatus;
        this.fee = fee;
    }

    public VisitItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordInfoId() {
        return medicalRecordInfoId;
    }

    public void setMedicalRecordInfoId(Integer medicalRecordInfoId) {
        this.medicalRecordInfoId = medicalRecordInfoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPurposeRequirement() {
        return purposeRequirement;
    }

    public void setPurposeRequirement(String purposeRequirement) {
        this.purposeRequirement = purposeRequirement == null ? null : purposeRequirement.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getApplicationDoctorId() {
        return applicationDoctorId;
    }

    public void setApplicationDoctorId(Integer applicationDoctorId) {
        this.applicationDoctorId = applicationDoctorId;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus == null ? null : feeStatus.trim();
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus == null ? null : executionStatus.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}