package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptTest {

	@Test
	public void test() {
		byte[] bytes = new byte[]{'a','b','c','d'};
		byte[] bytes2 = new byte[]{'a','b','c','d'};
		byte[] key = new byte[]{'h','e','j'};
		Crypt.encrypt(bytes, bytes.length, key, key.length);
		assertNotEquals(bytes[0], bytes2[0] );
		assertNotEquals(bytes[1], bytes2[1] );
		assertNotEquals(bytes[2], bytes2[2] );
		assertNotEquals(bytes[3], bytes2[3] );
		assertEquals(bytes.length, bytes2.length);
		System.out.println(new String(bytes));
		System.out.println(new String(bytes2));
		Crypt.decrypt(bytes, bytes.length, key, key.length);
		
		assertEquals(bytes[0], bytes2[0] );
		assertEquals(bytes[1], bytes2[1] );
		assertEquals(bytes[2], bytes2[2] );
		assertEquals(bytes[3], bytes2[3] );
		
	}

}
