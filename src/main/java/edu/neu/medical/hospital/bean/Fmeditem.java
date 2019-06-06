package edu.neu.medical.hospital.bean;

import java.util.Date;

public class Fmeditem {
    @Override
    public String toString() {
        return "Fmeditem{" +
                "id=" + id +
                ", itemcode='" + itemcode + '\'' +
                ", itemname='" + itemname + '\'' +
                ", format='" + format + '\'' +
                ", price=" + price +
                ", expclassid=" + expclassid +
                ", deptid=" + deptid +
                ", mnemoniccode='" + mnemoniccode + '\'' +
                ", recordtype=" + recordtype +
                ", creationdate=" + creationdate +
                '}';
    }

    private Short id;

    private String itemcode;

    private String itemname;

    private String format;

    private Float price;

    private Short expclassid;

    private Short deptid;

    private String mnemoniccode;

    private Short recordtype;

    private Date creationdate;

    public Fmeditem(Short id, String itemcode, String itemname, String format, Float price, Short expclassid, Short deptid, String mnemoniccode, Short recordtype, Date creationdate) {
        this.id = id;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.format = format;
        this.price = price;
        this.expclassid = expclassid;
        this.deptid = deptid;
        this.mnemoniccode = mnemoniccode;
        this.recordtype = recordtype;
        this.creationdate = creationdate;
    }

    public Fmeditem() {
        super();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode == null ? null : itemcode.trim();
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getExpclassid() {
        return expclassid;
    }

    public void setExpclassid(Short expclassid) {
        this.expclassid = expclassid;
    }

    public Short getDeptid() {
        return deptid;
    }

    public void setDeptid(Short deptid) {
        this.deptid = deptid;
    }

    public String getMnemoniccode() {
        return mnemoniccode;
    }

    public void setMnemoniccode(String mnemoniccode) {
        this.mnemoniccode = mnemoniccode == null ? null : mnemoniccode.trim();
    }

    public Short getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(Short recordtype) {
        this.recordtype = recordtype;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
}