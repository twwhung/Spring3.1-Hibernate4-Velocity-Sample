package com.imob.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * http://www.onjava.com/pub/a/onjava/2003/11/19/filters.html 
 * http://www.koders.com/java/fid0310F66A5807F9E89C9AA558454C728DC07C971A.aspx
 * */
public class GZIPFilter implements Filter{
		 
	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {		  		  		  
		  
		  
		  if (req instanceof HttpServletRequest) {
			 
		  HttpServletRequest request = (HttpServletRequest) req;
	      HttpServletResponse response = (HttpServletResponse) res;

	      if (isGZIPSupported(request)) {	    	  
	        GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
	       
	       
	        chain.doFilter(req, wrappedResponse);
	        wrappedResponse.finishResponse();

	        return;
	      }

	      chain.doFilter(req, res);
	    }
	  }
	  private boolean isGZIPSupported(HttpServletRequest req) {
		    String browserEncodings = req.getHeader("accept-encoding");
		    boolean supported = ((browserEncodings != null) && (browserEncodings.indexOf("gzip") != -1));
		    return supported;
		  }
	public void destroy() {
				
	}	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
