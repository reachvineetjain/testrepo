//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.31 at 04:13:50 PM IST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerRecruitmentAdminScreeningAdditionalInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdminScreeningAdditionalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programsYouLikeToParticipate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="interestedInTeachAbroad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interestedInVolunteerAbroad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interestedInHighSchoolAbroad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interestedInOther" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="programsYouOffer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sendPartnersToUSA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isYourOrganizationSendingParticipantstoUSA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="likeToKnowMoreAboutAmbassadorScholarship" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="yearsInBusiness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="describeProgramsOrganizationOffers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hearAboutUsFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdminScreeningAdditionalInfo", propOrder = {
    "programsYouLikeToParticipate",
    "interestedInTeachAbroad",
    "interestedInVolunteerAbroad",
    "interestedInHighSchoolAbroad",
    "interestedInOther",
    "programsYouOffer",
    "sendPartnersToUSA",
    "isYourOrganizationSendingParticipantstoUSA",
    "likeToKnowMoreAboutAmbassadorScholarship",
    "yearsInBusiness",
    "describeProgramsOrganizationOffers",
    "hearAboutUsFrom"
})
public class PartnerRecruitmentAdminScreeningAdditionalInfo {

    @XmlElement(required = true)
    protected String programsYouLikeToParticipate;
    protected boolean interestedInTeachAbroad;
    protected boolean interestedInVolunteerAbroad;
    protected boolean interestedInHighSchoolAbroad;
    protected boolean interestedInOther;
    @XmlElement(required = true)
    protected String programsYouOffer;
    protected boolean sendPartnersToUSA;
    protected boolean isYourOrganizationSendingParticipantstoUSA;
    protected boolean likeToKnowMoreAboutAmbassadorScholarship;
    protected int yearsInBusiness;
    @XmlElement(required = true)
    protected String describeProgramsOrganizationOffers;
    @XmlElement(required = true)
    protected String hearAboutUsFrom;

    /**
     * Gets the value of the programsYouLikeToParticipate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramsYouLikeToParticipate() {
        return programsYouLikeToParticipate;
    }

    /**
     * Sets the value of the programsYouLikeToParticipate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramsYouLikeToParticipate(String value) {
        this.programsYouLikeToParticipate = value;
    }

    /**
     * Gets the value of the interestedInTeachAbroad property.
     * 
     */
    public boolean isInterestedInTeachAbroad() {
        return interestedInTeachAbroad;
    }

    /**
     * Sets the value of the interestedInTeachAbroad property.
     * 
     */
    public void setInterestedInTeachAbroad(boolean value) {
        this.interestedInTeachAbroad = value;
    }

    /**
     * Gets the value of the interestedInVolunteerAbroad property.
     * 
     */
    public boolean isInterestedInVolunteerAbroad() {
        return interestedInVolunteerAbroad;
    }

    /**
     * Sets the value of the interestedInVolunteerAbroad property.
     * 
     */
    public void setInterestedInVolunteerAbroad(boolean value) {
        this.interestedInVolunteerAbroad = value;
    }

    /**
     * Gets the value of the interestedInHighSchoolAbroad property.
     * 
     */
    public boolean isInterestedInHighSchoolAbroad() {
        return interestedInHighSchoolAbroad;
    }

    /**
     * Sets the value of the interestedInHighSchoolAbroad property.
     * 
     */
    public void setInterestedInHighSchoolAbroad(boolean value) {
        this.interestedInHighSchoolAbroad = value;
    }

    /**
     * Gets the value of the interestedInOther property.
     * 
     */
    public boolean isInterestedInOther() {
        return interestedInOther;
    }

    /**
     * Sets the value of the interestedInOther property.
     * 
     */
    public void setInterestedInOther(boolean value) {
        this.interestedInOther = value;
    }

    /**
     * Gets the value of the programsYouOffer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramsYouOffer() {
        return programsYouOffer;
    }

    /**
     * Sets the value of the programsYouOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramsYouOffer(String value) {
        this.programsYouOffer = value;
    }

    /**
     * Gets the value of the sendPartnersToUSA property.
     * 
     */
    public boolean isSendPartnersToUSA() {
        return sendPartnersToUSA;
    }

    /**
     * Sets the value of the sendPartnersToUSA property.
     * 
     */
    public void setSendPartnersToUSA(boolean value) {
        this.sendPartnersToUSA = value;
    }

    /**
     * Gets the value of the isYourOrganizationSendingParticipantstoUSA property.
     * 
     */
    public boolean isIsYourOrganizationSendingParticipantstoUSA() {
        return isYourOrganizationSendingParticipantstoUSA;
    }

    /**
     * Sets the value of the isYourOrganizationSendingParticipantstoUSA property.
     * 
     */
    public void setIsYourOrganizationSendingParticipantstoUSA(boolean value) {
        this.isYourOrganizationSendingParticipantstoUSA = value;
    }

    /**
     * Gets the value of the likeToKnowMoreAboutAmbassadorScholarship property.
     * 
     */
    public boolean isLikeToKnowMoreAboutAmbassadorScholarship() {
        return likeToKnowMoreAboutAmbassadorScholarship;
    }

    /**
     * Sets the value of the likeToKnowMoreAboutAmbassadorScholarship property.
     * 
     */
    public void setLikeToKnowMoreAboutAmbassadorScholarship(boolean value) {
        this.likeToKnowMoreAboutAmbassadorScholarship = value;
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
     * Gets the value of the describeProgramsOrganizationOffers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescribeProgramsOrganizationOffers() {
        return describeProgramsOrganizationOffers;
    }

    /**
     * Sets the value of the describeProgramsOrganizationOffers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescribeProgramsOrganizationOffers(String value) {
        this.describeProgramsOrganizationOffers = value;
    }

    /**
     * Gets the value of the hearAboutUsFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHearAboutUsFrom() {
        return hearAboutUsFrom;
    }

    /**
     * Sets the value of the hearAboutUsFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHearAboutUsFrom(String value) {
        this.hearAboutUsFrom = value;
    }

}
