package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.view.DreamReplyView;

@Repository
public class DreamReplyViewDao extends BaseDao<DreamReplyView>{

    @Override
    protected Class<DreamReplyView> getEntityClass() {
        return DreamReplyView.class;
    }

}
