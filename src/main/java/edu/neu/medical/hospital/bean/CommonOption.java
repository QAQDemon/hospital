package edu.neu.medical.hospital.bean;

public class CommonOption {
    private Integer id;

    private Integer doctorId;

    private Integer belongId;

    private String type;

    public CommonOption(Integer id, Integer doctorId, Integer belongId, String type) {
        this.id = id;
        this.doctorId = doctorId;
        this.belongId = belongId;
        this.type = type;
    }

    public CommonOption() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}