//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 02:05:57 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerJ1HSCCIContact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerJ1HSCCIContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactImageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactDesignation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCCIContactExtentionNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerJ1HSCCIContact", propOrder = {
    "partnerProgramName",
    "partnerCCIContactImageUrl",
    "partnerCCIContactName",
    "partnerCCIContactDesignation",
    "partnerCCIContactPhone",
    "partnerCCIContactEmail",
    "partnerCCIContactExtentionNo"
})
public class PartnerJ1HSCCIContact {

    @XmlElement(required = true)
    protected String partnerProgramName;
    @XmlElement(required = true)
    protected String partnerCCIContactImageUrl;
    @XmlElement(required = true)
    protected String partnerCCIContactName;
    @XmlElement(required = true)
    protected String partnerCCIContactDesignation;
    @XmlElement(required = true)
    protected String partnerCCIContactPhone;
    @XmlElement(required = true)
    protected String partnerCCIContactEmail;
    @XmlElement(required = true)
    protected String partnerCCIContactExtentionNo;

    /**
     * Gets the value of the partnerProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerProgramName() {
        return partnerProgramName;
    }

    /**
     * Sets the value of the partnerProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerProgramName(String value) {
        this.partnerProgramName = value;
    }

    /**
     * Gets the value of the partnerCCIContactImageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactImageUrl() {
        return partnerCCIContactImageUrl;
    }

    /**
     * Sets the value of the partnerCCIContactImageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactImageUrl(String value) {
        this.partnerCCIContactImageUrl = value;
    }

    /**
     * Gets the value of the partnerCCIContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactName() {
        return partnerCCIContactName;
    }

    /**
     * Sets the value of the partnerCCIContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactName(String value) {
        this.partnerCCIContactName = value;
    }

    /**
     * Gets the value of the partnerCCIContactDesignation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactDesignation() {
        return partnerCCIContactDesignation;
    }

    /**
     * Sets the value of the partnerCCIContactDesignation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactDesignation(String value) {
        this.partnerCCIContactDesignation = value;
    }

    /**
     * Gets the value of the partnerCCIContactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactPhone() {
        return partnerCCIContactPhone;
    }

    /**
     * Sets the value of the partnerCCIContactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactPhone(String value) {
        this.partnerCCIContactPhone = value;
    }

    /**
     * Gets the value of the partnerCCIContactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactEmail() {
        return partnerCCIContactEmail;
    }

    /**
     * Sets the value of the partnerCCIContactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactEmail(String value) {
        this.partnerCCIContactEmail = value;
    }

    /**
     * Gets the value of the partnerCCIContactExtentionNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCCIContactExtentionNo() {
        return partnerCCIContactExtentionNo;
    }

    /**
     * Sets the value of the partnerCCIContactExtentionNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCCIContactExtentionNo(String value) {
        this.partnerCCIContactExtentionNo = value;
    }

}
