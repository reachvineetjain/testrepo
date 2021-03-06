//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 11:30:03 AM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for HFProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFProfile">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="hfSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyContact" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="physicalAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="physicalCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="physicalState" type="{http://www.ccighgo.com/hfprofile}HFState"/>
 *         &lt;element name="physicalZip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mailingAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mailingCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mailingState" type="{http://www.ccighgo.com/hfprofile}HFState"/>
 *         &lt;element name="mailingZip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recieveEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFProfile", propOrder = {
    "hfSeasonId",
    "picUrl",
    "firstName",
    "lastName",
    "primaryPhone",
    "emergencyPhone",
    "emergencyContact",
    "physicalAddress",
    "physicalCity",
    "physicalState",
    "physicalZip",
    "mailingAddress",
    "mailingCity",
    "mailingState",
    "mailingZip",
    "recieveEmail",
    "email",
    "userName"
})
public class HFProfile
    extends Response
{

    protected int hfSeasonId;
    @XmlElement(required = true)
    protected String picUrl;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String primaryPhone;
    @XmlElement(required = true)
    protected String emergencyPhone;
    @XmlElement(required = true)
    protected String emergencyContact;
    @XmlElement(required = true)
    protected String physicalAddress;
    @XmlElement(required = true)
    protected String physicalCity;
    @XmlElement(required = true)
    protected HFState physicalState;
    @XmlElement(required = true)
    protected String physicalZip;
    @XmlElement(required = true)
    protected String mailingAddress;
    @XmlElement(required = true)
    protected String mailingCity;
    @XmlElement(required = true)
    protected HFState mailingState;
    @XmlElement(required = true)
    protected String mailingZip;
    protected boolean recieveEmail;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String userName;

    /**
     * Gets the value of the hfSeasonId property.
     * 
     */
    public int getHfSeasonId() {
        return hfSeasonId;
    }

    /**
     * Sets the value of the hfSeasonId property.
     * 
     */
    public void setHfSeasonId(int value) {
        this.hfSeasonId = value;
    }
    
    /**
     * Gets the value of the picUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * Sets the value of the picUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicUrl(String value) {
        this.picUrl = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the primaryPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Sets the value of the primaryPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPhone(String value) {
        this.primaryPhone = value;
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
     * Gets the value of the emergencyContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Sets the value of the emergencyContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyContact(String value) {
        this.emergencyContact = value;
    }

    /**
     * Gets the value of the physicalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Sets the value of the physicalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddress(String value) {
        this.physicalAddress = value;
    }

    /**
     * Gets the value of the physicalCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalCity() {
        return physicalCity;
    }

    /**
     * Sets the value of the physicalCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalCity(String value) {
        this.physicalCity = value;
    }

    /**
     * Gets the value of the physicalState property.
     * 
     * @return
     *     possible object is
     *     {@link HFState }
     *     
     */
    public HFState getPhysicalState() {
        return physicalState;
    }

    /**
     * Sets the value of the physicalState property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFState }
     *     
     */
    public void setPhysicalState(HFState value) {
        this.physicalState = value;
    }

    /**
     * Gets the value of the physicalZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalZip() {
        return physicalZip;
    }

    /**
     * Sets the value of the physicalZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalZip(String value) {
        this.physicalZip = value;
    }

    /**
     * Gets the value of the mailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Sets the value of the mailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddress(String value) {
        this.mailingAddress = value;
    }

    /**
     * Gets the value of the mailingCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingCity() {
        return mailingCity;
    }

    /**
     * Sets the value of the mailingCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingCity(String value) {
        this.mailingCity = value;
    }

    /**
     * Gets the value of the mailingState property.
     * 
     * @return
     *     possible object is
     *     {@link HFState }
     *     
     */
    public HFState getMailingState() {
        return mailingState;
    }

    /**
     * Sets the value of the mailingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFState }
     *     
     */
    public void setMailingState(HFState value) {
        this.mailingState = value;
    }

    /**
     * Gets the value of the mailingZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingZip() {
        return mailingZip;
    }

    /**
     * Sets the value of the mailingZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingZip(String value) {
        this.mailingZip = value;
    }

    /**
     * Gets the value of the recieveEmail property.
     * 
     */
    public boolean isRecieveEmail() {
        return recieveEmail;
    }

    /**
     * Sets the value of the recieveEmail property.
     * 
     */
    public void setRecieveEmail(boolean value) {
        this.recieveEmail = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
