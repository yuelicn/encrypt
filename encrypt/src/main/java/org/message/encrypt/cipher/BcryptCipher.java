package org.message.encrypt.cipher;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
/**
 * Bcrypt encryption algorithm
 * @author yue.li3
 *
 */
public class BcryptCipher {
	// generate salt seed
	private static final int SALT_SEED = 12;
	// the head fo salt
	private static final String SALT_STARTSWITH = "$2a$12";
	
	public static final String SALT_KEY = "salt";
	
	public static final String CIPHER_KEY = "cipher";
	
	/**
	 * Bcrypt encryption algorithm method
	 * @param encryptSource 
	 * 					need to encrypt the string
	 * @return Map , two values in Map , salt and cipher
	 */
	public static Map<String, String> Bcrypt(final String encryptSource) {
		String salt  = BCrypt.gensalt(SALT_SEED);
		Map<String, String> bcryptResult = Bcrypt(salt, encryptSource);
		return bcryptResult;
	}
	/**
	 * 
	 * @param salt encrypt salt, Must conform to the rules 
	 * @param encryptSource
	 * @return
	 */
	public static Map<String, String> Bcrypt(final String salt, final String encryptSource) {
		if (StringUtils.isBlank(encryptSource)) {
			throw new RuntimeException("Bcrypt encrypt input params can not be empty");
		}
		
		if (StringUtils.isBlank(salt) || salt.length() != 29) {
			throw new RuntimeException("Salt can't be empty and length must be to 29");
		}
		if (!salt.startsWith(SALT_STARTSWITH)) {
			throw new RuntimeException("Invalid salt version, salt version is $2a$12");
		}
		
		String cipher = BCrypt.hashpw(encryptSource, salt);
		Map<String, String> bcryptResult = new HashMap<String, String>();
		bcryptResult.put(SALT_KEY, salt);
		bcryptResult.put(CIPHER_KEY, cipher);
		return bcryptResult;
	}
	
}
