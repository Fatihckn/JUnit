package com.junitdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JunitApplicationTests {
	JunitApplication application = new JunitApplication();

	@Test
	void contextLoads() {
		Assertions.assertEquals(6, application.add(2, 4), "2+4 must be 6");

		// fail("bu şekilde direkt hata mesajı fırlatabiliriz");
	}

	@Test
	void testEqualsAndNotEquals() {
		assertNotEquals(8, application.add(2, 8), "2+4 must not be 8");
	}

	@Test
	void testNullAndNotNull(){
		assertNull(application.checkNull(null), "object should be null");
		assertNotNull(application.checkNull("obj"), "object should not be null");
	}
}
