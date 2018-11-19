package com.fpt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RouterInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.startsWith("/api/")) {
			request.setAttribute("isAPI", true);
		}
		if ((Boolean) request.getAttribute("isAPI") != Boolean.TRUE
				&& response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
