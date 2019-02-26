package com.opendevj.soap.demo.interceptor;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.UnmarshalException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoapFaultInterceptor extends AbstractSoapInterceptor {

	public SoapFaultInterceptor() {
		super(Phase.MARSHAL);
	}
	
	public SoapFaultInterceptor(String p) {
		super(p);
	}

	@Override
	public void handleMessage(SoapMessage message) {
		//TODO: improve logger
		Fault fault = (Fault) message.getContent(Exception.class);
		fault.setDetail(null);
		if (fault.getCause().getClass().equals(AuthenticationCredentialsNotFoundException.class)
				|| fault.getCause().getClass().equals(WSSecurityException.class)) {
			log.debug("Failed authentication attempt: {}", message);

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C401"));
		} else if (fault.getCause().getClass().equals(UnmarshalException.class)) {
			log.debug("UnmarshalException: {}", fault.getMessage());

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C400"));
			if (fault.getMessage() != null) {
				String faultMessage = fault.getMessage()
						.substring(fault.getMessage().indexOf(':') + 1);

				fault.setMessage(faultMessage);
			}
		} else if (fault.getCause().getClass().equals(ConstraintViolationException.class) || 
				fault.getCause().getClass().equals(IllegalArgumentException.class)) {
			log.debug("IllegalArgumentException: {}", fault.getMessage());
			
			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C400"));
		} else if (fault.getCause().getClass().equals(InternalServiceException.class)) {
			log.debug("InternalServiceException: {}", fault.getMessage());
			InternalServiceException e = (InternalServiceException) fault.getCause();

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, e.getCode()));
		} else {
			log.debug("No expected exception [{}]: {}", fault.getCause().getClass().toString(), fault.getMessage());

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "S500"));
			fault.setMessage("An unexpected error has occurred, please try again or contact us");
		}
	}
}
