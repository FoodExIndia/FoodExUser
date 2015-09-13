package com.foodex.user.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class DecryptHandler {

	@Before(value = "execution(* com.foodex.user.controller(..))")
	public void decryptionHandler(ProceedingJoinPoint joinPoint) {
		Object[] obj = joinPoint.getArgs();
		//MethodSignature method=(MethodSignature) joinPoint.getSignature();
		//method.getParameterNames();
	}

}
