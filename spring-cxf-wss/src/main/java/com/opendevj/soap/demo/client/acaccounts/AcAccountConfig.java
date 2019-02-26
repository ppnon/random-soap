package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class AcAccountConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new AcAccountErrorDecoder();
	}
	
}
