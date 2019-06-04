package edu.neu.medical.hospital.bean;

public class MedrecTemplate {
    @Override
    public String toString() {
        return "MedrecTemplate{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                ", category='" + category + '\'' +
                ", belongId=" + belongId +
                ", chiefComplaint='" + chiefComplaint + '\'' +
                ", currentMedicalHistory='" + currentMedicalHistory + '\'' +
                ", currentTreatmentSituation='" + currentTreatmentSituation + '\'' +
                ", pastHistory='" + pastHistory + '\'' +
                ", allergiesHistory='" + allergiesHistory + '\'' +
                ", physicalExamination='" + physicalExamination + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    private Integer id;

    private String templateName;

    private String category;

    private Integer belongId;

    private String chiefComplaint;

    private String currentMedicalHistory;

    private String currentTreatmentSituation;

    private String pastHistory;

    private String allergiesHistory;

    private String physicalExamination;

    private String status;

    public MedrecTemplate(Integer id, String templateName, String category, Integer belongId, String chiefComplaint, String currentMedicalHistory, String currentTreatmentSituation, String pastHistory, String allergiesHistory, String physicalExamination, String status) {
        this.id = id;
        this.templateName = templateName;
        this.category = category;
        this.belongId = belongId;
        this.chiefComplaint = chiefComplaint;
        this.currentMedicalHistory = currentMedicalHistory;
        this.currentTreatmentSituation = currentTreatmentSituation;
        this.pastHistory = pastHistory;
        this.allergiesHistory = allergiesHistory;
        this.physicalExamination = physicalExamination;
        this.status = status;
    }

    public MedrecTemplate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
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
}