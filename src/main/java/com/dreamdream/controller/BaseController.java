package com.dreamdream.controller;

import com.dreamdream.model.view.RespStruct;
import com.dreamdream.util.ConstString;

public class BaseController {

    public RespStruct succ() {
        return new RespStruct.Builder().succ(true).build();
    }

    public RespStruct succ(Object obj, String message) {
        return new RespStruct.Builder().succ(true).message(message).obj(obj).build();
    }

    public RespStruct succ(Object obj) {
        return new RespStruct.Builder().succ(true).obj(obj).build();
    }

    public RespStruct failedCausedException() {
        return failed(ConstString.OPERATION_FAILED);
    }
    
    public RespStruct failed(String message) {
        return new RespStruct.Builder().succ(false).message(message).build();
    }
    
    public RespStruct failed(String message, Object obj) {
        return new RespStruct.Builder().succ(false).message(message).obj(obj).build();
    }
}
