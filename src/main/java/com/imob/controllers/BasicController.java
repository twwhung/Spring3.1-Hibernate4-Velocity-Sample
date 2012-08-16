package com.imob.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {
	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);
	
	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	public ModelAndView ioExceptionHandler(HttpServletRequest request, HibernateOptimisticLockingFailureException ex) throws JsonGenerationException, JsonMappingException, IOException {							
		logger.info("hiberante error: " + ex.getMessage());
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
		logger.info("validation error: " + ex.getMessage());
		ObjectMapper mapper = new ObjectMapper();		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", false);
		result.put("message", "Validation error");
		String errorJSON = mapper.writeValueAsString(result);		
		ModelAndView mav = new ModelAndView("/error");						
		mav.addObject("errorJSON", errorJSON);		
		return mav;
	}
}
