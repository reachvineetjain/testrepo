//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.11 at 06:26:51 PM IST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasondetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonStatus", propOrder = {
    "seasonStatusId",
    "seasonStatus"
})
public class SeasonStatus {

    protected int seasonStatusId;
    @XmlElement(required = true)
    protected String seasonStatus;

    /**
     * Gets the value of the seasonStatusId property.
     * 
     */
    public int getSeasonStatusId() {
        return seasonStatusId;
    }

    /**
     * Sets the value of the seasonStatusId property.
     * 
     */
    public void setSeasonStatusId(int value) {
        this.seasonStatusId = value;
    }

    /**
     * Gets the value of the seasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonStatus() {
        return seasonStatus;
    }

    /**
     * Sets the value of the seasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonStatus(String value) {
        this.seasonStatus = value;
    }

}
