package com.foodex.user.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class FoodExLogging {
	@Around("execution(* com.foodex.user.controller.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
	//	System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

	//	System.out.println("Around before is running!");
		return joinPoint.proceed(); // continue on the intercepted method
		//System.out.println("Around after is running!");

		//System.out.println("******");

	}
}
