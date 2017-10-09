package org.arpit.java2blog.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arpit.java2blog.common.CookieName;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.util.WebUtils;

public class HitCountHandler {
	public void beforeAdvice(JoinPoint jp){
		Object[] signatureArgs = jp.getArgs();
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		for (Object signatureArg: signatureArgs) {
			if (signatureArg instanceof HttpServletRequest) {
				request = (HttpServletRequest) signatureArg; 
			}
			if (signatureArg instanceof javax.servlet.http.HttpServletResponse) {
				response = (HttpServletResponse) signatureArg;
			}	
		}

		if (request != null && response != null) { 
			// increment hit counter
			Cookie hitCounterCookie = WebUtils.getCookie(request, CookieName.HIT_COUNTER);
			Long hitCounter = new Long(0);
			if (hitCounterCookie != null) {
				hitCounter = Long.valueOf(hitCounterCookie.getValue());
				hitCounter++;
				hitCounterCookie.setValue(hitCounter.toString());
			} else {
				hitCounter++;
				hitCounterCookie = new Cookie(CookieName.HIT_COUNTER, hitCounter.toString());
			}
			
			// ensure cookie of same path to avoid duplicate 
			hitCounterCookie.setPath(request.getContextPath());
			
			// create cookie and set it in response
			// refer by ${cookie.hitCounter.value} in jsp				
			response.addCookie(hitCounterCookie);
		}
		
	}
}
