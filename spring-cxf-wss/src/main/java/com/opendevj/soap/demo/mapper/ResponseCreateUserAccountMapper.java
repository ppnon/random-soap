package com.opendevj.soap.demo.mapper;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;

public class ResponseCreateUserAccountMapper extends BeanMappingBuilder {

	@Override
	protected void configure() {
		mapping(type (AcAccountRequest.class).mapEmptyString(true), 
				type(ResponseCreateUserAccountMapper.class));
	}

}
