package com.opendevj.soap.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.opendevj.soap.demo.assembler.AcAccountRequestAssembler;
import com.opendevj.soap.demo.client.acaccounts.AcAccountClient;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileResponse;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest.Metadata;
import com.opendevj.soap.demo.client.acaccounts.dto.AcResponse;
import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;
import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.model.User;
import com.opendevj.soap.demo.util.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("accountService")
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private Messages envMessages;

	@Value("${countryId}")
	private String countryId;
	
	@Value("${entityId}")
	private String entityId;
	
	@Autowired
	AcAccountClient client;
	
	@Autowired
	Validator validator;
	
	@Override
	public ResponseCreateUserAccount createAccount(RequestCreateUserAccount request) {
		
		AcAccountRequest newAccount = AcAccountRequestAssembler.toDto(request);
		newAccount.setCountryId(countryId);
		newAccount.setEntityId(entityId);

		ResponseEntity<AcResponse<AcAccountResponse>> account = client.createAccount(
				new AcRequest.Builder<AcAccountRequest>()
				.widthMetadata(new Metadata.Builder()
						.widthUser(getUser().getUsername())
						.widthHostname(getHostname())
						.build())
				.widthData(newAccount)
				.build());

		if (account.getBody() == null || account.getBody().getData() == null) {
			throw new InternalServiceException("S500", "Error con la respuesta");
		} else if (validate(account.getBody().getData())) {
			throw new InternalServiceException("S500", "Error con la data");
		}

		ResponseEntity<AcResponse<AcProfileResponse>> profile = client.associateProfile(
				account.getBody().getData().getId(), new AcRequest.Builder<ArrayList<AcProfileRequest>>()
				.widthMetadata(new Metadata.Builder()
						.widthUser(getUser().getUsername())
						.widthHostname(getHostname())
						.build())
				.widthData(new ArrayList<AcProfileRequest>(Arrays.asList(new AcProfileRequest.Builder()
						.widthId(request.getProfileId().toString()).build())))
				.build());

		if (profile.getBody() == null | profile.getBody().getData() == null) {
			throw new InternalServiceException("S500", "Erro con la respuesta");
		} else if (validate(profile.getBody().getData())) {
			throw new InternalServiceException("S500", "Error con la data");
		}
		
		ResponseCreateUserAccount response = new ResponseCreateUserAccount();
		response.setFirstName(account.getBody().getData().getFirstName());
		response.setMiddleName(account.getBody().getData().getMiddleName());
		response.setSurname(account.getBody().getData().getSurname());
		response.setSecondSurname(account.getBody().getData().getSecondSurname());
		response.setUsername(profile.getBody().getData().getUsername());
		response.setCredential(profile.getBody().getData().getCredential());
		
		return response;
	}

	private User getUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			return user;
		}
		throw new InternalServiceException("S500",envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_ERROR));
	}
	
	private String getHostname() {
		Object hostname = MDC.get("ipAddress");
		return hostname != null ? hostname.toString() : "0.0.0.0";
	}
	
	private <T> boolean validate(T type) {

		MapBindingResult errors = new MapBindingResult(
				new HashMap<String, String>(), type.getClass().getName());
		validator.validate(type, errors);

		if (errors.hasErrors()) {
			for (ObjectError e : errors.getAllErrors()) {
				log.info("validation error width {} : {}", e.getObjectName(), e.getDefaultMessage());
			}
		}
		return errors.hasErrors();
	}
}
