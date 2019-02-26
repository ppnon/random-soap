package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SecurityS2SFallback implements FallbackFactory<SecurityS2SClient> {

	@Override
	public SecurityS2SClient create(Throwable cause) {
		return new SecurityS2SClientImpl(cause);
	}
}
