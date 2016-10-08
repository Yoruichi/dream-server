package com.dreamdream.util;

public class ConstString {

    public static final String NOT_VALID_USERNAME = "无效的用户名";
    public static final Integer NOT_VALID_USERNAME_CODE = new Integer(2002);
    public static final String NOT_VALID_PASSWD = "密码错误";
    public static final Integer NOT_VALID_PASSWD_CODE = new Integer(2003);
    public static final String NOT_VALID_USER_STATS = "该用户已被锁定";
    public static final Integer NOT_VALID_USER_STATS_CODE = new Integer(2004);
    public static final String USERNAME_EXISTS = "用户名已被占用";
    public static final Integer USERNAME_EXISTS_CODE = new Integer(2005);
    public static final String NOT_VALID_PHONE = "无效的手机号";
    public static final Integer NOT_VALID_PHONE_CODE = new Integer(2006);
    public static final String NOT_VALID_USERNAME_PHONE = "无效的用户名/手机号";
    public static final Integer NOT_VALID_USERNAME_PHONE_CODE = new Integer(2007);
    public static final String PHONE_EXISTS = "手机号已被占用";
    public static final Integer PHONE_EXISTS_CODE = new Integer(2008);
    public static final String OP_FAILED = "操作失败";
    public static final Integer OP_FAILED_CODE = new Integer(2009);
    public static final String NOT_VALID_MESSAGE_CONTENT = "发布内容不能为空";
    public static final Integer NOT_VALID_MESSAGE_CONTENT_CODE = new Integer(2010);
    public static final String NOT_VALID_MESSAGE_TYPE = "发布内容类型错误";
    public static final Integer NOT_VALID_MESSAGE_TYPE_CODE = new Integer(2011);
    public static final String NOT_VALID_MESSAGE = "错误的内容编号";
    public static final Integer NOT_VALID_MESSAGE_CODE = new Integer(2012);
    public static final String NOT_VALID_REPLY = "错误的回复编号";
    public static final Integer NOT_VALID_REPLY_CODE = new Integer(2013);
    public static final String NOT_VALID_USER_ID = "错误的用户ID";
    public static final Integer NOT_VALID_USER_ID_CODE = new Integer(2014);
    public static final String NOT_VALID_GREATER = "错误的点赞ID";
    public static final Integer NOT_VALID_GREATER_CODE = new Integer(2014);

    public static final String COOKIE_SESSION_NAME = "JSESSIONID=";
    public static final String VERSION = "1.0";
    public static final String SESSION_USER_INFO = "sessionuserinfo";
    public static final int REPLY_HEADER_COUNT = 3;
    public static final String OTHER_DREAM_TYPE = "特别";
    public static final String HEADER_FOR_DREAM_CONTENT =
            "${dreamTime} 我${dreamLocationContent}做了一个很${dreamTypeContent}的梦。我梦见";
    public static final long LOGIN_TIMEOUT = 86400;

}
