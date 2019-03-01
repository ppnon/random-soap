package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialRequest;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialResponse;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;
import com.opendevj.soap.demo.exception.InternalServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityS2SClientImpl implements SecurityS2SClient {

	private final Throwable cause;
	
	public SecurityS2SClientImpl(Throwable cause) {
		this.cause = cause;
	}
	
	@Override
	public ResponseEntity<S2SResponse<S2SCredentialResponse>> login(S2SCredentialRequest credentials) {
		log.warn("Fallback cause: {} : {}", cause.getClass().getName(), cause.getMessage());
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException decoded = (ClientDecoderException) cause;
			try {
				S2SResponse<S2SCredentialResponse> response = new ObjectMapper().convertValue(decoded.getBody(),
						new TypeReference<S2SResponse<S2SCredentialResponse>>() {});
				
				log.debug("Response decoded[{}]: {}", decoded.getStatus(), response.toString());
				return new ResponseEntity<>(response, decoded.getStatus());
			} catch (Exception e) {
				log.error("Error reading response {}", e.getMessage(), e);
				throw e;
			}
			
		} 
		throw new InternalServiceException(cause);
	}
}
