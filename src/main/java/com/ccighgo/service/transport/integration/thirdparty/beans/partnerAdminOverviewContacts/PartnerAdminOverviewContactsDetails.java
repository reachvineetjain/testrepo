//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.20 at 06:07:17 PM CST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewContacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerAdminOverviewContactsDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminOverviewContactsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerContactId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="salutation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="titile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="skypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="programs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryContact" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="receiveNotificationEmails" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAdminOverviewContactsDetails", propOrder = {
    "goId",
    "partnerContactId",
    "salutation",
    "titile",
    "firstName",
    "lastName",
    "email",
    "phone",
    "emergencyPhone",
    "fax",
    "skypeId",
    "username",
    "active",
    "programs",
    "primaryContact",
    "loginId",
    "receiveNotificationEmails"
})
public class PartnerAdminOverviewContactsDetails {

    protected int goId;
    protected int partnerContactId;
    @XmlElement(required = true)
    protected String salutation;
    @XmlElement(required = true)
    protected String titile;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String emergencyPhone;
    @XmlElement(required = true)
    protected String fax;
    @XmlElement(required = true)
    protected String skypeId;
    @XmlElement(required = true)
    protected String username;
    protected boolean active;
    @XmlElement(required = true)
    protected String programs;
    protected boolean primaryContact;
    protected int loginId;
    protected boolean receiveNotificationEmails;

    /**
     * Gets the value of the goId property.
     * 
     */
    public int getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     */
    public void setGoId(int value) {
        this.goId = value;
    }

    /**
     * Gets the value of the partnerContactId property.
     * 
     */
    public int getPartnerContactId() {
        return partnerContactId;
    }

    /**
     * Sets the value of the partnerContactId property.
     * 
     */
    public void setPartnerContactId(int value) {
        this.partnerContactId = value;
    }

    /**
     * Gets the value of the salutation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * Sets the value of the salutation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalutation(String value) {
        this.salutation = value;
    }

    /**
     * Gets the value of the titile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitile() {
        return titile;
    }

    /**
     * Sets the value of the titile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitile(String value) {
        this.titile = value;
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
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the skypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkypeId() {
        return skypeId;
    }

    /**
     * Sets the value of the skypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkypeId(String value) {
        this.skypeId = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the programs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrograms() {
        return programs;
    }

    /**
     * Sets the value of the programs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrograms(String value) {
        this.programs = value;
    }

    /**
     * Gets the value of the primaryContact property.
     * 
     */
    public boolean isPrimaryContact() {
        return primaryContact;
    }

    /**
     * Sets the value of the primaryContact property.
     * 
     */
    public void setPrimaryContact(boolean value) {
        this.primaryContact = value;
    }

    /**
     * Gets the value of the loginId property.
     * 
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     */
    public void setLoginId(int value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the receiveNotificationEmails property.
     * 
     */
    public boolean isReceiveNotificationEmails() {
        return receiveNotificationEmails;
    }

    /**
     * Sets the value of the receiveNotificationEmails property.
     * 
     */
    public void setReceiveNotificationEmails(boolean value) {
        this.receiveNotificationEmails = value;
    }

}
