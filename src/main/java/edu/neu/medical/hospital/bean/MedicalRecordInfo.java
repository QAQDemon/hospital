package edu.neu.medical.hospital.bean;

import java.util.Date;

public class MedicalRecordInfo {
    @Override
    public String toString() {
        return "MedicalRecordInfo{" +
                "id=" + id +
                ", medicalRecordNo=" + medicalRecordNo +
                ", registrationId=" + registrationId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", chiefComplaint='" + chiefComplaint + '\'' +
                ", currentMedicalHistory='" + currentMedicalHistory + '\'' +
                ", currentTreatmentSituation='" + currentTreatmentSituation + '\'' +
                ", pastHistory='" + pastHistory + '\'' +
                ", allergiesHistory='" + allergiesHistory + '\'' +
                ", physicalExamination='" + physicalExamination + '\'' +
                ", status='" + status + '\'' +
                ", dateOfOnset=" + dateOfOnset +
                ", visitTime=" + visitTime +
                ", visitDepartName='" + visitDepartName + '\'' +
                ", visitDoctorName='" + visitDoctorName + '\'' +
                '}';
    }

    private Integer id;

    private Integer medicalRecordNo;

    private Integer registrationId;

    private Integer patientId;

    private Integer doctorId;

    private String chiefComplaint;

    private String currentMedicalHistory;

    private String currentTreatmentSituation;

    private String pastHistory;

    private String allergiesHistory;

    private String physicalExamination;

    private String status;

    private Date dateOfOnset;

    private Date visitTime;

    private String visitDepartName;

    private String visitDoctorName;

    public MedicalRecordInfo(Integer id, Integer medicalRecordNo, Integer registrationId, Integer patientId, Integer doctorId, String chiefComplaint, String currentMedicalHistory, String currentTreatmentSituation, String pastHistory, String allergiesHistory, String physicalExamination, String status, Date dateOfOnset, Date visitTime, String visitDepartName, String visitDoctorName) {
        this.id = id;
        this.medicalRecordNo = medicalRecordNo;
        this.registrationId = registrationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.chiefComplaint = chiefComplaint;
        this.currentMedicalHistory = currentMedicalHistory;
        this.currentTreatmentSituation = currentTreatmentSituation;
        this.pastHistory = pastHistory;
        this.allergiesHistory = allergiesHistory;
        this.physicalExamination = physicalExamination;
        this.status = status;
        this.dateOfOnset = dateOfOnset;
        this.visitTime = visitTime;
        this.visitDepartName = visitDepartName;
        this.visitDoctorName = visitDoctorName;
    }

    public MedicalRecordInfo() {
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

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint == null ? null : chiefComplaint.trim();
    }

    public String getCurrentMedicalHistory() {
        return currentMedicalHistory;
    }

    public void setCurrentMedicalHistory(String currentMedicalHistory) {
        this.currentMedicalHistory = currentMedicalHistory == null ? null : currentMedicalHistory.trim();
    }

    public String getCurrentTreatmentSituation() {
        return currentTreatmentSituation;
    }

    public void setCurrentTreatmentSituation(String currentTreatmentSituation) {
        this.currentTreatmentSituation = currentTreatmentSituation == null ? null : currentTreatmentSituation.trim();
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory == null ? null : pastHistory.trim();
    }

    public String getAllergiesHistory() {
        return allergiesHistory;
    }

    public void setAllergiesHistory(String allergiesHistory) {
        this.allergiesHistory = allergiesHistory == null ? null : allergiesHistory.trim();
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination == null ? null : physicalExamination.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDateOfOnset() {
        return dateOfOnset;
    }

    public void setDateOfOnset(Date dateOfOnset) {
        this.dateOfOnset = dateOfOnset;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitDepartName() {
        return visitDepartName;
    }

    public void setVisitDepartName(String visitDepartName) {
        this.visitDepartName = visitDepartName == null ? null : visitDepartName.trim();
    }

    public String getVisitDoctorName() {
        return visitDoctorName;
    }

    public void setVisitDoctorName(String visitDoctorName) {
        this.visitDoctorName = visitDoctorName == null ? null : visitDoctorName.trim();
    }
}