package com.imob.controllers;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;




import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.servlet.ModelAndView;

import com.imob.commons.Ajax;


@Controller
public class ExceptionController {			
	
	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	public ModelAndView hibernateExceptionHandler(HttpServletRequest request, HibernateOptimisticLockingFailureException ex) {											
		return handleErrorMav(request,"Unexpected DB error");
	}	
	@ExceptionHandler(org.springframework.validation.BindException.class)
	public ModelAndView bindExceptionHandler(HttpServletRequest request, Exception ex) {											
		return handleErrorMav(request,"Validation error");
	}	
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView validateExceptionHandler(HttpServletRequest request, ConstraintViolationException ex) {											
		return handleErrorMav(request,"Validation error");
	}				
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/erroroutput",produces="application/json",headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> forAjax(HttpServletRequest request) {								
		return (Map<String, Object>) request.getAttribute("errorMap");		
	}
	@RequestMapping(value="/erroroutput",produces="application/json",headers="X-Requested-With!=XMLHttpRequest")
	public String forNotAjax(HttpServletRequest request,Model model) {								
		@SuppressWarnings("unchecked")
		Map<String,Object> result = (Map<String, Object>) request.getAttribute("errorMap"); 			
		model.addAttribute("errorMessage", (String)result.get("message"));				
		return "error";		
	}
	
	private ModelAndView handleErrorMav(HttpServletRequest request,String errorMessage){
		
		request.setAttribute("errorMap", Ajax.buildErrorResult(errorMessage));
		ModelAndView mav = new ModelAndView("forward:/erroroutput");
		return mav;	
	}
	
	
}
