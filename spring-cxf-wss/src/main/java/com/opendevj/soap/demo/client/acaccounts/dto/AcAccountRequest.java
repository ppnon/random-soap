package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcAccountRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Setter
	private String countryId;
	@Setter
	private String entityId;
	private String officeId;
	private String subEntityId;
	private String username;
	private String documentIDType;
	private String documentIDNumber;
	private String firstName;
	private String middleName;
	private String surname;
	private String secondSurname;
	private String email;
	private String phoneNumber1;
	private String phoneNumber2;
	private String observation;
	private String channelId;
	private String salesChannelId;
	private String jobTitleId;
	private String roleId;
	
	private AcAccountRequest() {}
	
	public static class Builder {
		
		private String countryId;
		private String entityId;
		private String officeId;
		private String subEntityId;
		private String username;
		private String documentIDType;
		private String documentIDNumber;
		private String firstName;
		private String middleName;
		private String surname;
		private String secondSurname;
		private String email;
		private String phoneNumber1;
		private String phoneNumber2;
		private String observation;
		private String channelId;
		private String salesChannelId;
		private String jobTitleId;
		private String roleId;
		
		public Builder withCountryId(String countryId) {
			this.countryId = countryId;
			return this;
		}
		
		public Builder withEntityId(String entityId) {
			this.entityId = entityId;
			return this;
		}
		
		public Builder withOfficeId(String officeId) {
			this.officeId = officeId;
			return this;
		}
		
		public Builder withSubEntityId(String subEntityId) {
			this.subEntityId = subEntityId;
			return this;
		}
		
		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder withDocumentIDType(String documentIDType) {
			this.documentIDType = documentIDType;
			return this;
		}
		
		public Builder withDocumentIDNumber(String documentIDNumber) {
			this.documentIDNumber = documentIDNumber;
			return this;
		}
		
		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder withMiddleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		
		public Builder withSurname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public Builder withSecondSurname(String secondSurname) {
			this.secondSurname = secondSurname;
			return this;
		}
		
		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Builder withPhoneNumber1(String phoneNumber1) {
			this.phoneNumber1 = phoneNumber1;
			return this;
		}
		
		public Builder withPhoneNumber2(String phoneNumber2) {
			this.phoneNumber2 = phoneNumber2;
			return this;
		}
		
		public Builder withObservation(String observation) {
			this.observation = observation;
			return this;
		}
		
		public Builder withChannelId(String channelId) {
			this.channelId = channelId;
			return this;
		}
		
		public Builder withSalesChannelId(String salesChannelId) {
			this.salesChannelId = salesChannelId;
			return this;
		}
		
		public Builder withJobTitleId(String jobTitleId) {
			this.jobTitleId = jobTitleId;
			return this;
		}
		
		public Builder withRoleId(String roleId) {
			this.roleId = roleId;
			return this;
		}
		
		public AcAccountRequest build() {
			AcAccountRequest instance = new AcAccountRequest();
			instance.countryId = countryId;
			instance.entityId = entityId;
			instance.officeId = officeId;
			instance.subEntityId = subEntityId;
			instance.username = username;
			instance.documentIDType = documentIDType;
			instance.documentIDNumber = documentIDNumber;
			instance.firstName = firstName;
			instance.middleName = middleName;
			instance.surname = surname;
			instance.secondSurname = secondSurname;
			instance.email = email;
			instance.phoneNumber1 = phoneNumber1;
			instance.phoneNumber2 = phoneNumber2;
			instance.observation = observation;
			instance.channelId = channelId;
			instance.salesChannelId = salesChannelId;
			instance.jobTitleId = jobTitleId;
			instance.roleId = roleId;
			return instance;
		}
		
	}
}
