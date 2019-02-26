package com.opendevj.soap.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.validation.BeanValidationInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.opendevj.soap.demo.endpoint.CreateUserAccountPortImpl;
import com.opendevj.soap.demo.interceptor.SoapFaultInterceptor;
import com.opendevj.soap.demo.interceptor.SoapMessageInterceptor;
import com.opendevj.soap.demo.security.CredentialValidator;

@Configuration
public class WebServiceConfig {

    private CreateUserAccountPortImpl createUserAccountPort;
    private CredentialValidator validator;
    private Environment env;
	private SpringBus bus;
	
    @Autowired
    public WebServiceConfig(Environment env, SpringBus bus,
    		CredentialValidator validator,
    		CreateUserAccountPortImpl createUserAccountPort) {
    	
    	this.createUserAccountPort = createUserAccountPort;
    	this.validator = validator;
    	this.env= env;
    	this.bus = bus;
    }
    
    public SpringBus bus() {
    	bus.setFeatures(
    			new ArrayList<>(Arrays.asList(loggingFeature())));
    	return bus;
    }
    
    @Bean
    public LoggingFeature loggingFeature() {
    	LoggingFeature loggingFeature = new LoggingFeature();
    	loggingFeature.setPrettyLogging(true);
    	return loggingFeature;
    }
	
    @Bean
    public Endpoint createUserAcoountEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus(), createUserAccountPort);
        endpoint.setPublishedEndpointUrl(env.getProperty("endpoint.createUserAccountPort.published.url"));
        endpoint.publish(env.getProperty("endpoint.createUserAccountPort.publish"));
        
        Map<String, Object> props = new HashMap<>();
        props.put("ws-security.ut.validator", validator);
        endpoint.setProperties(props);
        
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put(WSHandlerConstants.TIMESTAMP_STRICT, "false");
        inProps.put(WSHandlerConstants.TTL_TIMESTAMP, "300");
        inProps.put(WSHandlerConstants.TTL_FUTURE_TIMESTAMP, "300");
        inProps.put(WSHandlerConstants.TTL_USERNAMETOKEN, "300");
        inProps.put(WSHandlerConstants.TTL_FUTURE_USERNAMETOKEN, "300");
        endpoint.getInInterceptors()
        	.add(new WSS4JInInterceptor(inProps));
        endpoint.getInInterceptors()
        	.add(new BeanValidationInInterceptor());
        endpoint.getInInterceptors()
        	.add(new SoapMessageInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors()
        	.add(new SoapMessageInterceptor(Phase.PRE_LOGICAL_ENDING));
        endpoint.getOutFaultInterceptors()
        	.add(new SoapFaultInterceptor(Phase.MARSHAL));

        return endpoint;
    }
}