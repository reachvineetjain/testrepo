//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 02:34:00 PM CDT 
//


package com.ccighgo.service.auth.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoginType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="loginType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userDetailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginType", propOrder = {
    "loginTypeId",
    "loginType",
    "_default",
    "userDetailUrl"
})
public class LoginType {

    protected int loginTypeId;
    @XmlElement(required = true)
    protected String loginType;
    @XmlElement(name = "default")
    protected boolean _default;
    @XmlElement(required = true)
    protected String userDetailUrl;

    /**
     * Gets the value of the loginTypeId property.
     * 
     */
    public int getLoginTypeId() {
        return loginTypeId;
    }

    /**
     * Sets the value of the loginTypeId property.
     * 
     */
    public void setLoginTypeId(int value) {
        this.loginTypeId = value;
    }

    /**
     * Gets the value of the loginType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * Sets the value of the loginType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginType(String value) {
        this.loginType = value;
    }

    /**
     * Gets the value of the default property.
     * 
     */
    public boolean isDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     */
    public void setDefault(boolean value) {
        this._default = value;
    }

    /**
     * Gets the value of the userDetailUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDetailUrl() {
        return userDetailUrl;
    }

    /**
     * Sets the value of the userDetailUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDetailUrl(String value) {
        this.userDetailUrl = value;
    }

}
