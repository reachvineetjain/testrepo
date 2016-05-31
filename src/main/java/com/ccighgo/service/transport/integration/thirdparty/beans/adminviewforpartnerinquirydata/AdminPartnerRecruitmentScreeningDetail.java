//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.18 at 10:35:15 AM IST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdminPartnerRecruitmentScreeningDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdminPartnerRecruitmentScreeningDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quickbooksCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="generalEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canHaveSubPartner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="multiCountrySender" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cciContact" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}CCIInquiryFormPerson"/>
 *         &lt;element name="billingNotes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminPartnerRecruitmentScreeningDetail", propOrder = {
    "rating",
    "companyName",
    "logo",
    "quickbooksCode",
    "username",
    "generalEmail",
    "invoiceEmail",
    "canHaveSubPartner",
    "multiCountrySender",
    "cciContact",
    "billingNotes",
    "acronym","generalContact"
})
public class AdminPartnerRecruitmentScreeningDetail {

    protected int rating;
    @XmlElement(required = true)
    protected String companyName;
    @XmlElement(required = true)
    protected String logo;
    @XmlElement(required = true)
    protected String quickbooksCode;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String generalEmail;
    @XmlElement(required = true)
    protected String invoiceEmail;
    protected boolean canHaveSubPartner;
    protected boolean multiCountrySender;
    @XmlElement(required = true)
    protected CCIInquiryFormPerson cciContact;
    @XmlElement(required = true)
    protected String billingNotes;
    @XmlElement(required = true)
    protected String acronym;
    protected String generalContact;

    /**
     * Gets the value of the rating property.
     * 
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(int value) {
        this.rating = value;
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
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogo(String value) {
        this.logo = value;
    }

    /**
     * Gets the value of the quickbooksCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuickbooksCode() {
        return quickbooksCode;
    }

    /**
     * Sets the value of the quickbooksCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuickbooksCode(String value) {
        this.quickbooksCode = value;
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
     * Gets the value of the generalEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneralEmail() {
        return generalEmail;
    }

    /**
     * Sets the value of the generalEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneralEmail(String value) {
        this.generalEmail = value;
    }

    /**
     * Gets the value of the invoiceEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    /**
     * Sets the value of the invoiceEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceEmail(String value) {
        this.invoiceEmail = value;
    }

    /**
     * Gets the value of the canHaveSubPartner property.
     * 
     */
    public boolean isCanHaveSubPartner() {
        return canHaveSubPartner;
    }

    /**
     * Sets the value of the canHaveSubPartner property.
     * 
     */
    public void setCanHaveSubPartner(boolean value) {
        this.canHaveSubPartner = value;
    }

    /**
     * Gets the value of the multiCountrySender property.
     * 
     */
    public boolean isMultiCountrySender() {
        return multiCountrySender;
    }

    /**
     * Sets the value of the multiCountrySender property.
     * 
     */
    public void setMultiCountrySender(boolean value) {
        this.multiCountrySender = value;
    }

    /**
     * Gets the value of the cciContact property.
     * 
     * @return
     *     possible object is
     *     {@link CCIInquiryFormPerson }
     *     
     */
    public CCIInquiryFormPerson getCciContact() {
        return cciContact;
    }

    /**
     * Sets the value of the cciContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCIInquiryFormPerson }
     *     
     */
    public void setCciContact(CCIInquiryFormPerson value) {
        this.cciContact = value;
    }

    /**
     * Gets the value of the billingNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingNotes() {
        return billingNotes;
    }

    /**
     * Sets the value of the billingNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingNotes(String value) {
        this.billingNotes = value;
    }

    /**
     * Gets the value of the acronym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Sets the value of the acronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcronym(String value) {
        this.acronym = value;
    }

	public String getGeneralContact() {
		return generalContact;
	}

	public void setGeneralContact(String generalContact) {
		this.generalContact = generalContact;
	}

    
    
}
