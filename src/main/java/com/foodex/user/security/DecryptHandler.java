package com.foodex.user.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DecryptHandler {

	@Before(value = "execution(* com.foodex.user.controller(..))")
	public void decryptionHandler(ProceedingJoinPoint joinPoint) {
		Object[] obj = joinPoint.getArgs();
	}

}
