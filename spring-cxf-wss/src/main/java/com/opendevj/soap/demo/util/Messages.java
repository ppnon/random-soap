package com.opendevj.soap.demo.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	
	public static final String KEY_LOGIN_CREDENTIALS_REQUIRED = "message.login.credentials.required";
	public static final String KEY_LOGIN_CREDENTIALS_INVALID = "message.login.credentials.invalid";
	public static final String KEY_LOGIN_CREDENTIALS_ERROR = "message.login.credentials.error";
	public static final String KEY_SERVICE_ERROR = "message.service.error";
	
	public static final String MSG_SERVICE_ERROR = "Problema al momento de procesar su solicitud";
	
	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;
	
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, new Locale("es", "us"));
	}

	public String get(String code) {
		return accessor.getMessage(code);
	}
	
	public String get(String code, String...strings) {
		return accessor.getMessage(code, strings);
	}
}
