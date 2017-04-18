package org.message.encrypt.hash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HMACCipherTest {

	public static final String MESSAGE = "I am test";
	public static String MAC_KEY = "GBAH4kFTtNR7YLehGaZ5kNR4dWbiA8/QCuOvJhG1NVcCiEcdANiT2QjAwRjTJ+fcChneo2lfg867GarqU2ICsw==" ;
	//@Before
	public void before() {
		System.out.println("====");
		MAC_KEY = HMACCipher.initMacKey();
	}
	
	@Test
	public void testEncryptHmac() {
		System.out.println(MAC_KEY);
		String cipher  = HMACCipher.encryptHmac(MESSAGE.getBytes(), MAC_KEY);
		System.out.println(cipher);
		assertEquals(cipher, "qq4nPm9L8IAtZHo/3Yjorw==");
	}
}
