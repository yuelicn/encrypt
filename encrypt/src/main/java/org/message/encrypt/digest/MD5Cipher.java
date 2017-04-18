package org.message.encrypt.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import com.google.common.io.BaseEncoding;

public class MD5Cipher {
	
	public static String getEncryptedMessage(final String encryptSource) {
		if (StringUtils.isBlank(encryptSource)) {
			throw new RuntimeException("MD5 encrypt data is null");
		}
		byte[] encrypteByte = null;
		try {
			encrypteByte = encryptSource.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5 encrypt data convert into an array of bytes ,exception");
		}
		byte[] resultByte = getMD5(encrypteByte);
		String result = BaseEncoding.base32().encode(resultByte);
		return result;
	}

	private static byte[] getMD5(byte[] encryptSource) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(encryptSource);
			byte[] md5Digest = messageDigest.digest();
			return md5Digest;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 encrypt exception");
		}
	}
	
	
	
	

}
