//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 10:05:31 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.country.partnerusers;

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
 *         &lt;element name="partnerUserCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerUserFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserLoginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerUserStatus" type="{http://www.ccighgo.com/partnerusers}PartnerUserStatus"/>
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
    "partnerUserCount",
    "partnerUserId",
    "partnerUserFirstName",
    "partnerUserLastName",
    "partnerUserLoginName",
    "partnerUserEmail",
    "partnerUserStatus"
})
public class PartnerUser {

    protected int partnerUserCount;
    protected int partnerUserId;
    @XmlElement(required = true)
    protected String partnerUserFirstName;
    @XmlElement(required = true)
    protected String partnerUserLastName;
    @XmlElement(required = true)
    protected String partnerUserLoginName;
    @XmlElement(required = true)
    protected String partnerUserEmail;
    @XmlElement(required = true)
    protected PartnerUserStatus partnerUserStatus;

    /**
     * Gets the value of the partnerUserCount property.
     * 
     */
    public int getPartnerUserCount() {
        return partnerUserCount;
    }

    /**
     * Sets the value of the partnerUserCount property.
     * 
     */
    public void setPartnerUserCount(int value) {
        this.partnerUserCount = value;
    }

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
     * @return
     *     possible object is
     *     {@link PartnerUserStatus }
     *     
     */
    public PartnerUserStatus getPartnerUserStatus() {
        return partnerUserStatus;
    }

    /**
     * Sets the value of the partnerUserStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerUserStatus }
     *     
     */
    public void setPartnerUserStatus(PartnerUserStatus value) {
        this.partnerUserStatus = value;
    }

}
