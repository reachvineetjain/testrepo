//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 11:01:32 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubPartnerCountry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnerCountry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subPartnerCountryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerCountry", propOrder = {
    "subPartnerCountryId",
    "subPartnerCountry"
})
public class SubPartnerCountry {

    protected int subPartnerCountryId;
    @XmlElement(required = true)
    protected String subPartnerCountry;

    /**
     * Gets the value of the subPartnerCountryId property.
     * 
     */
    public int getSubPartnerCountryId() {
        return subPartnerCountryId;
    }

    /**
     * Sets the value of the subPartnerCountryId property.
     * 
     */
    public void setSubPartnerCountryId(int value) {
        this.subPartnerCountryId = value;
    }

    /**
     * Gets the value of the subPartnerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerCountry() {
        return subPartnerCountry;
    }

    /**
     * Sets the value of the subPartnerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerCountry(String value) {
        this.subPartnerCountry = value;
    }

}
