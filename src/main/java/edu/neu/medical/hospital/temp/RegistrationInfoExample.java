package edu.neu.medical.hospital.temp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegistrationInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoIsNull() {
            addCriterion("medical_record_no is null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoIsNotNull() {
            addCriterion("medical_record_no is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoEqualTo(Integer value) {
            addCriterion("medical_record_no =", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoNotEqualTo(Integer value) {
            addCriterion("medical_record_no <>", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoGreaterThan(Integer value) {
            addCriterion("medical_record_no >", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("medical_record_no >=", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoLessThan(Integer value) {
            addCriterion("medical_record_no <", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoLessThanOrEqualTo(Integer value) {
            addCriterion("medical_record_no <=", value, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoIn(List<Integer> values) {
            addCriterion("medical_record_no in", values, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoNotIn(List<Integer> values) {
            addCriterion("medical_record_no not in", values, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoBetween(Integer value1, Integer value2) {
            addCriterion("medical_record_no between", value1, value2, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordNoNotBetween(Integer value1, Integer value2) {
            addCriterion("medical_record_no not between", value1, value2, "medicalRecordNo");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("patient_name is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("patient_name is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("patient_name =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("patient_name <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("patient_name >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("patient_name >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("patient_name <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("patient_name <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("patient_name like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("patient_name not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("patient_name in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("patient_name not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("patient_name between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("patient_name not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIsNull() {
            addCriterion("patient_gender is null");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIsNotNull() {
            addCriterion("patient_gender is not null");
            return (Criteria) this;
        }

        public Criteria andPatientGenderEqualTo(String value) {
            addCriterion("patient_gender =", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotEqualTo(String value) {
            addCriterion("patient_gender <>", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderGreaterThan(String value) {
            addCriterion("patient_gender >", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderGreaterThanOrEqualTo(String value) {
            addCriterion("patient_gender >=", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLessThan(String value) {
            addCriterion("patient_gender <", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLessThanOrEqualTo(String value) {
            addCriterion("patient_gender <=", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderLike(String value) {
            addCriterion("patient_gender like", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotLike(String value) {
            addCriterion("patient_gender not like", value, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderIn(List<String> values) {
            addCriterion("patient_gender in", values, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotIn(List<String> values) {
            addCriterion("patient_gender not in", values, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderBetween(String value1, String value2) {
            addCriterion("patient_gender between", value1, value2, "patientGender");
            return (Criteria) this;
        }

        public Criteria andPatientGenderNotBetween(String value1, String value2) {
            addCriterion("patient_gender not between", value1, value2, "patientGender");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNull() {
            addCriterion("birth_date is null");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNotNull() {
            addCriterion("birth_date is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDateEqualTo(Date value) {
            addCriterion("birth_date =", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotEqualTo(Date value) {
            addCriterion("birth_date <>", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThan(Date value) {
            addCriterion("birth_date >", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThanOrEqualTo(Date value) {
            addCriterion("birth_date >=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThan(Date value) {
            addCriterion("birth_date <", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThanOrEqualTo(Date value) {
            addCriterion("birth_date <=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateIn(List<Date> values) {
            addCriterion("birth_date in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotIn(List<Date> values) {
            addCriterion("birth_date not in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateBetween(Date value1, Date value2) {
            addCriterion("birth_date between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotBetween(Date value1, Date value2) {
            addCriterion("birth_date not between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryIsNull() {
            addCriterion("settle_accounts_category is null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryIsNotNull() {
            addCriterion("settle_accounts_category is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryEqualTo(String value) {
            addCriterion("settle_accounts_category =", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryNotEqualTo(String value) {
            addCriterion("settle_accounts_category <>", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryGreaterThan(String value) {
            addCriterion("settle_accounts_category >", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("settle_accounts_category >=", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryLessThan(String value) {
            addCriterion("settle_accounts_category <", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryLessThanOrEqualTo(String value) {
            addCriterion("settle_accounts_category <=", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryLike(String value) {
            addCriterion("settle_accounts_category like", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryNotLike(String value) {
            addCriterion("settle_accounts_category not like", value, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryIn(List<String> values) {
            addCriterion("settle_accounts_category in", values, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryNotIn(List<String> values) {
            addCriterion("settle_accounts_category not in", values, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryBetween(String value1, String value2) {
            addCriterion("settle_accounts_category between", value1, value2, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsCategoryNotBetween(String value1, String value2) {
            addCriterion("settle_accounts_category not between", value1, value2, "settleAccountsCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryIsNull() {
            addCriterion("registration_category is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryIsNotNull() {
            addCriterion("registration_category is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryEqualTo(String value) {
            addCriterion("registration_category =", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryNotEqualTo(String value) {
            addCriterion("registration_category <>", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryGreaterThan(String value) {
            addCriterion("registration_category >", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("registration_category >=", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryLessThan(String value) {
            addCriterion("registration_category <", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryLessThanOrEqualTo(String value) {
            addCriterion("registration_category <=", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryLike(String value) {
            addCriterion("registration_category like", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryNotLike(String value) {
            addCriterion("registration_category not like", value, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryIn(List<String> values) {
            addCriterion("registration_category in", values, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryNotIn(List<String> values) {
            addCriterion("registration_category not in", values, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryBetween(String value1, String value2) {
            addCriterion("registration_category between", value1, value2, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andRegistrationCategoryNotBetween(String value1, String value2) {
            addCriterion("registration_category not between", value1, value2, "registrationCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryIsNull() {
            addCriterion("medical_category is null");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryIsNotNull() {
            addCriterion("medical_category is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryEqualTo(String value) {
            addCriterion("medical_category =", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryNotEqualTo(String value) {
            addCriterion("medical_category <>", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryGreaterThan(String value) {
            addCriterion("medical_category >", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("medical_category >=", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryLessThan(String value) {
            addCriterion("medical_category <", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryLessThanOrEqualTo(String value) {
            addCriterion("medical_category <=", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryLike(String value) {
            addCriterion("medical_category like", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryNotLike(String value) {
            addCriterion("medical_category not like", value, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryIn(List<String> values) {
            addCriterion("medical_category in", values, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryNotIn(List<String> values) {
            addCriterion("medical_category not in", values, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryBetween(String value1, String value2) {
            addCriterion("medical_category between", value1, value2, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andMedicalCategoryNotBetween(String value1, String value2) {
            addCriterion("medical_category not between", value1, value2, "medicalCategory");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNull() {
            addCriterion("depart_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNotNull() {
            addCriterion("depart_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartIdEqualTo(Integer value) {
            addCriterion("depart_id =", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotEqualTo(Integer value) {
            addCriterion("depart_id <>", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThan(Integer value) {
            addCriterion("depart_id >", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("depart_id >=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThan(Integer value) {
            addCriterion("depart_id <", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThanOrEqualTo(Integer value) {
            addCriterion("depart_id <=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdIn(List<Integer> values) {
            addCriterion("depart_id in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotIn(List<Integer> values) {
            addCriterion("depart_id not in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdBetween(Integer value1, Integer value2) {
            addCriterion("depart_id between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("depart_id not between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(Integer value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(Integer value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(Integer value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(Integer value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(Integer value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<Integer> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<Integer> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(Integer value1, Integer value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNull() {
            addCriterion("doctor_name is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("doctor_name is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("doctor_name =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("doctor_name <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("doctor_name >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("doctor_name >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("doctor_name <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("doctor_name <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("doctor_name like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("doctor_name not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("doctor_name in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("doctor_name not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("doctor_name between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("doctor_name not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNull() {
            addCriterion("registration_date is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNotNull() {
            addCriterion("registration_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateEqualTo(Date value) {
            addCriterion("registration_date =", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotEqualTo(Date value) {
            addCriterion("registration_date <>", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThan(Date value) {
            addCriterion("registration_date >", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("registration_date >=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThan(Date value) {
            addCriterion("registration_date <", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterion("registration_date <=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIn(List<Date> values) {
            addCriterion("registration_date in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotIn(List<Date> values) {
            addCriterion("registration_date not in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateBetween(Date value1, Date value2) {
            addCriterion("registration_date between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterion("registration_date not between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceIsNull() {
            addCriterion("registration_source is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceIsNotNull() {
            addCriterion("registration_source is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceEqualTo(String value) {
            addCriterion("registration_source =", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceNotEqualTo(String value) {
            addCriterion("registration_source <>", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceGreaterThan(String value) {
            addCriterion("registration_source >", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceGreaterThanOrEqualTo(String value) {
            addCriterion("registration_source >=", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceLessThan(String value) {
            addCriterion("registration_source <", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceLessThanOrEqualTo(String value) {
            addCriterion("registration_source <=", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceLike(String value) {
            addCriterion("registration_source like", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceNotLike(String value) {
            addCriterion("registration_source not like", value, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceIn(List<String> values) {
            addCriterion("registration_source in", values, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceNotIn(List<String> values) {
            addCriterion("registration_source not in", values, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceBetween(String value1, String value2) {
            addCriterion("registration_source between", value1, value2, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andRegistrationSourceNotBetween(String value1, String value2) {
            addCriterion("registration_source not between", value1, value2, "registrationSource");
            return (Criteria) this;
        }

        public Criteria andExpenseIsNull() {
            addCriterion("expense is null");
            return (Criteria) this;
        }

        public Criteria andExpenseIsNotNull() {
            addCriterion("expense is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseEqualTo(BigDecimal value) {
            addCriterion("expense =", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotEqualTo(BigDecimal value) {
            addCriterion("expense <>", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseGreaterThan(BigDecimal value) {
            addCriterion("expense >", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("expense >=", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseLessThan(BigDecimal value) {
            addCriterion("expense <", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("expense <=", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseIn(List<BigDecimal> values) {
            addCriterion("expense in", values, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotIn(List<BigDecimal> values) {
            addCriterion("expense not in", values, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expense between", value1, value2, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("expense not between", value1, value2, "expense");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("refund_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("refund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("refund_amount =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_amount >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("refund_amount <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("refund_amount in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateIsNull() {
            addCriterion("see_doctor_date is null");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateIsNotNull() {
            addCriterion("see_doctor_date is not null");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateEqualTo(Date value) {
            addCriterion("see_doctor_date =", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateNotEqualTo(Date value) {
            addCriterion("see_doctor_date <>", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateGreaterThan(Date value) {
            addCriterion("see_doctor_date >", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateGreaterThanOrEqualTo(Date value) {
            addCriterion("see_doctor_date >=", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateLessThan(Date value) {
            addCriterion("see_doctor_date <", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateLessThanOrEqualTo(Date value) {
            addCriterion("see_doctor_date <=", value, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateIn(List<Date> values) {
            addCriterion("see_doctor_date in", values, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateNotIn(List<Date> values) {
            addCriterion("see_doctor_date not in", values, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateBetween(Date value1, Date value2) {
            addCriterion("see_doctor_date between", value1, value2, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andSeeDoctorDateNotBetween(Date value1, Date value2) {
            addCriterion("see_doctor_date not between", value1, value2, "seeDoctorDate");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorIsNull() {
            addCriterion("is_seen_docator is null");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorIsNotNull() {
            addCriterion("is_seen_docator is not null");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorEqualTo(String value) {
            addCriterion("is_seen_docator =", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorNotEqualTo(String value) {
            addCriterion("is_seen_docator <>", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorGreaterThan(String value) {
            addCriterion("is_seen_docator >", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorGreaterThanOrEqualTo(String value) {
            addCriterion("is_seen_docator >=", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorLessThan(String value) {
            addCriterion("is_seen_docator <", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorLessThanOrEqualTo(String value) {
            addCriterion("is_seen_docator <=", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorLike(String value) {
            addCriterion("is_seen_docator like", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorNotLike(String value) {
            addCriterion("is_seen_docator not like", value, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorIn(List<String> values) {
            addCriterion("is_seen_docator in", values, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorNotIn(List<String> values) {
            addCriterion("is_seen_docator not in", values, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorBetween(String value1, String value2) {
            addCriterion("is_seen_docator between", value1, value2, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIsSeenDocatorNotBetween(String value1, String value2) {
            addCriterion("is_seen_docator not between", value1, value2, "isSeenDocator");
            return (Criteria) this;
        }

        public Criteria andIfBookIsNull() {
            addCriterion("if_book is null");
            return (Criteria) this;
        }

        public Criteria andIfBookIsNotNull() {
            addCriterion("if_book is not null");
            return (Criteria) this;
        }

        public Criteria andIfBookEqualTo(String value) {
            addCriterion("if_book =", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookNotEqualTo(String value) {
            addCriterion("if_book <>", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookGreaterThan(String value) {
            addCriterion("if_book >", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookGreaterThanOrEqualTo(String value) {
            addCriterion("if_book >=", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookLessThan(String value) {
            addCriterion("if_book <", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookLessThanOrEqualTo(String value) {
            addCriterion("if_book <=", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookLike(String value) {
            addCriterion("if_book like", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookNotLike(String value) {
            addCriterion("if_book not like", value, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookIn(List<String> values) {
            addCriterion("if_book in", values, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookNotIn(List<String> values) {
            addCriterion("if_book not in", values, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookBetween(String value1, String value2) {
            addCriterion("if_book between", value1, value2, "ifBook");
            return (Criteria) this;
        }

        public Criteria andIfBookNotBetween(String value1, String value2) {
            addCriterion("if_book not between", value1, value2, "ifBook");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}