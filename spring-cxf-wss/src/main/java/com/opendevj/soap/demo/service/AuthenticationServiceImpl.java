package com.opendevj.soap.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendevj.soap.demo.client.securitys2s.SecurityS2SClient;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialRequest;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialResponse;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SError;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SResponse;
import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.model.User;
import com.opendevj.soap.demo.util.Constants;
import com.opendevj.soap.demo.util.Messages;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private SecurityS2SClient client;
	
	@Autowired
	private Messages envMessages;
	
	@Value("${appCode}")
	private String appCode;
	
	@Override
	public UserDetails doAuthenticate(String username, String credential) {
		
		ResponseEntity<S2SResponse<S2SCredentialResponse>> response = client.login(
				new S2SCredentialRequest.Builder()
					.widthTxtLogin(username)
					.widthTxtContrasena(credential)
					.widthCodApli(appCode)
					.build());
		
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
				throw new InternalServiceException("S401",
						envMessages.get(Messages.KEY_LOGIN_CREDENTIALS_ERROR));
			}
			
		} else if (!CollectionUtils.isEmpty(response.getBody().getErrors())) {
				
			String message = getError(response.getBody().getErrors(), Constants.LOGIN_ACCOUNT_ERROR_CODE);
			
			if (StringUtils.hasText(message)) {
				throw new AuthenticationCredentialsNotFoundException(message);
			}
			
			message = getError(response.getBody().getErrors(), Constants.LOGIN_AUTH_ERROR_CODE);
			if (StringUtils.hasText(message)) {
				throw new AuthenticationCredentialsNotFoundException(
						envMessages.get(Messages.KEY_LOGIN_CREDENTIALS_INVALID));
			}
			
			throw new InternalServiceException("S401", 
					envMessages.get(Messages.KEY_LOGIN_CREDENTIALS_ERROR));
		} else {
			throw new InternalServiceException("S401", 
					envMessages.get(Messages.KEY_LOGIN_CREDENTIALS_ERROR));
		}
				
	}
	
	private String getError(List<S2SError> errors, List<String> codes) {
		List<String> messages = errors.stream()
				.filter(e -> codes.contains(e.getCode()))
				.map(S2SError::getUserMessage)
				.collect(Collectors.toList());
		return String.join(" ", messages);
	}

}
