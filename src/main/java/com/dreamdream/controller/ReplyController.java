package com.dreamdream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamReplyDao;
import com.dreamdream.dao.DreamReplyViewDao;
import com.dreamdream.model.DreamReply;
import com.dreamdream.page.view.RespStruct;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.DateUtils;
import com.dreamdream.view.DreamReplyView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/reply")
public class ReplyController extends BaseController {

    @Autowired
    private DreamReplyDao drDao;
    
    @Autowired
    private DreamReplyViewDao drvDao;
    
    @ApiOperation(value = "查看某条信息的回复", notes = "查看某条信息的回复", response = RespStruct.class)
    @RequestMapping(value="/check/message", method = RequestMethod.POST)
    public RespStruct checkMessage(
            @ApiParam(value = "信息ID", required = true) @RequestParam int messageId,
            @ApiParam(value = "需要获取的条数", required = true,
            defaultValue = "10") @RequestParam() int limit,
            @ApiParam(value = "从第几条开始获取", required = true,
            defaultValue = "0") @RequestParam() int index,
            @ApiIgnore HttpSession session) throws Exception {
        DreamReplyView drv = new DreamReplyView();
        drv.setReplyStats(true);
        drv.setDreamerStats(true);
        drv.setMessageId(messageId);
        drv.setLimit(limit);
        drv.setIndex(index);
        drv.setAsc(false);
        drv.orderBy("replyCreateTime");
        return succ(drvDao.selectMany(drv));
    }
    @ApiOperation(value = "回复某条信息", notes = "回复某条信息", response = RespStruct.class)
    @RequestMapping(value="/message", method = RequestMethod.POST)
    public RespStruct forMessage(
            @ApiParam(value = "信息ID", required = true) @RequestParam int messageId,
            @ApiParam(value = "回复内容", required = true) @RequestParam String content,
            @ApiIgnore HttpSession session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamReply dr = new DreamReply();
        dr.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        dr.setDreamerId(dreamerId);
        dr.setMessageId(messageId);
        dr.setStats(true);
        dr.setContent(content);
        drDao.insertOrUpdate(dr);
        return succ();
    }
    
    @ApiOperation(value = "回复某个用户的回复", notes = "回复某个用户的回复", response = RespStruct.class)
    @RequestMapping(value="/dreamer", method = RequestMethod.POST)
    public RespStruct forMessage(
            @ApiParam(value = "信息ID", required = true) @RequestParam int messageId,
            @ApiParam(value = "回复给用户的ID", required = true) @RequestParam int replyDreamerId,
            @ApiParam(value = "回复内容", required = true) @RequestParam String content,
            @ApiIgnore HttpSession session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamReply dr = new DreamReply();
        dr.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        dr.setDreamerId(dreamerId);
        dr.setReplyDreamerId(replyDreamerId);
        dr.setMessageId(messageId);
        dr.setStats(true);
        dr.setContent(content);
        drDao.insertOrUpdate(dr);
        return succ();
    }
    
    @ApiOperation(value = "撤销某条回复", notes = "撤销某条回复", response = RespStruct.class)
    @RequestMapping(value="/undo", method = RequestMethod.POST)
    public RespStruct undo(
            @ApiParam(value = "回复的ID", required = true) @RequestParam int id,
            @ApiIgnore HttpSession session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamReply dr = new DreamReply();
        dr.setDreamerId(dreamerId);
        dr.setId(id);
        dr.setStats(true);
        if((dr=drDao.select(dr)) == null) return failed(ConstString.NOT_VALID_REPLY, ConstString.NOT_VALID_REPLY_CODE);
        dr.setLastUpdateTime(null);
        dr.setStats(false);
        drDao.insertOrUpdate(dr);
        return succ();
    }

}
