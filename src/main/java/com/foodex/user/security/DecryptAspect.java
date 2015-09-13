package com.foodex.user.security;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Handles encryption/decryption based on Spring AOP. This encrypts/decrypts
 * when controller and service methods are called using spring wiring.
 * 
 * @author Cognizant
 * @version 1.0
 * 
 */
/*@Component
@Aspect
@Order(value = 2)*/
public class DecryptAspect extends CipherAspect {

	/**
	 * Advice for service de-cryption.
	 * 
	 * @param pjp
	 * @param cipher
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.foodex.user.controller.*.*(..)) && @annotation(cipher)")
	public Object cipherPoint(final ProceedingJoinPoint pjp, Cipher cipher) throws Throwable {
		final Object[] parameters = pjp.getArgs();
		final MethodSignature signature = (MethodSignature) pjp.getSignature();
		final Method method = signature.getMethod();
		final Annotation[][] methodAnnotations = method.getParameterAnnotations();
		try {
			System.out.println(signature.getName());
			System.out.println("cipher");
			
			
			for (int position = 0; position < methodAnnotations.length; position++) {
				final Object value = parameters[position];
				if (value == null) {
					continue;
				}
				final Annotation[] b = methodAnnotations[position];
				Decrypt decrypt = null;
				for (int counter = 0; counter < b.length; counter++) {
					final Annotation annotation = b[counter];
					if (annotation instanceof Decrypt) {
						decrypt = ((Decrypt) annotation);
						break;
					}
				}
				if (value instanceof String) {
					if (decrypt == null || StringUtils.isBlank((String) value)) {
						continue; 
					}
					parameters[position] = cipher(value, null, false);
				} else {
					if (decrypt == null || ArrayUtils.isNotEmpty(decrypt.value())) {
						decrypt = value.getClass().getAnnotation(Decrypt.class);
					}
					if (decrypt != null) {
						parameters[position] = cipher(value, decrypt.value(), false);
					}
				}
			}
		} catch (Exception exception) {
			// throw ErrorUtils.error(exception, SiemensError.BAD_REQ);
		}
		return pjp.proceed(parameters);
	}

}
