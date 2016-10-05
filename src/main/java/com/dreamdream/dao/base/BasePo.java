package com.dreamdream.dao.base;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.collect.Lists;

public class BasePo {

    private List<ConditionField> conditionFieldList=Lists.newLinkedList();
    private int limit;
    private int index;
    
    public void gt(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.GT).setValue(o));
    }
    
    public void gt(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.GT).setValue(f.get(this)));
    }
    
    public void gte(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.GTE).setValue(o));
    }
    
    public void gte(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.GTE).setValue(f.get(this)));
    }
    
    public void lt(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.LT).setValue(o));
    }
    
    public void lt(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.LT).setValue(f.get(this)));
    }
    
    public void lte(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.LTE).setValue(o));
    }
    
    public void lte(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.LTE).setValue(f.get(this)));
    }
    
    public void eq(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.EQ).setValue(o));
    }
    
    public void eq(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.EQ).setValue(f.get(this)));
    }
    
    public void ne(String field, Object o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.NE).setValue(o));
    }
    
    public void ne(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.NE).setValue(f.get(this)));
    }
    
    public void isNull(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.IS_NULL).setValue(null));
    }
    
    public void isNotNull(String field) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.IS_NOT_NULL).setValue(null));
    }
    
    public void in(String field, Object[] o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.IN).setValues(o));
    }
    public void notIn(String field, Object[] o) throws Exception {
        Field f = this.getClass().getDeclaredField(field);
        getConditionFieldList().add(new ConditionField().setFieldName(field).setCondition(CONDITION.NOT_IN).setValues(o));
    }
    
    public List<ConditionField> getConditionFieldList() {
        return conditionFieldList;
    }

    public void setConditionFieldList(List<ConditionField> conditionFieldList) {
        this.conditionFieldList = conditionFieldList;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public enum CONDITION {
        GT(">"),EQ("="),LT("<"),GTE(">="),LTE("<="),IN("in"),IS_NULL("is null"),IS_NOT_NULL("is not null"),NE("<>"),NOT_IN("not in");
        private String sign;
        private CONDITION(String sign){this.setSign(sign);}
        public String getSign() {
            return sign;
        }
        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
