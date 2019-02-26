package com.opendevj.soap.demo.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.MessageInfo;
import org.apache.cxf.service.model.OperationInfo;
import org.apache.cxf.service.model.ServiceInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoapMessageInterceptor extends AbstractSoapInterceptor {

	private static final String PROCESS_TIME = "processTime";
	private static final String LOG_DETAIL = "service [{}] port [{}] address [{}] operation [{}]";
	
	public SoapMessageInterceptor(String p) {
		super(p);
	}

	@Override
	public void handleMessage(SoapMessage message) {
		
		Message inMessage = message.getExchange().getInMessage();
		MessageInfo messageInfo = inMessage.get(MessageInfo.class);
		OperationInfo operationInfo = messageInfo.getOperation();
		EndpointInfo endpointInfo = message.getExchange().get(Endpoint.class).getEndpointInfo();
		ServiceInfo serviceInfo = endpointInfo.getService();
		BindingInfo bidingInfo = endpointInfo.getBinding();
		
		Object startTime = inMessage.get(PROCESS_TIME);
		if (startTime == null) {
			log.debug("Start processing soap message: " + LOG_DETAIL, serviceInfo.getName().getLocalPart(), bidingInfo.getName().getLocalPart(), 
					endpointInfo.getAddress(), operationInfo.getInputName());
			
			inMessage.put(PROCESS_TIME, System.currentTimeMillis());
		} else {
			Long start = (Long) startTime;
			log.debug("End processing soap message: " + LOG_DETAIL + ": [{}ms]", serviceInfo.getName().getLocalPart(), bidingInfo.getName().getLocalPart(), 
					endpointInfo.getAddress(), operationInfo.getInputName(), System.currentTimeMillis() - start);
		}
	}

}
