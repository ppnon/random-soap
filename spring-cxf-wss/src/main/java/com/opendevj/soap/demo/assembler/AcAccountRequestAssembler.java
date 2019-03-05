package com.opendevj.soap.demo.assembler;

import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;

public class AcAccountRequestAssembler {

	private AcAccountRequestAssembler() {}
	
	public static AcAccountRequest toDto(RequestCreateUserAccount in) {
		return new AcAccountRequest.Builder()
				.withOfficeId(in.getOfficeId())
				.withSubEntityId(in.getSubEntityId())
				.withUsername(in.getUsername())
				.withDocumentIDType(in.getDocumentIDTypeCode().toString())
				.withDocumentIDNumber(in.getDocumentIDNumber())
				.withFirstName(in.getFirstName())
				.withMiddleName(in.getMiddleName())
				.withSurname(in.getSurname())
				.withSecondSurname(in.getSecondSurname())
				.withEmail(in.getEmail())
				.withObservation(in.getObservation())
				.withChannelId(in.getChannel().id())
				.withSalesChannelId(in.getSalesChannelId())
				.withJobTitleId(in.getJobTitleId().toString())
				.withRoleId(in.getRoleId().toString())
				.build();
	}
}
