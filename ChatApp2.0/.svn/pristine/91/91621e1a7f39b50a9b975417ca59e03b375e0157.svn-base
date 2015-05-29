package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class PDU_FactoryTest {
	
	/**
	 * A JUnit test. Checks to see that the last up to four
	 * zeroes is removed from an byte array.
	 */
	@Test
	public void testRemoveZerosByteArray() {
		byte[] bytes = {'\0','\0',3,5,2,5,'\0','\0'};
		byte[] bytesAfterRemovedZeros = PDU_Factory.removeZeros(bytes);
		assertEquals(6, bytesAfterRemovedZeros.length);
		
		bytes = new byte[]{3,5,2,5,'\0','\0','\0','\0'};
		bytesAfterRemovedZeros = PDU_Factory.removeZeros(bytes);
		assertEquals(4, bytesAfterRemovedZeros.length);
		
		bytes = new byte[]{'\0','\0','\0','\0',3,5,2,5};
		bytesAfterRemovedZeros = PDU_Factory.removeZeros(bytes);
		assertEquals(8, bytesAfterRemovedZeros.length);
	}
}
