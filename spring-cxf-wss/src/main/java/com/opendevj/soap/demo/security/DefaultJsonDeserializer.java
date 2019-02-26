package com.opendevj.soap.demo.security;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.google.json.JsonSanitizer;

@JsonComponent
public class DefaultJsonDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {

	@Override
	public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

		String value = parser.getValueAsString();

		if (StringUtils.isEmpty(value))
			return value;
		else {
			return JsonSanitizer.sanitize(value);
		}

	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {		
		return this;
	}
}
