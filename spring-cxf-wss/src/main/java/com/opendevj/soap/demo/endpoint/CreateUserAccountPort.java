package com.opendevj.soap.demo.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.Valid;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;

import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;
import com.opendevj.soap.demo.endpoint.dto.ResponseCreateUserAccount;
import com.opendevj.soap.demo.util.Constants;

@WebService( targetNamespace = "http://www.opendevj.com/soap/demo/service", name="userAccountPort")
@SchemaValidation(type = SchemaValidationType.REQUEST)
public interface CreateUserAccountPort {
	
	@WebResult(name = "ReponseCreateUserAccount", 
				targetNamespace = Constants.NAMESPACE_DOCUMENT)
	@WebMethod(operationName = "CreateUserAccount")
	@Valid
	public ResponseCreateUserAccount createUserAccount(@Valid  @WebParam(name = "RequestCreateUserAccount", 
		targetNamespace = Constants.NAMESPACE_DOCUMENT) RequestCreateUserAccount request);
	
}
