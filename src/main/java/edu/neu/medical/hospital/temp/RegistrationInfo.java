package edu.neu.medical.hospital.temp;

import java.math.BigDecimal;
import java.util.Date;

public class RegistrationInfo {
    private Integer id;

    private Integer medicalRecordNo;

    private String patientName;

    private String patientGender;

    private Integer age;

    private Date birthDate;

    private String settleAccountsCategory;

    private String registrationCategory;

    private String medicalCategory;

    private Integer departId;

    private Integer doctorId;

    private String doctorName;

    private Date registrationDate;

    private String status;

    private String registrationSource;

    private BigDecimal expense;

    private BigDecimal refundAmount;

    private Date seeDoctorDate;

    private String isSeenDocator;

    private String ifBook;

    public RegistrationInfo(Integer id, Integer medicalRecordNo, String patientName, String patientGender, Integer age, Date birthDate, String settleAccountsCategory, String registrationCategory, String medicalCategory, Integer departId, Integer doctorId, String doctorName, Date registrationDate, String status, String registrationSource, BigDecimal expense, BigDecimal refundAmount, Date seeDoctorDate, String isSeenDocator, String ifBook) {
        this.id = id;
        this.medicalRecordNo = medicalRecordNo;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.age = age;
        this.birthDate = birthDate;
        this.settleAccountsCategory = settleAccountsCategory;
        this.registrationCategory = registrationCategory;
        this.medicalCategory = medicalCategory;
        this.departId = departId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.registrationDate = registrationDate;
        this.status = status;
        this.registrationSource = registrationSource;
        this.expense = expense;
        this.refundAmount = refundAmount;
        this.seeDoctorDate = seeDoctorDate;
        this.isSeenDocator = isSeenDocator;
        this.ifBook = ifBook;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender == null ? null : patientGender.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSettleAccountsCategory() {
        return settleAccountsCategory;
    }

    public void setSettleAccountsCategory(String settleAccountsCategory) {
        this.settleAccountsCategory = settleAccountsCategory == null ? null : settleAccountsCategory.trim();
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRegistrationSource() {
        return registrationSource;
    }

    public void setRegistrationSource(String registrationSource) {
        this.registrationSource = registrationSource == null ? null : registrationSource.trim();
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

    public Date getSeeDoctorDate() {
        return seeDoctorDate;
    }

    public void setSeeDoctorDate(Date seeDoctorDate) {
        this.seeDoctorDate = seeDoctorDate;
    }

    public String getIsSeenDocator() {
        return isSeenDocator;
    }

    public void setIsSeenDocator(String isSeenDocator) {
        this.isSeenDocator = isSeenDocator == null ? null : isSeenDocator.trim();
    }

    public String getIfBook() {
        return ifBook;
    }

    public void setIfBook(String ifBook) {
        this.ifBook = ifBook == null ? null : ifBook.trim();
    }
}