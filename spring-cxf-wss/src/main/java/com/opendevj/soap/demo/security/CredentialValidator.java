package com.opendevj.soap.demo.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.handler.RequestData;
import org.apache.wss4j.dom.message.token.UsernameToken;
import org.apache.wss4j.dom.validate.Credential;
import org.apache.wss4j.dom.validate.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CredentialValidator implements Validator {

	private AuthenticationManager providerManager;
	
	@Autowired
	public CredentialValidator(AuthenticationManager providerManager) {
		this.providerManager = providerManager;
	}
	
	@Override
	public Credential validate(Credential credential, RequestData data) throws WSSecurityException {
		
		UsernameToken usernameToken = credential.getUsernametoken();
		
		if (StringUtils.hasText(usernameToken.getName())) {
			
			Authentication authentication = providerManager.authenticate(
					new UsernamePasswordAuthenticationToken(usernameToken.getName(), usernameToken.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} 
		return credential;
	}

	@SuppressWarnings("unused")
	private HttpServletRequest getHttpRequest(RequestData data) {
		SoapMessage message = (SoapMessage) data.getMsgContext();
		return (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
	}
}
