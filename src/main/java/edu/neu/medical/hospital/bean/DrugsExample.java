package edu.neu.medical.hospital.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DrugsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DrugsExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Short value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Short value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Short value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Short value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Short value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Short value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Short> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Short> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Short value1, Short value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Short value1, Short value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDrugscodeIsNull() {
            addCriterion("DrugsCode is null");
            return (Criteria) this;
        }

        public Criteria andDrugscodeIsNotNull() {
            addCriterion("DrugsCode is not null");
            return (Criteria) this;
        }

        public Criteria andDrugscodeEqualTo(String value) {
            addCriterion("DrugsCode =", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeNotEqualTo(String value) {
            addCriterion("DrugsCode <>", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeGreaterThan(String value) {
            addCriterion("DrugsCode >", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeGreaterThanOrEqualTo(String value) {
            addCriterion("DrugsCode >=", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeLessThan(String value) {
            addCriterion("DrugsCode <", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeLessThanOrEqualTo(String value) {
            addCriterion("DrugsCode <=", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeLike(String value) {
            addCriterion("DrugsCode like", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeNotLike(String value) {
            addCriterion("DrugsCode not like", value, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeIn(List<String> values) {
            addCriterion("DrugsCode in", values, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeNotIn(List<String> values) {
            addCriterion("DrugsCode not in", values, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeBetween(String value1, String value2) {
            addCriterion("DrugsCode between", value1, value2, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugscodeNotBetween(String value1, String value2) {
            addCriterion("DrugsCode not between", value1, value2, "drugscode");
            return (Criteria) this;
        }

        public Criteria andDrugsnameIsNull() {
            addCriterion("DrugsName is null");
            return (Criteria) this;
        }

        public Criteria andDrugsnameIsNotNull() {
            addCriterion("DrugsName is not null");
            return (Criteria) this;
        }

        public Criteria andDrugsnameEqualTo(String value) {
            addCriterion("DrugsName =", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameNotEqualTo(String value) {
            addCriterion("DrugsName <>", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameGreaterThan(String value) {
            addCriterion("DrugsName >", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameGreaterThanOrEqualTo(String value) {
            addCriterion("DrugsName >=", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameLessThan(String value) {
            addCriterion("DrugsName <", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameLessThanOrEqualTo(String value) {
            addCriterion("DrugsName <=", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameLike(String value) {
            addCriterion("DrugsName like", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameNotLike(String value) {
            addCriterion("DrugsName not like", value, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameIn(List<String> values) {
            addCriterion("DrugsName in", values, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameNotIn(List<String> values) {
            addCriterion("DrugsName not in", values, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameBetween(String value1, String value2) {
            addCriterion("DrugsName between", value1, value2, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsnameNotBetween(String value1, String value2) {
            addCriterion("DrugsName not between", value1, value2, "drugsname");
            return (Criteria) this;
        }

        public Criteria andDrugsformatIsNull() {
            addCriterion("DrugsFormat is null");
            return (Criteria) this;
        }

        public Criteria andDrugsformatIsNotNull() {
            addCriterion("DrugsFormat is not null");
            return (Criteria) this;
        }

        public Criteria andDrugsformatEqualTo(String value) {
            addCriterion("DrugsFormat =", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatNotEqualTo(String value) {
            addCriterion("DrugsFormat <>", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatGreaterThan(String value) {
            addCriterion("DrugsFormat >", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatGreaterThanOrEqualTo(String value) {
            addCriterion("DrugsFormat >=", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatLessThan(String value) {
            addCriterion("DrugsFormat <", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatLessThanOrEqualTo(String value) {
            addCriterion("DrugsFormat <=", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatLike(String value) {
            addCriterion("DrugsFormat like", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatNotLike(String value) {
            addCriterion("DrugsFormat not like", value, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatIn(List<String> values) {
            addCriterion("DrugsFormat in", values, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatNotIn(List<String> values) {
            addCriterion("DrugsFormat not in", values, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatBetween(String value1, String value2) {
            addCriterion("DrugsFormat between", value1, value2, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsformatNotBetween(String value1, String value2) {
            addCriterion("DrugsFormat not between", value1, value2, "drugsformat");
            return (Criteria) this;
        }

        public Criteria andDrugsunitIsNull() {
            addCriterion("DrugsUnit is null");
            return (Criteria) this;
        }

        public Criteria andDrugsunitIsNotNull() {
            addCriterion("DrugsUnit is not null");
            return (Criteria) this;
        }

        public Criteria andDrugsunitEqualTo(String value) {
            addCriterion("DrugsUnit =", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitNotEqualTo(String value) {
            addCriterion("DrugsUnit <>", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitGreaterThan(String value) {
            addCriterion("DrugsUnit >", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitGreaterThanOrEqualTo(String value) {
            addCriterion("DrugsUnit >=", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitLessThan(String value) {
            addCriterion("DrugsUnit <", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitLessThanOrEqualTo(String value) {
            addCriterion("DrugsUnit <=", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitLike(String value) {
            addCriterion("DrugsUnit like", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitNotLike(String value) {
            addCriterion("DrugsUnit not like", value, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitIn(List<String> values) {
            addCriterion("DrugsUnit in", values, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitNotIn(List<String> values) {
            addCriterion("DrugsUnit not in", values, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitBetween(String value1, String value2) {
            addCriterion("DrugsUnit between", value1, value2, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andDrugsunitNotBetween(String value1, String value2) {
            addCriterion("DrugsUnit not between", value1, value2, "drugsunit");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("Manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("Manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("Manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("Manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("Manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("Manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("Manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("Manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("Manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("Manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("Manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("Manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("Manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("Manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidIsNull() {
            addCriterion("DrugsDosageID is null");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidIsNotNull() {
            addCriterion("DrugsDosageID is not null");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidEqualTo(Short value) {
            addCriterion("DrugsDosageID =", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidNotEqualTo(Short value) {
            addCriterion("DrugsDosageID <>", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidGreaterThan(Short value) {
            addCriterion("DrugsDosageID >", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidGreaterThanOrEqualTo(Short value) {
            addCriterion("DrugsDosageID >=", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidLessThan(Short value) {
            addCriterion("DrugsDosageID <", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidLessThanOrEqualTo(Short value) {
            addCriterion("DrugsDosageID <=", value, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidIn(List<Short> values) {
            addCriterion("DrugsDosageID in", values, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidNotIn(List<Short> values) {
            addCriterion("DrugsDosageID not in", values, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidBetween(Short value1, Short value2) {
            addCriterion("DrugsDosageID between", value1, value2, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugsdosageidNotBetween(Short value1, Short value2) {
            addCriterion("DrugsDosageID not between", value1, value2, "drugsdosageid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidIsNull() {
            addCriterion("DrugsTypeID is null");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidIsNotNull() {
            addCriterion("DrugsTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidEqualTo(Short value) {
            addCriterion("DrugsTypeID =", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidNotEqualTo(Short value) {
            addCriterion("DrugsTypeID <>", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidGreaterThan(Short value) {
            addCriterion("DrugsTypeID >", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidGreaterThanOrEqualTo(Short value) {
            addCriterion("DrugsTypeID >=", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidLessThan(Short value) {
            addCriterion("DrugsTypeID <", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidLessThanOrEqualTo(Short value) {
            addCriterion("DrugsTypeID <=", value, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidIn(List<Short> values) {
            addCriterion("DrugsTypeID in", values, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidNotIn(List<Short> values) {
            addCriterion("DrugsTypeID not in", values, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidBetween(Short value1, Short value2) {
            addCriterion("DrugsTypeID between", value1, value2, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugstypeidNotBetween(Short value1, Short value2) {
            addCriterion("DrugsTypeID not between", value1, value2, "drugstypeid");
            return (Criteria) this;
        }

        public Criteria andDrugspriceIsNull() {
            addCriterion("DrugsPrice is null");
            return (Criteria) this;
        }

        public Criteria andDrugspriceIsNotNull() {
            addCriterion("DrugsPrice is not null");
            return (Criteria) this;
        }

        public Criteria andDrugspriceEqualTo(Float value) {
            addCriterion("DrugsPrice =", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceNotEqualTo(Float value) {
            addCriterion("DrugsPrice <>", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceGreaterThan(Float value) {
            addCriterion("DrugsPrice >", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceGreaterThanOrEqualTo(Float value) {
            addCriterion("DrugsPrice >=", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceLessThan(Float value) {
            addCriterion("DrugsPrice <", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceLessThanOrEqualTo(Float value) {
            addCriterion("DrugsPrice <=", value, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceIn(List<Float> values) {
            addCriterion("DrugsPrice in", values, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceNotIn(List<Float> values) {
            addCriterion("DrugsPrice not in", values, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceBetween(Float value1, Float value2) {
            addCriterion("DrugsPrice between", value1, value2, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andDrugspriceNotBetween(Float value1, Float value2) {
            addCriterion("DrugsPrice not between", value1, value2, "drugsprice");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeIsNull() {
            addCriterion("MnemonicCode is null");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeIsNotNull() {
            addCriterion("MnemonicCode is not null");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeEqualTo(String value) {
            addCriterion("MnemonicCode =", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeNotEqualTo(String value) {
            addCriterion("MnemonicCode <>", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeGreaterThan(String value) {
            addCriterion("MnemonicCode >", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeGreaterThanOrEqualTo(String value) {
            addCriterion("MnemonicCode >=", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeLessThan(String value) {
            addCriterion("MnemonicCode <", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeLessThanOrEqualTo(String value) {
            addCriterion("MnemonicCode <=", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeLike(String value) {
            addCriterion("MnemonicCode like", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeNotLike(String value) {
            addCriterion("MnemonicCode not like", value, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeIn(List<String> values) {
            addCriterion("MnemonicCode in", values, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeNotIn(List<String> values) {
            addCriterion("MnemonicCode not in", values, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeBetween(String value1, String value2) {
            addCriterion("MnemonicCode between", value1, value2, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andMnemoniccodeNotBetween(String value1, String value2) {
            addCriterion("MnemonicCode not between", value1, value2, "mnemoniccode");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNull() {
            addCriterion("CreationDate is null");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNotNull() {
            addCriterion("CreationDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreationdateEqualTo(Date value) {
            addCriterionForJDBCDate("CreationDate =", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CreationDate <>", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThan(Date value) {
            addCriterionForJDBCDate("CreationDate >", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CreationDate >=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThan(Date value) {
            addCriterionForJDBCDate("CreationDate <", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CreationDate <=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateIn(List<Date> values) {
            addCriterionForJDBCDate("CreationDate in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CreationDate not in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CreationDate between", value1, value2, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CreationDate not between", value1, value2, "creationdate");
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