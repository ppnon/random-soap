package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcAccountErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		log.debug("Error Response: [{}] {}", response.status(), response.body().toString());
		
		AcResponse<?> responseAC;
		try {
			responseAC = new ObjectMapper().readValue(response.body().asInputStream(), AcResponse.class);
		} catch (Exception e) {
			log.debug("Error trying to read the response: {}", e.getMessage());
			
			responseAC = new AcResponse.Builder<>().build();
		}
		
		return new ClientDecoderException(HttpStatus.valueOf(response.status()), responseAC);
	}

}
