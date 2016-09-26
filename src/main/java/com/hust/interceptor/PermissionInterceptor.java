package com.hust.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionInterceptor implements HandlerInterceptor {
    /**
     * Logger for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        try {
            String url = request.getRequestURI();
            if ("/".equals(url) || "/index.html".equals(url)) {
                if (null != request.getSession().getAttribute("username")) {
                    response.sendRedirect("/page/main.html");
                } else {
                    return true;
                }
            } else {
                if (null != request.getSession().getAttribute("username")) {
                    return true;
                } else {
                    LOG.warn("PermissionDeny: errorMsg=用户{}没有权限，访问的URL：{}", request.getRemoteHost(),
                            request.getRequestURI());
                    response.sendRedirect("/index.html");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOG.error("redirect to index.html error \t" + e.toString());
            try {
                response.sendRedirect("/page/error.html");
            } catch (Exception e1) {
                LOG.error("跳转发生异常" + e1.toString());
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
