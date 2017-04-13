package org.message.encrypt.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * SHA algorithm support two algorithm first: SHA256 second: SHA512
 * 
 * @author yue.li3
 */
public class SHACipher {

	private static final String ALGORITHM_SHA256 = "SHA-256";
	private static final String ALGORITHM_SHA512 = "SHA-512";

	/**
	 * SHA256 algorithm
	 * 
	 * @param encryptSource
	 *            -- >need to hash the String
	 * @return --> After the SHA256 hash value of the
	 */
	public static String SHA256(final String encryptSource) {
		return SHA(encryptSource, ALGORITHM_SHA256);
	}

	/**
	 * SHA512 algorithm
	 * 
	 * @param encryptSource
	 *            --> need to hash String
	 * @return After the SHA512 hash value of the
	 */
	public static String SHA512(final String encryptSource) {
		return SHA(encryptSource, ALGORITHM_SHA512);
	}

	private static String SHA(String encryptSource, String algorithm) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA Exception", e);
		}
		byte[] input = encryptSource.getBytes();
		messageDigest.update(input);
		byte[] cipher = messageDigest.digest();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cipher.length; i++) {
			String hex = Integer.toHexString(0xff & cipher[i]);
			if (hex.length() == 1) {
				str.append('0');
			}
			str.append(hex);
		}
		return str.toString();
	}
}