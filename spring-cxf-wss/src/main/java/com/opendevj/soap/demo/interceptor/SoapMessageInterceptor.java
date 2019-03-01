package com.opendevj.soap.demo.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.MDC;

import com.opendevj.soap.demo.util.Constants;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoapMessageInterceptor extends AbstractSoapInterceptor {

	private Tracer tracer;
	private String phase;
	
	public SoapMessageInterceptor(String p, Tracer tracer) {
		super(p);
		this.tracer = tracer;
		this.phase = p;
	}

	@Override
	public void handleMessage(SoapMessage message) {

		Message inMessage = message.getExchange().getInMessage();
		Object startTime = inMessage.get(Constants.TIMER);
		
		if (Phase.PRE_LOGICAL.equals(phase)) {
			
			MDC.put("traceId", getTraceId());
			MDC.put("ipAddress", getRemoteAddress(message));
			
			log.info("Start processing soap message: " + Constants.SOAP_LOG_DETAIL, 
					message.get("javax.xml.ws.wsdl.service"),
					message.get("javax.xml.ws.wsdl.operation"), 
					message.get("org.apache.cxf.request.uri"), 
					message.get("javax.xml.ws.wsdl.description"));
			
			inMessage.put(Constants.TIMER, System.currentTimeMillis());
		} else {
			Long start = (Long) startTime;
			log.info("End processing soap message: " + Constants.SOAP_LOG_DETAIL + ": [{}ms]", 
					message.get("javax.xml.ws.wsdl.service"),
					message.get("javax.xml.ws.wsdl.operation"), 
					message.get("org.apache.cxf.request.uri"), 
					message.get("javax.xml.ws.wsdl.description"), 
					System.currentTimeMillis() - start);
			
			MDC.clear();
		}
	}
	
	private String getTraceId() {
		return (tracer != null && tracer.currentSpan() != null) 
				? Long.toHexString(tracer.currentSpan().context().traceId()) 
						: UUID.randomUUID().toString();
	}
	
	private String getRemoteAddress(SoapMessage message) {
		HttpServletRequest request = (HttpServletRequest) message.get("HTTP.REQUEST");
		return request.getRemoteAddr();
	}

}
