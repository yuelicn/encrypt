package org.message.encrypt.hash;


import static org.junit.Assert.*;
import org.junit.Test;

public class ND5CipherTest {
	
	private static final String PLAINTEXT = "I am test";
	
	@Test
	public void testGetEncryptedMessage(){
		String md5 = ND5Cipher.getEncryptedMessage(PLAINTEXT);
		assertNotNull(md5);
	}

}
