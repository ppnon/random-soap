package com.opendevj.soap.demo.client.securitys2s.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class S2SResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private transient Object data;
	private List<S2SResponseError> errors;
	
	@Data
	@ToString(callSuper=true, includeFieldNames=true)
	public static class S2SResponseError implements Serializable {

		private static final long serialVersionUID = 1L;
		private String userMessage;
		private String code;
	}
}
