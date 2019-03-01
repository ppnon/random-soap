package com.opendevj.soap.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClientDecoderException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final HttpStatus status;
	private final transient Object body;
		
	public ClientDecoderException(HttpStatus status, Object body) {
		this.status = status;
		this.body = body;
	}
	
}
