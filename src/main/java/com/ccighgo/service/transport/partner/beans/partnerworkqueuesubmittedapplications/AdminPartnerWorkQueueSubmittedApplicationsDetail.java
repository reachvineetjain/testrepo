//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.21 at 04:19:42 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for AdminPartnerWorkQueueSubmittedApplicationsDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdminPartnerWorkQueueSubmittedApplicationsDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="companyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="followUpDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sunmittedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusOfInquiry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notesCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminPartnerWorkQueueSubmittedApplicationsDetail", propOrder = {
    "companyId",
    "companyName",
    "flagUrl",
    "firstName",
    "lastName",
    "phone",
    "email",
    "followUpDate",
    "website",
    "country",
    "programs",
    "sunmittedOn",
    "statusOfInquiry",
    "notesCount",
    "goId"
})
public class AdminPartnerWorkQueueSubmittedApplicationsDetail
    extends Response
{

    protected int companyId;
    @XmlElement(required = true)
    protected String companyName;
    @XmlElement(required = true)
    protected String flagUrl;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String followUpDate;
    @XmlElement(required = true)
    protected String website;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    protected String programs;
    @XmlElement(required = true)
    protected String sunmittedOn;
    @XmlElement(required = true)
    protected String statusOfInquiry;
    protected int notesCount;
    protected int goId;

    /**
     * Gets the value of the companyId property.
     * 
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the value of the companyId property.
     * 
     */
    public void setCompanyId(int value) {
        this.companyId = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the flagUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagUrl() {
        return flagUrl;
    }

    /**
     * Sets the value of the flagUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagUrl(String value) {
        this.flagUrl = value;
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
     * Gets the value of the followUpDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFollowUpDate() {
        return followUpDate;
    }

    /**
     * Sets the value of the followUpDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFollowUpDate(String value) {
        this.followUpDate = value;
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
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
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
     * Gets the value of the sunmittedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSunmittedOn() {
        return sunmittedOn;
    }

    /**
     * Sets the value of the sunmittedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSunmittedOn(String value) {
        this.sunmittedOn = value;
    }

    /**
     * Gets the value of the statusOfInquiry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusOfInquiry() {
        return statusOfInquiry;
    }

    /**
     * Sets the value of the statusOfInquiry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusOfInquiry(String value) {
        this.statusOfInquiry = value;
    }

    /**
     * Gets the value of the notesCount property.
     * 
     */
    public int getNotesCount() {
        return notesCount;
    }

    /**
     * Sets the value of the notesCount property.
     * 
     */
    public void setNotesCount(int value) {
        this.notesCount = value;
    }

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

}
