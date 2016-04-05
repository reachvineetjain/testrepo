//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.05 at 06:07:53 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFContactInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFContactInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="haveHomePhoneOrLandline" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bestContactNumberToReach" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferPhone" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="preferEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyContactPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bestWayToReachHome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFContactInfo", propOrder = {
    "haveHomePhoneOrLandline",
    "phone",
    "bestContactNumberToReach",
    "preferPhone",
    "preferEmail",
    "contactPhone",
    "contactEmail",
    "emergencyContactPerson",
    "emergencyPhone",
    "bestWayToReachHome"
})
public class HFContactInfo {

    protected boolean haveHomePhoneOrLandline;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String bestContactNumberToReach;
    protected boolean preferPhone;
    protected boolean preferEmail;
    @XmlElement(required = true)
    protected String contactPhone;
    @XmlElement(required = true)
    protected String contactEmail;
    @XmlElement(required = true)
    protected String emergencyContactPerson;
    @XmlElement(required = true)
    protected String emergencyPhone;
    @XmlElement(required = true)
    protected String bestWayToReachHome;

    /**
     * Gets the value of the haveHomePhoneOrLandline property.
     * 
     */
    public boolean isHaveHomePhoneOrLandline() {
        return haveHomePhoneOrLandline;
    }

    /**
     * Sets the value of the haveHomePhoneOrLandline property.
     * 
     */
    public void setHaveHomePhoneOrLandline(boolean value) {
        this.haveHomePhoneOrLandline = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the bestContactNumberToReach property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBestContactNumberToReach() {
        return bestContactNumberToReach;
    }

    /**
     * Sets the value of the bestContactNumberToReach property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBestContactNumberToReach(String value) {
        this.bestContactNumberToReach = value;
    }

    /**
     * Gets the value of the preferPhone property.
     * 
     */
    public boolean isPreferPhone() {
        return preferPhone;
    }

    /**
     * Sets the value of the preferPhone property.
     * 
     */
    public void setPreferPhone(boolean value) {
        this.preferPhone = value;
    }

    /**
     * Gets the value of the preferEmail property.
     * 
     */
    public boolean isPreferEmail() {
        return preferEmail;
    }

    /**
     * Sets the value of the preferEmail property.
     * 
     */
    public void setPreferEmail(boolean value) {
        this.preferEmail = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmail(String value) {
        this.contactEmail = value;
    }

    /**
     * Gets the value of the emergencyContactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    /**
     * Sets the value of the emergencyContactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyContactPerson(String value) {
        this.emergencyContactPerson = value;
    }

    /**
     * Gets the value of the emergencyPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    /**
     * Sets the value of the emergencyPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyPhone(String value) {
        this.emergencyPhone = value;
    }

    /**
     * Gets the value of the bestWayToReachHome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBestWayToReachHome() {
        return bestWayToReachHome;
    }

    /**
     * Sets the value of the bestWayToReachHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBestWayToReachHome(String value) {
        this.bestWayToReachHome = value;
    }

}
