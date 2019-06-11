package edu.neu.medical.hospital.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Prescription {
    private Integer id;

    private Integer medicalRecordInfoId;

    private String type;

    private String prescriptionType;

    private String prescriptionName;

    private Date buildTime;

    private BigDecimal prescriptionInAmount;

    private BigDecimal prescriptionOutAmount;

    private String status;

    private String feeStatus;

    private String executionStatus;

    private Integer outNum;

    private Integer backNumber;

    public Prescription(Integer id, Integer medicalRecordInfoId, String type, String prescriptionType, String prescriptionName, Date buildTime, BigDecimal prescriptionInAmount, BigDecimal prescriptionOutAmount, String status, String feeStatus, String executionStatus, Integer outNum, Integer backNumber) {
        this.id = id;
        this.medicalRecordInfoId = medicalRecordInfoId;
        this.type = type;
        this.prescriptionType = prescriptionType;
        this.prescriptionName = prescriptionName;
        this.buildTime = buildTime;
        this.prescriptionInAmount = prescriptionInAmount;
        this.prescriptionOutAmount = prescriptionOutAmount;
        this.status = status;
        this.feeStatus = feeStatus;
        this.executionStatus = executionStatus;
        this.outNum = outNum;
        this.backNumber = backNumber;
    }

    public Prescription() {
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

    public String getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(String prescriptionType) {
        this.prescriptionType = prescriptionType == null ? null : prescriptionType.trim();
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName == null ? null : prescriptionName.trim();
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public BigDecimal getPrescriptionInAmount() {
        return prescriptionInAmount;
    }

    public void setPrescriptionInAmount(BigDecimal prescriptionInAmount) {
        this.prescriptionInAmount = prescriptionInAmount;
    }

    public BigDecimal getPrescriptionOutAmount() {
        return prescriptionOutAmount;
    }

    public void setPrescriptionOutAmount(BigDecimal prescriptionOutAmount) {
        this.prescriptionOutAmount = prescriptionOutAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(Integer backNumber) {
        this.backNumber = backNumber;
    }
}