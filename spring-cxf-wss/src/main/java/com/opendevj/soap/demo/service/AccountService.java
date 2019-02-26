package com.opendevj.soap.demo.service;

import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;

public interface AccountService {

	public ResponseCreateUserAccount createAccount(RequestCreateUserAccount request);
	
}
