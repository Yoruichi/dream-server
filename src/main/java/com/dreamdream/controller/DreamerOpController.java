package com.dreamdream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamerDao;
import com.dreamdream.model.Dreamer;
import com.dreamdream.page.view.RespStruct;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.MD5Utils;
import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/dreamer")
public class DreamerOpController extends BaseController {

    @Autowired
    private DreamerDao dreamerDao;

    @ApiOperation(value = "查看用户信息", notes = "查看用户信息", response = Dreamer.class)
    @RequestMapping(value = "/check/info", method = RequestMethod.POST)
    public RespStruct checkInfo(
            @ApiParam(value = "查看用户的ID", required = true) @RequestParam int dreamerId,
            @ApiIgnore HttpSession session) throws Exception {
        Dreamer d = new Dreamer();
        d.setId(dreamerId);
        d.setStats(true);
        return succ(dreamerDao.select(d));
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", response = Dreamer.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespStruct update(
            @ApiParam(value = "安全邮箱") @RequestParam(required = false,
                    defaultValue = "") String email,
            @ApiParam(value = "登录的密码") @RequestParam(required = false,
                    defaultValue = "") String password,
            @ApiParam(value = "昵称") @RequestParam(required = false,
                    defaultValue = "") String nickName,
            @ApiParam(value = "头像") @RequestParam(required = false,
                    defaultValue = "") String avatarUrl,
            @ApiParam(value = "性别") @RequestParam(required = false,
                    defaultValue = "") String gender,
            @ApiParam(value = "职业") @RequestParam(required = false,
            defaultValue = "") String job,
            @ApiIgnore HttpSession session) throws Exception {
        Dreamer d = new Dreamer();
        Integer dreamerId = getDreamerIdFromSession(session);
        d.setId(dreamerId);
        if(dreamerDao.select(d) == null) return failed(ConstString.NOT_VALID_USER_ID, ConstString.NOT_VALID_USER_ID_CODE);
        if (!Strings.isNullOrEmpty(email))
            d.setEmail(email);
        if (!Strings.isNullOrEmpty(password))
            d.setPassword(MD5Utils.getPassword(password));
        if (!Strings.isNullOrEmpty(nickName))
            d.setNickName(nickName);
        if (!Strings.isNullOrEmpty(avatarUrl))
            d.setAvatarUrl(avatarUrl);
        if (!Strings.isNullOrEmpty(gender))
            d.setGender(gender);
        if (!Strings.isNullOrEmpty(job))
            d.setJob(job);
        dreamerDao.insertOrUpdate(d);
        d = new Dreamer();
        d.setId(dreamerId);
        return succ(dreamerDao.select(d));
    }

    @ApiOperation(value = "检验密码", notes = "检验密码", response = Dreamer.class)
    @RequestMapping(value = "/check/password", method = RequestMethod.POST)
    public RespStruct checkPassword(
            @ApiParam(value = "登录的密码", required = true) @RequestParam String password,
            @ApiIgnore HttpSession session) throws Exception {
        Dreamer d = new Dreamer();
        d.setId(getDreamerIdFromSession(session));
        d.setPassword(MD5Utils.getPassword(password));
        d.setStats(true);
        d = dreamerDao.select(d);
        if (d == null)
            return failed(ConstString.NOT_VALID_PASSWD, ConstString.NOT_VALID_PASSWD_CODE);
        return succ();
    }

}
