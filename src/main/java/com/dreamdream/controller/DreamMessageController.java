package com.dreamdream.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamMessageDao;
import com.dreamdream.model.DreamMessage;
import com.dreamdream.page.view.RespStruct;
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
    @RequestMapping(value = "/changeStats", method = RequestMethod.POST)
    public RespStruct changeMessageStats(
            @ApiParam(value = "信息ID", required = true) @RequestParam int id,
            @ApiParam(value = "信息状态", required = true) @RequestParam boolean stats,
            @ApiIgnore HttpServletRequest session) throws Exception {
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setId(id);
        dm = dmDao.select(dm);
        if (dm == null)
            return failed(ConstString.NOT_VALID_MESSAGE, ConstString.NOT_VALID_MESSAGE_CODE);
        dm.setStats(stats);
        dmDao.insertOrUpdate(dm);
        return succ(dmDao.select(dm));
    }

    @ApiOperation(value = "修改自己发布信息的类型", notes = "修改自己发布信息的类型", response = DreamMessage.class)
    @RequestMapping(value = "/changeType", method = RequestMethod.POST)
    public RespStruct changeMessageType(
            @ApiParam(value = "信息ID", required = true) @RequestParam int id,
            @ApiParam(value = "信息类型(PUBLIC/PRIVATE)", required = true) @RequestParam String type,
            @ApiIgnore HttpServletRequest session) throws Exception {
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        Integer dreamerId = getDreamerIdFromSession(session);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setId(id);
        dm.setStats(true);
        dm = dmDao.select(dm);
        if (dm == null)
            return failed(ConstString.NOT_VALID_MESSAGE, ConstString.NOT_VALID_MESSAGE_CODE);
        dm.setType(type);
        dmDao.insertOrUpdate(dm);
        return succ(dmDao.select(dm));
    }

    @ApiOperation(value = "用户发布信息", notes = "用户发布信息", response = RespStruct.class)
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public RespStruct sendMessage(
			@ApiParam(value = "信息内容类型") @RequestParam(required = false) String dreamTypeContent,
			@ApiParam(value = "信息内容时间") @RequestParam(required = false) String dreamTime,
			@ApiParam(value = "信息位置内容") @RequestParam(required = false) String dreamLocationContent,
			@ApiParam(value = "信息内容", required = true) @RequestParam String content,
			@ApiParam(value = "信息类型(PUBLIC/PRIVATE)", required = true,
                    defaultValue = "PUBLIC") @RequestParam String type,
			@ApiParam(value = "配图") @RequestParam(required = false) String imageUrl, @ApiIgnore HttpServletRequest req)
            throws Exception {
        String dreamType = dreamTypeContent;
        String dreamLocation = getIpLocationFromSession(req);
        if (Strings.isNullOrEmpty(dreamTime))
            dreamTime = DateUtils.getCurrentDateTime().toString("yyyy年MM月dd日 HH时");
        if (Strings.isNullOrEmpty(dreamType))
            dreamType = ConstString.OTHER_DREAM_TYPE;
        if (Strings.isNullOrEmpty(content))
            return failed(ConstString.NOT_VALID_MESSAGE_CONTENT,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        if (DreamMessage.DreamMessageType.valueOf(type) == null)
            return failed(ConstString.NOT_VALID_MESSAGE_TYPE,
                    ConstString.NOT_VALID_MESSAGE_CONTENT_CODE);
        String header =
                ConstString.HEADER_FOR_DREAM_CONTENT.replaceAll("\\$\\{dreamTime\\}", dreamTime)
                .replaceAll("\\$\\{dreamTypeContent\\}", dreamTypeContent);
        if (!Strings.isNullOrEmpty(dreamLocationContent))
            header = header.replaceAll("\\$\\{dreamLocationContent\\}", "在" + dreamLocationContent);
        else
            header = header.replaceAll("\\$\\{dreamLocationContent\\}", "");
        
        Integer dreamerId = getDreamerIdFromSession(req);
        DreamMessage dm = new DreamMessage();
        dm.setDreamerId(dreamerId);
        dm.setDreamType(dreamType);
        dm.setDreamTypeContent(dreamTypeContent);
        dm.setDreamLocationContent(dreamLocationContent);
        dm.setDreamLocation(dreamLocation);
        if(!Strings.isNullOrEmpty(imageUrl)) dm.setImageUrl(imageUrl);
        dm.setContent(header + content);
        dm.setType(type);
        dm.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        dmDao.insertOrUpdate(dm);
        return succ();
    }
}
