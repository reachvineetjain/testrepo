//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.22 at 12:47:37 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="showSeasonProgramToCurrentHF" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="defaultLcPaymentSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fsAgreement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hfReferencesNo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="addOrStartHFInquiriesDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="showWelcomeFamilyModle" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowFSToStartRenewalProcess" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showSpecialRequestStudentToRD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldSettings", propOrder = {
    "seasonId",
    "showSeasonProgramToCurrentHF",
    "defaultLcPaymentSchedule",
    "fsAgreement",
    "hfReferencesNo",
    "addOrStartHFInquiriesDate",
    "showWelcomeFamilyModle",
    "allowFSToStartRenewalProcess",
    "showSpecialRequestStudentToRD"
})
public class FieldSettings {

    @XmlElement(required = true)
    protected BigInteger seasonId;
    protected Boolean showSeasonProgramToCurrentHF;
    protected String defaultLcPaymentSchedule;
    protected String fsAgreement;
    protected BigInteger hfReferencesNo;
    protected String addOrStartHFInquiriesDate;
    protected Boolean showWelcomeFamilyModle;
    protected Boolean allowFSToStartRenewalProcess;
    protected Boolean showSpecialRequestStudentToRD;

    /**
     * Gets the value of the seasonId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeasonId(BigInteger value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the showSeasonProgramToCurrentHF property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSeasonProgramToCurrentHF() {
        return showSeasonProgramToCurrentHF;
    }

    /**
     * Sets the value of the showSeasonProgramToCurrentHF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSeasonProgramToCurrentHF(Boolean value) {
        this.showSeasonProgramToCurrentHF = value;
    }

    /**
     * Gets the value of the defaultLcPaymentSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultLcPaymentSchedule() {
        return defaultLcPaymentSchedule;
    }

    /**
     * Sets the value of the defaultLcPaymentSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultLcPaymentSchedule(String value) {
        this.defaultLcPaymentSchedule = value;
    }

    /**
     * Gets the value of the fsAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsAgreement() {
        return fsAgreement;
    }

    /**
     * Sets the value of the fsAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsAgreement(String value) {
        this.fsAgreement = value;
    }

    /**
     * Gets the value of the hfReferencesNo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHfReferencesNo() {
        return hfReferencesNo;
    }

    /**
     * Sets the value of the hfReferencesNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHfReferencesNo(BigInteger value) {
        this.hfReferencesNo = value;
    }

    /**
     * Gets the value of the addOrStartHFInquiriesDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOrStartHFInquiriesDate() {
        return addOrStartHFInquiriesDate;
    }

    /**
     * Sets the value of the addOrStartHFInquiriesDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOrStartHFInquiriesDate(String value) {
        this.addOrStartHFInquiriesDate = value;
    }

    /**
     * Gets the value of the showWelcomeFamilyModle property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowWelcomeFamilyModle() {
        return showWelcomeFamilyModle;
    }

    /**
     * Sets the value of the showWelcomeFamilyModle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowWelcomeFamilyModle(Boolean value) {
        this.showWelcomeFamilyModle = value;
    }

    /**
     * Gets the value of the allowFSToStartRenewalProcess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowFSToStartRenewalProcess() {
        return allowFSToStartRenewalProcess;
    }

    /**
     * Sets the value of the allowFSToStartRenewalProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowFSToStartRenewalProcess(Boolean value) {
        this.allowFSToStartRenewalProcess = value;
    }

    /**
     * Gets the value of the showSpecialRequestStudentToRD property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSpecialRequestStudentToRD() {
        return showSpecialRequestStudentToRD;
    }

    /**
     * Sets the value of the showSpecialRequestStudentToRD property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSpecialRequestStudentToRD(Boolean value) {
        this.showSpecialRequestStudentToRD = value;
    }

}
