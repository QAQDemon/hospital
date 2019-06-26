package com.neusoft.ssm.bean;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andTrustNameIsNull() {
            addCriterion("trust_name is null");
            return (Criteria) this;
        }

        public Criteria andTrustNameIsNotNull() {
            addCriterion("trust_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrustNameEqualTo(String value) {
            addCriterion("trust_name =", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameNotEqualTo(String value) {
            addCriterion("trust_name <>", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameGreaterThan(String value) {
            addCriterion("trust_name >", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameGreaterThanOrEqualTo(String value) {
            addCriterion("trust_name >=", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameLessThan(String value) {
            addCriterion("trust_name <", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameLessThanOrEqualTo(String value) {
            addCriterion("trust_name <=", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameLike(String value) {
            addCriterion("trust_name like", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameNotLike(String value) {
            addCriterion("trust_name not like", value, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameIn(List<String> values) {
            addCriterion("trust_name in", values, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameNotIn(List<String> values) {
            addCriterion("trust_name not in", values, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameBetween(String value1, String value2) {
            addCriterion("trust_name between", value1, value2, "trustName");
            return (Criteria) this;
        }

        public Criteria andTrustNameNotBetween(String value1, String value2) {
            addCriterion("trust_name not between", value1, value2, "trustName");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIsNull() {
            addCriterion("user_category is null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIsNotNull() {
            addCriterion("user_category is not null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryEqualTo(String value) {
            addCriterion("user_category =", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNotEqualTo(String value) {
            addCriterion("user_category <>", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryGreaterThan(String value) {
            addCriterion("user_category >", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("user_category >=", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryLessThan(String value) {
            addCriterion("user_category <", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryLessThanOrEqualTo(String value) {
            addCriterion("user_category <=", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryLike(String value) {
            addCriterion("user_category like", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNotLike(String value) {
            addCriterion("user_category not like", value, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIn(List<String> values) {
            addCriterion("user_category in", values, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNotIn(List<String> values) {
            addCriterion("user_category not in", values, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryBetween(String value1, String value2) {
            addCriterion("user_category between", value1, value2, "userCategory");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNotBetween(String value1, String value2) {
            addCriterion("user_category not between", value1, value2, "userCategory");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIsNull() {
            addCriterion("professional_title is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIsNotNull() {
            addCriterion("professional_title is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleEqualTo(String value) {
            addCriterion("professional_title =", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNotEqualTo(String value) {
            addCriterion("professional_title <>", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleGreaterThan(String value) {
            addCriterion("professional_title >", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleGreaterThanOrEqualTo(String value) {
            addCriterion("professional_title >=", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleLessThan(String value) {
            addCriterion("professional_title <", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleLessThanOrEqualTo(String value) {
            addCriterion("professional_title <=", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleLike(String value) {
            addCriterion("professional_title like", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNotLike(String value) {
            addCriterion("professional_title not like", value, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIn(List<String> values) {
            addCriterion("professional_title in", values, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNotIn(List<String> values) {
            addCriterion("professional_title not in", values, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleBetween(String value1, String value2) {
            addCriterion("professional_title between", value1, value2, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNotBetween(String value1, String value2) {
            addCriterion("professional_title not between", value1, value2, "professionalTitle");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
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