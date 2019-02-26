package com.opendevj.soap.demo.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.opendevj.soap.demo.client.acaccounts.AcAccountClient;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcProfileRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest;
import com.opendevj.soap.demo.client.acaccounts.dto.AcRequest.Metadata;
import com.opendevj.soap.demo.endpoint.dto.Channel;
import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;
import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.mapper.AcAccountMapper;
import com.opendevj.soap.demo.mapper.ChannelConvert;
import com.opendevj.soap.demo.mapper.ResponseCreateUserAccountMapper;
import com.opendevj.soap.demo.model.User;
import com.opendevj.soap.demo.util.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private Messages envMessages;

	@Value("${countryId}")
	private String countryId;
	
	@Value("${entityId}")
	private String entityId;
	
	@Autowired
	AcAccountClient client;
	
	@Override
	public ResponseCreateUserAccount createAccount(RequestCreateUserAccount request) {
		
		AcRequest accountReq = new AcRequest();		
		Metadata meta = new Metadata();
		meta.setUser(getUser().getUsername());
		meta.setHostname(getHostname());
		
		AcAccountRequest account = mapAccount(request);
		account.setCountryId(countryId);
		account.setEntityId(entityId);
		
		log.info("Sub Entity: {}", account.getSubEntityId());
		
		accountReq.setMetadata(meta);
		accountReq.setData(account);
		
		client.createAccount(accountReq);
		// if ok then associate profile
		
		AcRequest profiletReq = new AcRequest();
		profiletReq.setMetadata(meta);
		
		profiletReq.setData(
				Stream.of(new AcProfileRequest(String.valueOf(request.getProfileId())))
					.collect(Collectors.toList()));
		
		client.associateProfile("1", profiletReq);
		// if ok then create response
		
		//account.setPassword("myclave");
		
		log.info("Account: {}", account);
		
		ResponseCreateUserAccount response = mapResponse(account);//TODO: remplace accont with new account
		
		
		log.info("Response: {}", response);
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
	
	private AcAccountRequest mapAccount(RequestCreateUserAccount request) {		
		Mapper mapper = DozerBeanMapperBuilder.create()
				.withMappingBuilder(new AcAccountMapper())
				.withCustomConverters(
						Stream.of(new ChannelConvert(Channel.class, String.class))
							.collect(Collectors.toList()))
				.build();
		return mapper.map(request, AcAccountRequest.class);
	}
	
	private ResponseCreateUserAccount mapResponse(AcAccountRequest account) {
		Mapper mapper = DozerBeanMapperBuilder.create()
				.withMappingBuilder(new ResponseCreateUserAccountMapper())
				.build();
		return mapper.map(account, ResponseCreateUserAccount.class);
	}
}
