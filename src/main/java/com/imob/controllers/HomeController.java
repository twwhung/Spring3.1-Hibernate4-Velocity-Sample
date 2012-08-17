package com.imob.controllers;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {						
	@RequestMapping(value="/", method={RequestMethod.GET})
	public ModelAndView home() throws Exception {								
		return new ModelAndView("forward:/analyzes.show");
	}				
}
