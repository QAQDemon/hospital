package edu.neu.medical.hospital.bean;

public class MedrecTemplate {
    private Integer id;

    private String templateName;

    private String category;

    private Integer belongId;

    private String chiefComplaint;

    private String currentMedicalHistory;

    private String physicalExamination;

    private String status;

    private String templateCode;

    private Integer createrId;

    public MedrecTemplate(Integer id, String templateName, String category, Integer belongId, String chiefComplaint, String currentMedicalHistory, String physicalExamination, String status, String templateCode, Integer createrId) {
        this.id = id;
        this.templateName = templateName;
        this.category = category;
        this.belongId = belongId;
        this.chiefComplaint = chiefComplaint;
        this.currentMedicalHistory = currentMedicalHistory;
        this.physicalExamination = physicalExamination;
        this.status = status;
        this.templateCode = templateCode;
        this.createrId = createrId;
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

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }
}