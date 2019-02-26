package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;

@FeignClient(name="acaccountsClient", url="${client.acaccountsClient.url}", 
				fallbackFactory=AcAccountFallback.class, primary=false,
				configuration=AcAccountConfig.class)
public interface AcAccountClient {

	@PostMapping(value="${client.acaccountsClient.post.accounts.path}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AcResponse> createAccount(@RequestBody AcRequest account);
	
	@PostMapping(value="${client.acaccountsClient.post.accounts.officeprofile.path}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AcResponse> associateProfile(@PathVariable(name="id", required=true) String id,@RequestBody AcRequest account);
}
