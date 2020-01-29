package com.ddu.tes.interceptors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CustomSessionInterceptor extends HandlerInterceptorAdapter {

    private static final Log logger = LogFactory.getLog(CustomSessionInterceptor.class);
    private static final String default_image = "6ab86dc6-4a41-11e9-bd0f-d0bf9c21f3b8";
    @Value("${build.version}")
    private String appicationVersion;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        try {


            if (StringUtils.isBlank(request.getHeader("x-requested-with")) && modelAndView != null) {
                HttpSession session = request.getSession();
                modelAndView.addObject("loggedInUserName", session.getAttribute("loggedInUserName") != null ? session.getAttribute("loggedInUserName") : "");
                modelAndView.addObject("appicationVersion", "Application Version: " + appicationVersion);
                modelAndView.addObject("currentOrgImage", session.getAttribute("currentOrgImage") != null ? session.getAttribute("currentOrgImage") : default_image);
            }

        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
