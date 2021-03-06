//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.06 at 03:33:34 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFAdultDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFAdultDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hostfamilyMemberId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="genderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isHostParent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="personalPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activitiesOrInterests" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="educationLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="communityInvolvement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="residencyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="livingInsideHomeExplanation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jobTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jobPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hasAnotherJob" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="otherEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherJobTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherContactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherJobPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFAdultDetails", propOrder = {
    "hostfamilyMemberId",
    "relationship",
    "firstName",
    "lastName",
    "birthdate",
    "genderId",
    "email",
    "isHostParent",
    "personalPhone",
    "activitiesOrInterests",
    "educationLevel",
    "communityInvolvement",
    "singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome",
    "residencyTime",
    "livingInsideHomeExplanation",
    "employed",
    "employmentType",
    "employer",
    "jobTitle",
    "contactName",
    "jobPhone",
    "hasAnotherJob",
    "otherEmployer",
    "otherJobTitle",
    "otherContactName",
    "otherJobPhone"
})
public class HFAdultDetails {

    protected int hostfamilyMemberId;
    @XmlElement(required = true)
    protected String relationship;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String birthdate;
    protected int genderId;
    @XmlElement(required = true)
    protected String email;
    protected boolean isHostParent;
    @XmlElement(required = true)
    protected String personalPhone;
    @XmlElement(required = true)
    protected String activitiesOrInterests;
    @XmlElement(required = true)
    protected String educationLevel;
    @XmlElement(required = true)
    protected String communityInvolvement;
    protected boolean singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome;
    @XmlElement(required = true)
    protected String residencyTime;
    @XmlElement(required = true)
    protected String livingInsideHomeExplanation;
    @XmlElement(required = true)
    protected String employed;
    @XmlElement(required = true)
    protected String employmentType;
    @XmlElement(required = true)
    protected String employer;
    @XmlElement(required = true)
    protected String jobTitle;
    @XmlElement(required = true)
    protected String contactName;
    @XmlElement(required = true)
    protected String jobPhone;
    protected boolean hasAnotherJob;
    @XmlElement(required = true)
    protected String otherEmployer;
    @XmlElement(required = true)
    protected String otherJobTitle;
    @XmlElement(required = true)
    protected String otherContactName;
    @XmlElement(required = true)
    protected String otherJobPhone;

    /**
     * Gets the value of the hostfamilyMemberId property.
     * 
     */
    public int getHostfamilyMemberId() {
        return hostfamilyMemberId;
    }

    /**
     * Sets the value of the hostfamilyMemberId property.
     * 
     */
    public void setHostfamilyMemberId(int value) {
        this.hostfamilyMemberId = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationship(String value) {
        this.relationship = value;
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
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthdate(String value) {
        this.birthdate = value;
    }

    /**
     * Gets the value of the genderId property.
     * 
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * Sets the value of the genderId property.
     * 
     */
    public void setGenderId(int value) {
        this.genderId = value;
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
     * Gets the value of the isHostParent property.
     * 
     */
    public boolean isIsHostParent() {
        return isHostParent;
    }

    /**
     * Sets the value of the isHostParent property.
     * 
     */
    public void setIsHostParent(boolean value) {
        this.isHostParent = value;
    }

    /**
     * Gets the value of the personalPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonalPhone() {
        return personalPhone;
    }

    /**
     * Sets the value of the personalPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonalPhone(String value) {
        this.personalPhone = value;
    }

    /**
     * Gets the value of the activitiesOrInterests property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivitiesOrInterests() {
        return activitiesOrInterests;
    }

    /**
     * Sets the value of the activitiesOrInterests property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivitiesOrInterests(String value) {
        this.activitiesOrInterests = value;
    }

    /**
     * Gets the value of the educationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducationLevel() {
        return educationLevel;
    }

    /**
     * Sets the value of the educationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducationLevel(String value) {
        this.educationLevel = value;
    }

    /**
     * Gets the value of the communityInvolvement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunityInvolvement() {
        return communityInvolvement;
    }

    /**
     * Sets the value of the communityInvolvement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunityInvolvement(String value) {
        this.communityInvolvement = value;
    }

    /**
     * Gets the value of the singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome property.
     * 
     */
    public boolean isSingleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome() {
        return singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome;
    }

    /**
     * Sets the value of the singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome property.
     * 
     */
    public void setSingleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome(boolean value) {
        this.singleAdultWithnoRelatedByBloodOrMarrageOrAdoptionLivingHome = value;
    }

    /**
     * Gets the value of the residencyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidencyTime() {
        return residencyTime;
    }

    /**
     * Sets the value of the residencyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidencyTime(String value) {
        this.residencyTime = value;
    }

    /**
     * Gets the value of the livingInsideHomeExplanation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivingInsideHomeExplanation() {
        return livingInsideHomeExplanation;
    }

    /**
     * Sets the value of the livingInsideHomeExplanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivingInsideHomeExplanation(String value) {
        this.livingInsideHomeExplanation = value;
    }

    /**
     * Gets the value of the employed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployed() {
        return employed;
    }

    /**
     * Sets the value of the employed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployed(String value) {
        this.employed = value;
    }

    /**
     * Gets the value of the employmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmploymentType() {
        return employmentType;
    }

    /**
     * Sets the value of the employmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmploymentType(String value) {
        this.employmentType = value;
    }

    /**
     * Gets the value of the employer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * Sets the value of the employer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployer(String value) {
        this.employer = value;
    }

    /**
     * Gets the value of the jobTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets the value of the jobTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobTitle(String value) {
        this.jobTitle = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the jobPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobPhone() {
        return jobPhone;
    }

    /**
     * Sets the value of the jobPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobPhone(String value) {
        this.jobPhone = value;
    }

    /**
     * Gets the value of the hasAnotherJob property.
     * 
     */
    public boolean isHasAnotherJob() {
        return hasAnotherJob;
    }

    /**
     * Sets the value of the hasAnotherJob property.
     * 
     */
    public void setHasAnotherJob(boolean value) {
        this.hasAnotherJob = value;
    }

    /**
     * Gets the value of the otherEmployer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherEmployer() {
        return otherEmployer;
    }

    /**
     * Sets the value of the otherEmployer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherEmployer(String value) {
        this.otherEmployer = value;
    }

    /**
     * Gets the value of the otherJobTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherJobTitle() {
        return otherJobTitle;
    }

    /**
     * Sets the value of the otherJobTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherJobTitle(String value) {
        this.otherJobTitle = value;
    }

    /**
     * Gets the value of the otherContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherContactName() {
        return otherContactName;
    }

    /**
     * Sets the value of the otherContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherContactName(String value) {
        this.otherContactName = value;
    }

    /**
     * Gets the value of the otherJobPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherJobPhone() {
        return otherJobPhone;
    }

    /**
     * Sets the value of the otherJobPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherJobPhone(String value) {
        this.otherJobPhone = value;
    }

}
