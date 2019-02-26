package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AcResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient Object data;
}
