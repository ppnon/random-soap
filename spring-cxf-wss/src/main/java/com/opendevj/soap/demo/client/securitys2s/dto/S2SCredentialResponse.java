package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class S2SCredentialResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String accountID;
	private String entityID;
	private String subEntityID;
	private String profileCode;
	
	private S2SCredentialResponse() {}
	
	public static class Builder {
	
		private String accountID;
		private String entityID;
		private String subEntityID;
		private String profileCode;
		
		public Builder widthAccountID(String accountID) {
			this.accountID = accountID;
			return this;
		}
		
		public Builder widthEntityID(String entityID) {
			this.entityID = entityID;
			return this;
		}
		
		public Builder widthSubEntityID(String subEntityID) {
			this.subEntityID = subEntityID;
			return this;
		}
		
		public Builder widthProfileCode(String profileCode) {
			this.profileCode = profileCode;
			return this;
		}
		
		public S2SCredentialResponse build() {
			S2SCredentialResponse instance = new S2SCredentialResponse();
			instance.accountID = this.accountID;
			instance.entityID = this.entityID;
			instance.subEntityID = this.subEntityID;
			instance.profileCode = this.profileCode;
			return instance;
		}
	}
}