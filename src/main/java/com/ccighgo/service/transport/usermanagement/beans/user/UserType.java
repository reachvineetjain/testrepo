//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.14 at 10:58:39 PM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserType", propOrder = {
    "userTypeId",
    "userTypeCode",
    "userTypeName"
})
public class UserType {

    protected int userTypeId;
    @XmlElement(required = true)
    protected String userTypeCode;
    @XmlElement(required = true)
    protected String userTypeName;

    /**
     * Gets the value of the userTypeId property.
     * 
     */
    public int getUserTypeId() {
        return userTypeId;
    }

    /**
     * Sets the value of the userTypeId property.
     * 
     */
    public void setUserTypeId(int value) {
        this.userTypeId = value;
    }

    /**
     * Gets the value of the userTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTypeCode() {
        return userTypeCode;
    }

    /**
     * Sets the value of the userTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTypeCode(String value) {
        this.userTypeCode = value;
    }

    /**
     * Gets the value of the userTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTypeName() {
        return userTypeName;
    }

    /**
     * Sets the value of the userTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTypeName(String value) {
        this.userTypeName = value;
    }

}
