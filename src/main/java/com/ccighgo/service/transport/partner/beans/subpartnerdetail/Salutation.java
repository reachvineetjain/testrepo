//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.28 at 04:08:14 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Salutation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Salutation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salutationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="salutationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Salutation", propOrder = {
    "salutationId",
    "salutationName",
    "active"
})
public class Salutation {

    protected int salutationId;
    @XmlElement(required = true)
    protected String salutationName;
    protected boolean active;

    /**
     * Gets the value of the salutationId property.
     * 
     */
    public int getSalutationId() {
        return salutationId;
    }

    /**
     * Sets the value of the salutationId property.
     * 
     */
    public void setSalutationId(int value) {
        this.salutationId = value;
    }

    /**
     * Gets the value of the salutationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalutationName() {
        return salutationName;
    }

    /**
     * Sets the value of the salutationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalutationName(String value) {
        this.salutationName = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

}
