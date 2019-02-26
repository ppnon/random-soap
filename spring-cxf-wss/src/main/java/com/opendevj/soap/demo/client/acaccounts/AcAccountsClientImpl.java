package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
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
	public ResponseEntity<AcResponse> createAccount(AcRequest account) {
		log.debug("Throwable class: [{}] {}", cause.getClass().getName(), cause.getMessage());
		
		ResponseEntity<AcResponse> response;
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException e = (ClientDecoderException) cause;
			AcResponse data = new ObjectMapper().convertValue(e.getData(), 
					AcResponse.class);
			
			log.debug("ClientDecoderException data: [{}] {}", e.getStatus(), data.toString());
			
			response = new ResponseEntity<>(data, e.getStatus());
			
		} else if (cause.getClass().equals(HystrixTimeoutException.class)) {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.REQUEST_TIMEOUT);
			
		} else if (cause.getClass().equals(HystrixBadRequestException.class)) {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public ResponseEntity<AcResponse> associateProfile(String id, AcRequest account) {
		log.debug("Throwable class: [{}] {}", cause.getClass().getName(), cause.getMessage());
		
		ResponseEntity<AcResponse> response;
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException e = (ClientDecoderException) cause;
			AcResponse data = new ObjectMapper().convertValue(e.getData(), 
					AcResponse.class);
			
			log.debug("ClientDecoderException data: [{}] {}", e.getStatus(), data.toString());
			
			response = new ResponseEntity<>(data, e.getStatus());
			
		} else if (cause.getClass().equals(HystrixTimeoutException.class)) {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.REQUEST_TIMEOUT);
			
		} else if (cause.getClass().equals(HystrixBadRequestException.class)) {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(new AcResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
