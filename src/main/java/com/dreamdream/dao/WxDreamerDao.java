package com.dreamdream.dao;

import org.springframework.stereotype.Repository;

import com.dreamdream.dao.base.BaseDao;
import com.dreamdream.model.WxDreamer;

@Repository
public class WxDreamerDao extends BaseDao<WxDreamer>{

    @Override
    protected Class<WxDreamer> getEntityClass() {
        return WxDreamer.class;
    }

}
