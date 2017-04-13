package org.message.encrypt.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.message.encrypt.tool.Encode;
import org.message.encrypt.tool.Numeric;

import com.google.common.io.BaseEncoding;

public class AESCipher {
	private static final String ALGORITHM_AES256 = "AES/CBC/PKCS5Padding";// "AES/CBC/PKCS7Padding";
	private final SecretKeySpec secretKeySpec;
	private static final String CHARSET = "UTF-8";
	private static final String DEFAULT_IV = "iv is default value";
	private Cipher cipher;
	private IvParameterSpec iv;

	
	public AESCipher(String key) {
		this(key, DEFAULT_IV);
	}

	public AESCipher(String key, String iv) {
		this(Numeric.hexStringToByteArray(key), Numeric.hexStringToByteArray(iv));
	}

	private AESCipher(byte[] key, byte[] iv) {
		// Security.addProvider(new BouncyCastleProvider());
		if (null == key || key.length != 32) {
			throw new RuntimeException("input params key must be 32bit bytes array");
		}
		if (null == iv || iv.length != 16) {
			throw new RuntimeException("input params iv must be 16bit bytes array");
		}
		this.secretKeySpec = new SecretKeySpec(key, "AES");
		this.iv = new IvParameterSpec(iv);
		try {
			this.cipher = Cipher.getInstance(ALGORITHM_AES256);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException("instantiation objects Cipher exception");
		}
	}

	/**
	 * AES Encrypt algorithm
	 * 
	 * @param encryptSource
	 *            not null string
	 * @return after AES encrypt result , the type of the string
	 */
	public String getEncryptedMessage(final String encryptSource) {
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
		byte[] encryptedTextBytes = null;
		try {
			encryptedTextBytes = cipher.doFinal(encryptSource.getBytes(CHARSET));
		} catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			throw new RuntimeException("AES encrypt exception");
		}
		return Encode.baseEncode(encryptedTextBytes);
	}

	/**
	 * AES decrypt algorithm
	 * 
	 * @param decryptSource
	 *            AES encrypted cipher, type of String
	 * @return decrypted plaintext, type of string
	 */
	public String getDecryptMessage(String decryptSource) {

		Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
		byte[] encryptedTextBytes = null;
		String decryptResult = null;
		try {
			encryptedTextBytes = cipher.doFinal(BaseEncoding.base64().decode(decryptSource));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException("AES decrypt exception");
		}
		try {
			decryptResult = new String(encryptedTextBytes, CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("bytes array convert into string exception");
		}
		return decryptResult;
	}

	private Cipher getCipher(int encryptMode) {
		try {
			cipher.init(encryptMode, secretKeySpec, iv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException("init objects Cipher exception");
		}
		return cipher;
	}

	/*public static byte[] hexStringToBytes(String hexStr) {
		int len = (hexStr.length() / 2);
		byte[] result = new byte[len];
		char[] charArr = hexStr.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(charArr[pos]) << 4 | toByte(charArr[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	private static byte[] parseHexStr2Byte(String hexStr) {
		if (null == hexStr || hexStr.length() != 64){
			throw new RuntimeException("key convert into bytes arrays error, key must be 64-");
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}*/

}
