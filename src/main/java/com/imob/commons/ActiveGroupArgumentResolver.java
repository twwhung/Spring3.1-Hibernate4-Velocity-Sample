package com.imob.commons;

import java.lang.annotation.Annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.imob.domains.BadGroup;

public class ActiveGroupArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {		
		if (BadGroup.class != parameter.getParameterType()){
			return false;
		};
		
		Annotation[] paramAnns = parameter.getParameterAnnotations(); 
		for (Annotation paramAnn : paramAnns) {                     
	        if (ActiveGroup.class.isInstance(paramAnn)) {
	        	return true;
	        }
		}
		return false;
	}
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {				
		BadGroup group = new BadGroup();
		
		/*
		Object command = BeanUtils.instantiateClass(parameter.getParameterType()); 	
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command);		
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);		
		binder.bind(request);*/				
		return   group; 
	}
}