package it.flowbe.ai.config;

import org.aspectj.lang.ProceedingJoinPoint;
import it.flowbe.tenetcommonlibrary.config.security.LoggingAspectGenericBehaviour;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	@Autowired
	private LoggingAspectGenericBehaviour loggingAspectGenericBehaviour;

	//AOP expression for which methods shall be intercepted
    @Around("within(it.flowbe.ot.service.impl.*) || within(it.flowbe.ot.controller.*)")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	if(log.isDebugEnabled() || log.isWarnEnabled() || log.isTraceEnabled())
			return loggingAspectGenericBehaviour.profileAllMethods(proceedingJoinPoint);
		else
			return proceedingJoinPoint.proceed();
    }
	
}
