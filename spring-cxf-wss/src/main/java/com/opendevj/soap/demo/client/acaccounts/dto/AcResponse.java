package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AcResponse<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T data;
	
	private AcResponse() {}
	
	public static class Builder<T extends Serializable> {
		
		private T data;
		
		public Builder<T> widthData(T data) {
			this.data =  data;
			return this;
		}
		
		public AcResponse<T> build() {
			AcResponse<T> instance = new AcResponse<>();
			instance.data = this.data;
			return instance;
		}
	}
}
