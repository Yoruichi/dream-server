package com.dreamdream.controller;

import javax.servlet.http.HttpSession;

import com.dreamdream.model.view.RespStruct;
import com.dreamdream.model.view.SessionInfo;
import com.dreamdream.util.ConstString;

public class BaseController {

    public Integer getDreamerIdFromSession(HttpSession session) throws Exception {
        Object o = session.getAttribute(ConstString.SESSION_USER_INFO);
        if(o == null) throw new Exception("Error when got dreamer id from session");
        SessionInfo info = (SessionInfo) o;
        return Integer.getInteger(info.getDreamerId());
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
