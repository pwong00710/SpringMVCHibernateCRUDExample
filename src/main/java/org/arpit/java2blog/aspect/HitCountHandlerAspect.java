package org.arpit.java2blog.aspect;

import org.arpit.java2blog.aop.HitCountHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//<aop:config>
//<aop:aspect id="hitCount" ref="hitCountHandler">
//	<aop:pointcut id="controller"
//		expression="execution(* org.arpit.java2blog.controller.*.*(..))" />
//	<aop:before pointcut-ref="controller" method="beforeAdvice" />
//</aop:aspect>
//</aop:config>
//
//<beans:bean id="hitCountHandler" class="org.arpit.java2blog.aop.HitCountHandler" />   

@Aspect
public class HitCountHandlerAspect extends HitCountHandler {

	@Pointcut("execution(* org.arpit.java2blog.controller.*.*(..))")
	private void controller() {}

	@Before("controller()")
	public void beforeAdvise(JoinPoint jp) {
		super.beforeAdvice(jp);
	}	
}
