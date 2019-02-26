package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class S2SCredentialResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String accountID;
	private String entityID;
	private String subEntityID;
	private String profileCode;
}