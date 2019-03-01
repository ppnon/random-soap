package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcAccountResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message="id must not be empty")
	private String id;
	@NotEmpty(message="firtName must not be empty")
	private String firstName;
	private String middleName;
	@NotEmpty(message="surname must not be empty")
	private String surname;
	private String secondSurname;
	
	private AcAccountResponse() {}
	
	public static class Builder {
		
		private String id;
		private String firstName;
		private String middleName;
		private String surname;
		private String secondSurname;
		
		public Builder widthId(String id) {
			this.id = id;
			return this;
		}
		
		public Builder widthFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder widthMiddleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		
		public Builder widthSurname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public Builder widthSecondSurname(String secondSurname) {
			this.secondSurname = secondSurname;
			return this;
		}
		
		public AcAccountResponse build() {
			AcAccountResponse instance = new AcAccountResponse();
			instance.id = this.id;
			instance.firstName = this.firstName;
			instance.middleName = this.middleName;
			instance.surname = this.surname;
			instance.secondSurname = this.secondSurname;
			return instance;
		}
	}
}
