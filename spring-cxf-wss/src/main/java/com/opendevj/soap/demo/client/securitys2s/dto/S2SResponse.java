package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class S2SResponse<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private T data;
	private List<S2SError> errors;
	
	private S2SResponse() {}
	
	public static class Builder<T extends Serializable> {
		
		private String code;
		private String message;
		private T data;
		private List<S2SError> errors;
		
		public Builder<T> withCode(String code) {
			this.code = code;
			return this;
		}
		
		public Builder<T> withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public Builder<T> withData(T data) {
			this.data = data;
			return this;
		}
		
		public Builder<T> withErrors(List<S2SError> errors) {
			this.errors = errors;
			return this;
		}
		
		public S2SResponse<T> build() {
			S2SResponse<T> instance = new S2SResponse<>();
			instance.code = this.code;
			instance.message = this.message;
			instance.data = this.data;
			instance.errors = this.errors;
			return instance;
		}
	}
}
