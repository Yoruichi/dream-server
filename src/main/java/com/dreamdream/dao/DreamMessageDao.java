package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.model.DreamMessage;

@Repository
public class DreamMessageDao extends BaseDao<DreamMessage>{

    @Override
    protected Class<DreamMessage> getEntityClass() {
        return DreamMessage.class;
    }

}
