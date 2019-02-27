package com.opendevj.soap.demo.client.acaccounts;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcAccountsClientImpl implements AcAccountClient {

	private final Throwable cause;
	
	public AcAccountsClientImpl(Throwable cause) {
		this.cause = cause;
	}
	
	@Override
	public ResponseEntity<AcResponse<AcAccountResponse>> createAccount(AcRequest<AcAccountRequest> account) {
		log.debug("Throwable class: [{}] {}", cause.getClass().getName(), cause.getMessage());
		
		ResponseEntity<AcResponse<AcAccountResponse>> response;
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			
			ClientDecoderException e = (ClientDecoderException) cause;
			
			AcResponse<AcAccountResponse> data = new ObjectMapper().convertValue(e.getData(), 
					new TypeReference<AcResponse<AcAccountResponse>>() {});
			
			log.debug("ClientDecoderException data: [{}] {}", e.getStatus(), data.toString());
			
			response = new ResponseEntity<>(data, e.getStatus());
			
		} else if (cause.getClass().equals(HystrixTimeoutException.class)) {
			response = new ResponseEntity<>(new AcResponse.Builder<AcAccountResponse>().build(),
					HttpStatus.REQUEST_TIMEOUT);
			
		} else if (cause.getClass().equals(HystrixBadRequestException.class)) {
			response = new ResponseEntity<>(new AcResponse.Builder<AcAccountResponse>().build(),
					HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(new AcResponse.Builder<AcAccountResponse>().build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public ResponseEntity<AcResponse<AcProfileResponse>> associateProfile(String id,
			AcRequest<ArrayList<AcProfileRequest>> account) {
		
		log.debug("Throwable class: [{}] {}", cause.getClass().getName(), cause.getMessage());
		
		ResponseEntity<AcResponse<AcProfileResponse>> response;
		
		//TODO: Response list of objects
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException e = (ClientDecoderException) cause;
			AcResponse<AcProfileResponse> data = new ObjectMapper().convertValue(e.getData(), 
					new TypeReference<AcResponse<AcProfileResponse>> () {});
			
			log.debug("ClientDecoderException data: [{}] {}", e.getStatus(), data.toString());
			
			response = new ResponseEntity<>(data, e.getStatus());
			
		} else if (cause.getClass().equals(HystrixTimeoutException.class)) {
			response = new ResponseEntity<>(new AcResponse.Builder<AcProfileResponse>().build(), 
					HttpStatus.REQUEST_TIMEOUT);
			
		} else if (cause.getClass().equals(HystrixBadRequestException.class)) {
			response = new ResponseEntity<>(new AcResponse.Builder<AcProfileResponse>().build(),
					HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(new AcResponse.Builder<AcProfileResponse>().build(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}
