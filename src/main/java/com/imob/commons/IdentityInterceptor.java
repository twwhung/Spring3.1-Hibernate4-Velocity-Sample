package com.imob.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IdentityInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
		    throws Exception {	 			
			if(request.getServletPath().equals("/login")){
				return true;
			}
		
		
			HttpSession session = request.getSession();			
			if (session.getAttribute("user") == null && !request.getServletPath().equals("/whoami.show")){
				response.sendRedirect("/badminton/whoami.show");				
			}
			return true;
		}
}
