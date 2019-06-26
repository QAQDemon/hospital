package com.neusoft.ssm.bean;

public class Depart {
    private Short id;

    private String deptcode;

    private String deptname;

    private Short deptcategoryid;

    private Short depttype;

    public Depart(Short id, String deptcode, String deptname, Short deptcategoryid, Short depttype) {
        this.id = id;
        this.deptcode = deptcode;
        this.deptname = deptname;
        this.deptcategoryid = deptcategoryid;
        this.depttype = depttype;
    }

    public Depart() {
        super();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode == null ? null : deptcode.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public Short getDeptcategoryid() {
        return deptcategoryid;
    }

    public void setDeptcategoryid(Short deptcategoryid) {
        this.deptcategoryid = deptcategoryid;
    }

    public Short getDepttype() {
        return depttype;
    }

    public void setDepttype(Short depttype) {
        this.depttype = depttype;
    }
}