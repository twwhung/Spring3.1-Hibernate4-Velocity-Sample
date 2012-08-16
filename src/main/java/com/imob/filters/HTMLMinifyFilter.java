package com.imob.filters;

import java.io.*;

import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/*
 * http://rod.vagg.org/2011/11/minifying-html-in-the-servlet-container/
 * */ 
public class HTMLMinifyFilter implements Filter {
    private Pattern regex = null;
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        ResponseWrapper wrapper = new ResponseWrapper(response);
        chain.doFilter(req, wrapper);
        String html = wrapper.toString();
        if (regex != null && response.getContentType() != null && response.getContentType().startsWith("text/html"))
            html = regex.matcher(html).replaceAll("");
        response.setContentLength(html.getBytes().length);
        /*
        OutputStream out = response.getOutputStream();        
        out.write(html.getBytes());                
        out.close();
        throw new IOException("!!");
        */ 
        PrintWriter out = response.getWriter();
        out.write(html);
        out.close();
    }
 
    public void destroy() {
    }
 
    public void init(FilterConfig config) throws ServletException {
        StringBuffer pattern = new StringBuffer();
        
        appendIf(config, "strip-linestart-whitespace", pattern, "(?<=^)[ \\t]+");
        appendIf(config, "strip-lineend-whitespace", pattern, "[ \\t]+(?:$)");
        appendIf(config, "strip-multiple-whitespace", pattern, "([ \\t](?:[ \\t]))+");
        appendIf(config, "strip-blank-lines", pattern, "((\\r)?\\n[ \\t]*(?:(\\r)?\\n))+");
        if (pattern.length() != 0)
            regex = Pattern.compile(pattern.toString(), Pattern.MULTILINE);
    }
 
    private void appendIf(FilterConfig config, String configKey, StringBuffer pattern, String s) {
        if (config.getInitParameter(configKey) != null && config.getInitParameter(configKey).equals("true")) {
            if (pattern.length() != 0)
                pattern.append('|');
            pattern.append(s);
        }
    }
 
    static class ResponseWrapper extends HttpServletResponseWrapper {
        private CharArrayWriter output;
 
        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            this.output = new CharArrayWriter();
        }
 
        public String toString() {
            return output.toString();
        }
 
        public PrintWriter getWriter() {
            return new PrintWriter(output);
        }
    }
}