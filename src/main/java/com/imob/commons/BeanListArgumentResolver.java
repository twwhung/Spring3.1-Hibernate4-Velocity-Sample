package com.imob.commons;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



public class BeanListArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {		
		if (ArrayList.class != parameter.getParameterType()){
			return false;
		};		
		Annotation[] paramAnns = parameter.getParameterAnnotations(); 
		for (Annotation paramAnn : paramAnns) {                     
	        if (BeanList.class.isInstance(paramAnn)) {
	        	return true;
	        }
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {				
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		
		Annotation annotation = parameter.getParameterAnnotation(BeanList.class);
		String baseName = ((BeanList)annotation).base();
		String[] baseArray = request.getParameterValues(baseName);		
		if (baseArray == null){
			return null;
		}					
		Type commandType = (((ParameterizedType)parameter.getGenericParameterType())
							.getActualTypeArguments())[0];		
		Class commandClass = (Class)commandType;
				
		Object command = BeanUtils.instantiateClass(commandClass);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command);
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
	    		new SimpleDateFormat("yyyy/MM/dd"), false));
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	    		new SimpleDateFormat("yyyy-MM-dd"), false));		
		binder.bind(request);					
		ArrayList aList = new ArrayList();								
		
		for (String base:baseArray){
			Object newCommand = BeanUtils.instantiateClass(commandClass);
			BeanUtils.copyProperties(command,newCommand);
			BeanWrapper bWrapper = new BeanWrapperImpl(newCommand);
			bWrapper.setPropertyValue(baseName, base);
			aList.add(newCommand);
		}		
		return   aList; 
	}
}