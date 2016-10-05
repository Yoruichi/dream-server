package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.model.Dreamer;

@Repository
public class DreamerDao extends BaseDao<Dreamer> {

    @Override
    protected Class<Dreamer> getEntityClass() {
        return Dreamer.class;
    }

}
