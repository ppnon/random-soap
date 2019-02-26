package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AcAccountResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String middleName;
	private String surname;
	private String secondSurnama;
	private String username;
}
