package com.dreamdream.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamdream.model.view.RespStruct;

@ControllerAdvice
public class GloableExceptionHandler {

    private static final String ERROR_MSG = "请求失败，请稍后再试。";
    private static final Integer ERROR_CODE = new Integer(2001);

    private static final Logger logger = LoggerFactory.getLogger(GloableExceptionHandler.class);
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RespStruct errorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("Error! When process request {} . Error detail >>> ", req, e);
        RespStruct resp = new RespStruct();
        resp.setSucc(false);
        resp.setObj(ERROR_CODE);
        resp.setMessage(ERROR_MSG);
        return resp;
    }
}
