//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 11:35:57 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasonf1detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerSeasonAnnouncements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerSeasonAnnouncements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="partnerSeasonAnnouncement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="announcementDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonAnnouncements", propOrder = {
    "partnerSeasonAnnouncement",
    "announcementDate"
})
public class PartnerSeasonAnnouncements {

    @XmlElement(required = true)
    protected String partnerSeasonAnnouncement;
    @XmlElement(required = true)
    protected String announcementDate;

    /**
     * Gets the value of the partnerSeasonAnnouncement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonAnnouncement() {
        return partnerSeasonAnnouncement;
    }

    /**
     * Sets the value of the partnerSeasonAnnouncement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonAnnouncement(String value) {
        this.partnerSeasonAnnouncement = value;
    }

    /**
     * Gets the value of the announcementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnouncementDate() {
        return announcementDate;
    }

    /**
     * Sets the value of the announcementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnouncementDate(String value) {
        this.announcementDate = value;
    }

}
