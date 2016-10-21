package com.dreamdream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.model.DreamMessage;
import com.dreamdream.model.DreamMessage.DreamMessageType;
import com.dreamdream.page.view.DreamPageView;
import com.dreamdream.page.view.RespStruct;
import com.dreamdream.serv.DreamPageViewServ;
import com.dreamdream.util.ConstString;
import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/messageView")
public class MessageViewController extends BaseController {

    @Autowired
    private DreamPageViewServ dpvServ;

    @ApiOperation(value = "查看自己发布的信息", notes = "查看自己发布的信息", response = DreamMessage.class)
    @RequestMapping(value="/checkSelf", method = RequestMethod.POST)
    public RespStruct getSelfMessageViewStream(
            @ApiParam(value = "信息类型(PUBLIC/PRIVATE)") @RequestParam(required=false, defaultValue = "") String type,
            @ApiParam(value = "需要获取的条数", required = true,
                    defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
                    defaultValue = "0") @RequestParam() int index,
            @ApiIgnore HttpServletRequest session) throws Exception {
        int loginDreamerId = getDreamerIdFromSession(session).intValue();
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        Integer dreamerId = getDreamerIdFromSession(session);
        if (!Strings.isNullOrEmpty(type) && DreamMessageType.valueOf(type) != null)
            return succ(dpvServ.getSomeonesDreamPageView(dreamerId, limit, index, loginDreamerId));
        return succ(dpvServ.getSomeonesSomeTypeDreamPageView(type, dreamerId, limit, index, loginDreamerId));
    }
    
    @ApiOperation(value = "查看自己收藏的消息流", notes = "查看自己收藏的消息流", response = DreamPageView.class)
    @RequestMapping(value = "/checkSelf/like", method = RequestMethod.POST)
    public RespStruct getSelfLikeMessageViewStream(
            @ApiParam(value = "需要获取的条数", required = true,
            defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
            defaultValue = "0") @RequestParam() int index,
            @ApiIgnore HttpServletRequest session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        return succ(dpvServ.getSomeonesLikeDreamPageView(dreamerId, limit, index, dreamerId));
    }
    @ApiOperation(value = "查看某个用户消息流", notes = "查看某个用户消息流", response = DreamPageView.class)
    @RequestMapping(value = "/checkSomeone", method = RequestMethod.POST)
    public RespStruct getSomeoneMessageViewStream(
            @ApiParam(value = "需要获取的条数", required = true,
                    defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
                    defaultValue = "0") @RequestParam() int index,
            @ApiParam(value = "用户ID", required = true) @RequestParam() int dreamerId,
            @ApiIgnore HttpServletRequest session) throws Exception {
        int loginDreamerId = getDreamerIdFromSession(session).intValue();
        return succ(dpvServ.getSomeonesDreamPageView(dreamerId, limit, index, loginDreamerId));
    }
    
    @ApiOperation(value = "查看发现页内的消息流", notes = "查看发现页内的消息流", response = DreamPageView.class)
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public RespStruct getMessageViewStream(
            @ApiParam(value = "需要获取的条数", required = true,
                    defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
                    defaultValue = "0") @RequestParam() int index,
            @ApiIgnore HttpServletRequest session) throws Exception {
        int loginDreamerId = getDreamerIdFromSession(session).intValue();
        return succ(dpvServ.getDreamPageView(0, limit, index, loginDreamerId));
    }
    
    @ApiOperation(value = "查看某条消息", notes = "查看某条消息", response = DreamPageView.class)
    @RequestMapping(value = "/check/one", method = RequestMethod.POST)
    public RespStruct getMessageViewOne(
            @ApiParam(value = "需要获取的消息的ID", required = true) @RequestParam int messageId,
            @ApiIgnore HttpServletRequest session) throws Exception {
        int loginDreamerId = getDreamerIdFromSession(session).intValue();
        return succ(dpvServ.getDreamPageViewOne(messageId, loginDreamerId));
    }
}
