package com.opendevj.soap.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

	UserDetails doAuthenticate(String username, String credential);
}
