package edu.neu.medical.hospital.bean;

import java.util.Date;

public class Diagnosis {
    private Integer id;

    private Integer medicalRecordInfoId;

    private Integer diseaseId;

    private String type;

    private String isNewMajorDiagnosis;

    private String isNewSuspect;

    private String isFinalDiagnosis;

    private Date dateOfOnset;

    private String category;

    public Diagnosis(Integer id, Integer medicalRecordInfoId, Integer diseaseId, String type, String isNewMajorDiagnosis, String isNewSuspect, String isFinalDiagnosis, Date dateOfOnset, String category) {
        this.id = id;
        this.medicalRecordInfoId = medicalRecordInfoId;
        this.diseaseId = diseaseId;
        this.type = type;
        this.isNewMajorDiagnosis = isNewMajorDiagnosis;
        this.isNewSuspect = isNewSuspect;
        this.isFinalDiagnosis = isFinalDiagnosis;
        this.dateOfOnset = dateOfOnset;
        this.category = category;
    }

    public Diagnosis() {
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

    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsNewMajorDiagnosis() {
        return isNewMajorDiagnosis;
    }

    public void setIsNewMajorDiagnosis(String isNewMajorDiagnosis) {
        this.isNewMajorDiagnosis = isNewMajorDiagnosis == null ? null : isNewMajorDiagnosis.trim();
    }

    public String getIsNewSuspect() {
        return isNewSuspect;
    }

    public void setIsNewSuspect(String isNewSuspect) {
        this.isNewSuspect = isNewSuspect == null ? null : isNewSuspect.trim();
    }

    public String getIsFinalDiagnosis() {
        return isFinalDiagnosis;
    }

    public void setIsFinalDiagnosis(String isFinalDiagnosis) {
        this.isFinalDiagnosis = isFinalDiagnosis == null ? null : isFinalDiagnosis.trim();
    }

    public Date getDateOfOnset() {
        return dateOfOnset;
    }

    public void setDateOfOnset(Date dateOfOnset) {
        this.dateOfOnset = dateOfOnset;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }
}