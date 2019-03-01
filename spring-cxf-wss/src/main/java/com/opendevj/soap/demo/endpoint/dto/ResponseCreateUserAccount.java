package com.opendevj.soap.demo.endpoint.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "firstName",
    "middleName",
    "surname",
    "secondSurname",
    "username",
    "credential"
})
@Getter
@Setter
@ToString
public class ResponseCreateUserAccount {

    @XmlElement(required = true, nillable = true)
    @ToString.Exclude
    protected String firstName;
    
    @XmlElement(required = false, nillable = true)
    @ToString.Exclude
    protected String middleName;
    
    @XmlElement(required = true, nillable = true)
    @ToString.Exclude
    protected String surname;
    
    @XmlElement(required = false, nillable = true)
    @ToString.Exclude
    protected String secondSurname;
    
    @XmlElement(required = true, nillable = true)
    protected String username;
    
    @XmlElement(required = true, nillable = true)
    @ToString.Exclude
    protected String credential;

}
