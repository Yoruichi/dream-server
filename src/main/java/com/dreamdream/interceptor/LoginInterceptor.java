package com.dreamdream.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dreamdream.model.view.SessionInfo;
import com.dreamdream.util.ConstString;
import com.google.common.base.Strings;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
            throws Exception {
        HttpSession session = req.getSession(false);
        if(session == null) return false;
        Object o = session.getAttribute(ConstString.SESSION_USER_INFO);
        if (o == null) return false;
        SessionInfo info = (SessionInfo) o;
        if (info == null || Strings.isNullOrEmpty(info.getDreamerId())) {
            req.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
            return false;
        }
        return true;
    }

}
