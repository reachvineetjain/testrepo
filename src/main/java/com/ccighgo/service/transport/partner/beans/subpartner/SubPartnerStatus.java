//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.01 at 01:38:35 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubPartnerStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnerStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subPartnerStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerStatus", propOrder = {
    "subPartnerStatusId",
    "subPartnerStatus"
})
public class SubPartnerStatus {

    protected int subPartnerStatusId;
    @XmlElement(required = true)
    protected String subPartnerStatus;

    /**
     * Gets the value of the subPartnerStatusId property.
     * 
     */
    public int getSubPartnerStatusId() {
        return subPartnerStatusId;
    }

    /**
     * Sets the value of the subPartnerStatusId property.
     * 
     */
    public void setSubPartnerStatusId(int value) {
        this.subPartnerStatusId = value;
    }

    /**
     * Gets the value of the subPartnerStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerStatus() {
        return subPartnerStatus;
    }

    /**
     * Sets the value of the subPartnerStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerStatus(String value) {
        this.subPartnerStatus = value;
    }

}
