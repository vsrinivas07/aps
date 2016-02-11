package com.apceps.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionValidationFilter
 */
public class SessionValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 	         
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
        //Get the IP address of client machine.
        String ipAddress = httpReq.getRemoteAddr();
        String URI = httpReq.getRequestURI(); 
        
        
        //Log the IP address and current timestamp.
        System.out.println("IP "+ipAddress + ", Time "  + new Date().toString()+"URI:"+URI);
		
        if(URI.endsWith("index.jsp") || URI.endsWith("logout.jsp") || URI.endsWith("loginController") || URI.endsWith("js") || URI.endsWith("css")){
        	chain.doFilter(request, response);
        	
        } 
        else if(httpReq.getSession().getAttribute("userCdo")==null){
        	httpReq.setAttribute("message", "Please login first");
    		httpRes.sendRedirect("index.jsp" );
        }
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
