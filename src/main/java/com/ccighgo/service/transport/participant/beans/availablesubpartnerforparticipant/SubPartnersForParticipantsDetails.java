//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.27 at 04:16:59 PM CDT 
//


package com.ccighgo.service.transport.participant.beans.availablesubpartnerforparticipant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubPartnersForParticipantsDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnersForParticipantsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subPartnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnersForParticipantsDetails", propOrder = {
    "subPartnerId",
    "subPartnerName"
})
public class SubPartnersForParticipantsDetails {

    protected int subPartnerId;
    @XmlElement(required = true)
    protected String subPartnerName;

    /**
     * Gets the value of the subPartnerId property.
     * 
     */
    public int getSubPartnerId() {
        return subPartnerId;
    }

    /**
     * Sets the value of the subPartnerId property.
     * 
     */
    public void setSubPartnerId(int value) {
        this.subPartnerId = value;
    }

    /**
     * Gets the value of the subPartnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerName() {
        return subPartnerName;
    }

    /**
     * Sets the value of the subPartnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerName(String value) {
        this.subPartnerName = value;
    }

}
