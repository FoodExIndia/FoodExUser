package com.foodex.user.security;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Handles encryption/decryption based on Spring AOP. This encrypts/decrypts
 * when controller and service methods are called using spring wiring.
 * 
 * @author Cognizant
 * @version 1.0
 * 
 */

public class EncryptAspect extends CipherAspect {

	/**
	 * Advice for service en-cryption.
	 * 
	 * @param pjp
	 * @param cipher
	 * @return Object
	 * @throws Throwable
	 */
	// @Around("execution(* com.foodex.user.controller.*.*(..)) &&
	// @annotation(cipher)")
	public Object cipherPoint(final ProceedingJoinPoint pjp, Cipher cipher) throws Throwable {
		final MethodSignature signature = (MethodSignature) pjp.getSignature();
		final Method method = signature.getMethod();

		final Object response = pjp.proceed();
		if (response != null) {
			System.out.println("cipherPoint");
			Encrypt encrypt = null;
			if ((encrypt = method.getAnnotation(Encrypt.class)) != null
					|| (encrypt = response.getClass().getAnnotation(Encrypt.class)) != null) {
				cipher(response, encrypt.value(), true);
			}
		}
		return response;
	}

}
