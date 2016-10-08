package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.model.DreamGreater;

@Repository
public class DreamGreaterDao extends BaseDao<DreamGreater>{

    @Override
    protected Class<DreamGreater> getEntityClass() {
        return DreamGreater.class;
    }

}
