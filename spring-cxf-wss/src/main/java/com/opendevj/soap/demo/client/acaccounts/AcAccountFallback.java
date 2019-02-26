package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class AcAccountFallback implements FallbackFactory<AcAccountClient>{

	@Override
	public AcAccountClient create(Throwable cause) {
		return new AcAccountsClientImpl(cause);
	}
 
}

