package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true, includeFieldNames=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcError implements Serializable {

	private static final long serialVersionUID = 1L;
	private String traceId;
	private String code;
	private String userMessage;
	
	private AcError() {}
	
	public static class Builder {
		
		private String traceId;
		private String code;
		private String userMessage;
		
		public Builder widthTraceId(String traceId) {
			this.traceId = traceId;
			return this;
		}
		
		public Builder widthUserMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}
		
		public Builder widthCode(String code) {
			this.code = code;
			return this;
		}
		
		public AcError build() {
			AcError instance = new AcError();
			instance.traceId = traceId;
			instance.userMessage = this.userMessage;
			instance.code = this.code;
			return instance;
		}
	}
}
