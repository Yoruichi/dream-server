package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.view.DreamMessageView;

@Repository
public class DreamMessageViewDao extends BaseDao<DreamMessageView>{

    @Override
    protected Class<DreamMessageView> getEntityClass() {
        return DreamMessageView.class;
    }

}
