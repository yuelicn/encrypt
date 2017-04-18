package org.message.encrypt.hash;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.message.encrypt.tool.Encode;

public class HMACCipher {

	/**
	 * generated HMAC algorithm Key
	 * 
	 * @return MAC key
	 */
	public static String initMacKey() {
		String macKey = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(TYPE.HmacMD5.toString());
			SecretKey secretKey = keyGenerator.generateKey();
			macKey = Encode.baseEncode(secretKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("initMacKey exception", e);
		}
		return macKey;
	}
	/**
	 * @param message 
	 * @param macKey
	 * @return
	 */
	public static String encryptHmac(byte[] message, String macKey) {
		byte[] byteMacKey = Encode.baseDecode(macKey);
		SecretKey secretKey = new SecretKeySpec(byteMacKey, TYPE.HmacMD5.toString());
		Mac mac;
		String encrypt = null;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] cipher = mac.doFinal(message);
			encrypt = Encode.baseEncode(cipher);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException("HMAC encrpty method exception", e);
		}
		return encrypt;
	}

	enum TYPE {
		HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512
	}

}
