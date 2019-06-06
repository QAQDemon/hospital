package edu.neu.medical.hospital.bean;

public class VisitItemDetail {
    private Integer id;

    private Integer visitItemId;

    private Integer fmeditemId;

    private String doctorEntrustment;

    private String addItem;

    private Integer executionDoctorId;

    private String executionStatus;

    public VisitItemDetail(Integer id, Integer visitItemId, Integer fmeditemId, String doctorEntrustment, String addItem, Integer executionDoctorId, String executionStatus) {
        this.id = id;
        this.visitItemId = visitItemId;
        this.fmeditemId = fmeditemId;
        this.doctorEntrustment = doctorEntrustment;
        this.addItem = addItem;
        this.executionDoctorId = executionDoctorId;
        this.executionStatus = executionStatus;
    }

    public VisitItemDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitItemId() {
        return visitItemId;
    }

    public void setVisitItemId(Integer visitItemId) {
        this.visitItemId = visitItemId;
    }

    public Integer getFmeditemId() {
        return fmeditemId;
    }

    public void setFmeditemId(Integer fmeditemId) {
        this.fmeditemId = fmeditemId;
    }

    public String getDoctorEntrustment() {
        return doctorEntrustment;
    }

    public void setDoctorEntrustment(String doctorEntrustment) {
        this.doctorEntrustment = doctorEntrustment == null ? null : doctorEntrustment.trim();
    }

    public String getAddItem() {
        return addItem;
    }

    public void setAddItem(String addItem) {
        this.addItem = addItem == null ? null : addItem.trim();
    }

    public Integer getExecutionDoctorId() {
        return executionDoctorId;
    }

    public void setExecutionDoctorId(Integer executionDoctorId) {
        this.executionDoctorId = executionDoctorId;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus == null ? null : executionStatus.trim();
    }
}