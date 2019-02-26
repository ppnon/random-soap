package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialRequest;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;

@FeignClient(name="securityS2SClient", url="${client.securityS2SClient.url}", 
				fallbackFactory=SecurityS2SFallback.class, primary=false, 
				configuration=SecurityS2SConfig.class)
public interface SecurityS2SClient {
	
	@PostMapping(value="${client.securityS2SClient.login.path}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<S2SResponse> login(@RequestBody S2SCredentialRequest credentials);
}
