package com.opendevj.soap.demo.endpoint;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;
import com.opendevj.soap.demo.service.UserAccountService;

import lombok.extern.slf4j.Slf4j;

@WebService(serviceName="userAccountPortService", 
			portName = "userAccountPort", 
			targetNamespace = "http://www.opendevj.com/soap/demo/service", 
			endpointInterface = "com.opendevj.soap.demo.endpoint.CreateUserAccountPort")
@Slf4j
@Component
public class CreateUserAccountPortImpl implements CreateUserAccountPort {
	
	@Autowired
	@Qualifier("accountService")
	private UserAccountService service;
	
	@Override
	public ResponseCreateUserAccount createUserAccount(RequestCreateUserAccount request) {
		Assert.notNull(request, "The element 'doc:RequestCreateUserAccount' is missing");
		log.info("Request: {}", request.toString());
		return service.createAccount(request);
	}

}
