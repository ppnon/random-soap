package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class S2SError implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userMessage;
	private String code;
	
	private S2SError() {}
	
	public static class Builder {
		
		private String userMessage;
		private String code;
		
		public Builder widthUserMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}
		
		public Builder widthCode(String code) {
			this.code = code;
			return this;
		}
		
		public S2SError build() {
			S2SError instance = new S2SError();
			instance.userMessage = this.userMessage;
			instance.code = this.code;
			return instance;
		}
	}
}
