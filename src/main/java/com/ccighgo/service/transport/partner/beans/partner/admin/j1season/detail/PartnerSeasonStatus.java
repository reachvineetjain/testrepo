//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.10 at 01:44:06 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerSeasonStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerSeasonStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerSeasonStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerSeasonStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonStatus", propOrder = {
    "partnerSeasonStatusId",
    "partnerSeasonStatus"
})
public class PartnerSeasonStatus {

    protected int partnerSeasonStatusId;
    @XmlElement(required = true)
    protected String partnerSeasonStatus;

    /**
     * Gets the value of the partnerSeasonStatusId property.
     * 
     */
    public int getPartnerSeasonStatusId() {
        return partnerSeasonStatusId;
    }

    /**
     * Sets the value of the partnerSeasonStatusId property.
     * 
     */
    public void setPartnerSeasonStatusId(int value) {
        this.partnerSeasonStatusId = value;
    }

    /**
     * Gets the value of the partnerSeasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonStatus() {
        return partnerSeasonStatus;
    }

    /**
     * Sets the value of the partnerSeasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonStatus(String value) {
        this.partnerSeasonStatus = value;
    }

}
