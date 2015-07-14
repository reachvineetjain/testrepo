//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 02:19:49 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WPCAPProgramAllocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WPCAPProgramAllocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="internshipMaximumParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="internshipExpectedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="internshipAcceptedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="internshipPendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="internshipCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="internshipRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineeMaximumParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineeExpectedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineeAcceptedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineePendingVerification" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineeCCIReview" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="traineeRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalMaximumParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalExpectedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalAcceptedParticipant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "WPCAPProgramAllocations", propOrder = {
    "seasonId",
    "seasonProgramId",
    "internshipMaximumParticipant",
    "internshipExpectedParticipant",
    "internshipAcceptedParticipant",
    "internshipPendingVerification",
    "internshipCCIReview",
    "internshipRemainingParticipants",
    "traineeMaximumParticipant",
    "traineeExpectedParticipant",
    "traineeAcceptedParticipant",
    "traineePendingVerification",
    "traineeCCIReview",
    "traineeRemainingParticipants",
    "totalMaximumParticipant",
    "totalExpectedParticipant",
    "totalAcceptedParticipant",
    "totalPendingVerification",
    "totalCCIReview",
    "totalRemainingParticipants"
})
public class WPCAPProgramAllocations {

    protected int seasonId;
    protected int seasonProgramId;
    protected Integer internshipMaximumParticipant;
    protected Integer internshipExpectedParticipant;
    protected Integer internshipAcceptedParticipant;
    protected Integer internshipPendingVerification;
    protected Integer internshipCCIReview;
    protected Integer internshipRemainingParticipants;
    protected Integer traineeMaximumParticipant;
    protected Integer traineeExpectedParticipant;
    protected Integer traineeAcceptedParticipant;
    protected Integer traineePendingVerification;
    protected Integer traineeCCIReview;
    protected Integer traineeRemainingParticipants;
    protected Integer totalMaximumParticipant;
    protected Integer totalExpectedParticipant;
    protected Integer totalAcceptedParticipant;
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
     * Gets the value of the internshipMaximumParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipMaximumParticipant() {
        return internshipMaximumParticipant;
    }

    /**
     * Sets the value of the internshipMaximumParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipMaximumParticipant(Integer value) {
        this.internshipMaximumParticipant = value;
    }

    /**
     * Gets the value of the internshipExpectedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipExpectedParticipant() {
        return internshipExpectedParticipant;
    }

    /**
     * Sets the value of the internshipExpectedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipExpectedParticipant(Integer value) {
        this.internshipExpectedParticipant = value;
    }

    /**
     * Gets the value of the internshipAcceptedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipAcceptedParticipant() {
        return internshipAcceptedParticipant;
    }

    /**
     * Sets the value of the internshipAcceptedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipAcceptedParticipant(Integer value) {
        this.internshipAcceptedParticipant = value;
    }

    /**
     * Gets the value of the internshipPendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipPendingVerification() {
        return internshipPendingVerification;
    }

    /**
     * Sets the value of the internshipPendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipPendingVerification(Integer value) {
        this.internshipPendingVerification = value;
    }

    /**
     * Gets the value of the internshipCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipCCIReview() {
        return internshipCCIReview;
    }

    /**
     * Sets the value of the internshipCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipCCIReview(Integer value) {
        this.internshipCCIReview = value;
    }

    /**
     * Gets the value of the internshipRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternshipRemainingParticipants() {
        return internshipRemainingParticipants;
    }

    /**
     * Sets the value of the internshipRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternshipRemainingParticipants(Integer value) {
        this.internshipRemainingParticipants = value;
    }

    /**
     * Gets the value of the traineeMaximumParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineeMaximumParticipant() {
        return traineeMaximumParticipant;
    }

    /**
     * Sets the value of the traineeMaximumParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineeMaximumParticipant(Integer value) {
        this.traineeMaximumParticipant = value;
    }

    /**
     * Gets the value of the traineeExpectedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineeExpectedParticipant() {
        return traineeExpectedParticipant;
    }

    /**
     * Sets the value of the traineeExpectedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineeExpectedParticipant(Integer value) {
        this.traineeExpectedParticipant = value;
    }

    /**
     * Gets the value of the traineeAcceptedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineeAcceptedParticipant() {
        return traineeAcceptedParticipant;
    }

    /**
     * Sets the value of the traineeAcceptedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineeAcceptedParticipant(Integer value) {
        this.traineeAcceptedParticipant = value;
    }

    /**
     * Gets the value of the traineePendingVerification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineePendingVerification() {
        return traineePendingVerification;
    }

    /**
     * Sets the value of the traineePendingVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineePendingVerification(Integer value) {
        this.traineePendingVerification = value;
    }

    /**
     * Gets the value of the traineeCCIReview property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineeCCIReview() {
        return traineeCCIReview;
    }

    /**
     * Sets the value of the traineeCCIReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineeCCIReview(Integer value) {
        this.traineeCCIReview = value;
    }

    /**
     * Gets the value of the traineeRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTraineeRemainingParticipants() {
        return traineeRemainingParticipants;
    }

    /**
     * Sets the value of the traineeRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTraineeRemainingParticipants(Integer value) {
        this.traineeRemainingParticipants = value;
    }

    /**
     * Gets the value of the totalMaximumParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalMaximumParticipant() {
        return totalMaximumParticipant;
    }

    /**
     * Sets the value of the totalMaximumParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalMaximumParticipant(Integer value) {
        this.totalMaximumParticipant = value;
    }

    /**
     * Gets the value of the totalExpectedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalExpectedParticipant() {
        return totalExpectedParticipant;
    }

    /**
     * Sets the value of the totalExpectedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalExpectedParticipant(Integer value) {
        this.totalExpectedParticipant = value;
    }

    /**
     * Gets the value of the totalAcceptedParticipant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalAcceptedParticipant() {
        return totalAcceptedParticipant;
    }

    /**
     * Sets the value of the totalAcceptedParticipant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalAcceptedParticipant(Integer value) {
        this.totalAcceptedParticipant = value;
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
