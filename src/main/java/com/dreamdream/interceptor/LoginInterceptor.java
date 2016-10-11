package com.dreamdream.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
            throws Exception {
        logger.info(BeanUtil.httpServletRequest2String(req));
        SessionInfo eInfo = BeanUtil.getSessionInfoFromSessionRepository(repository, req);
        if(eInfo == null) eInfo = BeanUtil.getSessionInfoFromRequest(req);
        
        if (eInfo == null || (eInfo.getDreamer() == null || eInfo.getDreamer().getId() == null)) {
            logger.debug("Got no valid session info in session.");
            return false;
        }
        logger.info("Got session >>> " + eInfo);
        return true;
    }

}
