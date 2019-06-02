package edu.neu.medical.hospital.bean;

import java.util.Date;
import java.util.Objects;

public class Patient {
    private Integer id;

    private Integer medicalRecordNo;

    private String name;

    private Integer age;

    private String gender;

    private Date birthday;

    private String identityCardNo;

    private String familyAddress;

    public Patient(Integer id, Integer medicalRecordNo, String name, Integer age, String gender, Date birthday, String identityCardNo, String familyAddress) {
        this.id = id;
        this.medicalRecordNo = medicalRecordNo;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.identityCardNo = identityCardNo;
        this.familyAddress = familyAddress;
    }

    public Patient() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalRecordNo() {
        return medicalRecordNo;
    }

    public void setMedicalRecordNo(Integer medicalRecordNo) {
        this.medicalRecordNo = medicalRecordNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo == null ? null : identityCardNo.trim();
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress == null ? null : familyAddress.trim();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", medicalRecordNo=" + medicalRecordNo +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(medicalRecordNo, patient.medicalRecordNo) &&
                Objects.equals(name, patient.name) &&
                Objects.equals(age, patient.age) &&
                Objects.equals(gender, patient.gender) &&
                Objects.equals(birthday, patient.birthday) &&
                Objects.equals(identityCardNo, patient.identityCardNo) &&
                Objects.equals(familyAddress, patient.familyAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicalRecordNo, name, age, gender, birthday, identityCardNo, familyAddress);
    }
}