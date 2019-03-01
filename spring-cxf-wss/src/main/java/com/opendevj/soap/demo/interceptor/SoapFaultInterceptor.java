package com.opendevj.soap.demo.interceptor;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.UnmarshalException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.slf4j.MDC;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.opendevj.soap.demo.exception.InternalServiceException;
import com.opendevj.soap.demo.util.Constants;
import com.opendevj.soap.demo.util.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SoapFaultInterceptor extends AbstractSoapInterceptor {

	public SoapFaultInterceptor() {
		super(Phase.MARSHAL);
	}
	
	public SoapFaultInterceptor(String p) {
		super(p);
	}

	@Override
	public void handleMessage(SoapMessage message) {

		Fault fault = (Fault) message.getContent(Exception.class);
		fault.setDetail(null);
		
		if (fault.getCause().getClass().equals(AuthenticationCredentialsNotFoundException.class)
				|| fault.getCause().getClass().equals(WSSecurityException.class)) {
			log.warn("Failed authentication attempt: [message]{}", fault.getMessage());
			log.debug("{}: [message]{}", fault.getClass().getName(), fault.getMessage(), fault.getCause());

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C401"));
		} else if (fault.getCause().getClass().equals(UnmarshalException.class)) {
			log.warn("UnmarshalException: [message]{}", fault.getMessage());
			log.debug("UnmarshalException: [message]{}", fault.getMessage(), fault.getCause());

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C400"));
			if (fault.getMessage() != null) {
				String faultMessage = fault.getMessage()
						.substring(fault.getMessage().indexOf(':') + 1);

				fault.setMessage(faultMessage);
			}
		} else if (fault.getCause().getClass().equals(ConstraintViolationException.class) || 
				fault.getCause().getClass().equals(IllegalArgumentException.class)) {
			log.warn("IllegalArgumentException: [message]{}", fault.getMessage());
			log.debug("IllegalArgumentException: [message]{}", fault.getMessage(), fault.getCause());
			
			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "C400ó"));
		} else if (fault.getCause().getClass().equals(InternalServiceException.class)) {
			log.warn("InternalServiceException: [message]{}", fault.getMessage());
			
			InternalServiceException e = (InternalServiceException) fault.getCause();
			log.debug("InternalServiceException: [code]{} [message]{}", 
					e.getCode(), e.getMessage(), fault.getCause());

			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, 
					StringUtils.hasText(e.getCode()) ? e.getCode() : "S500"));
			fault.setMessage(StringUtils.hasText(e.getMessage()) ? e.getMessage() 
					: Messages.KEY_SERVICE_ERROR);
		} else {
			log.error("Unknow Exception: [message]{}", fault.getMessage(), fault.getCause());
			
			fault.setFaultCode(new QName(Constants.NAMESPACE_SERVICE, "S500"));
			fault.setMessage(Messages.MSG_SERVICE_ERROR);
		}
		
		// ending request log
		Message inMessage = message.getExchange().getInMessage();
		Long start = (Long) inMessage.get(Constants.TIMER);
		log.info("End processing soap message: " + Constants.SOAP_LOG_DETAIL + ": [{}ms]", 
				message.get("javax.xml.ws.wsdl.service"),
				message.get("javax.xml.ws.wsdl.operation"), 
				message.get("org.apache.cxf.request.uri"), 
				message.get("javax.xml.ws.wsdl.description"), 
				System.currentTimeMillis() - start);
		
		MDC.clear();
	}
}
