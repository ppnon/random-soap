package com.opendevj.soap.demo.mapper;

import com.github.dozermapper.core.DozerConverter;
import com.opendevj.soap.demo.endpoint.dto.Channel;

public class ChannelConvert extends DozerConverter<Channel, String> {

	public ChannelConvert(Class<Channel> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertTo(Channel source, String destination) {
		return source.id();
	}

	@Override
	public Channel convertFrom(String source, Channel destination) {
		return Channel.valueOf(source);
	}

}
