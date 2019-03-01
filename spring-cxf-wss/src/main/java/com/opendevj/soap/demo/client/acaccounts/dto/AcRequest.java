package com.opendevj.soap.demo.client.acaccounts.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcRequest<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Metadata metadata;
	private T data;
	
	private AcRequest() {}

	public static class Builder<T extends Serializable> {
		
		private Metadata metadata;
		private T data;
		
		public Builder<T> widthMetadata(Metadata metadada) {
			this.metadata = metadada;
			return this;
		}
		
		public Builder<T> widthData(T data) {
			this.data = data;
			return this;
		}
		
		public AcRequest<T> build() {
			AcRequest<T> instance = new AcRequest<>();
			instance.metadata = this.metadata;
			instance.data = this.data;
			return instance;
		}
	}
	
	@Getter
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Metadata implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private String user;
		private String hostname;
		
		private Metadata() {}
		
		public static class Builder {
			
			private String user;
			private String hostname;
			
			public Builder widthUser(String user) {
				this.user = user;
				return this;
			}
			
			public Builder widthHostname(String hostname) {
				this.hostname = hostname;
				return this;
			}
			
			public Metadata build() {
				Metadata instance = new Metadata();
				instance.user = this.user;
				instance.hostname = this.hostname;
				return instance;
			}
		}
	}
}
