//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.23 at 02:53:48 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.companydetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerMailingAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerMailingAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerMailingAddress" type="{http://www.ccighgo.com/companydetail}PartnerAddress"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerMailingAddress", propOrder = {
    "partnerMailingAddress"
})
public class PartnerMailingAddress {

    @XmlElement(required = true)
    protected PartnerAddress partnerMailingAddress;

    /**
     * Gets the value of the partnerMailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerAddress }
     *     
     */
    public PartnerAddress getPartnerMailingAddress() {
        return partnerMailingAddress;
    }

    /**
     * Sets the value of the partnerMailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerAddress }
     *     
     */
    public void setPartnerMailingAddress(PartnerAddress value) {
        this.partnerMailingAddress = value;
    }

}