//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.21 at 10:17:30 AM CDT 
//


package com.ccighgo.service.transport.participant.beans.newparticipant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParticipantSubPartner complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParticipantSubPartner">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantSubPartnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantSubPartner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantSubPartner", propOrder = {
    "partnerGoId",
    "participantSubPartnerId",
    "participantSubPartner"
})
public class ParticipantSubPartner {

    protected int partnerGoId;
    protected int participantSubPartnerId;
    @XmlElement(required = true)
    protected String participantSubPartner;

    /**
     * Gets the value of the partnerGoId property.
     * 
     */
    public int getPartnerGoId() {
        return partnerGoId;
    }

    /**
     * Sets the value of the partnerGoId property.
     * 
     */
    public void setPartnerGoId(int value) {
        this.partnerGoId = value;
    }

    /**
     * Gets the value of the participantSubPartnerId property.
     * 
     */
    public int getParticipantSubPartnerId() {
        return participantSubPartnerId;
    }

    /**
     * Sets the value of the participantSubPartnerId property.
     * 
     */
    public void setParticipantSubPartnerId(int value) {
        this.participantSubPartnerId = value;
    }

    /**
     * Gets the value of the participantSubPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantSubPartner() {
        return participantSubPartner;
    }

    /**
     * Sets the value of the participantSubPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantSubPartner(String value) {
        this.participantSubPartner = value;
    }

}