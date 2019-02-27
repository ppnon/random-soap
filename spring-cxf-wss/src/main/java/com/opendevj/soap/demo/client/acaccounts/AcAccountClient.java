package com.opendevj.soap.demo.client.acaccounts;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;

@FeignClient(name="acaccountsClient", url="${client.acaccountsClient.url}", 
				fallbackFactory=AcAccountFallback.class, primary=false,
				configuration=AcAccountConfig.class)
public interface AcAccountClient {

	@PostMapping(value="${client.acaccountsClient.post.accounts.path}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AcResponse<AcAccountResponse>> createAccount(
			@RequestBody AcRequest<AcAccountRequest> account);
	
	@PostMapping(value="${client.acaccountsClient.post.accounts.officeprofile.path}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AcResponse<AcProfileResponse>> associateProfile(
			@PathVariable(name="id", required=true) String id,
			@RequestBody AcRequest<ArrayList<AcProfileRequest>> account);
}
