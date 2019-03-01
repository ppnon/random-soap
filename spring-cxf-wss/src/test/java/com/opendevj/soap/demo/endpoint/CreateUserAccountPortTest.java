package com.opendevj.soap.demo.endpoint;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;
import com.opendevj.soap.demo.service.UserAccountService;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserAccountPortTest {

	@Mock
	private UserAccountService service;
	
	private CreateUserAccountPortImpl endpoint;
	
	@Before
	public void setUp() {
		endpoint = new CreateUserAccountPortImpl(service);
	}
	
	@Test
	public void testCreateUserAccountOk() {
		
		ResponseCreateUserAccount response = new ResponseCreateUserAccount();
		response.setFirstName("Name");
		response.setMiddleName("Middle Name");
		response.setSurname("Surname");
		response.setSecondSurname("Second Surname");
		response.setUsername("NMS1");
		
		Mockito.when(service.createAccount(Mockito.any(RequestCreateUserAccount.class)))
			.thenReturn(response);
		assertEquals(response, endpoint.createUserAccount(new RequestCreateUserAccount()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateUserAccountNull() {
		
		ResponseCreateUserAccount response = new ResponseCreateUserAccount();
		response.setFirstName("Name");
		response.setMiddleName("Middle Name");
		response.setSurname("Surname");
		response.setSecondSurname("Second Surname");
		response.setUsername("NMS1");
		
		assertEquals(response, endpoint.createUserAccount(null));
	}
}
