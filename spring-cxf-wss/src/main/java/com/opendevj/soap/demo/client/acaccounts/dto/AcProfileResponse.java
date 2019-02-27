package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AcProfileResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "username must not be empty")
	private String username;
	@NotEmpty(message = "email must not be empty")
	private String email;
	@NotEmpty(message = "credential must not be empty")
	private String credential;
	
	private AcProfileResponse() {}
	
	public static class Builder {
		
		private String username;
		private String email;
		private String credential;
		
		public Builder widthUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder widthEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Builder widthCredential(String credential) {
			this.credential = credential;
			return this;
		}
		
		public AcProfileResponse build() {
			AcProfileResponse instance = new AcProfileResponse();
			instance.username = this.username;
			instance.email = this.email;
			instance.credential = this.credential;
			return instance;
		}
	}
}
