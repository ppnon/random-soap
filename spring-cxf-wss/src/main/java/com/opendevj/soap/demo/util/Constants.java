package com.opendevj.soap.demo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static final String NAMESPACE_SERVICE = "http://www.opendevj.com/soap/demo/service";
	public static final String NAMESPACE_DOCUMENT = "http://www.opendevj.com/soap/demo/document";

	public static final String DEFAULT_ROLE = "ROLE_USER";
	public static final String TIMER = "TIMER";
	public static final String SOAP_LOG_DETAIL = "[service] {} [operation] {} [address] {} [wsdl] {}";
	
	public static final List<String> LOGIN_ACCOUNT_ERROR_CODE = Collections.unmodifiableList(Arrays.asList("1003","1004","1005","1013","1020"));
	public static final List<String> LOGIN_AUTH_ERROR_CODE = Collections.unmodifiableList(Arrays.asList("1001","1002"));
	
	private Constants() {}
	
}
