package com.opendevj.soap.demo.client.acaccounts;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;
import com.opendevj.soap.demo.exception.InternalServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcAccountClientImpl implements AcAccountClient {

	private final Throwable cause;
	
	public AcAccountClientImpl(Throwable cause) {
		this.cause = cause;
	}
	
	@Override
	public ResponseEntity<AcResponse<AcAccountResponse>> createAccount(AcRequest<AcAccountRequest> account) {
		log.warn("Fallback cause: {} : {}", cause.getClass().getName(), cause.getMessage());

		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException decoded = (ClientDecoderException) cause;
			try {
				AcResponse<AcAccountResponse> response = new ObjectMapper().convertValue(decoded.getBody(),
						new TypeReference<AcResponse<AcAccountResponse>>() {});
				
				log.debug("Response decoded[{}]: {}", decoded.getStatus(), response.toString());
				return new ResponseEntity<>(response, decoded.getStatus());
			} catch (Exception e) {
				log.error("Error reading response {}", e.getMessage(), e);
				throw e;
			}
			
		} 
		throw new InternalServiceException(cause);
	}

	@Override
	public ResponseEntity<AcResponse<AcProfileResponse>> associateProfile(String id,
			AcRequest<ArrayList<AcProfileRequest>> account) {
		log.warn("Fallback cause: {} : {}", cause.getClass().getName(), cause.getMessage());
		
		if (cause.getClass().equals(ClientDecoderException.class)) {
			ClientDecoderException decoded = (ClientDecoderException) cause;
			try {
				AcResponse<AcProfileResponse> response = new ObjectMapper().convertValue(decoded.getBody(),
						new TypeReference<AcResponse<AcProfileResponse>> () {});
				
				log.debug("Response decoded[{}]: {}", decoded.getStatus(), response.toString());
				return new ResponseEntity<>(response, decoded.getStatus());
			} catch (Exception e) {
				log.error("Error reading response: {}", e.getMessage(), e);
				throw e;
			}
		} 
		throw new InternalServiceException(cause);
	}
	
}
