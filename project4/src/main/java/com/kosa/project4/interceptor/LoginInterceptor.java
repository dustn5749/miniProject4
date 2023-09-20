package com.kosa.project4.interceptor;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginInterceptor extends  HandlerInterceptorAdapter{
	   @Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		   try {
			HttpSession session =  request.getSession();

			if( session.getAttribute("admin") == null ) {
	 	           String redirectUrl = request.getContextPath() + "/admin.do";
	 	            String script = "<script>alert('로그인 후 이용해주세요.');";
	 	            script += "window.location.href='" + redirectUrl + "';</script>";
	 	            response.setContentType("text/html;charset=UTF-8");
	 	           	response.getWriter().write(script);
	 	            return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	      return true;
	   }
	   
	 


	}
