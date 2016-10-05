package com.dreamdream.dao.base;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.dreamdream.dao.base.SqlBuilder.*;
import com.google.common.collect.Lists;

public abstract class BaseDao<T extends BasePo> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract Class<T> getEntityClass();

    @Autowired
    protected JdbcTemplate template;

    protected final ResultSetExtractor<List<T>> rseList = new ResultSetExtractor<List<T>>() {

        @Override
        public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<T> l = Lists.newLinkedList();
            Class<T> clazz = getEntityClass();
            try {
                while (rs.next()) {
                    T i = clazz.newInstance();
                    Field[] fields = clazz.getDeclaredFields();
                    for (int j = 0; j < fields.length; j++) {
                        int columnIndex =
                                rs.findColumn(SqlBuilder.getDbName(fields[j].getName()));
                        if (columnIndex > -1) {
                            fields[j].setAccessible(true);
                            fields[j].set(i, rs.getObject(columnIndex));
                        }
                    }
                    l.add(i);
                }
            } catch (Exception e) {
                logger.error("Error when processing class match {} .Caused by {}", clazz, e);
                e.printStackTrace();
            }
            return l;
        }
    };

    public void insertMany(List<T> list) throws Exception {
        try {
            InsertManyNeed ind = SqlBuilder.getInsertManyNeed(list);
            if (ind == null) {
                logger.warn("Warn no valid value when insert list of {}", list);
                return;
            }
            logger.info("running:{} with args{} based on list {}", ind.sql, ind.args, list);
            getTemplate().batchUpdate(ind.sql, ind.args);
        }catch (Exception e) {
            logger.error("Error when insert list of {}.Caused by:", list, e);
            throw e;
        }
    }
    
    public void insertOrUpdate(T o) throws Exception {
        try {
            InsertOrUpdateNeed ind = SqlBuilder.getInsertOrUpdateNeed(o);
            if (ind == null) {
                logger.warn("Warn no valid value when insert or update po class{}", o);
                return;
            }
            logger.info("running:{} with args{} based on class{}", ind.sql, ind.args, o);
            getTemplate().update(ind.sql, ind.args);
        } catch (Exception e) {
            logger.error("Error when insert or update po class{}.Caused by:{}", o, e);
            throw e;
        }
    }
    public List<T> select(final T o, String orderBy, boolean asc, int limit, int index) throws Exception {
        List<T> list = Lists.newArrayList();
        try {
            SelectNeed sed = SqlBuilder.getSelectManyNeed(o, orderBy, asc, limit, index);
            if (sed == null) {
                logger.warn("Warn no valid value when insert or update po class{}", o);
                return list;
            }
            list = getTemplate().query(sed.sql, sed.args, rseList);
            logger.info("running:{} with args{} based on class{} got result{}", sed.sql, sed.args,
                    o, list);
        } catch (Exception e) {
            logger.error("Error when select many of class{}.Caused by {}", o, e);
            throw e;
        }
        return list;
    }
    public List<T> select(final T o, String orderBy, boolean asc) throws Exception {
        List<T> list = Lists.newArrayList();
        try {
            SelectNeed sed = SqlBuilder.getSelectManyNeed(o, orderBy, asc);
            if (sed == null) {
                logger.warn("Warn no valid value when insert or update po class{}", o);
                return list;
            }
            list = getTemplate().query(sed.sql, sed.args, rseList);
            logger.info("running:{} with args{} based on class{} got result{}", sed.sql, sed.args,
                    o, list);
        } catch (Exception e) {
            logger.error("Error when select many of class{}.Caused by {}", o, e);
            throw e;
        }
        return list;
    }

    public T select(final T o) throws Exception {
        T t = null;
        try {
            SelectNeed sed = SqlBuilder.getSelectOneNeed(o);
            if (sed == null) {
                logger.warn("Warn no valid value when select one of class{}", o);
                return null;
            }
            List<T> l = getTemplate().query(sed.sql, sed.args, rseList);
            if (l.size() > 0)
                t = l.get(0);
            logger.info("running:{} with args{} based on class{} got result{}", sed.sql, sed.args,
                    o, t);
        } catch (Exception e) {
            logger.error("Error when select one of class{}.Caused by {}", o, e);
            throw e;
        }
        return t;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

}
