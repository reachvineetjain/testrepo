//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 03:08:48 PM CST 
//


package com.ccighgo.service.transport.partner.beans.companydetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerAddressState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAddressState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerAddressStateId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerAddressStateISOCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerAddressStateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAddressState", propOrder = {
    "partnerAddressStateId",
    "partnerAddressStateISOCode",
    "partnerAddressStateName"
})
public class PartnerAddressState {

    protected int partnerAddressStateId;
    @XmlElement(required = true)
    protected String partnerAddressStateISOCode;
    @XmlElement(required = true)
    protected String partnerAddressStateName;

    /**
     * Gets the value of the partnerAddressStateId property.
     * 
     */
    public int getPartnerAddressStateId() {
        return partnerAddressStateId;
    }

    /**
     * Sets the value of the partnerAddressStateId property.
     * 
     */
    public void setPartnerAddressStateId(int value) {
        this.partnerAddressStateId = value;
    }

    /**
     * Gets the value of the partnerAddressStateISOCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerAddressStateISOCode() {
        return partnerAddressStateISOCode;
    }

    /**
     * Sets the value of the partnerAddressStateISOCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerAddressStateISOCode(String value) {
        this.partnerAddressStateISOCode = value;
    }

    /**
     * Gets the value of the partnerAddressStateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerAddressStateName() {
        return partnerAddressStateName;
    }

    /**
     * Sets the value of the partnerAddressStateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerAddressStateName(String value) {
        this.partnerAddressStateName = value;
    }

}
