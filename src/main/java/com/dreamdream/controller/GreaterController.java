package com.dreamdream.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamGreaterDao;
import com.dreamdream.model.DreamGreater;
import com.dreamdream.page.view.RespStruct;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.DateUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/greater")
public class GreaterController extends BaseController {
    
    @Autowired
    private DreamGreaterDao dgDao;
    
    @ApiOperation(value = "给某个消息点赞", notes = "给某个消息点赞", response = RespStruct.class)
    @RequestMapping(value="/like", method = RequestMethod.POST)
    public RespStruct like(
            @ApiParam(value = "信息ID", required = true) @RequestParam int messageId,
            @ApiIgnore HttpServletRequest session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamGreater dg = new DreamGreater();
        dg.setDreamerId(dreamerId);
        dg.setMessageId(messageId);
        if(dgDao.select(dg) == null)
            dg.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        dg.setStats(true);
        dgDao.insertOrUpdate(dg);
        return succ();
    }
    
    @ApiOperation(value = "取消给某个消息的赞", notes = "取消给某个消息的赞", response = RespStruct.class)
    @RequestMapping(value="/unlike", method = RequestMethod.POST)
    public RespStruct unlike(
            @ApiParam(value = "信息ID", required = true) @RequestParam int messageId,
            @ApiIgnore HttpServletRequest session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamGreater dg = new DreamGreater();
        dg.setDreamerId(dreamerId);
        dg.setMessageId(messageId);
        dg.setStats(true);
        if(dgDao.select(dg) == null) return failed(ConstString.NOT_VALID_GREATER,ConstString.NOT_VALID_GREATER_CODE);
        dg.setStats(false);
        dgDao.insertOrUpdate(dg);
        return succ();
    }
}
