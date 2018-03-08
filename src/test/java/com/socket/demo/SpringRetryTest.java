package com.socket.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.socket.demo.service.RetryClientSocketService;
import com.socket.demo.service.RetryTemplateDemo;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:/Users/syli/Documents/workspace-sts-3.9.2.RELEASE/socketdemo/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
//@ContextConfiguration(locations = {"classpath:./spring/appServlet/servlet-context.xml" })
public class SpringRetryTest {

	@Autowired
	private RetryClientSocketService retryClientSocketService;
	
	@Autowired
	private RetryTemplateDemo retryTemplateDemo;
	
	@Test
	public void testRetry() throws Exception {
		//retryClientSocketService.requestSoc();
		retryTemplateDemo.retryTemp("这是一个测试");
		assertTrue(true);
	}
	
}
