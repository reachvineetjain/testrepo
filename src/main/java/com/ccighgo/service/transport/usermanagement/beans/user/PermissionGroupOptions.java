//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.07 at 11:56:46 AM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PermissionGroupOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PermissionGroupOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permissionGroupOptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="permissionGroupOptionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissionGroupOptionActionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionGroupOptionAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermissionGroupOptions", propOrder = {
    "permissionGroupOptionId",
    "permissionGroupOptionName",
    "permissionGroupOptionActionId",
    "permissionGroupOptionAction"
})
public class PermissionGroupOptions {

    protected int permissionGroupOptionId;
    @XmlElement(required = true)
    protected String permissionGroupOptionName;
    protected String permissionGroupOptionActionId;
    protected String permissionGroupOptionAction;

    /**
     * Gets the value of the permissionGroupOptionId property.
     * 
     */
    public int getPermissionGroupOptionId() {
        return permissionGroupOptionId;
    }

    /**
     * Sets the value of the permissionGroupOptionId property.
     * 
     */
    public void setPermissionGroupOptionId(int value) {
        this.permissionGroupOptionId = value;
    }

    /**
     * Gets the value of the permissionGroupOptionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionGroupOptionName() {
        return permissionGroupOptionName;
    }

    /**
     * Sets the value of the permissionGroupOptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionGroupOptionName(String value) {
        this.permissionGroupOptionName = value;
    }

    /**
     * Gets the value of the permissionGroupOptionActionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionGroupOptionActionId() {
        return permissionGroupOptionActionId;
    }

    /**
     * Sets the value of the permissionGroupOptionActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionGroupOptionActionId(String value) {
        this.permissionGroupOptionActionId = value;
    }

    /**
     * Gets the value of the permissionGroupOptionAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionGroupOptionAction() {
        return permissionGroupOptionAction;
    }

    /**
     * Sets the value of the permissionGroupOptionAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionGroupOptionAction(String value) {
        this.permissionGroupOptionAction = value;
    }

}
