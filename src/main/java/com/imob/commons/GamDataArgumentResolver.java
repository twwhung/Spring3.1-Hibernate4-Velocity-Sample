package com.imob.commons;

import java.util.Date;

import org.springframework.core.MethodParameter;

import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class GamDataArgumentResolver implements HandlerMethodArgumentResolver{

	

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		return new Date();
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
	
		return Date.class == methodParameter.getParameterType();
	}
	
	
}
