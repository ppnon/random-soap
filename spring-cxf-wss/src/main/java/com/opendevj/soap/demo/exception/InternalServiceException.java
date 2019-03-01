package com.opendevj.soap.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class InternalServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String code;
	private final String message;

	public InternalServiceException(Throwable cause) {
		super(cause);
		this.message = cause.getMessage();
		this.code = "";
	}
	
	public InternalServiceException(String message) {
		super(message);
		this.message = message;
		this.code = "";
	}
	
	public InternalServiceException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
}
