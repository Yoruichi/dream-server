package com.dreamdream.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamerDao;
import com.dreamdream.dao.WxDreamerDao;
import com.dreamdream.model.Dreamer;
import com.dreamdream.model.WxDreamer;
import com.dreamdream.page.view.RespStruct;
import com.dreamdream.session.model.SessionInfo;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.DateUtils;
import com.dreamdream.util.IpUtil;
import com.dreamdream.util.MD5Utils;
import com.dreamdream.util.NumberUtils;
import com.dreamdream.util.Validator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("dreamer")
public class DreamerController extends BaseController {

    @Autowired
    private DreamerDao dreamerDao;
    @Autowired
    private WxDreamerDao wxDreamerDao;

    @ApiOperation(value = "注册新用户", notes = "注册新用户", response = Dreamer.class)
    @RequestMapping(value = "/register/phone", method = RequestMethod.POST)
    public RespStruct registerWithUserPhone(
            @ApiParam(value = "手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "登录的密码", required = true) @RequestParam String password,
            @ApiIgnore HttpSession session, @ApiIgnore HttpServletRequest req) throws Exception {
        Dreamer d = new Dreamer();
        if (!Validator.isMobile(phone))
            return failed(ConstString.NOT_VALID_PHONE, ConstString.NOT_VALID_PHONE_CODE);
        d.setPhone(phone);
        if (dreamerDao.select(d) != null)
            return failed(ConstString.PHONE_EXISTS, ConstString.PHONE_EXISTS_CODE);
        d.setPassword(MD5Utils.getPassword(password));
        d.setStats(true);
        d.setCreateTime(DateUtils.getCurrentDateTime().toDate());
        d.setUsername(NumberUtils.createOrderNumber(d));
        dreamerDao.insertOrUpdate(d);
        d = new Dreamer();
        d.setPhone(phone);
        d = dreamerDao.select(d);
        return succ(d, setSessionInfoInSession(d, session, req));
    }

    @ApiOperation(value = "通过微信进行登录", notes = "登录成功返回用户数据", response = Dreamer.class)
    @RequestMapping(value = "/login/wx", method = RequestMethod.POST)
    public RespStruct loginWithWx(
            @ApiParam(value = "通过微信登录成功之后返回的code", required = true) @RequestParam String code,
            @ApiIgnore HttpSession session, @ApiIgnore HttpServletRequest req) throws Exception {
        WxDreamer w = new WxDreamer();
        // TODO call wx service to get user info(openId and something) with code
        String openid = "";
        w.setOpenid(openid);
        w = wxDreamerDao.select(w);
        if (w != null) {
            Dreamer d = new Dreamer();
            d.setId(w.getDreamerId());
            d = dreamerDao.select(d);
            if (d == null)
                throw new Exception("Error! when login with WeChat but got null dreamer by id");
            if (!d.getStats())
                return failed(ConstString.NOT_VALID_USER_STATS,
                        ConstString.NOT_VALID_USER_STATS_CODE);
            return succ(d, setSessionInfoInSession(d, session, req));
        } else {
            // TODO create dreamer to bind wx openId
        }
        return failed("Not for use now");
    }

    private String setSessionInfoInSession(Dreamer d, HttpSession session, HttpServletRequest req) {
        SessionInfo info = new SessionInfo();
        info.setDreamerId(d.getId().intValue() + "");
        info.setDreamerName(d.getUsername());
        info.setIp(IpUtil.getIpAddr(req));
        session.setAttribute(ConstString.SESSION_USER_INFO, info);
        session.setMaxInactiveInterval(3600);
        // 返回到客户端的加密之后的密码固定为hehe
        d.setPassword("hehe");
        return ConstString.COOKIE_SESSION_NAME + session.getId();
    }

    @ApiOperation(value = "通过手机号进行登录", notes = "登录成功返回用户数据", response = Dreamer.class)
    @RequestMapping(value = "/login/phone", method = RequestMethod.POST)
    public RespStruct loginWithPhone(
            @ApiParam(value = "登录手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "密码", required = true) @RequestParam String password,
            @ApiIgnore HttpSession session, @ApiIgnore HttpServletRequest req) throws Exception {
        Dreamer d = new Dreamer();
        d.setPhone(phone);
        d = dreamerDao.select(d);
        if (d == null)
            return failed(ConstString.NOT_VALID_USERNAME, ConstString.NOT_VALID_USERNAME_CODE);
        if (!MD5Utils.getPassword(password).equals(d.getPassword()))
            return failed(ConstString.NOT_VALID_PASSWD, ConstString.NOT_VALID_PASSWD_CODE);
        if (!d.getStats())
            return failed(ConstString.NOT_VALID_USER_STATS, ConstString.NOT_VALID_USER_STATS_CODE);
        return succ(d, setSessionInfoInSession(d, session, req));
    }
}
