//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 11:35:51 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasondetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerHLSeason complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerHLSeason">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerHLSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerHLSeasonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerHLSeason", propOrder = {
    "partnerHLSeasonId",
    "partnerHLSeasonName"
})
public class PartnerHLSeason {

    protected int partnerHLSeasonId;
    @XmlElement(required = true)
    protected String partnerHLSeasonName;

    /**
     * Gets the value of the partnerHLSeasonId property.
     * 
     */
    public int getPartnerHLSeasonId() {
        return partnerHLSeasonId;
    }

    /**
     * Sets the value of the partnerHLSeasonId property.
     * 
     */
    public void setPartnerHLSeasonId(int value) {
        this.partnerHLSeasonId = value;
    }

    /**
     * Gets the value of the partnerHLSeasonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerHLSeasonName() {
        return partnerHLSeasonName;
    }

    /**
     * Sets the value of the partnerHLSeasonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerHLSeasonName(String value) {
        this.partnerHLSeasonName = value;
    }

}
