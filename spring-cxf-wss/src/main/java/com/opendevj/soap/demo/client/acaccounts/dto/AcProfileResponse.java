package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AcProfileResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String credential;
}
