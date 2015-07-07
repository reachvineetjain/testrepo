//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.07 at 11:56:53 AM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WPCAPProgramAllocationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WPCAPProgramAllocationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeOrTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maximumParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="expectedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="acceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pendingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cciReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WPCAPProgramAllocationDetails", propOrder = {
    "typeOrTotal",
    "maximumParticipants",
    "expectedParticipants",
    "acceptedParticipants",
    "pendingParticipants",
    "cciReview",
    "remainingParticipants"
})
public class WPCAPProgramAllocationDetails {

    protected String typeOrTotal;
    protected Integer maximumParticipants;
    protected Integer expectedParticipants;
    protected Integer acceptedParticipants;
    protected Integer pendingParticipants;
    protected Integer cciReview;
    protected Integer remainingParticipants;

    /**
     * Gets the value of the typeOrTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOrTotal() {
        return typeOrTotal;
    }

    /**
     * Sets the value of the typeOrTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOrTotal(String value) {
        this.typeOrTotal = value;
    }

    /**
     * Gets the value of the maximumParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaximumParticipants() {
        return maximumParticipants;
    }

    /**
     * Sets the value of the maximumParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximumParticipants(Integer value) {
        this.maximumParticipants = value;
    }

    /**
     * Gets the value of the expectedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpectedParticipants() {
        return expectedParticipants;
    }

    /**
     * Sets the value of the expectedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpectedParticipants(Integer value) {
        this.expectedParticipants = value;
    }

    /**
     * Gets the value of the acceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAcceptedParticipants() {
        return acceptedParticipants;
    }

    /**
     * Sets the value of the acceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAcceptedParticipants(Integer value) {
        this.acceptedParticipants = value;
    }

    /**
     * Gets the value of the pendingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPendingParticipants() {
        return pendingParticipants;
    }

    /**
     * Sets the value of the pendingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPendingParticipants(Integer value) {
        this.pendingParticipants = value;
    }

    /**
     * Gets the value of the cciReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCciReview() {
        return cciReview;
    }

    /**
     * Sets the value of the cciReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCciReview(Integer value) {
        this.cciReview = value;
    }

    /**
     * Gets the value of the remainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRemainingParticipants() {
        return remainingParticipants;
    }

    /**
     * Sets the value of the remainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRemainingParticipants(Integer value) {
        this.remainingParticipants = value;
    }

}
