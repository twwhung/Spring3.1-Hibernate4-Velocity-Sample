package com.imob.controllers;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;







 

@Controller
public class HomeController extends BasicController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
			
	
	
		
	@RequestMapping(value="/test", method={RequestMethod.GET})
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		logger.info("testing...");		
		//response.setContentType("text/pain");		
		ModelAndView mav = new ModelAndView("/test");	
		
		return mav;
	}
	
	@RequestMapping(value="/test2", method={RequestMethod.GET})
    @ResponseBody public Map<String,String> homePageJson(HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("status", "success");
		result.put("content", "It Works!");
		String k = mapper.writeValueAsString(result);
		result.put("json",k);
		return result;
		
    }	
		
}
