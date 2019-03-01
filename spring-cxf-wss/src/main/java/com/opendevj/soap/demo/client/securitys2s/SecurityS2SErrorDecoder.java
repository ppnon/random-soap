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
		
		log.warn("Response to decode: {}[{}] : {}", methodKey, response.status(), response.body());
		
		try {
			S2SResponse<?> body = new ObjectMapper().readValue(
					response.body().asInputStream(), S2SResponse.class);
			
			log.debug("Reponse decoded: {}", body.toString());

			return new ClientDecoderException(HttpStatus.valueOf(response.status()), body);
		} catch (Exception e) {
			log.error("Error decoding response: {}", e.getMessage(), e);

			return e;
		}
	}

}
