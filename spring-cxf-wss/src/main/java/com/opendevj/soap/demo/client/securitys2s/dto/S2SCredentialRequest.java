package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class S2SCredentialRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String txtLogin;
	private String txtContrasena;
	private String codApli;
	
	private S2SCredentialRequest() {}
	
	public static class Builder {
		private String txtlogin;
		private String txtContrasena;
		private String codApli;
		
		public Builder widthTxtLogin(String login) {
			this.txtlogin = login;
			return this;
		}
		
		public Builder widthTxtContrasena(String contrasena) {
			this.txtContrasena = contrasena;
			return this;
		}
		
		public Builder widthCodApli(String app) {
			this.codApli = app;
			return this;
		}
		
		public S2SCredentialRequest build() {
			S2SCredentialRequest instance = new S2SCredentialRequest();
			instance.txtLogin = this.txtlogin;
			instance.txtContrasena = this.txtContrasena;
			instance.codApli = this.codApli;
			return instance;
		}
	}
}
