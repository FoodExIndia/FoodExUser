package com.foodex.user.security;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Handles encryption/decryption based on Spring AOP. This encrypts/decrypts
 * when controller and service methods are called using spring wiring.
 * 
 * @author Cognizant
 * @version 1.0
 * 
 */
public class CipherAspect {

	/**
	 * 
	 * @param object
	 * @param fieldNames
	 * @param enCrypt
	 * @param encryptedList
	 * @throws Exception
	 */
	protected void cipherInnerObject(final Object object, final String[] fieldNames, boolean enCrypt,
			final List<String> encryptedList) throws Exception {

		if (object != null) {

			// Check whether the cipher object is a list.
			if (object instanceof List) {
				for (Object listValue : (List<?>) object) {

					// Recursive call
					cipherInnerObject(listValue, fieldNames, enCrypt, encryptedList);
				}
			} else {

				for (String fieldName : fieldNames) {

					// Get the field from the object
					final Field field = object.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);

					// Get value to be encrypted from the Object
					final Object value = field.get(object);
					if (value != null) {
						if (value instanceof String) {

							// Check if the object is already encrypted.
							if (encryptedList.contains(value)) {
								return;
							}

							// Encrypt/decrypt value based on the encrypt flag.
							final String encryptedValue = CipherHandler.get(value.toString(), enCrypt);

							// Add encrypted value in a list to future check.
							// Check line number 54.
							encryptedList.add(encryptedValue);

							// Set encrypted value to the Object.
							field.set(object, encryptedValue);
						} else {
							Annotation annotation = null;
							String[] objectFieldName = fieldNames;
							if (enCrypt && (annotation = field.getAnnotation(Encrypt.class)) != null) {
								// Get encrypt field name.
								objectFieldName = ((Encrypt) annotation).value();
							} else if ((annotation = field.getAnnotation(Decrypt.class)) != null) {

								// Get de-crypt field name.
								objectFieldName = ((Decrypt) annotation).value();
							}

							// Recursive call
							cipherInnerObject(value, objectFieldName, enCrypt, encryptedList);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param object
	 * @param fieldNames
	 * @param enCrypt
	 * @return Object
	 * @throws Exception
	 */
	protected Object cipher(final Object object, final String[] fieldNames, boolean enCrypt) throws Exception {
		Object ciphered = object;
		if (object != null) {
			if (object instanceof String) {
				final String stringValue = object.toString();
				if (StringUtils.isNotBlank(stringValue)) {
					// Encrypt/decrypt String value based on the encrypt flag.
					ciphered = CipherHandler.get(stringValue, enCrypt);
				}
			} else {

				// Encrypt/decrypt Object based on the encrypt flag.
				cipherInnerObject(object, fieldNames, enCrypt, new ArrayList<String>(0));
			}
		}

		return ciphered;
	}
}
