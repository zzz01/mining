package com.hust.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionInterceptor implements HandlerInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

	@Autowired
	private MappingJackson2HttpMessageConverter converter;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (null != request.getSession().getAttribute("username")) {
			return true;
		}
		try {
			LOG.error("PermissionFail: errorMsg=用户没有权限");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			converter.write(new HashMap<String, String>().put("errorMsg", "用户没有权限"), MediaType.APPLICATION_JSON,
					new ServletServerHttpResponse(response));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("ajax error");
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
