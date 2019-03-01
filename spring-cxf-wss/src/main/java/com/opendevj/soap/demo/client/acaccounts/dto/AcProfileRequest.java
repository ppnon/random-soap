package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcProfileRequest implements Serializable {


	private static final long serialVersionUID = 1L;
	private String id;
	
	private AcProfileRequest() {}
	
	public static class Builder {
		
		private String id;
		
		public Builder widthId(String id) {
			this.id = id;
			return this;
		}
		
		public AcProfileRequest build() {
			AcProfileRequest instance = new AcProfileRequest();
			instance.id = this.id;
			return instance;
		}
	}
}
