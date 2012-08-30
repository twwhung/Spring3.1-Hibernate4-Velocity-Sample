package com.imob.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ControllerTest {
	@Autowired
    private RequestMappingHandlerAdapter handleAdapter;
	
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	@Test
	public void playerControllerTest() throws Exception{
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/players.show");
		request.setMethod("GET");
		
		Object handler = handlerMapping.getHandler(request).getHandler();
		ModelAndView mav = handleAdapter.handle(request, response,handler);
		
		ModelAndViewAssert.assertViewName(mav,"players");
								
	}

}
