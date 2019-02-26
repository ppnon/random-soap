package com.opendevj.soap.demo.endpoint.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for channel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="channel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PUSH"/>
 *     &lt;enumeration value="PULL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
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
