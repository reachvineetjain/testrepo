//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.01 at 01:56:22 PM CDT 
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
 *         &lt;element name="permisstionGroupOptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="permisstionGroupOptionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "permisstionGroupOptionId",
    "permisstionGroupOptionName",
    "permissionGroupOptionAction"
})
public class PermissionGroupOptions {

    protected int permisstionGroupOptionId;
    @XmlElement(required = true)
    protected String permisstionGroupOptionName;
    protected String permissionGroupOptionAction;

    /**
     * Gets the value of the permisstionGroupOptionId property.
     * 
     */
    public int getPermisstionGroupOptionId() {
        return permisstionGroupOptionId;
    }

    /**
     * Sets the value of the permisstionGroupOptionId property.
     * 
     */
    public void setPermisstionGroupOptionId(int value) {
        this.permisstionGroupOptionId = value;
    }

    /**
     * Gets the value of the permisstionGroupOptionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermisstionGroupOptionName() {
        return permisstionGroupOptionName;
    }

    /**
     * Sets the value of the permisstionGroupOptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermisstionGroupOptionName(String value) {
        this.permisstionGroupOptionName = value;
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
