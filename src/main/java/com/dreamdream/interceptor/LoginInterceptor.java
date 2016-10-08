package com.dreamdream.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dreamdream.session.model.SessionInfo;
import com.dreamdream.util.BeanUtil;
import com.dreamdream.util.ConstString;
import com.google.common.base.Strings;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private SessionRepository<? extends ExpiringSession> repository;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {}

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {}

    private String httpServletRequest2String(HttpServletRequest req) {
        if (req == null)
            return null;
        StringBuffer sb = new StringBuffer();
        sb.append("Request >>> {uri:").append(req.getRequestURI());
        if (!Strings.isNullOrEmpty(req.getQueryString()))
            sb.append(", QueryString:").append(req.getQueryString());
        sb.append(", header:{");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            sb.append(key).append(":").append(value).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "},parameters:{");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = req.getParameter(key);
            sb.append(key).append(":").append(value).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }

    private SessionInfo getSessionInfoFromSessionRepository(HttpServletRequest req) {
        String sessionId=BeanUtil.getSessionIdFromHeader(req);
        if(Strings.isNullOrEmpty(sessionId)) {
            logger.debug("Got no valid session id from request.");
            return null;
        }
        ExpiringSession eSession = repository.getSession(sessionId);
        if (eSession == null) {
            logger.debug("Got no valid session id {} from session server(REDIS).", sessionId);
            return null;
        }
        Object eo = eSession.getAttribute(ConstString.SESSION_USER_INFO);
        if (eo == null) {
            logger.debug("Got no valid session info in session {}.", eSession);
            return null;
        }
        SessionInfo eInfo = (SessionInfo) eo;
        return eInfo;
    }

    private SessionInfo getSessionInfoFromRequest(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(session == null) {
            logger.debug("Got no valid session from request.");
            return null;
        }
        Object eo = session.getAttribute(ConstString.SESSION_USER_INFO);
        if (eo == null) {
            logger.debug("Got no valid session info in session {}.", session);
            return null;
        }
        SessionInfo eInfo = (SessionInfo) eo;
        return eInfo;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
            throws Exception {
        logger.info(httpServletRequest2String(req));
        SessionInfo eInfo = getSessionInfoFromSessionRepository(req);
        if(eInfo == null) eInfo = getSessionInfoFromRequest(req);
        
        if (eInfo == null || Strings.isNullOrEmpty(eInfo.getDreamerId())) {
            logger.debug("Got no valid session info in session.");
            return false;
        }
        logger.info("Got session >>> " + eInfo);
        return true;
    }

}
