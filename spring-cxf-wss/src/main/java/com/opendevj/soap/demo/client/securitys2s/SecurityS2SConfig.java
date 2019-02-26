package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class SecurityS2SConfig {
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new SecurityS2SErrorDecoder();
	}
}
