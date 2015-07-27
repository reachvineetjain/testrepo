//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.24 at 07:15:55 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for J1HSFieldSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="J1HSFieldSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="showSeasProgToCurrentHF" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fsHoldDayLength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="holdExpirationWarning" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultLCPaymentSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fsAgreement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hfReferences" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addStartHFInquiriesDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="showWelcomeFamilyModal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showAllGuranteedParticipantsToFS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showAllUnGuranteedParticipantsToFS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showSpecialRequestStudentsToRD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "J1HSFieldSettings", propOrder = {
    "seasonId",
    "seasonProgramId",
    "showSeasProgToCurrentHF",
    "fsHoldDayLength",
    "holdExpirationWarning",
    "defaultLCPaymentSchedule",
    "fsAgreement",
    "hfReferences",
    "addStartHFInquiriesDate",
    "showWelcomeFamilyModal",
    "showAllGuranteedParticipantsToFS",
    "showAllUnGuranteedParticipantsToFS",
    "showSpecialRequestStudentsToRD"
})
public class J1HSFieldSettings {

    protected int seasonId;
    protected int seasonProgramId;
    protected Boolean showSeasProgToCurrentHF;
    protected String fsHoldDayLength;
    protected String holdExpirationWarning;
    protected String defaultLCPaymentSchedule;
    protected String fsAgreement;
    protected String hfReferences;
    protected String addStartHFInquiriesDate;
    protected Boolean showWelcomeFamilyModal;
    protected Boolean showAllGuranteedParticipantsToFS;
    protected Boolean showAllUnGuranteedParticipantsToFS;
    protected Boolean showSpecialRequestStudentsToRD;

    /**
     * Gets the value of the seasonId property.
     * 
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     */
    public void setSeasonId(int value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the seasonProgramId property.
     * 
     */
    public int getSeasonProgramId() {
        return seasonProgramId;
    }

    /**
     * Sets the value of the seasonProgramId property.
     * 
     */
    public void setSeasonProgramId(int value) {
        this.seasonProgramId = value;
    }

    /**
     * Gets the value of the showSeasProgToCurrentHF property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSeasProgToCurrentHF() {
        return showSeasProgToCurrentHF;
    }

    /**
     * Sets the value of the showSeasProgToCurrentHF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSeasProgToCurrentHF(Boolean value) {
        this.showSeasProgToCurrentHF = value;
    }

    /**
     * Gets the value of the fsHoldDayLength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsHoldDayLength() {
        return fsHoldDayLength;
    }

    /**
     * Sets the value of the fsHoldDayLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsHoldDayLength(String value) {
        this.fsHoldDayLength = value;
    }

    /**
     * Gets the value of the holdExpirationWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldExpirationWarning() {
        return holdExpirationWarning;
    }

    /**
     * Sets the value of the holdExpirationWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldExpirationWarning(String value) {
        this.holdExpirationWarning = value;
    }

    /**
     * Gets the value of the defaultLCPaymentSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultLCPaymentSchedule() {
        return defaultLCPaymentSchedule;
    }

    /**
     * Sets the value of the defaultLCPaymentSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultLCPaymentSchedule(String value) {
        this.defaultLCPaymentSchedule = value;
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
     * Gets the value of the hfReferences property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHfReferences() {
        return hfReferences;
    }

    /**
     * Sets the value of the hfReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHfReferences(String value) {
        this.hfReferences = value;
    }

    /**
     * Gets the value of the addStartHFInquiriesDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddStartHFInquiriesDate() {
        return addStartHFInquiriesDate;
    }

    /**
     * Sets the value of the addStartHFInquiriesDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddStartHFInquiriesDate(String value) {
        this.addStartHFInquiriesDate = value;
    }

    /**
     * Gets the value of the showWelcomeFamilyModal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowWelcomeFamilyModal() {
        return showWelcomeFamilyModal;
    }

    /**
     * Sets the value of the showWelcomeFamilyModal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowWelcomeFamilyModal(Boolean value) {
        this.showWelcomeFamilyModal = value;
    }

    /**
     * Gets the value of the showAllGuranteedParticipantsToFS property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowAllGuranteedParticipantsToFS() {
        return showAllGuranteedParticipantsToFS;
    }

    /**
     * Sets the value of the showAllGuranteedParticipantsToFS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowAllGuranteedParticipantsToFS(Boolean value) {
        this.showAllGuranteedParticipantsToFS = value;
    }

    /**
     * Gets the value of the showAllUnGuranteedParticipantsToFS property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowAllUnGuranteedParticipantsToFS() {
        return showAllUnGuranteedParticipantsToFS;
    }

    /**
     * Sets the value of the showAllUnGuranteedParticipantsToFS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowAllUnGuranteedParticipantsToFS(Boolean value) {
        this.showAllUnGuranteedParticipantsToFS = value;
    }

    /**
     * Gets the value of the showSpecialRequestStudentsToRD property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSpecialRequestStudentsToRD() {
        return showSpecialRequestStudentsToRD;
    }

    /**
     * Sets the value of the showSpecialRequestStudentsToRD property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSpecialRequestStudentsToRD(Boolean value) {
        this.showSpecialRequestStudentsToRD = value;
    }

}
