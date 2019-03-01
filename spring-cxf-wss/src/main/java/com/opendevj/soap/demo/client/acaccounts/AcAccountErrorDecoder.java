package com.opendevj.soap.demo.client.acaccounts;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
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
		
		log.warn("Response to decode: {}[{}] : {}", methodKey, response.status(), response.body());

		try {
			AcResponse<?> body = new ObjectMapper().readValue(
					response.body().asInputStream(), new TypeReference<AcResponse<?>>() {});
			
			log.debug("Reponse decoded: {}", body.toString());

			return new ClientDecoderException(HttpStatus.valueOf(response.status()), body);
		} catch (Exception e) {
			log.error("Error decoding response: {}", e.getMessage(), e);

			return e;
		}
	}

}
