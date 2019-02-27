package com.opendevj.soap.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.opendevj.soap.demo.service.AuthenticationService;
import com.opendevj.soap.demo.util.Messages;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private AuthenticationService service;
	
	@Autowired
	private Messages envMessages;
	
	@Value("${field.login.username}")
	private String usrRegex;
	
	@Value("${field.login.password}")
	private String pwdRegex;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) {
		//Not used
	}

	@Override
	protected UserDetails retrieveUser(String username, 
			UsernamePasswordAuthenticationToken authentication) {

		String credential = authentication.getCredentials().toString();
		Assert.hasText(username, envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_REQUIRED));
		Assert.hasText(credential, envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_REQUIRED));
		
		Assert.isTrue(username.matches(usrRegex), envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_INVALID));
		Assert.isTrue(credential.matches(pwdRegex), envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_INVALID));
		
		return service.doAuthenticate(username, credential);
	}
}
