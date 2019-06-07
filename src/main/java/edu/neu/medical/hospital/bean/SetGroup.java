package edu.neu.medical.hospital.bean;

import java.util.Date;

public class SetGroup {
    private Integer id;

    private String setCode;

    private String businessClassify;

    private String setName;

    private String useScope;

    private Integer belongId;

    private Date buildDate;

    private Integer createrId;

    private String status;

    public SetGroup(Integer id, String setCode, String businessClassify, String setName, String useScope, Integer belongId, Date buildDate, Integer createrId, String status) {
        this.id = id;
        this.setCode = setCode;
        this.businessClassify = businessClassify;
        this.setName = setName;
        this.useScope = useScope;
        this.belongId = belongId;
        this.buildDate = buildDate;
        this.createrId = createrId;
        this.status = status;
    }

    public SetGroup() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode == null ? null : setCode.trim();
    }

    public String getBusinessClassify() {
        return businessClassify;
    }

    public void setBusinessClassify(String businessClassify) {
        this.businessClassify = businessClassify == null ? null : businessClassify.trim();
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName == null ? null : setName.trim();
    }

    public String getUseScope() {
        return useScope;
    }

    public void setUseScope(String useScope) {
        this.useScope = useScope == null ? null : useScope.trim();
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}