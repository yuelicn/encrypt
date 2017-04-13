package org.message.encrypt.hash;
import static org.junit.Assert.*;

import org.junit.Test;
public class SHACipherTest {
	private static final String PLAINTEXT = "I am test";
	@Test
	public void testSHA256(){
		String hash256 = SHACipher.SHA256(PLAINTEXT);
		assertNotNull(hash256);
	}
	@Test
	public void testSHA512(){
		String hash512 = SHACipher.SHA512(PLAINTEXT);
		assertNotNull(hash512);
	}
}
