package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.model.DreamReply;

@Repository
public class DreamReplyDao extends BaseDao<DreamReply>{

    @Override
    protected Class<DreamReply> getEntityClass() {
        return DreamReply.class;
    }

}
