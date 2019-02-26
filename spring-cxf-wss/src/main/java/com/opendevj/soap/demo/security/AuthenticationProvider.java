package com.opendevj.soap.demo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.securitys2s.SecurityS2SClient;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialResponse;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialRequest;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse.S2SResponseError;
import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.model.User;
import com.opendevj.soap.demo.util.Constants;
import com.opendevj.soap.demo.util.Messages;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private SecurityS2SClient client;
	
	@Autowired
	private Messages envMessages;
	
	@Value("${appCode}")
	private String appCode;
	
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

		return authenticate(username, authentication.getCredentials().toString());
	}

	private UserDetails authenticate(String username, String password) {
		
		Assert.hasText(username, envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_REQUIRED));
		Assert.hasText(password, envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_REQUIRED));
		
		Assert.isTrue(username.matches(usrRegex), envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_INVALID));
		Assert.isTrue(password.matches(pwdRegex), envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_INVALID));
		
		ResponseEntity<S2SResponse> response = client.login(new S2SCredentialRequest(username, password, appCode));
		
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			
			try {
				
				S2SCredentialResponse account = new ObjectMapper().convertValue(
						response.getBody().getData(), S2SCredentialResponse.class);
				User user = new User(username, null);
				user.setAccountID(account.getAccountID());
				user.setEntityID(account.getEntityID());
				user.setSubentity(account.getSubEntityID());
				user.setProfileCode(account.getProfileCode());
				List<SimpleGrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(Constants.DEFAULT_ROLE));
				user.setAuthorities(authorities);
				
				return user;
			} catch (Exception e) {
				throw new InternalServiceException(envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_ERROR));
			}
			
		} else { 
			if (!CollectionUtils.isEmpty(response.getBody().getErrors())) {
				
				String message = getError(response.getBody().getErrors(), Constants.LOGIN_ACCOUNT_ERROR_CODE);
				if (StringUtils.hasText(message)) {
					throw new AuthenticationCredentialsNotFoundException(message);
				}
				
				message = getError(response.getBody().getErrors(), Constants.LOGIN_AUTH_ERROR_CODE);
				if (StringUtils.hasText(message)) {
					throw new AuthenticationCredentialsNotFoundException(envMessages.get("message.login.credentials.invalid"));
				}
			}
			
			throw new InternalServiceException("S401", envMessages.get(Messages.MSG_LOGIN_CREDENTIALS_ERROR));
				
		}
	}
	
	private String getError(List<S2SResponseError> errors, List<String> codes) {
		List<String> messages = errors.stream()
				.filter(e -> codes.contains(e.getCode()))
				.map(S2SResponseError::getUserMessage)
				.collect(Collectors.toList());
		return String.join(" ", messages);
	}
}
