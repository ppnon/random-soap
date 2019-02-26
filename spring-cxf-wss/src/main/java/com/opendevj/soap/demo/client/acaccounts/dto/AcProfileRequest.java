package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AcProfileRequest implements Serializable {


	private static final long serialVersionUID = 1L;
	private String id;
	public AcProfileRequest(String id) {
		this.id = id;
	}
}
