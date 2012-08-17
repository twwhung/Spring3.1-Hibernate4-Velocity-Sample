package com.imob.commons;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



public class ArmArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {		
		Annotation[] paramAnns = parameter.getParameterAnnotations(); 
		for (Annotation paramAnn : paramAnns) {                     
	        if (ArmBean.class.isInstance(paramAnn)) {
	        	return true;
	        }
		}
		return false;
	}
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		Object command = BeanUtils.instantiateClass(parameter.getParameterType()); 	
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command);		
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);		
		binder.bind(request);
		
		return   command; 
	}

}
