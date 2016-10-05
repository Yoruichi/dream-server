package com.dreamdream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamMessageDao;
import com.dreamdream.model.DreamMessage;
import com.dreamdream.model.DreamMessage.DreamMessageType;
import com.dreamdream.model.view.RespStruct;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.DateUtils;
import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/dreamMessage")
public class DreamMessageController extends BaseController {

    @Autowired
    private DreamMessageDao dmDao;

    @ApiOperation(value = "修改自己发布信息的状态", notes = "修改自己发布信息的状态", response = DreamMessage.class)
    @RequestMapping(method = RequestMethod.POST)
    public RespStruct changeMessageStats(
            @ApiParam(value = "信息ID", required = true) @RequestParam int id,
            @ApiParam(value = "信息状态", required = true) @RequestParam boolean stats,
            @ApiIgnore HttpSession session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setId(id);
        dm = dmDao.select(dm);
        if(dm == null) return failed(ConstString.NOT_VALID_MESSAGE, ConstString.NOT_VALID_MESSAGE_CODE);
        dm.setStats(stats);
        dmDao.insertOrUpdate(dm);
        return succ(dmDao.select(dm));
    }
    
    @ApiOperation(value = "修改自己发布信息的类型", notes = "修改自己发布信息的类型", response = DreamMessage.class)
    @RequestMapping(method = RequestMethod.POST)
    public RespStruct changeMessageType(
            @ApiParam(value = "信息ID", required = true) @RequestParam int id,
            @ApiParam(value = "信息类型(PUBLIC/PRIVATE)", required = true) @RequestParam String type,
            @ApiIgnore HttpSession session) throws Exception {
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setId(id);
        dm.setStats(true);
        dm = dmDao.select(dm);
        if(dm == null) return failed(ConstString.NOT_VALID_MESSAGE, ConstString.NOT_VALID_MESSAGE_CODE);
        dm.setType(type);
        dmDao.insertOrUpdate(dm);
        return succ(dmDao.select(dm));
    }
    
    @ApiOperation(value = "查看自己发布的信息", notes = "查看自己发布的信息", response = DreamMessage.class)
    @RequestMapping(method = RequestMethod.POST)
    public RespStruct getMessage(
            @ApiParam(value = "信息类型(PUBLIC/PRIVATE)", required = true,
                    defaultValue = "") @RequestParam(defaultValue = "") String type,
            @ApiParam(value = "需要获取的条数", required = true,
                    defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
                    defaultValue = "1") @RequestParam() int index,
            @ApiIgnore HttpSession session) throws Exception {
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setStats(true);
        if (!Strings.isNullOrEmpty(type) && DreamMessageType.valueOf(type) != null)
            dm.setType(type);
        dm.setLimit(limit);
        dm.setIndex(index);
        return succ(dmDao.select(dm, "id", false));
    }

    @ApiOperation(value = "用户发布信息", notes = "用户发布信息", response = RespStruct.class)
    @RequestMapping(method = RequestMethod.POST)
    public RespStruct sendMessage(
            @ApiParam(value = "信息内容", required = true) @RequestParam String content,
            @ApiParam(value = "信息类型(PUBLIC/PRIVATE)", required = true,
                    defaultValue = "PUBLIC") @RequestParam String type,
            @ApiParam(value = "配图") @RequestParam String imageUrl, @ApiIgnore HttpSession session)
            throws Exception {
        if (Strings.isNullOrEmpty(content))
            return failed(ConstString.NOT_VALID_MESSAGE_CONTENT,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setContent(content);
        dm.setType(type);
        dm.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        dmDao.insertOrUpdate(dm);
        return succ();
    }
}
