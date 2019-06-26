package com.neusoft.ssm.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrescriptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrescriptionExample() {
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

        public Criteria andPrescriptionTypeIsNull() {
            addCriterion("prescription_type is null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeIsNotNull() {
            addCriterion("prescription_type is not null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeEqualTo(String value) {
            addCriterion("prescription_type =", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeNotEqualTo(String value) {
            addCriterion("prescription_type <>", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeGreaterThan(String value) {
            addCriterion("prescription_type >", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("prescription_type >=", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeLessThan(String value) {
            addCriterion("prescription_type <", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeLessThanOrEqualTo(String value) {
            addCriterion("prescription_type <=", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeLike(String value) {
            addCriterion("prescription_type like", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeNotLike(String value) {
            addCriterion("prescription_type not like", value, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeIn(List<String> values) {
            addCriterion("prescription_type in", values, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeNotIn(List<String> values) {
            addCriterion("prescription_type not in", values, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeBetween(String value1, String value2) {
            addCriterion("prescription_type between", value1, value2, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionTypeNotBetween(String value1, String value2) {
            addCriterion("prescription_type not between", value1, value2, "prescriptionType");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameIsNull() {
            addCriterion("prescription_name is null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameIsNotNull() {
            addCriterion("prescription_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameEqualTo(String value) {
            addCriterion("prescription_name =", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameNotEqualTo(String value) {
            addCriterion("prescription_name <>", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameGreaterThan(String value) {
            addCriterion("prescription_name >", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameGreaterThanOrEqualTo(String value) {
            addCriterion("prescription_name >=", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameLessThan(String value) {
            addCriterion("prescription_name <", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameLessThanOrEqualTo(String value) {
            addCriterion("prescription_name <=", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameLike(String value) {
            addCriterion("prescription_name like", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameNotLike(String value) {
            addCriterion("prescription_name not like", value, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameIn(List<String> values) {
            addCriterion("prescription_name in", values, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameNotIn(List<String> values) {
            addCriterion("prescription_name not in", values, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameBetween(String value1, String value2) {
            addCriterion("prescription_name between", value1, value2, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNameNotBetween(String value1, String value2) {
            addCriterion("prescription_name not between", value1, value2, "prescriptionName");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNull() {
            addCriterion("build_time is null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNotNull() {
            addCriterion("build_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeEqualTo(Date value) {
            addCriterion("build_time =", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotEqualTo(Date value) {
            addCriterion("build_time <>", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThan(Date value) {
            addCriterion("build_time >", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("build_time >=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThan(Date value) {
            addCriterion("build_time <", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("build_time <=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIn(List<Date> values) {
            addCriterion("build_time in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotIn(List<Date> values) {
            addCriterion("build_time not in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeBetween(Date value1, Date value2) {
            addCriterion("build_time between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("build_time not between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountIsNull() {
            addCriterion("prescription_in_amount is null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountIsNotNull() {
            addCriterion("prescription_in_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountEqualTo(BigDecimal value) {
            addCriterion("prescription_in_amount =", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountNotEqualTo(BigDecimal value) {
            addCriterion("prescription_in_amount <>", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountGreaterThan(BigDecimal value) {
            addCriterion("prescription_in_amount >", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prescription_in_amount >=", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountLessThan(BigDecimal value) {
            addCriterion("prescription_in_amount <", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prescription_in_amount <=", value, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountIn(List<BigDecimal> values) {
            addCriterion("prescription_in_amount in", values, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountNotIn(List<BigDecimal> values) {
            addCriterion("prescription_in_amount not in", values, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prescription_in_amount between", value1, value2, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionInAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prescription_in_amount not between", value1, value2, "prescriptionInAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountIsNull() {
            addCriterion("prescription_out_amount is null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountIsNotNull() {
            addCriterion("prescription_out_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountEqualTo(BigDecimal value) {
            addCriterion("prescription_out_amount =", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountNotEqualTo(BigDecimal value) {
            addCriterion("prescription_out_amount <>", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountGreaterThan(BigDecimal value) {
            addCriterion("prescription_out_amount >", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prescription_out_amount >=", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountLessThan(BigDecimal value) {
            addCriterion("prescription_out_amount <", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prescription_out_amount <=", value, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountIn(List<BigDecimal> values) {
            addCriterion("prescription_out_amount in", values, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountNotIn(List<BigDecimal> values) {
            addCriterion("prescription_out_amount not in", values, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prescription_out_amount between", value1, value2, "prescriptionOutAmount");
            return (Criteria) this;
        }

        public Criteria andPrescriptionOutAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prescription_out_amount not between", value1, value2, "prescriptionOutAmount");
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

        public Criteria andFeeStatusIsNull() {
            addCriterion("fee_status is null");
            return (Criteria) this;
        }

        public Criteria andFeeStatusIsNotNull() {
            addCriterion("fee_status is not null");
            return (Criteria) this;
        }

        public Criteria andFeeStatusEqualTo(String value) {
            addCriterion("fee_status =", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusNotEqualTo(String value) {
            addCriterion("fee_status <>", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusGreaterThan(String value) {
            addCriterion("fee_status >", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("fee_status >=", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusLessThan(String value) {
            addCriterion("fee_status <", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusLessThanOrEqualTo(String value) {
            addCriterion("fee_status <=", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusLike(String value) {
            addCriterion("fee_status like", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusNotLike(String value) {
            addCriterion("fee_status not like", value, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusIn(List<String> values) {
            addCriterion("fee_status in", values, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusNotIn(List<String> values) {
            addCriterion("fee_status not in", values, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusBetween(String value1, String value2) {
            addCriterion("fee_status between", value1, value2, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andFeeStatusNotBetween(String value1, String value2) {
            addCriterion("fee_status not between", value1, value2, "feeStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusIsNull() {
            addCriterion("execution_status is null");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusIsNotNull() {
            addCriterion("execution_status is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusEqualTo(String value) {
            addCriterion("execution_status =", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusNotEqualTo(String value) {
            addCriterion("execution_status <>", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusGreaterThan(String value) {
            addCriterion("execution_status >", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusGreaterThanOrEqualTo(String value) {
            addCriterion("execution_status >=", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusLessThan(String value) {
            addCriterion("execution_status <", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusLessThanOrEqualTo(String value) {
            addCriterion("execution_status <=", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusLike(String value) {
            addCriterion("execution_status like", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusNotLike(String value) {
            addCriterion("execution_status not like", value, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusIn(List<String> values) {
            addCriterion("execution_status in", values, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusNotIn(List<String> values) {
            addCriterion("execution_status not in", values, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusBetween(String value1, String value2) {
            addCriterion("execution_status between", value1, value2, "executionStatus");
            return (Criteria) this;
        }

        public Criteria andExecutionStatusNotBetween(String value1, String value2) {
            addCriterion("execution_status not between", value1, value2, "executionStatus");
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