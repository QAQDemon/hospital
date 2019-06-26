package com.neusoft.ssm.bean;

import java.util.ArrayList;
import java.util.List;

public class VisitItemResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitItemResultExample() {
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

        public Criteria andVisitRecordDetailIdIsNull() {
            addCriterion("visit_record_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdIsNotNull() {
            addCriterion("visit_record_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdEqualTo(Integer value) {
            addCriterion("visit_record_detail_id =", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdNotEqualTo(Integer value) {
            addCriterion("visit_record_detail_id <>", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdGreaterThan(Integer value) {
            addCriterion("visit_record_detail_id >", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("visit_record_detail_id >=", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdLessThan(Integer value) {
            addCriterion("visit_record_detail_id <", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("visit_record_detail_id <=", value, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdIn(List<Integer> values) {
            addCriterion("visit_record_detail_id in", values, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdNotIn(List<Integer> values) {
            addCriterion("visit_record_detail_id not in", values, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("visit_record_detail_id between", value1, value2, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andVisitRecordDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("visit_record_detail_id not between", value1, value2, "visitRecordDetailId");
            return (Criteria) this;
        }

        public Criteria andDescribetionIsNull() {
            addCriterion("describetion is null");
            return (Criteria) this;
        }

        public Criteria andDescribetionIsNotNull() {
            addCriterion("describetion is not null");
            return (Criteria) this;
        }

        public Criteria andDescribetionEqualTo(String value) {
            addCriterion("describetion =", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionNotEqualTo(String value) {
            addCriterion("describetion <>", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionGreaterThan(String value) {
            addCriterion("describetion >", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionGreaterThanOrEqualTo(String value) {
            addCriterion("describetion >=", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionLessThan(String value) {
            addCriterion("describetion <", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionLessThanOrEqualTo(String value) {
            addCriterion("describetion <=", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionLike(String value) {
            addCriterion("describetion like", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionNotLike(String value) {
            addCriterion("describetion not like", value, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionIn(List<String> values) {
            addCriterion("describetion in", values, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionNotIn(List<String> values) {
            addCriterion("describetion not in", values, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionBetween(String value1, String value2) {
            addCriterion("describetion between", value1, value2, "describetion");
            return (Criteria) this;
        }

        public Criteria andDescribetionNotBetween(String value1, String value2) {
            addCriterion("describetion not between", value1, value2, "describetion");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
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