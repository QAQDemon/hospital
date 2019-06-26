package com.neusoft.ssm.bean;

import java.util.Date;

public class Drugs {
    private Short id;

    private String drugscode;

    private String drugsname;

    private String drugsformat;

    private String drugsunit;

    private String manufacturer;

    private Short drugsdosageid;

    private Short drugstypeid;

    private Float drugsprice;

    private String mnemoniccode;

    private Date creationdate;

    public Drugs(Short id, String drugscode, String drugsname, String drugsformat, String drugsunit, String manufacturer, Short drugsdosageid, Short drugstypeid, Float drugsprice, String mnemoniccode, Date creationdate) {
        this.id = id;
        this.drugscode = drugscode;
        this.drugsname = drugsname;
        this.drugsformat = drugsformat;
        this.drugsunit = drugsunit;
        this.manufacturer = manufacturer;
        this.drugsdosageid = drugsdosageid;
        this.drugstypeid = drugstypeid;
        this.drugsprice = drugsprice;
        this.mnemoniccode = mnemoniccode;
        this.creationdate = creationdate;
    }

    public Drugs() {
        super();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDrugscode() {
        return drugscode;
    }

    public void setDrugscode(String drugscode) {
        this.drugscode = drugscode == null ? null : drugscode.trim();
    }

    public String getDrugsname() {
        return drugsname;
    }

    public void setDrugsname(String drugsname) {
        this.drugsname = drugsname == null ? null : drugsname.trim();
    }

    public String getDrugsformat() {
        return drugsformat;
    }

    public void setDrugsformat(String drugsformat) {
        this.drugsformat = drugsformat == null ? null : drugsformat.trim();
    }

    public String getDrugsunit() {
        return drugsunit;
    }

    public void setDrugsunit(String drugsunit) {
        this.drugsunit = drugsunit == null ? null : drugsunit.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public Short getDrugsdosageid() {
        return drugsdosageid;
    }

    public void setDrugsdosageid(Short drugsdosageid) {
        this.drugsdosageid = drugsdosageid;
    }

    public Short getDrugstypeid() {
        return drugstypeid;
    }

    public void setDrugstypeid(Short drugstypeid) {
        this.drugstypeid = drugstypeid;
    }

    public Float getDrugsprice() {
        return drugsprice;
    }

    public void setDrugsprice(Float drugsprice) {
        this.drugsprice = drugsprice;
    }

    public String getMnemoniccode() {
        return mnemoniccode;
    }

    public void setMnemoniccode(String mnemoniccode) {
        this.mnemoniccode = mnemoniccode == null ? null : mnemoniccode.trim();
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
}