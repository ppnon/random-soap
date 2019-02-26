package com.opendevj.soap.demo.mapper;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.opendevj.soap.demo.client.acaccounts.dto.AcAccountRequest;
import com.opendevj.soap.demo.endpoint.dto.RequestCreateUserAccount;

public class AcAccountMapper extends BeanMappingBuilder {

	@Override
	protected void configure() {
		mapping(type (RequestCreateUserAccount.class).mapEmptyString(true), 
				type(AcAccountRequest.class))
		.fields(
				field("documentIDTypeCode"), 
				field("documentIDType"),
					FieldsMappingOptions.oneWay())
		.fields(
				field("channel"), 
				field("channelId"),
					FieldsMappingOptions.oneWay(),
					FieldsMappingOptions.customConverter(ChannelConvert.class));
	}
}
