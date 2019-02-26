package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AcAccountRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
}
