package com.opendevj.soap.demo.endpoint.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "channel")
@XmlEnum
public enum Channel {

    PUSH("001"),
    PULL("002");
	
	private String id;
	
	Channel(String id) {
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	
	public String value() {
        return name();
    }

    public static Channel fromValue(String v) {
        return valueOf(v);
    }

}
