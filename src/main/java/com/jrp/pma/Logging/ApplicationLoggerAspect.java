package com.jrp.pma.Logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.jrp.pma.Controllers..*)")
	public void definePackagePointcuts() {
		// empty method just to name the location specified in the point cut
		
	}
	
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint jp) throws Throwable {
		log.debug("\n \n \n");
		log.debug("************ Before Method Execution ************ \n {}.{} () with argument(s) = {}",
				// Will retrieve the class name
				jp.getSignature().getDeclaringTypeName(),
				// Will retrieve the method name that's being invoked
				jp.getSignature().getName(),
				// Will retrieve the arguments to given method
				Arrays.toString(jp.getArgs()));
		log.debug("------------------------------------------- \n \n \n");
		
		Object o = jp.proceed();
		
		log.debug("\n \n \n");
		log.debug("************ After Method Execution ************ \n {}.{} () with argument(s) = {}",
				// Will retrieve the class name
				jp.getSignature().getDeclaringTypeName(),
				// Will retrieve the method name that's being invoked
				jp.getSignature().getName(),
				// Will retrieve the arguments to given method
				Arrays.toString(jp.getArgs()));
		log.debug("------------------------------------------- \n \n \n");
		
		return o;
	}
	
}
