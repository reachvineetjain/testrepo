//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.01 at 04:10:33 PM IST 
//


package com.ccighgo.service.transport.partner.beans.partnerusers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerUserPhotoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isPrimary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partnerUserLoginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerUser", propOrder = {
    "partnerUserId",
    "partnerUserPhotoUrl",
    "partnerUserFirstName",
    "partnerUserLastName",
    "isPrimary",
    "partnerUserLoginName",
    "partnerUserEmail",
    "partnerUserStatus",
    "partnerGoId"
})
public class PartnerUser {

    protected int partnerUserId;
    @XmlElement(required = true)
    protected String partnerUserPhotoUrl;
    @XmlElement(required = true)
    protected String partnerUserFirstName;
    @XmlElement(required = true)
    protected String partnerUserLastName;
    protected boolean isPrimary;
    @XmlElement(required = true)
    protected String partnerUserLoginName;
    @XmlElement(required = true)
    protected String partnerUserEmail;
    protected int partnerUserStatus;
    protected int partnerGoId;

    /**
     * Gets the value of the partnerUserId property.
     * 
     */
    public int getPartnerUserId() {
        return partnerUserId;
    }

    /**
     * Sets the value of the partnerUserId property.
     * 
     */
    public void setPartnerUserId(int value) {
        this.partnerUserId = value;
    }

    /**
     * Gets the value of the partnerUserPhotoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserPhotoUrl() {
        return partnerUserPhotoUrl;
    }

    /**
     * Sets the value of the partnerUserPhotoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserPhotoUrl(String value) {
        this.partnerUserPhotoUrl = value;
    }

    /**
     * Gets the value of the partnerUserFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserFirstName() {
        return partnerUserFirstName;
    }

    /**
     * Sets the value of the partnerUserFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserFirstName(String value) {
        this.partnerUserFirstName = value;
    }

    /**
     * Gets the value of the partnerUserLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserLastName() {
        return partnerUserLastName;
    }

    /**
     * Sets the value of the partnerUserLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserLastName(String value) {
        this.partnerUserLastName = value;
    }

    /**
     * Gets the value of the isPrimary property.
     * 
     */
    public boolean isIsPrimary() {
        return isPrimary;
    }

    /**
     * Sets the value of the isPrimary property.
     * 
     */
    public void setIsPrimary(boolean value) {
        this.isPrimary = value;
    }

    /**
     * Gets the value of the partnerUserLoginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserLoginName() {
        return partnerUserLoginName;
    }

    /**
     * Sets the value of the partnerUserLoginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserLoginName(String value) {
        this.partnerUserLoginName = value;
    }

    /**
     * Gets the value of the partnerUserEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserEmail() {
        return partnerUserEmail;
    }

    /**
     * Sets the value of the partnerUserEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserEmail(String value) {
        this.partnerUserEmail = value;
    }

    /**
     * Gets the value of the partnerUserStatus property.
     * 
     */
    public int getPartnerUserStatus() {
        return partnerUserStatus;
    }

    /**
     * Sets the value of the partnerUserStatus property.
     * 
     */
    public void setPartnerUserStatus(int value) {
        this.partnerUserStatus = value;
    }

   public int getPartnerGoId() {
      return partnerGoId;
   }

   public void setPartnerGoId(int partnerGoId) {
      this.partnerGoId = partnerGoId;
   }

    
}
