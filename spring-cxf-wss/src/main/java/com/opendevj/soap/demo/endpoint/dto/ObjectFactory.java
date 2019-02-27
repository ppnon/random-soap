package com.opendevj.soap.demo.endpoint.dto;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema
     * 
     */
    public ObjectFactory() {
    	// can be used to create new instances of schema
    }

    /**
     * Create an instance of {@link ResponseCreateUserAccount }
     * 
     */
    public ResponseCreateUserAccount createResponse() {
        return new ResponseCreateUserAccount();
    }

    /**
     * Create an instance of {@link RequestCreateUserAccount }
     * 
     */
    public RequestCreateUserAccount createRequest() {
        return new RequestCreateUserAccount();
    }

}
