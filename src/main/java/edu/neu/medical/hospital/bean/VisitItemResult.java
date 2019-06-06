package edu.neu.medical.hospital.bean;

public class VisitItemResult {
    private Integer id;

    private Integer visitRecordDetailId;

    private String describetion;

    private String picture;

    public VisitItemResult(Integer id, Integer visitRecordDetailId, String describetion, String picture) {
        this.id = id;
        this.visitRecordDetailId = visitRecordDetailId;
        this.describetion = describetion;
        this.picture = picture;
    }

    public VisitItemResult() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitRecordDetailId() {
        return visitRecordDetailId;
    }

    public void setVisitRecordDetailId(Integer visitRecordDetailId) {
        this.visitRecordDetailId = visitRecordDetailId;
    }

    public String getDescribetion() {
        return describetion;
    }

    public void setDescribetion(String describetion) {
        this.describetion = describetion == null ? null : describetion.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}