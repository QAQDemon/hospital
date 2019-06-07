package edu.neu.medical.hospital.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiagnosisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiagnosisExample() {
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

        public Criteria andMedicalRecordInfoIdIsNull() {
            addCriterion("medical_record_info_id is null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdIsNotNull() {
            addCriterion("medical_record_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdEqualTo(Integer value) {
            addCriterion("medical_record_info_id =", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdNotEqualTo(Integer value) {
            addCriterion("medical_record_info_id <>", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdGreaterThan(Integer value) {
            addCriterion("medical_record_info_id >", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("medical_record_info_id >=", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdLessThan(Integer value) {
            addCriterion("medical_record_info_id <", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("medical_record_info_id <=", value, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdIn(List<Integer> values) {
            addCriterion("medical_record_info_id in", values, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdNotIn(List<Integer> values) {
            addCriterion("medical_record_info_id not in", values, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("medical_record_info_id between", value1, value2, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andMedicalRecordInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("medical_record_info_id not between", value1, value2, "medicalRecordInfoId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIsNull() {
            addCriterion("disease_id is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIsNotNull() {
            addCriterion("disease_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdEqualTo(Integer value) {
            addCriterion("disease_id =", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotEqualTo(Integer value) {
            addCriterion("disease_id <>", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdGreaterThan(Integer value) {
            addCriterion("disease_id >", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("disease_id >=", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdLessThan(Integer value) {
            addCriterion("disease_id <", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("disease_id <=", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIn(List<Integer> values) {
            addCriterion("disease_id in", values, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotIn(List<Integer> values) {
            addCriterion("disease_id not in", values, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdBetween(Integer value1, Integer value2) {
            addCriterion("disease_id between", value1, value2, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("disease_id not between", value1, value2, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisIsNull() {
            addCriterion("is_new_major_diagnosis is null");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisIsNotNull() {
            addCriterion("is_new_major_diagnosis is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisEqualTo(String value) {
            addCriterion("is_new_major_diagnosis =", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisNotEqualTo(String value) {
            addCriterion("is_new_major_diagnosis <>", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisGreaterThan(String value) {
            addCriterion("is_new_major_diagnosis >", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisGreaterThanOrEqualTo(String value) {
            addCriterion("is_new_major_diagnosis >=", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisLessThan(String value) {
            addCriterion("is_new_major_diagnosis <", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisLessThanOrEqualTo(String value) {
            addCriterion("is_new_major_diagnosis <=", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisLike(String value) {
            addCriterion("is_new_major_diagnosis like", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisNotLike(String value) {
            addCriterion("is_new_major_diagnosis not like", value, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisIn(List<String> values) {
            addCriterion("is_new_major_diagnosis in", values, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisNotIn(List<String> values) {
            addCriterion("is_new_major_diagnosis not in", values, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisBetween(String value1, String value2) {
            addCriterion("is_new_major_diagnosis between", value1, value2, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewMajorDiagnosisNotBetween(String value1, String value2) {
            addCriterion("is_new_major_diagnosis not between", value1, value2, "isNewMajorDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectIsNull() {
            addCriterion("is_new_suspect is null");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectIsNotNull() {
            addCriterion("is_new_suspect is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectEqualTo(String value) {
            addCriterion("is_new_suspect =", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectNotEqualTo(String value) {
            addCriterion("is_new_suspect <>", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectGreaterThan(String value) {
            addCriterion("is_new_suspect >", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectGreaterThanOrEqualTo(String value) {
            addCriterion("is_new_suspect >=", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectLessThan(String value) {
            addCriterion("is_new_suspect <", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectLessThanOrEqualTo(String value) {
            addCriterion("is_new_suspect <=", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectLike(String value) {
            addCriterion("is_new_suspect like", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectNotLike(String value) {
            addCriterion("is_new_suspect not like", value, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectIn(List<String> values) {
            addCriterion("is_new_suspect in", values, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectNotIn(List<String> values) {
            addCriterion("is_new_suspect not in", values, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectBetween(String value1, String value2) {
            addCriterion("is_new_suspect between", value1, value2, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsNewSuspectNotBetween(String value1, String value2) {
            addCriterion("is_new_suspect not between", value1, value2, "isNewSuspect");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisIsNull() {
            addCriterion("is_final_diagnosis is null");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisIsNotNull() {
            addCriterion("is_final_diagnosis is not null");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisEqualTo(String value) {
            addCriterion("is_final_diagnosis =", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisNotEqualTo(String value) {
            addCriterion("is_final_diagnosis <>", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisGreaterThan(String value) {
            addCriterion("is_final_diagnosis >", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisGreaterThanOrEqualTo(String value) {
            addCriterion("is_final_diagnosis >=", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisLessThan(String value) {
            addCriterion("is_final_diagnosis <", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisLessThanOrEqualTo(String value) {
            addCriterion("is_final_diagnosis <=", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisLike(String value) {
            addCriterion("is_final_diagnosis like", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisNotLike(String value) {
            addCriterion("is_final_diagnosis not like", value, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisIn(List<String> values) {
            addCriterion("is_final_diagnosis in", values, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisNotIn(List<String> values) {
            addCriterion("is_final_diagnosis not in", values, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisBetween(String value1, String value2) {
            addCriterion("is_final_diagnosis between", value1, value2, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andIsFinalDiagnosisNotBetween(String value1, String value2) {
            addCriterion("is_final_diagnosis not between", value1, value2, "isFinalDiagnosis");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetIsNull() {
            addCriterion("date_of_onset is null");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetIsNotNull() {
            addCriterion("date_of_onset is not null");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetEqualTo(Date value) {
            addCriterion("date_of_onset =", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetNotEqualTo(Date value) {
            addCriterion("date_of_onset <>", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetGreaterThan(Date value) {
            addCriterion("date_of_onset >", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetGreaterThanOrEqualTo(Date value) {
            addCriterion("date_of_onset >=", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetLessThan(Date value) {
            addCriterion("date_of_onset <", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetLessThanOrEqualTo(Date value) {
            addCriterion("date_of_onset <=", value, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetIn(List<Date> values) {
            addCriterion("date_of_onset in", values, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetNotIn(List<Date> values) {
            addCriterion("date_of_onset not in", values, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetBetween(Date value1, Date value2) {
            addCriterion("date_of_onset between", value1, value2, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andDateOfOnsetNotBetween(Date value1, Date value2) {
            addCriterion("date_of_onset not between", value1, value2, "dateOfOnset");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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