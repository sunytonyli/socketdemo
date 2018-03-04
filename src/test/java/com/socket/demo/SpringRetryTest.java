package com.socket.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.socket.demo.service.RetryClientSocketService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:servlet-context.xml" })
public class SpringRetryTest {

	@Autowired
	private RetryClientSocketService retryClientSocketService;
	
	@Test
	public void testRetry() {
		retryClientSocketService.requestSoc();
		assertTrue(true);
	}
	
}
