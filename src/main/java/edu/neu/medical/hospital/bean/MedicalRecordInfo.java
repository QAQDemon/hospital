package edu.neu.medical.hospital.bean;

import java.util.Date;

public class MedicalRecordInfo {
    @Override
    public String toString() {
        return "MedicalRecordInfo{" +
                "id=" + id +
                ", medicalRecordNo=" + medicalRecordNo +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", departId=" + departId +
                ", chiefComplaint='" + chiefComplaint + '\'' +
                ", currentMedicalHistory='" + currentMedicalHistory + '\'' +
                ", currentTreatmentSituation='" + currentTreatmentSituation + '\'' +
                ", pastHistory='" + pastHistory + '\'' +
                ", allergiesHistory='" + allergiesHistory + '\'' +
                ", physicalExamination='" + physicalExamination + '\'' +
                ", status='" + status + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }

    private Integer id;

    private Integer medicalRecordNo;

    private Integer patientId;

    private Integer doctorId;

    private Integer departId;

    private String chiefComplaint;

    private String currentMedicalHistory;

    private String currentTreatmentSituation;

    private String pastHistory;

    private String allergiesHistory;

    private String physicalExamination;

    private String status;

    private Date visitTime;

    public MedicalRecordInfo(Integer id, Integer medicalRecordNo, Integer patientId, Integer doctorId, Integer departId, String chiefComplaint, String currentMedicalHistory, String currentTreatmentSituation, String pastHistory, String allergiesHistory, String physicalExamination, String status, Date visitTime) {
        this.id = id;
        this.medicalRecordNo = medicalRecordNo;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.departId = departId;
        this.chiefComplaint = chiefComplaint;
        this.currentMedicalHistory = currentMedicalHistory;
        this.currentTreatmentSituation = currentTreatmentSituation;
        this.pastHistory = pastHistory;
        this.allergiesHistory = allergiesHistory;
        this.physicalExamination = physicalExamination;
        this.status = status;
        this.visitTime = visitTime;
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

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
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

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}