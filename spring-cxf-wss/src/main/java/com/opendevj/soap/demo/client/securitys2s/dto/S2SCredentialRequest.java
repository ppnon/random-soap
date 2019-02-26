package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class S2SCredentialRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String txtLogin;
	private String txtContrasena;
	private String codApli;
	
	public S2SCredentialRequest(String login, String pass, String app) {
		this.txtLogin = login;
		this.txtContrasena = pass;
		this.codApli = app;
	}
}
