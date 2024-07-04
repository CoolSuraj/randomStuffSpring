package com.random.stuff.suraj.randomStuffAPI.RestTemplateConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguation {
	
	@Bean
	public RestTemplate restTemplate() {
		
		
		return new RestTemplate();
		
	}

}
