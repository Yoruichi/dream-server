package com.dreamdream.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dreamdream.page.view.RespStruct;
import com.dreamdream.session.model.SessionInfo;
import com.dreamdream.util.BeanUtil;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
	@Autowired
	protected SessionRepository<? extends ExpiringSession> repository;

    public Integer getDreamerIdFromSession(HttpServletRequest req) throws Exception {
		SessionInfo info = BeanUtil.getSessionInfoFromSessionRepository(repository, req);
		if(info == null) info = BeanUtil.getSessionInfoFromRequest(req);
        if(info == null) throw new Exception("Error when got dreamer id from session");
        return Integer.valueOf(info.getDreamerId());
    }
    
    public String getIpLocationFromSession(HttpServletRequest req) throws Exception {
		SessionInfo info = BeanUtil.getSessionInfoFromSessionRepository(repository, req);
		if(info == null) info = BeanUtil.getSessionInfoFromRequest(req);
        if(info == null) throw new Exception("Error when got dreamer id from session");
        return IpUtil.getAddressByIp(info.getIp());
    }

    public RespStruct succ() {
        return new RespStruct.Builder().succ(true).build();
    }

    public RespStruct succ(Object obj, String message) {
        return new RespStruct.Builder().succ(true).message(message).obj(obj).build();
    }

    public RespStruct succ(Object obj) {
        return new RespStruct.Builder().succ(true).obj(obj).build();
    }

    public RespStruct failed(String message) {
        return new RespStruct.Builder().succ(false).message(message).build();
    }
    
    public RespStruct failed(String message, Object obj) {
        return new RespStruct.Builder().succ(false).message(message).obj(obj).build();
    }
}
