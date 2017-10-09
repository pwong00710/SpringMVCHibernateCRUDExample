package org.arpit.java2blog.filter;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class SiteHitCounter implements Filter{

	private static Logger log = Logger.getLogger(SiteHitCounter.class);

	private AtomicInteger hitCounter;

	public void  init(FilterConfig config) 
			throws ServletException{
		// Reset hit counter.
		hitCounter = new AtomicInteger(1);
	}

	public void  doFilter(ServletRequest request, 
			ServletResponse response,
			FilterChain chain) 
					throws java.io.IOException, ServletException {

		// increase counter by one
		int hitCount = hitCounter.getAndIncrement();

		// Print the counter.
		log.debug("Site visits count :"+ hitCount );

		// Pass request back down the filter chain
		chain.doFilter(request,response);
	}
	public void destroy() 
	{ 
		// This is optional step but if you like you
		// can write hitCount value in your database.
	} 
}