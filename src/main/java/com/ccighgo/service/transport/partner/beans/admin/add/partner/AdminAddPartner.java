//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.05 at 12:53:19 AM IST 
//


package com.ccighgo.service.transport.partner.beans.admin.add.partner;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for AdminAddPartner complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdminAddPartner">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quickbookCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="generalEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salutation" type="{http://www.ccighgo.com/adminaddpartner}Salutation"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCountry" type="{http://www.ccighgo.com/adminaddpartner}PartnerCountry"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canHaveSubpartners" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="multiCountrySender" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="generalContact" type="{http://www.ccighgo.com/adminaddpartner}CCIContact"/>
 *         &lt;element name="programContacts" type="{http://www.ccighgo.com/adminaddpartner}ProgramContacts" maxOccurs="unbounded"/>
 *         &lt;element name="sendLogin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminAddPartner", propOrder = {
    "partnerGoId",
    "loginId",
    "companyLogo",
    "companyName",
    "quickbookCode",
    "acronym",
    "generalEmail",
    "salutation",
    "firstName",
    "lastName",
    "email",
    "partnerCountry",
    "userName",
    "canHaveSubpartners",
    "multiCountrySender",
    "generalContact",
    "programContacts",
    "sendLogin"
})
public class AdminAddPartner
    extends Response
{

    protected int partnerGoId;
    protected int loginId;
    @XmlElement(required = true)
    protected String companyLogo;
    @XmlElement(required = true)
    protected String companyName;
    @XmlElement(required = true)
    protected String quickbookCode;
    @XmlElement(required = true)
    protected String acronym;
    @XmlElement(required = true)
    protected String generalEmail;
    @XmlElement(required = true)
    protected Salutation salutation;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected PartnerCountry partnerCountry;
    @XmlElement(required = true)
    protected String userName;
    protected boolean canHaveSubpartners;
    protected boolean multiCountrySender;
    @XmlElement(required = true)
    protected CCIContact generalContact;
    @XmlElement(required = true)
    protected List<ProgramContacts> programContacts;
    protected boolean sendLogin;

    /**
     * Gets the value of the partnerGoId property.
     * 
     */
    public int getPartnerGoId() {
        return partnerGoId;
    }

    /**
     * Sets the value of the partnerGoId property.
     * 
     */
    public void setPartnerGoId(int value) {
        this.partnerGoId = value;
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
     * Gets the value of the companyLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyLogo() {
        return companyLogo;
    }

    /**
     * Sets the value of the companyLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyLogo(String value) {
        this.companyLogo = value;
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
     * Gets the value of the quickbookCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuickbookCode() {
        return quickbookCode;
    }

    /**
     * Sets the value of the quickbookCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuickbookCode(String value) {
        this.quickbookCode = value;
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
     * Gets the value of the salutation property.
     * 
     * @return
     *     possible object is
     *     {@link Salutation }
     *     
     */
    public Salutation getSalutation() {
        return salutation;
    }

    /**
     * Sets the value of the salutation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Salutation }
     *     
     */
    public void setSalutation(Salutation value) {
        this.salutation = value;
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
     * Gets the value of the partnerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerCountry }
     *     
     */
    public PartnerCountry getPartnerCountry() {
        return partnerCountry;
    }

    /**
     * Sets the value of the partnerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerCountry }
     *     
     */
    public void setPartnerCountry(PartnerCountry value) {
        this.partnerCountry = value;
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

    /**
     * Gets the value of the canHaveSubpartners property.
     * 
     */
    public boolean isCanHaveSubpartners() {
        return canHaveSubpartners;
    }

    /**
     * Sets the value of the canHaveSubpartners property.
     * 
     */
    public void setCanHaveSubpartners(boolean value) {
        this.canHaveSubpartners = value;
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
     * Gets the value of the generalContact property.
     * 
     * @return
     *     possible object is
     *     {@link CCIContact }
     *     
     */
    public CCIContact getGeneralContact() {
        return generalContact;
    }

    /**
     * Sets the value of the generalContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCIContact }
     *     
     */
    public void setGeneralContact(CCIContact value) {
        this.generalContact = value;
    }

    /**
     * Gets the value of the programContacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programContacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgramContacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProgramContacts }
     * 
     * 
     */
    public List<ProgramContacts> getProgramContacts() {
        if (programContacts == null) {
            programContacts = new ArrayList<ProgramContacts>();
        }
        return this.programContacts;
    }

    /**
     * Gets the value of the sendLogin property.
     * 
     */
    public boolean isSendLogin() {
        return sendLogin;
    }

    /**
     * Sets the value of the sendLogin property.
     * 
     */
    public void setSendLogin(boolean value) {
        this.sendLogin = value;
    }

}
