package com.dreamdream.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dreamdream.session.model.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.base.Strings;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;

import java.util.Enumeration;

public class BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static void setContext(ApplicationContext context) {
        BeanUtil.context = context;
    }

    public static String getSessionIdFromHeader(HttpServletRequest req) {
        String sessionId = null;
        sessionId = req.getHeader("SESSION");
        if (Strings.isNullOrEmpty(sessionId)) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName()))
                    sessionId = cookie.getValue();
            }
        }
        return sessionId;
    }

    public static String httpServletRequest2String(HttpServletRequest req) {
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

    public static SessionInfo getSessionInfoFromSessionRepository(
            SessionRepository<? extends ExpiringSession> repository, HttpServletRequest req) {
        String sessionId = BeanUtil.getSessionIdFromHeader(req);
        if (Strings.isNullOrEmpty(sessionId)) {
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

    public static SessionInfo getSessionInfoFromRequest(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
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
}
