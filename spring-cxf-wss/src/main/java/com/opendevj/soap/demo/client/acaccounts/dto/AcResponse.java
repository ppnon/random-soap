package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcResponse<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T data;
	private List<AcError> errors;
	
	private AcResponse() {}
	
	public static class Builder<T extends Serializable> {
		
		private T data;
		private List<AcError> errors;
		
		public Builder<T> withData(T data) {
			this.data =  data;
			return this;
		}
		
		public Builder<T> withErrors(List<AcError> errors) {
			this.errors = errors;
			return this;
		}
		
		public AcResponse<T> build() {
			AcResponse<T> instance = new AcResponse<>();
			instance.data = this.data;
			instance.errors = this.errors;
			return instance;
		}
	}
}
