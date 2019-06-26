package com.neusoft.ssm.bean;

import java.util.ArrayList;
import java.util.List;

public class VisitItemDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitItemDetailExample() {
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

        public Criteria andVisitItemIdIsNull() {
            addCriterion("visit_item_id is null");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdIsNotNull() {
            addCriterion("visit_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdEqualTo(Integer value) {
            addCriterion("visit_item_id =", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdNotEqualTo(Integer value) {
            addCriterion("visit_item_id <>", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdGreaterThan(Integer value) {
            addCriterion("visit_item_id >", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("visit_item_id >=", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdLessThan(Integer value) {
            addCriterion("visit_item_id <", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("visit_item_id <=", value, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdIn(List<Integer> values) {
            addCriterion("visit_item_id in", values, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdNotIn(List<Integer> values) {
            addCriterion("visit_item_id not in", values, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdBetween(Integer value1, Integer value2) {
            addCriterion("visit_item_id between", value1, value2, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andVisitItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("visit_item_id not between", value1, value2, "visitItemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdIsNull() {
            addCriterion("fmeditem_id is null");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdIsNotNull() {
            addCriterion("fmeditem_id is not null");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdEqualTo(Integer value) {
            addCriterion("fmeditem_id =", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdNotEqualTo(Integer value) {
            addCriterion("fmeditem_id <>", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdGreaterThan(Integer value) {
            addCriterion("fmeditem_id >", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fmeditem_id >=", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdLessThan(Integer value) {
            addCriterion("fmeditem_id <", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdLessThanOrEqualTo(Integer value) {
            addCriterion("fmeditem_id <=", value, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdIn(List<Integer> values) {
            addCriterion("fmeditem_id in", values, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdNotIn(List<Integer> values) {
            addCriterion("fmeditem_id not in", values, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdBetween(Integer value1, Integer value2) {
            addCriterion("fmeditem_id between", value1, value2, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andFmeditemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fmeditem_id not between", value1, value2, "fmeditemId");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentIsNull() {
            addCriterion("doctor_entrustment is null");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentIsNotNull() {
            addCriterion("doctor_entrustment is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentEqualTo(String value) {
            addCriterion("doctor_entrustment =", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentNotEqualTo(String value) {
            addCriterion("doctor_entrustment <>", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentGreaterThan(String value) {
            addCriterion("doctor_entrustment >", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentGreaterThanOrEqualTo(String value) {
            addCriterion("doctor_entrustment >=", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentLessThan(String value) {
            addCriterion("doctor_entrustment <", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentLessThanOrEqualTo(String value) {
            addCriterion("doctor_entrustment <=", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentLike(String value) {
            addCriterion("doctor_entrustment like", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentNotLike(String value) {
            addCriterion("doctor_entrustment not like", value, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentIn(List<String> values) {
            addCriterion("doctor_entrustment in", values, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentNotIn(List<String> values) {
            addCriterion("doctor_entrustment not in", values, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentBetween(String value1, String value2) {
            addCriterion("doctor_entrustment between", value1, value2, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andDoctorEntrustmentNotBetween(String value1, String value2) {
            addCriterion("doctor_entrustment not between", value1, value2, "doctorEntrustment");
            return (Criteria) this;
        }

        public Criteria andAddItemIsNull() {
            addCriterion("add_item is null");
            return (Criteria) this;
        }

        public Criteria andAddItemIsNotNull() {
            addCriterion("add_item is not null");
            return (Criteria) this;
        }

        public Criteria andAddItemEqualTo(String value) {
            addCriterion("add_item =", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemNotEqualTo(String value) {
            addCriterion("add_item <>", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemGreaterThan(String value) {
            addCriterion("add_item >", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemGreaterThanOrEqualTo(String value) {
            addCriterion("add_item >=", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemLessThan(String value) {
            addCriterion("add_item <", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemLessThanOrEqualTo(String value) {
            addCriterion("add_item <=", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemLike(String value) {
            addCriterion("add_item like", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemNotLike(String value) {
            addCriterion("add_item not like", value, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemIn(List<String> values) {
            addCriterion("add_item in", values, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemNotIn(List<String> values) {
            addCriterion("add_item not in", values, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemBetween(String value1, String value2) {
            addCriterion("add_item between", value1, value2, "addItem");
            return (Criteria) this;
        }

        public Criteria andAddItemNotBetween(String value1, String value2) {
            addCriterion("add_item not between", value1, value2, "addItem");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdIsNull() {
            addCriterion("execution_doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdIsNotNull() {
            addCriterion("execution_doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdEqualTo(Integer value) {
            addCriterion("execution_doctor_id =", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdNotEqualTo(Integer value) {
            addCriterion("execution_doctor_id <>", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdGreaterThan(Integer value) {
            addCriterion("execution_doctor_id >", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("execution_doctor_id >=", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdLessThan(Integer value) {
            addCriterion("execution_doctor_id <", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdLessThanOrEqualTo(Integer value) {
            addCriterion("execution_doctor_id <=", value, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdIn(List<Integer> values) {
            addCriterion("execution_doctor_id in", values, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdNotIn(List<Integer> values) {
            addCriterion("execution_doctor_id not in", values, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdBetween(Integer value1, Integer value2) {
            addCriterion("execution_doctor_id between", value1, value2, "executionDoctorId");
            return (Criteria) this;
        }

        public Criteria andExecutionDoctorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("execution_doctor_id not between", value1, value2, "executionDoctorId");
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