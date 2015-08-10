//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.07 at 10:48:02 AM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.internationalPartners;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InternationalPartners complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternationalPartners">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="legalBusinessName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="counrty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="typeOfPrograms" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="currentlySendingParticipant" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="yearsInBusiness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descriptionOfPrograms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hearedAboutUs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalPartners", propOrder = {
    "legalBusinessName",
    "prefix",
    "firstName",
    "lastName",
    "address",
    "address2",
    "city",
    "stateOrProvince",
    "counrty",
    "phone",
    "email",
    "website",
    "programs",
    "typeOfPrograms",
    "currentlySendingParticipant",
    "yearsInBusiness",
    "descriptionOfPrograms",
    "hearedAboutUs"
})
public class InternationalPartners {

    @XmlElement(required = true)
    protected String legalBusinessName;
    @XmlElement(required = true)
    protected String prefix;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String address2;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String stateOrProvince;
    @XmlElement(required = true)
    protected String counrty;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String website;
    @XmlElement(required = true)
    protected List<String> programs;
    @XmlElement(required = true)
    protected List<String> typeOfPrograms;
    protected boolean currentlySendingParticipant;
    protected int yearsInBusiness;
    @XmlElement(required = true)
    protected String descriptionOfPrograms;
    @XmlElement(required = true)
    protected String hearedAboutUs;

    /**
     * Gets the value of the legalBusinessName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalBusinessName() {
        return legalBusinessName;
    }

    /**
     * Sets the value of the legalBusinessName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalBusinessName(String value) {
        this.legalBusinessName = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
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
     * Gets the value of the stateOrProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Sets the value of the stateOrProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateOrProvince(String value) {
        this.stateOrProvince = value;
    }

    /**
     * Gets the value of the counrty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounrty() {
        return counrty;
    }

    /**
     * Sets the value of the counrty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounrty(String value) {
        this.counrty = value;
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
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the programs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<String>();
        }
        return this.programs;
    }

    /**
     * Gets the value of the typeOfPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the typeOfPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypeOfPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTypeOfPrograms() {
        if (typeOfPrograms == null) {
            typeOfPrograms = new ArrayList<String>();
        }
        return this.typeOfPrograms;
    }

    /**
     * Gets the value of the currentlySendingParticipant property.
     * 
     */
    public boolean isCurrentlySendingParticipant() {
        return currentlySendingParticipant;
    }

    /**
     * Sets the value of the currentlySendingParticipant property.
     * 
     */
    public void setCurrentlySendingParticipant(boolean value) {
        this.currentlySendingParticipant = value;
    }

    /**
     * Gets the value of the yearsInBusiness property.
     * 
     */
    public int getYearsInBusiness() {
        return yearsInBusiness;
    }

    /**
     * Sets the value of the yearsInBusiness property.
     * 
     */
    public void setYearsInBusiness(int value) {
        this.yearsInBusiness = value;
    }

    /**
     * Gets the value of the descriptionOfPrograms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionOfPrograms() {
        return descriptionOfPrograms;
    }

    /**
     * Sets the value of the descriptionOfPrograms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionOfPrograms(String value) {
        this.descriptionOfPrograms = value;
    }

    /**
     * Gets the value of the hearedAboutUs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHearedAboutUs() {
        return hearedAboutUs;
    }

    /**
     * Sets the value of the hearedAboutUs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHearedAboutUs(String value) {
        this.hearedAboutUs = value;
    }

}
