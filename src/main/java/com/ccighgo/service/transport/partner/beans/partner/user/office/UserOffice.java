//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.02 at 11:05:26 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.user.office;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserOffice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserOffice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isPrimary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userOfficeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="officeAddressLineOne" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeAddressLineTwo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeAddressCountry" type="{http://www.ccighgo.com/partneruseroffice}UserAddressCountry"/>
 *         &lt;element name="officePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeWebsite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserOffice", propOrder = {
    "isPrimary",
    "userOfficeId",
    "officeAddressLineOne",
    "officeAddressLineTwo",
    "city",
    "zipCode",
    "officeAddressCountry",
    "officePhone",
    "officeFax",
    "officeEmail",
    "officeWebsite"
})
public class UserOffice {

    protected boolean isPrimary;
    protected int userOfficeId;
    @XmlElement(required = true)
    protected String officeAddressLineOne;
    @XmlElement(required = true)
    protected String officeAddressLineTwo;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String zipCode;
    @XmlElement(required = true)
    protected UserAddressCountry officeAddressCountry;
    @XmlElement(required = true)
    protected String officePhone;
    @XmlElement(required = true)
    protected String officeFax;
    @XmlElement(required = true)
    protected String officeEmail;
    @XmlElement(required = true)
    protected String officeWebsite;

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
     * Gets the value of the userOfficeId property.
     * 
     */
    public int getUserOfficeId() {
        return userOfficeId;
    }

    /**
     * Sets the value of the userOfficeId property.
     * 
     */
    public void setUserOfficeId(int value) {
        this.userOfficeId = value;
    }

    /**
     * Gets the value of the officeAddressLineOne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeAddressLineOne() {
        return officeAddressLineOne;
    }

    /**
     * Sets the value of the officeAddressLineOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeAddressLineOne(String value) {
        this.officeAddressLineOne = value;
    }

    /**
     * Gets the value of the officeAddressLineTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeAddressLineTwo() {
        return officeAddressLineTwo;
    }

    /**
     * Sets the value of the officeAddressLineTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeAddressLineTwo(String value) {
        this.officeAddressLineTwo = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * Gets the value of the officeAddressCountry property.
     * 
     * @return
     *     possible object is
     *     {@link UserAddressCountry }
     *     
     */
    public UserAddressCountry getOfficeAddressCountry() {
        return officeAddressCountry;
    }

    /**
     * Sets the value of the officeAddressCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserAddressCountry }
     *     
     */
    public void setOfficeAddressCountry(UserAddressCountry value) {
        this.officeAddressCountry = value;
    }

    /**
     * Gets the value of the officePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * Sets the value of the officePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficePhone(String value) {
        this.officePhone = value;
    }

    /**
     * Gets the value of the officeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeFax() {
        return officeFax;
    }

    /**
     * Sets the value of the officeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeFax(String value) {
        this.officeFax = value;
    }

    /**
     * Gets the value of the officeEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeEmail() {
        return officeEmail;
    }

    /**
     * Sets the value of the officeEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeEmail(String value) {
        this.officeEmail = value;
    }

    /**
     * Gets the value of the officeWebsite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeWebsite() {
        return officeWebsite;
    }

    /**
     * Sets the value of the officeWebsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeWebsite(String value) {
        this.officeWebsite = value;
    }

}
