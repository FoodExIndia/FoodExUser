/**
 * 
 */
package com.foodex.user.security;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

/**
 * @author Cognizant
 * @version : 1.0
 */
public class CipherHandler {

	private static final int SHA3_512 = 512;

	private static final int TRIPLE_DES_KEY_SIZE = 24;

	private static String UTF8 = "utf-8";

	private static String DESEDE = "DESede";

	private static String MODE = "DESede/ECB/PKCS5Padding";

	// private static final Logger LOGGER =
	// Logger.getLogger(CipherHandler.class);

	public static void main(String[] args) throws Exception {
		String text = "FOODEX";
		String codedtext = CipherHandler.encrypt(text);
		String decodedtext = CipherHandler.decrypt(codedtext);
		System.out.println(codedtext + " ---> " + decodedtext);
	}

	/**
	 * 
	 * @param text
	 * @param encrypted
	 * @return String
	 * @throws Exception
	 */
	public static String get(final String text, final boolean encrypted) throws Exception {
		try {
			return encrypted ? encrypt(text) : decrypt(text);
		} catch (Exception exception) {
			/*
			 * throw ErrorUtils.errorWithMsg(exception, SiemensError.BAD_REQ,
			 * "Invalid Data : " + text);
			 */
			return null;
		}
	}

	/**
	 * 
	 * @param message
	 * @return String
	 * @throws Exception
	 */
	public static String encrypt(final String message) throws Exception {
		String encryptedString = message;
		if (StringUtils.isNotBlank(message)) {

			final String sessionId ="12356" /*AuthHolder.getSessionId()*/;
			final DigestSHA3 md = new DigestSHA3(SHA3_512);
			final byte[] digestOfPassword = md.digest(sessionId.getBytes());
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, TRIPLE_DES_KEY_SIZE);

			final SecretKey key = new SecretKeySpec(keyBytes, DESEDE);
			final Cipher cipher = Cipher.getInstance(MODE);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			final byte[] plainTextBytes = message.getBytes(UTF8);
			final byte[] buf = cipher.doFinal(plainTextBytes);
			final char[] hexChars = Hex.encodeHex(buf, true);
			encryptedString = new String(hexChars);
		}
		// LOGGER.debug("Encrypted : " + message + " : " + encryptedString);
		return encryptedString;
	}

	/**
	 * 
	 * @param encryptedText
	 * @return String
	 * @throws Exception
	 */
	public static String decrypt(final String encryptedText) throws Exception {
		String decryptedString = encryptedText;
		if (StringUtils.isNotBlank(encryptedText)) {
			final String sessionId = "12356"/*AuthHolder.getSessionId()*/;
			final byte[] message = Hex.decodeHex(encryptedText.toCharArray());

			final DigestSHA3 md = new DigestSHA3(SHA3_512);
			final byte[] digestOfPassword = md.digest(sessionId.getBytes(UTF8));
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, TRIPLE_DES_KEY_SIZE);
			final SecretKey key = new SecretKeySpec(keyBytes, DESEDE);

			final Cipher decipher = Cipher.getInstance(MODE);
			decipher.init(Cipher.DECRYPT_MODE, key);

			final byte[] plainText = decipher.doFinal(message);

			decryptedString = new String(plainText, UTF8);
		}

	//	LOGGER.debug("Decrypted : " + encryptedText + " : " + decryptedString);

		return decryptedString;
	}
}
