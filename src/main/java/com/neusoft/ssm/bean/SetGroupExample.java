package com.neusoft.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SetGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SetGroupExample() {
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

        public Criteria andSetCodeIsNull() {
            addCriterion("set_code is null");
            return (Criteria) this;
        }

        public Criteria andSetCodeIsNotNull() {
            addCriterion("set_code is not null");
            return (Criteria) this;
        }

        public Criteria andSetCodeEqualTo(String value) {
            addCriterion("set_code =", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotEqualTo(String value) {
            addCriterion("set_code <>", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeGreaterThan(String value) {
            addCriterion("set_code >", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("set_code >=", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLessThan(String value) {
            addCriterion("set_code <", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLessThanOrEqualTo(String value) {
            addCriterion("set_code <=", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLike(String value) {
            addCriterion("set_code like", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotLike(String value) {
            addCriterion("set_code not like", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeIn(List<String> values) {
            addCriterion("set_code in", values, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotIn(List<String> values) {
            addCriterion("set_code not in", values, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeBetween(String value1, String value2) {
            addCriterion("set_code between", value1, value2, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotBetween(String value1, String value2) {
            addCriterion("set_code not between", value1, value2, "setCode");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyIsNull() {
            addCriterion("business_classify is null");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyIsNotNull() {
            addCriterion("business_classify is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyEqualTo(String value) {
            addCriterion("business_classify =", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyNotEqualTo(String value) {
            addCriterion("business_classify <>", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyGreaterThan(String value) {
            addCriterion("business_classify >", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("business_classify >=", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyLessThan(String value) {
            addCriterion("business_classify <", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyLessThanOrEqualTo(String value) {
            addCriterion("business_classify <=", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyLike(String value) {
            addCriterion("business_classify like", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyNotLike(String value) {
            addCriterion("business_classify not like", value, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyIn(List<String> values) {
            addCriterion("business_classify in", values, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyNotIn(List<String> values) {
            addCriterion("business_classify not in", values, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyBetween(String value1, String value2) {
            addCriterion("business_classify between", value1, value2, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andBusinessClassifyNotBetween(String value1, String value2) {
            addCriterion("business_classify not between", value1, value2, "businessClassify");
            return (Criteria) this;
        }

        public Criteria andSetNameIsNull() {
            addCriterion("set_name is null");
            return (Criteria) this;
        }

        public Criteria andSetNameIsNotNull() {
            addCriterion("set_name is not null");
            return (Criteria) this;
        }

        public Criteria andSetNameEqualTo(String value) {
            addCriterion("set_name =", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameNotEqualTo(String value) {
            addCriterion("set_name <>", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameGreaterThan(String value) {
            addCriterion("set_name >", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameGreaterThanOrEqualTo(String value) {
            addCriterion("set_name >=", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameLessThan(String value) {
            addCriterion("set_name <", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameLessThanOrEqualTo(String value) {
            addCriterion("set_name <=", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameLike(String value) {
            addCriterion("set_name like", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameNotLike(String value) {
            addCriterion("set_name not like", value, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameIn(List<String> values) {
            addCriterion("set_name in", values, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameNotIn(List<String> values) {
            addCriterion("set_name not in", values, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameBetween(String value1, String value2) {
            addCriterion("set_name between", value1, value2, "setName");
            return (Criteria) this;
        }

        public Criteria andSetNameNotBetween(String value1, String value2) {
            addCriterion("set_name not between", value1, value2, "setName");
            return (Criteria) this;
        }

        public Criteria andUseScopeIsNull() {
            addCriterion("use_scope is null");
            return (Criteria) this;
        }

        public Criteria andUseScopeIsNotNull() {
            addCriterion("use_scope is not null");
            return (Criteria) this;
        }

        public Criteria andUseScopeEqualTo(String value) {
            addCriterion("use_scope =", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeNotEqualTo(String value) {
            addCriterion("use_scope <>", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeGreaterThan(String value) {
            addCriterion("use_scope >", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeGreaterThanOrEqualTo(String value) {
            addCriterion("use_scope >=", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeLessThan(String value) {
            addCriterion("use_scope <", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeLessThanOrEqualTo(String value) {
            addCriterion("use_scope <=", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeLike(String value) {
            addCriterion("use_scope like", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeNotLike(String value) {
            addCriterion("use_scope not like", value, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeIn(List<String> values) {
            addCriterion("use_scope in", values, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeNotIn(List<String> values) {
            addCriterion("use_scope not in", values, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeBetween(String value1, String value2) {
            addCriterion("use_scope between", value1, value2, "useScope");
            return (Criteria) this;
        }

        public Criteria andUseScopeNotBetween(String value1, String value2) {
            addCriterion("use_scope not between", value1, value2, "useScope");
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

        public Criteria andBuildDateIsNull() {
            addCriterion("build_date is null");
            return (Criteria) this;
        }

        public Criteria andBuildDateIsNotNull() {
            addCriterion("build_date is not null");
            return (Criteria) this;
        }

        public Criteria andBuildDateEqualTo(Date value) {
            addCriterion("build_date =", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateNotEqualTo(Date value) {
            addCriterion("build_date <>", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateGreaterThan(Date value) {
            addCriterion("build_date >", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateGreaterThanOrEqualTo(Date value) {
            addCriterion("build_date >=", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateLessThan(Date value) {
            addCriterion("build_date <", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateLessThanOrEqualTo(Date value) {
            addCriterion("build_date <=", value, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateIn(List<Date> values) {
            addCriterion("build_date in", values, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateNotIn(List<Date> values) {
            addCriterion("build_date not in", values, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateBetween(Date value1, Date value2) {
            addCriterion("build_date between", value1, value2, "buildDate");
            return (Criteria) this;
        }

        public Criteria andBuildDateNotBetween(Date value1, Date value2) {
            addCriterion("build_date not between", value1, value2, "buildDate");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIsNull() {
            addCriterion("creater_id is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIsNotNull() {
            addCriterion("creater_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterIdEqualTo(Integer value) {
            addCriterion("creater_id =", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotEqualTo(Integer value) {
            addCriterion("creater_id <>", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdGreaterThan(Integer value) {
            addCriterion("creater_id >", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("creater_id >=", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdLessThan(Integer value) {
            addCriterion("creater_id <", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdLessThanOrEqualTo(Integer value) {
            addCriterion("creater_id <=", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIn(List<Integer> values) {
            addCriterion("creater_id in", values, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotIn(List<Integer> values) {
            addCriterion("creater_id not in", values, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdBetween(Integer value1, Integer value2) {
            addCriterion("creater_id between", value1, value2, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("creater_id not between", value1, value2, "createrId");
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