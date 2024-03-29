package com.imob.commons;


import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

//https://bowerstudios.com/node/918
public class ExtendedExceptionHandlerExceptionResolver extends
	ExceptionHandlerExceptionResolver {

	private Object handler;
	private ExceptionHandlerMethodResolver methodResolver;
	public void setExceptionHandler(Object handler) {
		this.handler = handler;
		this.methodResolver = new ExceptionHandlerMethodResolver(
		handler.getClass());
	}
	@Override
	protected ServletInvocableHandlerMethod getExceptionHandlerMethod(
		HandlerMethod handlerMethod, Exception exception) {
		ServletInvocableHandlerMethod result = super.getExceptionHandlerMethod(
				handlerMethod, exception);
		if (result != null) {
			return result;
		}
			Method method = this.methodResolver.resolveMethod(exception);
		return (method != null) ? new ServletInvocableHandlerMethod(this.handler, method) : null;
	}
}