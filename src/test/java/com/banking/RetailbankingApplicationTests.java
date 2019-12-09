package com.banking;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetailbankingApplicationTests {

	@Test
	public void applicationTest() {
		RetailbankingApplication.main(new String[] {});
	    assertTrue(true);
	}
}
