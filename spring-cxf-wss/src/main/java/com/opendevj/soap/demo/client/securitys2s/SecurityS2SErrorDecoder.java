package com.opendevj.soap.demo.client.securitys2s;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;
import com.opendevj.soap.demo.exception.ClientDecoderException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityS2SErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		log.debug("Error Response: [{}] {}", response.status(), response.body().toString());
		
		S2SResponse<?> responseS2S;
		try {
			responseS2S = new ObjectMapper().readValue(response.body().asInputStream(), S2SResponse.class);

		} catch (Exception e) {
			log.debug("Error trying to read the response: {}", e.getMessage());
			
			responseS2S = new S2SResponse.Builder<>().build();
		}
		
		return new ClientDecoderException(HttpStatus.valueOf(response.status()), responseS2S);
	}

}
