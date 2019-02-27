package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialRequest;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialResponse;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityS2SClientImpl implements SecurityS2SClient {

	private final Throwable cause;
	
	public SecurityS2SClientImpl(Throwable cause) {
		this.cause = cause;
	}
	
	@Override
	public ResponseEntity<S2SResponse<S2SCredentialResponse>> login(S2SCredentialRequest credentials) {
		log.debug("Throwable class: [{}] {}", cause.getClass().getName(), cause.getMessage());
		
		ResponseEntity<S2SResponse<S2SCredentialResponse>> response;
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException e = (ClientDecoderException) cause;
			
			S2SResponse<S2SCredentialResponse> data = new ObjectMapper()
					.convertValue(e.getData(), 
							new TypeReference<S2SResponse<S2SCredentialResponse>>() {});
			
			
			log.debug("ClientDecoderException data: [{}] {}", e.getStatus(), data.toString());
			
			response = new ResponseEntity<>(data, e.getStatus());
			
		} else if (cause.getClass().equals(HystrixTimeoutException.class)) {
			response = new ResponseEntity<>(new S2SResponse.Builder<S2SCredentialResponse>().build(), 
					HttpStatus.REQUEST_TIMEOUT);
			
		} else if (cause.getClass().equals(HystrixBadRequestException.class)) {
			response = new ResponseEntity<>(new S2SResponse.Builder<S2SCredentialResponse>().build(), 
					HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(new S2SResponse.Builder<S2SCredentialResponse>().build(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
