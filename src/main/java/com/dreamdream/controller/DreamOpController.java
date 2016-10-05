package com.dreamdream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamdream.dao.DreamerDao;
import com.dreamdream.model.Dreamer;
import com.dreamdream.model.view.RespStruct;
import com.dreamdream.util.ConstString;
import com.dreamdream.util.MD5Utils;
import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/op/dreamer")
public class DreamOpController extends BaseController {
    
    @Autowired
    private DreamerDao dreamerDao;

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", response=Dreamer.class)
    @RequestMapping(value="/register/phone", method=RequestMethod.POST)
    public RespStruct update(
            @ApiParam(value="安全邮箱") @RequestParam(required=false, defaultValue="") String email,
            @ApiParam(value="登录的密码") @RequestParam(required=false, defaultValue="") String password,
            @ApiParam(value="昵称") @RequestParam(required=false, defaultValue="") String nickName,
            @ApiParam(value="头像") @RequestParam(required=false, defaultValue="") String avatarUrl,
            @ApiParam(value="性别") @RequestParam(required=false, defaultValue="") String gender,
            @ApiIgnore HttpSession session
            ) throws Exception {
        Dreamer d = new Dreamer();
        d.setId(getDreamerIdFromSession(session));
        if(!Strings.isNullOrEmpty(email)) d.setEmail(email);
        if(!Strings.isNullOrEmpty(password)) d.setPassword(MD5Utils.getPassword(password));
        if(!Strings.isNullOrEmpty(nickName)) d.setNickName(nickName);
        if(!Strings.isNullOrEmpty(avatarUrl)) d.setAvatarUrl(avatarUrl);
        if(!Strings.isNullOrEmpty(gender)) d.setGender(gender);
        dreamerDao.insertOrUpdate(d);
        d.setId(getDreamerIdFromSession(session));
        return succ(dreamerDao.select(d));
    }
    
    @ApiOperation(value = "检验密码", notes = "检验密码", response=Dreamer.class)
    @RequestMapping(value="/check/password", method=RequestMethod.POST)
    public RespStruct checkPassword(
            @ApiParam(value="登录的密码", required=true) @RequestParam String password,
            @ApiIgnore HttpSession session
            ) throws Exception {
        Dreamer d = new Dreamer();
        d.setId(getDreamerIdFromSession(session));
        d.setPassword(MD5Utils.getPassword(password));
        d.setStats(true);
        d = dreamerDao.select(d);
        if (d == null) return failed(ConstString.NOT_VALID_PASSWD, ConstString.NOT_VALID_PASSWD_CODE);
        return succ();
    }

}
