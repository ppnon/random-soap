package com.opendevj.soap.demo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.json.JsonSanitizer;
import com.opendevj.soap.demo.client.securitys2s.dto.S2SCredentialResponse;

public class S2SAccountDeserializer extends StdDeserializer<S2SCredentialResponse> {

	private static final long serialVersionUID = 1L;
	
	public S2SAccountDeserializer() {
		this(null);
	}
	
	public S2SAccountDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public S2SCredentialResponse deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		
		JsonNode node = p.getCodec().readTree(p);
		
		S2SCredentialResponse account = new S2SCredentialResponse();
		
		account.setAccountID(node.get("accountID").textValue());
		account.setEntityID(node.get("entityID").textValue());
		account.setSubEntityID(JsonSanitizer.sanitize(node.get("subEntityID").textValue()));
		account.setProfileCode("987");
		return account;
	}

}
