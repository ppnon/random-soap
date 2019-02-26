package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AcRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Metadata metadata;
	private transient Object data;
	
	@Data
	public static class Metadata implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private String user;
		private String hostname;
	}
}
