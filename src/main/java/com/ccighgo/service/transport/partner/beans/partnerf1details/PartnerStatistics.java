//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.21 at 01:47:01 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationStats" type="{http://www.ccighgo.com/partnerf1}PartnerApplicationStats"/>
 *         &lt;element name="programStats" type="{http://www.ccighgo.com/partnerf1}PartnerProgramStats"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerStatistics", propOrder = {
    "applicationStats",
    "programStats"
})
public class PartnerStatistics {

    @XmlElement(required = true)
    protected PartnerApplicationStats applicationStats;
    @XmlElement(required = true)
    protected PartnerProgramStats programStats;

    /**
     * Gets the value of the applicationStats property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerApplicationStats }
     *     
     */
    public PartnerApplicationStats getApplicationStats() {
        return applicationStats;
    }

    /**
     * Sets the value of the applicationStats property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerApplicationStats }
     *     
     */
    public void setApplicationStats(PartnerApplicationStats value) {
        this.applicationStats = value;
    }

    /**
     * Gets the value of the programStats property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerProgramStats }
     *     
     */
    public PartnerProgramStats getProgramStats() {
        return programStats;
    }

    /**
     * Sets the value of the programStats property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerProgramStats }
     *     
     */
    public void setProgramStats(PartnerProgramStats value) {
        this.programStats = value;
    }

}