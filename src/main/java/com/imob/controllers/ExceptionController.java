package com.imob.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.servlet.ModelAndView;


@Controller
public class ExceptionController {			
	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	public ModelAndView ioExceptionHandler(HttpServletRequest request, HibernateOptimisticLockingFailureException ex) throws JsonGenerationException, JsonMappingException, IOException {									
		ObjectMapper mapper = new ObjectMapper();		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", false);
		result.put("message", "Unexpected DB error");
		String errorJSON = mapper.writeValueAsString(result);		
		ModelAndView mav = new ModelAndView("/error");						
		mav.addObject("errorJSON", errorJSON);		
		return mav;
	}		
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView errorHandler(HttpServletRequest request, ConstraintViolationException ex) throws JsonGenerationException, JsonMappingException, IOException {						
		ObjectMapper mapper = new ObjectMapper();		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", false);
		result.put("message", "Validation error");
		String errorJSON = mapper.writeValueAsString(result);		
		ModelAndView mav = new ModelAndView("/error");						
		mav.addObject("errorJSON", errorJSON);
		mav.addObject("errorMap", result);
		return mav;
	}		
	/*
	@RequestMapping(value="/ajaxerror")
	@ResponseBody public Map<String,Object> forAjax(Model model) {		
		
		return result;
	}*/			
}
