package com.ddu.tes.interceptors;

import com.ddu.tes.data.modle.User;
import com.ddu.tes.security.SecurityInfoMgn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionHandelerInterceptor extends HandlerInterceptorAdapter {
    private static final long MAX_INACTIVE_SESSION_TIME = 900000;
    @Autowired
    private HttpSession session;


    @Autowired
    private SecurityInfoMgn securityInfoManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            User user = securityInfoManager.getCurrentUser();

            if (user != null) {
                session = request.getSession();
                if (System.currentTimeMillis() - session.getLastAccessedTime()
                        > MAX_INACTIVE_SESSION_TIME) {

                    response.sendRedirect(request.getContextPath()+"/sessionExpired");
                }
            }

            return  true;
        } catch (Exception ex) {

            throw ex;
        }

    }

}