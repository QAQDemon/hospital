package edu.neu.medical.hospital.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RegistrationInfo {
    private Integer id;

    private Integer medicalRecordNo;

    private String patientId;

    private String registrationCategory;

    private String medicalCategory;

    private Date registrationDate;

    private Date seeDoctorDate;

    private Integer departId;

    private Integer doctorId;

    private String registrationSource;

    private String settleAccountsCategory;

    private String isSeenDocator;

    private String status;

    private BigDecimal expense;

    private BigDecimal refundAmount;

    public RegistrationInfo(Integer id, Integer medicalRecordNo, String patientId, String registrationCategory, String medicalCategory, Date registrationDate, Date seeDoctorDate, Integer departId, Integer doctorId, String registrationSource, String settleAccountsCategory, String isSeenDocator, String status, BigDecimal expense, BigDecimal refundAmount) {
        this.id = id;
        this.medicalRecordNo = medicalRecordNo;
        this.patientId = patientId;
        this.registrationCategory = registrationCategory;
        this.medicalCategory = medicalCategory;
        this.registrationDate = registrationDate;
        this.seeDoctorDate = seeDoctorDate;
        this.departId = departId;
        this.doctorId = doctorId;
        this.registrationSource = registrationSource;
        this.settleAccountsCategory = settleAccountsCategory;
        this.isSeenDocator = isSeenDocator;
        this.status = status;
        this.expense = expense;
        this.refundAmount = refundAmount;
    }

    public RegistrationInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordNo() {
        return medicalRecordNo;
    }

    public void setMedicalRecordNo(Integer medicalRecordNo) {
        this.medicalRecordNo = medicalRecordNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getRegistrationCategory() {
        return registrationCategory;
    }

    public void setRegistrationCategory(String registrationCategory) {
        this.registrationCategory = registrationCategory == null ? null : registrationCategory.trim();
    }

    public String getMedicalCategory() {
        return medicalCategory;
    }

    public void setMedicalCategory(String medicalCategory) {
        this.medicalCategory = medicalCategory == null ? null : medicalCategory.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getSeeDoctorDate() {
        return seeDoctorDate;
    }

    public void setSeeDoctorDate(Date seeDoctorDate) {
        this.seeDoctorDate = seeDoctorDate;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getRegistrationSource() {
        return registrationSource;
    }

    public void setRegistrationSource(String registrationSource) {
        this.registrationSource = registrationSource == null ? null : registrationSource.trim();
    }

    public String getSettleAccountsCategory() {
        return settleAccountsCategory;
    }

    public void setSettleAccountsCategory(String settleAccountsCategory) {
        this.settleAccountsCategory = settleAccountsCategory == null ? null : settleAccountsCategory.trim();
    }

    public String getIsSeenDocator() {
        return isSeenDocator;
    }

    public void setIsSeenDocator(String isSeenDocator) {
        this.isSeenDocator = isSeenDocator == null ? null : isSeenDocator.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
}