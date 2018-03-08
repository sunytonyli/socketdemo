package com.socket.demo.service;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class RetryTemplateDemo {

	public void retryTemp(String msg) throws Exception {
		
		final String tmpmsg = msg;
		
		SimpleRetryPolicy policy = new SimpleRetryPolicy();
		// Set the max retry attempts
		policy.setMaxAttempts(5);
		
		//退避策略：指数退避策略
		//nitialInterval属性为初始默认间隔，默认值是100毫秒；
		//maxInterval属性为最大默认间隔。当实际计算出的间隔超过该值时，使用该值,默认为30秒；
		//multiplier为乘数。默认2，当其等于1时，其行为同FixedBackOffPolicy为固定时间间隔.建议不要使用1，会造成重试过快！
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(1000);
		backOffPolicy.setMaxInterval(10000);
		backOffPolicy.setMultiplier(2);
		backOffPolicy.setSleeper(new ThreadWaitSleeper());

		// Use the policy...
		RetryTemplate template = new RetryTemplate();
		template.setRetryPolicy(policy);
		template.setBackOffPolicy(backOffPolicy);
		
		String message = template.execute(new RetryCallback<String, Exception>() {
		    public String doWithRetry(RetryContext context) {
		        // business logic here
		    	System.out.println("msg======" + tmpmsg);
		    		throw new RuntimeException("this is test template");
		    }
		},
		  new RecoveryCallback<String>() {
			public String recover(RetryContext context) throws Exception {
		          // recover logic here
		    		System.out.println("retry fail=====");
		    		
		    		return "Retry fail----------";
		    }
		});
		
		System.out.println("RETRY FINISH======");
		
	}
	
}
