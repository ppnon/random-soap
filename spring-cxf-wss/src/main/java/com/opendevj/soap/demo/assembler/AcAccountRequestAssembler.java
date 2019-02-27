package com.opendevj.soap.demo.assembler;

import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;

public class AcAccountRequestAssembler {

	private AcAccountRequestAssembler() {}
	
	public static AcAccountRequest toDto(RequestCreateUserAccount in) {
		return new AcAccountRequest.Builder()
				.widthOfficeId(in.getOfficeId())
				.widthSubEntityId(in.getSubEntityId())
				.widthUsername(in.getUsername())
				.widthDocumentIDType(in.getDocumentIDTypeCode().toString())
				.widthDocumentIDNumber(in.getDocumentIDNumber())
				.widthFirstName(in.getFirstName())
				.widthMiddleName(in.getMiddleName())
				.widthSurname(in.getSurname())
				.widthSecondSurname(in.getSecondSurname())
				.widthEmail(in.getEmail())
				.widthObservation(in.getObservation())
				.widthChannelId(in.getChannel().id())
				.widthSalesChannelId(in.getSalesChannelId())
				.widthJobTitleId(in.getJobTitleId().toString())
				.widthRoleId(in.getRoleId().toString())
				.build();
	}
}
