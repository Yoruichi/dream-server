package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.view.DreamGreaterView;

@Repository
public class DreamGreaterViewDao extends BaseDao<DreamGreaterView>{

    @Override
    protected Class<DreamGreaterView> getEntityClass() {
        return DreamGreaterView.class;
    }

}
