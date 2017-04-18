package org.message.encrypt.hash;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.message.encrypt.digest.MD5Cipher;

public class MD5CipherTest {
	
	private static final String PLAINTEXT = "I am test";
	
	@Test
	public void testGetEncryptedMessage(){
		String md5 = MD5Cipher.getEncryptedMessage(PLAINTEXT);
		assertNotNull(md5);
	}

}
