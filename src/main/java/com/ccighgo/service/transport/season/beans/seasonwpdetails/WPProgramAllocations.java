//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:48 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonwpdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for WPProgramAllocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WPProgramAllocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jobFairMaxParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jobFairExpectedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jobFairAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jobFairPendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jobFairCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jobFairRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedMaxParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedExpectedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedPendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="selfPlacedRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntMaxParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntExpectedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntPendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="directPlcmntRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalMaxParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalExpectedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalPendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WPProgramAllocations", propOrder = {
    "seasonId",
    "seasonProgramId",
    "jobFairMaxParticipants",
    "jobFairExpectedParticipants",
    "jobFairAcceptedParticipants",
    "jobFairPendingVerification",
    "jobFairCCIReview",
    "jobFairRemainingParticipants",
    "selfPlacedMaxParticipants",
    "selfPlacedExpectedParticipants",
    "selfPlacedAcceptedParticipants",
    "selfPlacedPendingVerification",
    "selfPlacedCCIReview",
    "selfPlacedRemainingParticipants",
    "directPlcmntMaxParticipants",
    "directPlcmntExpectedParticipants",
    "directPlcmntAcceptedParticipants",
    "directPlcmntPendingVerification",
    "directPlcmntCCIReview",
    "directPlcmntRemainingParticipants",
    "totalMaxParticipants",
    "totalExpectedParticipants",
    "totalAcceptedParticipants",
    "totalPendingVerification",
    "totalCCIReview",
    "totalRemainingParticipants"
})
public class WPProgramAllocations extends Response{

    protected int seasonId;
    protected int seasonProgramId;
    protected Integer jobFairMaxParticipants;
    protected Integer jobFairExpectedParticipants;
    protected Integer jobFairAcceptedParticipants;
    protected Integer jobFairPendingVerification;
    protected Integer jobFairCCIReview;
    protected Integer jobFairRemainingParticipants;
    protected Integer selfPlacedMaxParticipants;
    protected Integer selfPlacedExpectedParticipants;
    protected Integer selfPlacedAcceptedParticipants;
    protected Integer selfPlacedPendingVerification;
    protected Integer selfPlacedCCIReview;
    protected Integer selfPlacedRemainingParticipants;
    protected Integer directPlcmntMaxParticipants;
    protected Integer directPlcmntExpectedParticipants;
    protected Integer directPlcmntAcceptedParticipants;
    protected Integer directPlcmntPendingVerification;
    protected Integer directPlcmntCCIReview;
    protected Integer directPlcmntRemainingParticipants;
    protected Integer totalMaxParticipants;
    protected Integer totalExpectedParticipants;
    protected Integer totalAcceptedParticipants;
    protected Integer totalPendingVerification;
    protected Integer totalCCIReview;
    protected Integer totalRemainingParticipants;

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
     * Gets the value of the jobFairMaxParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairMaxParticipants() {
        return jobFairMaxParticipants;
    }

    /**
     * Sets the value of the jobFairMaxParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairMaxParticipants(Integer value) {
        this.jobFairMaxParticipants = value;
    }

    /**
     * Gets the value of the jobFairExpectedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairExpectedParticipants() {
        return jobFairExpectedParticipants;
    }

    /**
     * Sets the value of the jobFairExpectedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairExpectedParticipants(Integer value) {
        this.jobFairExpectedParticipants = value;
    }

    /**
     * Gets the value of the jobFairAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairAcceptedParticipants() {
        return jobFairAcceptedParticipants;
    }

    /**
     * Sets the value of the jobFairAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairAcceptedParticipants(Integer value) {
        this.jobFairAcceptedParticipants = value;
    }

    /**
     * Gets the value of the jobFairPendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairPendingVerification() {
        return jobFairPendingVerification;
    }

    /**
     * Sets the value of the jobFairPendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairPendingVerification(Integer value) {
        this.jobFairPendingVerification = value;
    }

    /**
     * Gets the value of the jobFairCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairCCIReview() {
        return jobFairCCIReview;
    }

    /**
     * Sets the value of the jobFairCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairCCIReview(Integer value) {
        this.jobFairCCIReview = value;
    }

    /**
     * Gets the value of the jobFairRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobFairRemainingParticipants() {
        return jobFairRemainingParticipants;
    }

    /**
     * Sets the value of the jobFairRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobFairRemainingParticipants(Integer value) {
        this.jobFairRemainingParticipants = value;
    }

    /**
     * Gets the value of the selfPlacedMaxParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedMaxParticipants() {
        return selfPlacedMaxParticipants;
    }

    /**
     * Sets the value of the selfPlacedMaxParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedMaxParticipants(Integer value) {
        this.selfPlacedMaxParticipants = value;
    }

    /**
     * Gets the value of the selfPlacedExpectedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedExpectedParticipants() {
        return selfPlacedExpectedParticipants;
    }

    /**
     * Sets the value of the selfPlacedExpectedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedExpectedParticipants(Integer value) {
        this.selfPlacedExpectedParticipants = value;
    }

    /**
     * Gets the value of the selfPlacedAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedAcceptedParticipants() {
        return selfPlacedAcceptedParticipants;
    }

    /**
     * Sets the value of the selfPlacedAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedAcceptedParticipants(Integer value) {
        this.selfPlacedAcceptedParticipants = value;
    }

    /**
     * Gets the value of the selfPlacedPendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedPendingVerification() {
        return selfPlacedPendingVerification;
    }

    /**
     * Sets the value of the selfPlacedPendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedPendingVerification(Integer value) {
        this.selfPlacedPendingVerification = value;
    }

    /**
     * Gets the value of the selfPlacedCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedCCIReview() {
        return selfPlacedCCIReview;
    }

    /**
     * Sets the value of the selfPlacedCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedCCIReview(Integer value) {
        this.selfPlacedCCIReview = value;
    }

    /**
     * Gets the value of the selfPlacedRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSelfPlacedRemainingParticipants() {
        return selfPlacedRemainingParticipants;
    }

    /**
     * Sets the value of the selfPlacedRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSelfPlacedRemainingParticipants(Integer value) {
        this.selfPlacedRemainingParticipants = value;
    }

    /**
     * Gets the value of the directPlcmntMaxParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntMaxParticipants() {
        return directPlcmntMaxParticipants;
    }

    /**
     * Sets the value of the directPlcmntMaxParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntMaxParticipants(Integer value) {
        this.directPlcmntMaxParticipants = value;
    }

    /**
     * Gets the value of the directPlcmntExpectedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntExpectedParticipants() {
        return directPlcmntExpectedParticipants;
    }

    /**
     * Sets the value of the directPlcmntExpectedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntExpectedParticipants(Integer value) {
        this.directPlcmntExpectedParticipants = value;
    }

    /**
     * Gets the value of the directPlcmntAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntAcceptedParticipants() {
        return directPlcmntAcceptedParticipants;
    }

    /**
     * Sets the value of the directPlcmntAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntAcceptedParticipants(Integer value) {
        this.directPlcmntAcceptedParticipants = value;
    }

    /**
     * Gets the value of the directPlcmntPendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntPendingVerification() {
        return directPlcmntPendingVerification;
    }

    /**
     * Sets the value of the directPlcmntPendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntPendingVerification(Integer value) {
        this.directPlcmntPendingVerification = value;
    }

    /**
     * Gets the value of the directPlcmntCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntCCIReview() {
        return directPlcmntCCIReview;
    }

    /**
     * Sets the value of the directPlcmntCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntCCIReview(Integer value) {
        this.directPlcmntCCIReview = value;
    }

    /**
     * Gets the value of the directPlcmntRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectPlcmntRemainingParticipants() {
        return directPlcmntRemainingParticipants;
    }

    /**
     * Sets the value of the directPlcmntRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectPlcmntRemainingParticipants(Integer value) {
        this.directPlcmntRemainingParticipants = value;
    }

    /**
     * Gets the value of the totalMaxParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalMaxParticipants() {
        return totalMaxParticipants;
    }

    /**
     * Sets the value of the totalMaxParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalMaxParticipants(Integer value) {
        this.totalMaxParticipants = value;
    }

    /**
     * Gets the value of the totalExpectedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalExpectedParticipants() {
        return totalExpectedParticipants;
    }

    /**
     * Sets the value of the totalExpectedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalExpectedParticipants(Integer value) {
        this.totalExpectedParticipants = value;
    }

    /**
     * Gets the value of the totalAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalAcceptedParticipants() {
        return totalAcceptedParticipants;
    }

    /**
     * Sets the value of the totalAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalAcceptedParticipants(Integer value) {
        this.totalAcceptedParticipants = value;
    }

    /**
     * Gets the value of the totalPendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalPendingVerification() {
        return totalPendingVerification;
    }

    /**
     * Sets the value of the totalPendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalPendingVerification(Integer value) {
        this.totalPendingVerification = value;
    }

    /**
     * Gets the value of the totalCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalCCIReview() {
        return totalCCIReview;
    }

    /**
     * Sets the value of the totalCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCCIReview(Integer value) {
        this.totalCCIReview = value;
    }

    /**
     * Gets the value of the totalRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalRemainingParticipants() {
        return totalRemainingParticipants;
    }

    /**
     * Sets the value of the totalRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalRemainingParticipants(Integer value) {
        this.totalRemainingParticipants = value;
    }

}
