package com.dreamdream.dao;

import com.dreamdream.dao.BasePo.CONDITION;

public class ConditionField {
    private String fieldName;
    private CONDITION condition;
    private Object value;
    private Object[] values;

    public String getFieldName() {
        return fieldName;
    }

    public ConditionField setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public CONDITION getCondition() {
        return condition;
    }

    public ConditionField setCondition(CONDITION condition) {
        this.condition = condition;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public ConditionField setValue(Object value) {
        this.value = value;
        return this;
    }

    public Object[] getValues() {
        return values;
    }

    public ConditionField setValues(Object[] values) {
        this.values = values;
        return this;
    }
}
