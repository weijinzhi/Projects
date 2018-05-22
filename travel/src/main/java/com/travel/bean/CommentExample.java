package com.travel.bean;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCommentidIsNull() {
            addCriterion("commentid is null");
            return (Criteria) this;
        }

        public Criteria andCommentidIsNotNull() {
            addCriterion("commentid is not null");
            return (Criteria) this;
        }

        public Criteria andCommentidEqualTo(Long value) {
            addCriterion("commentid =", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotEqualTo(Long value) {
            addCriterion("commentid <>", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThan(Long value) {
            addCriterion("commentid >", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThanOrEqualTo(Long value) {
            addCriterion("commentid >=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThan(Long value) {
            addCriterion("commentid <", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThanOrEqualTo(Long value) {
            addCriterion("commentid <=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidIn(List<Long> values) {
            addCriterion("commentid in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotIn(List<Long> values) {
            addCriterion("commentid not in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidBetween(Long value1, Long value2) {
            addCriterion("commentid between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotBetween(Long value1, Long value2) {
            addCriterion("commentid not between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andGuideidIsNull() {
            addCriterion("guideid is null");
            return (Criteria) this;
        }

        public Criteria andGuideidIsNotNull() {
            addCriterion("guideid is not null");
            return (Criteria) this;
        }

        public Criteria andGuideidEqualTo(Long value) {
            addCriterion("guideid =", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidNotEqualTo(Long value) {
            addCriterion("guideid <>", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidGreaterThan(Long value) {
            addCriterion("guideid >", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidGreaterThanOrEqualTo(Long value) {
            addCriterion("guideid >=", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidLessThan(Long value) {
            addCriterion("guideid <", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidLessThanOrEqualTo(Long value) {
            addCriterion("guideid <=", value, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidIn(List<Long> values) {
            addCriterion("guideid in", values, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidNotIn(List<Long> values) {
            addCriterion("guideid not in", values, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidBetween(Long value1, Long value2) {
            addCriterion("guideid between", value1, value2, "guideid");
            return (Criteria) this;
        }

        public Criteria andGuideidNotBetween(Long value1, Long value2) {
            addCriterion("guideid not between", value1, value2, "guideid");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNull() {
            addCriterion("groupid is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Long value) {
            addCriterion("groupid =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Long value) {
            addCriterion("groupid <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Long value) {
            addCriterion("groupid >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Long value) {
            addCriterion("groupid >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Long value) {
            addCriterion("groupid <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Long value) {
            addCriterion("groupid <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Long> values) {
            addCriterion("groupid in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Long> values) {
            addCriterion("groupid not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Long value1, Long value2) {
            addCriterion("groupid between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Long value1, Long value2) {
            addCriterion("groupid not between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGuidecommentIsNull() {
            addCriterion("guidecomment is null");
            return (Criteria) this;
        }

        public Criteria andGuidecommentIsNotNull() {
            addCriterion("guidecomment is not null");
            return (Criteria) this;
        }

        public Criteria andGuidecommentEqualTo(Float value) {
            addCriterion("guidecomment =", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentNotEqualTo(Float value) {
            addCriterion("guidecomment <>", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentGreaterThan(Float value) {
            addCriterion("guidecomment >", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentGreaterThanOrEqualTo(Float value) {
            addCriterion("guidecomment >=", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentLessThan(Float value) {
            addCriterion("guidecomment <", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentLessThanOrEqualTo(Float value) {
            addCriterion("guidecomment <=", value, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentIn(List<Float> values) {
            addCriterion("guidecomment in", values, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentNotIn(List<Float> values) {
            addCriterion("guidecomment not in", values, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentBetween(Float value1, Float value2) {
            addCriterion("guidecomment between", value1, value2, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andGuidecommentNotBetween(Float value1, Float value2) {
            addCriterion("guidecomment not between", value1, value2, "guidecomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentIsNull() {
            addCriterion("tourcomment is null");
            return (Criteria) this;
        }

        public Criteria andTourcommentIsNotNull() {
            addCriterion("tourcomment is not null");
            return (Criteria) this;
        }

        public Criteria andTourcommentEqualTo(Float value) {
            addCriterion("tourcomment =", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentNotEqualTo(Float value) {
            addCriterion("tourcomment <>", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentGreaterThan(Float value) {
            addCriterion("tourcomment >", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentGreaterThanOrEqualTo(Float value) {
            addCriterion("tourcomment >=", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentLessThan(Float value) {
            addCriterion("tourcomment <", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentLessThanOrEqualTo(Float value) {
            addCriterion("tourcomment <=", value, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentIn(List<Float> values) {
            addCriterion("tourcomment in", values, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentNotIn(List<Float> values) {
            addCriterion("tourcomment not in", values, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentBetween(Float value1, Float value2) {
            addCriterion("tourcomment between", value1, value2, "tourcomment");
            return (Criteria) this;
        }

        public Criteria andTourcommentNotBetween(Float value1, Float value2) {
            addCriterion("tourcomment not between", value1, value2, "tourcomment");
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