package edu.neu.medical.hospital.bean;

import java.util.ArrayList;
import java.util.List;

public class MedrecTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MedrecTemplateExample() {
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

        public Criteria andTemplateNameIsNull() {
            addCriterion("template_name is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("template_name is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("template_name =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("template_name <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("template_name >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("template_name >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("template_name <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("template_name <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("template_name like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("template_name not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("template_name in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("template_name not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("template_name between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("template_name not between", value1, value2, "templateName");
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

        public Criteria andBelongIdIsNull() {
            addCriterion("belong_id is null");
            return (Criteria) this;
        }

        public Criteria andBelongIdIsNotNull() {
            addCriterion("belong_id is not null");
            return (Criteria) this;
        }

        public Criteria andBelongIdEqualTo(Integer value) {
            addCriterion("belong_id =", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdNotEqualTo(Integer value) {
            addCriterion("belong_id <>", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdGreaterThan(Integer value) {
            addCriterion("belong_id >", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("belong_id >=", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdLessThan(Integer value) {
            addCriterion("belong_id <", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdLessThanOrEqualTo(Integer value) {
            addCriterion("belong_id <=", value, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdIn(List<Integer> values) {
            addCriterion("belong_id in", values, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdNotIn(List<Integer> values) {
            addCriterion("belong_id not in", values, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdBetween(Integer value1, Integer value2) {
            addCriterion("belong_id between", value1, value2, "belongId");
            return (Criteria) this;
        }

        public Criteria andBelongIdNotBetween(Integer value1, Integer value2) {
            addCriterion("belong_id not between", value1, value2, "belongId");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintIsNull() {
            addCriterion("chief_complaint is null");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintIsNotNull() {
            addCriterion("chief_complaint is not null");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintEqualTo(String value) {
            addCriterion("chief_complaint =", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintNotEqualTo(String value) {
            addCriterion("chief_complaint <>", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintGreaterThan(String value) {
            addCriterion("chief_complaint >", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintGreaterThanOrEqualTo(String value) {
            addCriterion("chief_complaint >=", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintLessThan(String value) {
            addCriterion("chief_complaint <", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintLessThanOrEqualTo(String value) {
            addCriterion("chief_complaint <=", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintLike(String value) {
            addCriterion("chief_complaint like", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintNotLike(String value) {
            addCriterion("chief_complaint not like", value, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintIn(List<String> values) {
            addCriterion("chief_complaint in", values, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintNotIn(List<String> values) {
            addCriterion("chief_complaint not in", values, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintBetween(String value1, String value2) {
            addCriterion("chief_complaint between", value1, value2, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andChiefComplaintNotBetween(String value1, String value2) {
            addCriterion("chief_complaint not between", value1, value2, "chiefComplaint");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryIsNull() {
            addCriterion("current_medical_history is null");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryIsNotNull() {
            addCriterion("current_medical_history is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryEqualTo(String value) {
            addCriterion("current_medical_history =", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryNotEqualTo(String value) {
            addCriterion("current_medical_history <>", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryGreaterThan(String value) {
            addCriterion("current_medical_history >", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("current_medical_history >=", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryLessThan(String value) {
            addCriterion("current_medical_history <", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryLessThanOrEqualTo(String value) {
            addCriterion("current_medical_history <=", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryLike(String value) {
            addCriterion("current_medical_history like", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryNotLike(String value) {
            addCriterion("current_medical_history not like", value, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryIn(List<String> values) {
            addCriterion("current_medical_history in", values, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryNotIn(List<String> values) {
            addCriterion("current_medical_history not in", values, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryBetween(String value1, String value2) {
            addCriterion("current_medical_history between", value1, value2, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentMedicalHistoryNotBetween(String value1, String value2) {
            addCriterion("current_medical_history not between", value1, value2, "currentMedicalHistory");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationIsNull() {
            addCriterion("current_treatment_situation is null");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationIsNotNull() {
            addCriterion("current_treatment_situation is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationEqualTo(String value) {
            addCriterion("current_treatment_situation =", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationNotEqualTo(String value) {
            addCriterion("current_treatment_situation <>", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationGreaterThan(String value) {
            addCriterion("current_treatment_situation >", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationGreaterThanOrEqualTo(String value) {
            addCriterion("current_treatment_situation >=", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationLessThan(String value) {
            addCriterion("current_treatment_situation <", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationLessThanOrEqualTo(String value) {
            addCriterion("current_treatment_situation <=", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationLike(String value) {
            addCriterion("current_treatment_situation like", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationNotLike(String value) {
            addCriterion("current_treatment_situation not like", value, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationIn(List<String> values) {
            addCriterion("current_treatment_situation in", values, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationNotIn(List<String> values) {
            addCriterion("current_treatment_situation not in", values, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationBetween(String value1, String value2) {
            addCriterion("current_treatment_situation between", value1, value2, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentTreatmentSituationNotBetween(String value1, String value2) {
            addCriterion("current_treatment_situation not between", value1, value2, "currentTreatmentSituation");
            return (Criteria) this;
        }

        public Criteria andPastHistoryIsNull() {
            addCriterion("past__history is null");
            return (Criteria) this;
        }

        public Criteria andPastHistoryIsNotNull() {
            addCriterion("past__history is not null");
            return (Criteria) this;
        }

        public Criteria andPastHistoryEqualTo(String value) {
            addCriterion("past__history =", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryNotEqualTo(String value) {
            addCriterion("past__history <>", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryGreaterThan(String value) {
            addCriterion("past__history >", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("past__history >=", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryLessThan(String value) {
            addCriterion("past__history <", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryLessThanOrEqualTo(String value) {
            addCriterion("past__history <=", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryLike(String value) {
            addCriterion("past__history like", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryNotLike(String value) {
            addCriterion("past__history not like", value, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryIn(List<String> values) {
            addCriterion("past__history in", values, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryNotIn(List<String> values) {
            addCriterion("past__history not in", values, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryBetween(String value1, String value2) {
            addCriterion("past__history between", value1, value2, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andPastHistoryNotBetween(String value1, String value2) {
            addCriterion("past__history not between", value1, value2, "pastHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryIsNull() {
            addCriterion("allergies_history is null");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryIsNotNull() {
            addCriterion("allergies_history is not null");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryEqualTo(String value) {
            addCriterion("allergies_history =", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryNotEqualTo(String value) {
            addCriterion("allergies_history <>", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryGreaterThan(String value) {
            addCriterion("allergies_history >", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("allergies_history >=", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryLessThan(String value) {
            addCriterion("allergies_history <", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryLessThanOrEqualTo(String value) {
            addCriterion("allergies_history <=", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryLike(String value) {
            addCriterion("allergies_history like", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryNotLike(String value) {
            addCriterion("allergies_history not like", value, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryIn(List<String> values) {
            addCriterion("allergies_history in", values, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryNotIn(List<String> values) {
            addCriterion("allergies_history not in", values, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryBetween(String value1, String value2) {
            addCriterion("allergies_history between", value1, value2, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andAllergiesHistoryNotBetween(String value1, String value2) {
            addCriterion("allergies_history not between", value1, value2, "allergiesHistory");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIsNull() {
            addCriterion("physical_examination is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIsNotNull() {
            addCriterion("physical_examination is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationEqualTo(String value) {
            addCriterion("physical_examination =", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotEqualTo(String value) {
            addCriterion("physical_examination <>", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationGreaterThan(String value) {
            addCriterion("physical_examination >", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationGreaterThanOrEqualTo(String value) {
            addCriterion("physical_examination >=", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLessThan(String value) {
            addCriterion("physical_examination <", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLessThanOrEqualTo(String value) {
            addCriterion("physical_examination <=", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLike(String value) {
            addCriterion("physical_examination like", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotLike(String value) {
            addCriterion("physical_examination not like", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIn(List<String> values) {
            addCriterion("physical_examination in", values, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotIn(List<String> values) {
            addCriterion("physical_examination not in", values, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationBetween(String value1, String value2) {
            addCriterion("physical_examination between", value1, value2, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotBetween(String value1, String value2) {
            addCriterion("physical_examination not between", value1, value2, "physicalExamination");
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