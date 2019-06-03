package edu.neu.medical.hospital.bean;

public class Disease {
    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", diseasecode='" + diseasecode + '\'' +
                ", diseasename='" + diseasename + '\'' +
                ", diseaseicd='" + diseaseicd + '\'' +
                ", disecategoryid=" + disecategoryid +
                '}';
    }

    private Short id;

    private String diseasecode;

    private String diseasename;

    private String diseaseicd;

    private Short disecategoryid;

    public Disease(Short id, String diseasecode, String diseasename, String diseaseicd, Short disecategoryid) {
        this.id = id;
        this.diseasecode = diseasecode;
        this.diseasename = diseasename;
        this.diseaseicd = diseaseicd;
        this.disecategoryid = disecategoryid;
    }

    public Disease() {
        super();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDiseasecode() {
        return diseasecode;
    }

    public void setDiseasecode(String diseasecode) {
        this.diseasecode = diseasecode == null ? null : diseasecode.trim();
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename == null ? null : diseasename.trim();
    }

    public String getDiseaseicd() {
        return diseaseicd;
    }

    public void setDiseaseicd(String diseaseicd) {
        this.diseaseicd = diseaseicd == null ? null : diseaseicd.trim();
    }

    public Short getDisecategoryid() {
        return disecategoryid;
    }

    public void setDisecategoryid(Short disecategoryid) {
        this.disecategoryid = disecategoryid;
    }
}