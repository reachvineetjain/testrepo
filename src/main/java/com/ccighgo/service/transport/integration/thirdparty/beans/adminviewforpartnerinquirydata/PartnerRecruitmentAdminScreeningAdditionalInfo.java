//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.12 at 05:36:08 PM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

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
 *         &lt;element name="likeToKnowMoreAboutAmbassadorScholarship" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sendPartnersToUSA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="yearsInBusiness" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "likeToKnowMoreAboutAmbassadorScholarship",
    "sendPartnersToUSA",
    "yearsInBusiness",
    "hearAboutUsFrom"
})
public class PartnerRecruitmentAdminScreeningAdditionalInfo {

    protected boolean likeToKnowMoreAboutAmbassadorScholarship;
    protected boolean sendPartnersToUSA;
    protected int yearsInBusiness;
    @XmlElement(required = true)
    protected String hearAboutUsFrom;

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
