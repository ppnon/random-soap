package com.opendevj.soap.demo.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	
	public static final String MSG_LOGIN_CREDENTIALS_REQUIRED = "message.login.credentials.required";
	public static final String MSG_LOGIN_CREDENTIALS_INVALID = "message.login.credentials.invalid";
	public static final String MSG_LOGIN_CREDENTIALS_ERROR = "message.login.credentials.error";
	
	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
	}

	public String get(String code) {
		return accessor.getMessage(code);
	}
	
	public String get(String code, String...strings) {
		return accessor.getMessage(code, strings);
	}
}
