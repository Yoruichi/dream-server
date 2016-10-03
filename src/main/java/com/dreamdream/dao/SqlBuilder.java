package com.dreamdream.dao;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.dreamdream.dao.BasePo;
import com.dreamdream.dao.ConditionField;

public class SqlBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SqlBuilder.class);

    private static final List<Character> upper =
            ImmutableList.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final List<Character> lower =
            ImmutableList.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    public static String getInsertManySql(Class<? extends Object> c) {
        return getInsertManySql(c, c.getDeclaredFields());
    }

    public static String getInsertManySql(Class<? extends Object> c, String[] strings) {
        Field[] fields = new Field[strings.length];
        for (int i = 0; i < strings.length; i++) {
            try {
                fields[i] = c.getDeclaredField(strings[i]);
            } catch (Exception e) {
                logger.error("Error when get field {} from {}.Causd by {}", strings[i], c.getName(),
                        e);
                fields[i] = null;
            }
        }
        return getInsertManySql(c, fields);
    }

    public static String getInsertManySql(Class<? extends Object> c, Field[] includeFields) {
        StringBuilder sb = new StringBuilder();
        String tableName = getDbName(c.getSimpleName());
        sb.append("insert into `").append(tableName).append("` (");
        List<Field> inList = Arrays.asList(includeFields);
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (inList.contains(fields[i])) {
                sb.append("`").append(getDbName(fields[i].getName())).append("`,");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), ") values (");
        for (int i = 0; i < fields.length; i++) {
            if (inList.contains(fields[i])) {
                sb.append("?").append(",");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }

    public static String getInsertOrUpdateSql(Class<? extends Object> c) {
        return getInsertOrUpdateSql(c, new String[] {});
    }

    public static String getInsertOrUpdateSql(Class<? extends Object> c, String[] strings) {
        Field[] fields = new Field[strings.length];
        for (int i = 0; i < strings.length; i++) {
            try {
                fields[i] = c.getDeclaredField(strings[i]);
            } catch (Exception e) {
                logger.error("Error when get field {} from {}.Causd by {}", strings[i], c.getName(),
                        e);
                fields[i] = null;
            }
        }
        return getInsertOrUpdateSql(c, fields);
    }

    public static String getDbName(String zName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zName.length(); i++) {
            char l = zName.charAt(i);
            if (upper.contains(l)) {
                if (i == 0) {
                    sb.append(lower.get(upper.indexOf(l)));
                } else {
                    sb.append("_").append(lower.get(upper.indexOf(l)));
                }
            } else {
                sb.append(l);
            }
        }
        return sb.toString();
    }

    public static String getInsertOrUpdateSql(Class<? extends Object> c,
            List<Field> includeFields) {
        return getInsertOrUpdateSql(c, includeFields.toArray(new Field[includeFields.size()]));
    }

    public static String getInsertOrUpdateSql(Class<? extends Object> c, Field[] includeFields) {
        StringBuilder sb = new StringBuilder();
        String tableName = getDbName(c.getSimpleName());
        List<Field> inList = Arrays.asList(includeFields);
        sb.append("insert into `").append(tableName).append("` (");
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (inList.contains(fields[i])) {
                sb.append("`").append(getDbName(fields[i].getName())).append("`,");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), ") values (");
        for (int i = 0; i < fields.length; i++) {
            if (inList.contains(fields[i])) {
                sb.append("?").append(",");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), ") on duplicate key update");
        for (int i = 0; i < fields.length; i++) {
            if (inList.contains(fields[i])) {
                sb.append(" `").append(getDbName(fields[i].getName())).append("` = ? ,");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public static class InsertOrUpdateNeed {
        public String sql;
        public Object[] args;

        public InsertOrUpdateNeed(String sql, Object[] args) {
            this.sql = sql;
            this.args = args;
        }
    }
    public static class InsertManyNeed {
        public String sql;
        public List<Object[]> args;

        public InsertManyNeed(String sql, List<Object[]> args) {
            this.sql = sql;
            this.args = args;
        }
    }

    public static InsertOrUpdateNeed getInsertOrUpdateNeed(Object o) throws Exception {
        Class<? extends Object> clazz = o.getClass();
        Field[] fs = clazz.getDeclaredFields();
        List<Field> inc = Lists.newLinkedList();
        List<Object> obs = Lists.newLinkedList();
        List<Object> obss = Lists.newLinkedList();
        for (int i = 0; i < fs.length; i++) {
            fs[i].setAccessible(true);
            Object v = fs[i].get(o);
            if (v != null) {
                inc.add(fs[i]);
                obs.add(v);
                obss.add(v);
            }
        }
        obs.addAll(obss);
        if (inc.size() == 0) {
            return null;
        }
        return new InsertOrUpdateNeed(getInsertOrUpdateSql(clazz, inc), obs.toArray());
    }

    public static class SelectNeed {
        public String sql;
        public Object[] args;

        public SelectNeed(String sql, Object[] args) {
            this.sql = sql;
            this.args = args;
        }
    }

    public static String getSelectOneSql(Class<? extends BasePo> c,
            List<ConditionField> conditionFields) {
        return getSelectOneSql(c, conditionFields, Arrays.asList(c.getDeclaredFields()));
    }

    public static String getSelectOneSql(Class<? extends BasePo> c,
            List<ConditionField> conditionFields, List<Field> includeFields) {
        return getSelectSql(c, conditionFields, includeFields, null, false);
    }

    public static String getSelectSql(Class<? extends BasePo> c,
            List<ConditionField> conditionFields, List<Field> includeFields, Field orderField,
            boolean asc) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        for (Field field : includeFields) {
            sb.append("`").append(getDbName(field.getName())).append("`,");
        }
        sb.replace(sb.length() - 1, sb.length(), " from `").append(getDbName(c.getSimpleName()))
                .append("`");
        if (conditionFields.size() > 0) {
            sb.append(" where");
            for (ConditionField cf : conditionFields) {
                sb.append(" `").append(getDbName(cf.getFieldName())).append("` ");
                switch (cf.getCondition()) {
                    case IN:
                    case NOT_IN:
                        String values = Lists.newArrayList(cf.getValues()).toString();
                        sb.append(cf.getCondition().getSign()).append(" (")
                                .append(values.substring(1, values.length() - 1)).append(")");
                        break;
                    case IS_NULL:
                    case IS_NOT_NULL:
                        sb.append(cf.getCondition().getSign());
                        break;
                    default:
                        sb.append(cf.getCondition().getSign()).append(" ?");
                        break;
                }
                sb.append(" and");
            }
            sb.replace(sb.length() - 3, sb.length(), "");
        }
        if (orderField != null) {
            sb.append(" order by `").append(getDbName(orderField.getName()))
                    .append(asc ? "` asc" : " desc");
        }
        return sb.toString();
    }

    public static SelectNeed getSelectOneNeed(BasePo o) throws Exception {
        Class<? extends BasePo> clazz = o.getClass();
        Field[] fs = clazz.getDeclaredFields();
        String[] includeFields = new String[fs.length];
        for (int i = 0; i < fs.length; i++) {
            includeFields[i] = fs[i].getName();
        }
        return getSelectOneNeed(o, includeFields);
    }

    public static SelectNeed getSelectOneNeed(BasePo o, String[] includeFields) throws Exception {
        Class<? extends BasePo> clazz = o.getClass();
        List<Field> inc = Lists.newLinkedList();
        for (int i = 0; i < includeFields.length; i++) {
            inc.add(clazz.getDeclaredField(includeFields[i]));
        }
        setBasePoReady(o);
        List<Object> obs = Lists.newLinkedList();
        for (ConditionField cf : o.getConditionFieldList()) {
            if (cf.getCondition() != BasePo.CONDITION.IN
                    && cf.getCondition() != BasePo.CONDITION.NOT_IN
                    && cf.getCondition() != BasePo.CONDITION.IS_NULL
                    && cf.getCondition() != BasePo.CONDITION.IS_NOT_NULL)
                obs.add(cf.getValue());
        }
        if (inc.size() == 0) {
            return null;
        }
        return new SelectNeed(getSelectOneSql(clazz, o.getConditionFieldList(), inc),
                obs.toArray());
    }

    public static SelectNeed getSelectManyNeed(BasePo o) throws Exception {
        return getSelectManyNeed(o, "id", true);
    }

    public static SelectNeed getSelectManyNeed(BasePo o, String orderField, boolean asc)
            throws Exception {
        Class<? extends BasePo> clazz = o.getClass();
        Field[] fs = clazz.getDeclaredFields();
        String[] includeFields = new String[fs.length];
        for (int i = 0; i < fs.length; i++) {
            includeFields[i] = fs[i].getName();
        }
        Field orderByField = clazz.getDeclaredField(orderField);
        return getSelectManyNeed(o, includeFields, orderByField, asc);
    }

    public static void setBasePoReady(BasePo o) throws Exception {
        Class<? extends BasePo> clazz = o.getClass();
        if (o.getConditionFieldList().size() == 0) {
            Field[] fs = clazz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                fs[i].setAccessible(true);
                Object v = fs[i].get(o);
                if (v != null) {
                    o.eq(fs[i].getName());
                }
            }
        }
    }

    public static SelectNeed getSelectManyNeed(BasePo o, String[] includeFields, Field orderByField,
            boolean asc) throws Exception {
        Class<? extends BasePo> clazz = o.getClass();
        List<Field> inc = Lists.newLinkedList();
        for (int i = 0; i < includeFields.length; i++) {
            inc.add(clazz.getDeclaredField(includeFields[i]));
        }
        setBasePoReady(o);
        if (inc.size() == 0) {
            return null;
        }
        return new SelectNeed(
                getSelectSql(clazz, o.getConditionFieldList(), inc, orderByField, asc),
                getSelectArgs(o));
    }

    public static Object[] getSelectArgs(BasePo o) {
        List<Object> obs = Lists.newLinkedList();
        for (ConditionField cf : o.getConditionFieldList()) {
            if (cf.getCondition() != BasePo.CONDITION.IN
                    && cf.getCondition() != BasePo.CONDITION.NOT_IN
                    && cf.getCondition() != BasePo.CONDITION.IS_NULL
                    && cf.getCondition() != BasePo.CONDITION.IS_NOT_NULL)
                obs.add(cf.getValue());
        }
        return obs.toArray();
    }

    public static InsertManyNeed getInsertManyNeed(List<? extends Object> os) throws Exception {
        List<Object[]> args = Lists.newLinkedList();
        List<Field> inc = Lists.newLinkedList();
        Class<? extends Object> clazz = os.get(0).getClass();
        Field[] fs = clazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            fs[i].setAccessible(true);
            Object v = fs[i].get(os.get(0));
            if (v != null) {
                inc.add(fs[i]);
            }
        }
        for (Object o : os) {
            if (o.getClass().equals(clazz)) {
                List<Object> arg = Lists.newLinkedList();
                for (int i = 0; i < fs.length; i++) {
                    fs[i].setAccessible(true);
                    Object v = fs[i].get(o);
                    if (v != null) {
                        arg.add(v);
                    }
                }
                args.add(arg.toArray());
            }
        }
        return new InsertManyNeed(getInsertManySql(clazz, inc), args);
    }

    public static String getInsertManySql(Class<? extends Object> c, List<Field> includeFields) {
        return getInsertManySql(c, includeFields.toArray(new Field[includeFields.size()]));
    }
}
