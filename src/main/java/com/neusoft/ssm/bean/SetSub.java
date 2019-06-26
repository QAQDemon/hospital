package com.neusoft.ssm.bean;

public class SetSub {
    private Integer id;

    private Integer setId;

    private Integer responseId;

    private String entrust;

    public SetSub(Integer id, Integer setId, Integer responseId, String entrust) {
        this.id = id;
        this.setId = setId;
        this.responseId = responseId;
        this.entrust = entrust;
    }

    public SetSub() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public String getEntrust() {
        return entrust;
    }

    public void setEntrust(String entrust) {
        this.entrust = entrust == null ? null : entrust.trim();
    }
}