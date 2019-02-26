package com.opendevj.soap.demo.endpoint.dto;

import java.math.BigInteger;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.opendevj.soap.demo.util.Regexp;

import lombok.Getter;
import lombok.ToString;


/**
 * <p>Java class for user complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestCreateUserAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subEntityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="secondSurname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentIDTypeCode" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="documentIDNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jobTitleId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="channel" type="{http://www.opendevj.com/soap/demo/document}channel"/>
 *         &lt;element name="salesChannelId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="observation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "subEntityId",
    "officeId",
    "firstName",
    "middleName",
    "surname",
    "secondSurname",
    "username",
    "documentIDTypeCode",
    "documentIDNumber",
    "email",
    "jobTitleId",
    "roleId",
    "channel",
    "salesChannelId",
    "observation",
    "profileId"
})
@Getter
@ToString
public class RequestCreateUserAccount {

    @XmlElement(required = true)
    @Pattern(regexp=Regexp.DIGITS, message="Debe estar commpuesto de d\u00EDgitos")
    @Size(max=6, min=6, message="Debe tener logintud de 6")
    protected String subEntityId;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.DIGITS, message="Debe estar commpuesto de d\\u00EDgitos")
    @Size(max=6, min=6, message="Debe tener logintud de 6")
    protected String officeId;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.PERSON_NAME, flags=Pattern.Flag.CASE_INSENSITIVE, message="No debe contener caracteres extra\u00F1os")
    @Size(max=50, message="Debe tener una longitud no mayor a 50")
    protected String firstName;
    
    @XmlElement(required = false)
    @Pattern(regexp=Regexp.PERSON_NAME, flags=Pattern.Flag.CASE_INSENSITIVE, message="No debe contener caracteres extra\\u00F1os")
    @Size(max=50, message="Debe tener una longitud no mayor a 50")
    protected String middleName;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.PERSON_NAME, flags=Pattern.Flag.CASE_INSENSITIVE, message="No debe contener caracteres extra\\u00F1os")
    @Size(max=50, message="Debe tener una longitud no mayor a 50")
    protected String surname;
    
    @XmlElement(required = false)
    @Pattern(regexp=Regexp.PERSON_NAME, flags=Pattern.Flag.CASE_INSENSITIVE, message="No debe contener caracteres extra\\u00F1os")
    @Size(max=50, message="Debe tener una longitud no mayor a 50")
    protected String secondSurname;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.USERNAME, message="Debe estar compuesto por valores alfanum\\u00E9ricos y guion bajo \"_\"")
    @Size(min=3, max=20, message="Debe de tener una longitud de 3 a 20")
    protected String username;
    
    @XmlElement(required = true)
    protected BigInteger documentIDTypeCode;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.DIGITS, message="Debe estar commpuesto de d\\u00EDgitos")
    @Size(min=8, max=11, message="Debe de tener una longitud de 8 a 11")
    protected String documentIDNumber;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.EMAIL, message="Formato de correo inv\\u00E1lido")
    @Size(max=200, message="Debe tener una longitud no mayor a 200")
    protected String email;
    
    @XmlElement(required = true)
    protected BigInteger jobTitleId;
    
    @XmlElement(required = true)
    protected BigInteger roleId;
    
    @XmlElement(required = true)
    protected Channel channel;
    
    @XmlElement(required = true)
    @Pattern(regexp=Regexp.DIGITS, message="Debe estar commpuesto de d\\u00EDgitos")
    @Size(max=50)
    protected String salesChannelId;
    
    @XmlElement(required = false)
    @Size(max=50, message="Debe tener una longitud no mayor a 50")
    protected String observation;
    
    @XmlElement(required = true)
    protected BigInteger profileId;

}
