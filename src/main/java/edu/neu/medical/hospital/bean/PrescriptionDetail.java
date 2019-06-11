package edu.neu.medical.hospital.bean;

import java.math.BigDecimal;

public class PrescriptionDetail {
    private Integer id;

    private Integer prescriptionId;

    private Integer drugId;

    private String usage;

    private BigDecimal consumption;

    private String frequent;

    private Integer days;

    private Integer amount;

    private String entrustment;

    private String isReturnMedicine;

    public PrescriptionDetail(Integer id, Integer prescriptionId, Integer drugId, String usage, BigDecimal consumption, String frequent, Integer days, Integer amount, String entrustment, String isReturnMedicine) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.drugId = drugId;
        this.usage = usage;
        this.consumption = consumption;
        this.frequent = frequent;
        this.days = days;
        this.amount = amount;
        this.entrustment = entrustment;
        this.isReturnMedicine = isReturnMedicine;
    }

    public PrescriptionDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage == null ? null : usage.trim();
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public String getFrequent() {
        return frequent;
    }

    public void setFrequent(String frequent) {
        this.frequent = frequent == null ? null : frequent.trim();
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEntrustment() {
        return entrustment;
    }

    public void setEntrustment(String entrustment) {
        this.entrustment = entrustment == null ? null : entrustment.trim();
    }

    public String getIsReturnMedicine() {
        return isReturnMedicine;
    }

    public void setIsReturnMedicine(String isReturnMedicine) {
        this.isReturnMedicine = isReturnMedicine == null ? null : isReturnMedicine.trim();
    }
}