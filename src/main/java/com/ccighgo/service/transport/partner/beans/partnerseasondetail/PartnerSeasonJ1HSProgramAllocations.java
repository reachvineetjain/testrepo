//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.03 at 10:25:37 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasondetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerSeasonJ1HSProgramAllocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerSeasonJ1HSProgramAllocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartRequestedMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartRequestedMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartUnderCCIReview" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartOpenings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="januaryStartMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartRequestedMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartRequestedMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartUnderCCIReview" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartOpenings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="januaryStartStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalUnderCCIReview" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalOpenings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonJ1HSProgramAllocations", propOrder = {
    "seasonId",
    "seasonProgramId",
    "augustStartMaxUnguaranteedParticipants",
    "augustStartRequestedMaxUnguaranteedParticipants",
    "augustStartMaxguaranteedParticipants",
    "augustStartRequestedMaxguaranteedParticipants",
    "augustStartAcceptedParticipants",
    "augustStartUnderCCIReview",
    "augustStartOpenings",
    "augustStartStatus",
    "januaryStartMaxUnguaranteedParticipants",
    "januaryStartRequestedMaxUnguaranteedParticipants",
    "januaryStartMaxguaranteedParticipants",
    "januaryStartRequestedMaxguaranteedParticipants",
    "januaryStartAcceptedParticipants",
    "januaryStartUnderCCIReview",
    "januaryStartOpenings",
    "januaryStartStatus",
    "totalMaxUnguaranteedParticipants",
    "totalMaxguaranteedParticipants",
    "totalAcceptedParticipants",
    "totalUnderCCIReview",
    "totalOpenings"
})
public class PartnerSeasonJ1HSProgramAllocations {

    protected int seasonId;
    protected int seasonProgramId;
    protected int augustStartMaxUnguaranteedParticipants;
    protected int augustStartRequestedMaxUnguaranteedParticipants;
    protected int augustStartMaxguaranteedParticipants;
    protected int augustStartRequestedMaxguaranteedParticipants;
    protected int augustStartAcceptedParticipants;
    protected int augustStartUnderCCIReview;
    protected int augustStartOpenings;
    @XmlElement(required = true)
    protected String augustStartStatus;
    protected int januaryStartMaxUnguaranteedParticipants;
    protected int januaryStartRequestedMaxUnguaranteedParticipants;
    protected int januaryStartMaxguaranteedParticipants;
    protected int januaryStartRequestedMaxguaranteedParticipants;
    protected int januaryStartAcceptedParticipants;
    protected int januaryStartUnderCCIReview;
    protected int januaryStartOpenings;
    @XmlElement(required = true)
    protected String januaryStartStatus;
    protected int totalMaxUnguaranteedParticipants;
    protected int totalMaxguaranteedParticipants;
    protected int totalAcceptedParticipants;
    protected int totalUnderCCIReview;
    protected int totalOpenings;

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
     * Gets the value of the augustStartMaxUnguaranteedParticipants property.
     * 
     */
    public int getAugustStartMaxUnguaranteedParticipants() {
        return augustStartMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartMaxUnguaranteedParticipants property.
     * 
     */
    public void setAugustStartMaxUnguaranteedParticipants(int value) {
        this.augustStartMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the augustStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public int getAugustStartRequestedMaxUnguaranteedParticipants() {
        return augustStartRequestedMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public void setAugustStartRequestedMaxUnguaranteedParticipants(int value) {
        this.augustStartRequestedMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the augustStartMaxguaranteedParticipants property.
     * 
     */
    public int getAugustStartMaxguaranteedParticipants() {
        return augustStartMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartMaxguaranteedParticipants property.
     * 
     */
    public void setAugustStartMaxguaranteedParticipants(int value) {
        this.augustStartMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the augustStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public int getAugustStartRequestedMaxguaranteedParticipants() {
        return augustStartRequestedMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public void setAugustStartRequestedMaxguaranteedParticipants(int value) {
        this.augustStartRequestedMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the augustStartAcceptedParticipants property.
     * 
     */
    public int getAugustStartAcceptedParticipants() {
        return augustStartAcceptedParticipants;
    }

    /**
     * Sets the value of the augustStartAcceptedParticipants property.
     * 
     */
    public void setAugustStartAcceptedParticipants(int value) {
        this.augustStartAcceptedParticipants = value;
    }

    /**
     * Gets the value of the augustStartUnderCCIReview property.
     * 
     */
    public int getAugustStartUnderCCIReview() {
        return augustStartUnderCCIReview;
    }

    /**
     * Sets the value of the augustStartUnderCCIReview property.
     * 
     */
    public void setAugustStartUnderCCIReview(int value) {
        this.augustStartUnderCCIReview = value;
    }

    /**
     * Gets the value of the augustStartOpenings property.
     * 
     */
    public int getAugustStartOpenings() {
        return augustStartOpenings;
    }

    /**
     * Sets the value of the augustStartOpenings property.
     * 
     */
    public void setAugustStartOpenings(int value) {
        this.augustStartOpenings = value;
    }

    /**
     * Gets the value of the augustStartStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAugustStartStatus() {
        return augustStartStatus;
    }

    /**
     * Sets the value of the augustStartStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAugustStartStatus(String value) {
        this.augustStartStatus = value;
    }

    /**
     * Gets the value of the januaryStartMaxUnguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartMaxUnguaranteedParticipants() {
        return januaryStartMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartMaxUnguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartMaxUnguaranteedParticipants(int value) {
        this.januaryStartMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartRequestedMaxUnguaranteedParticipants() {
        return januaryStartRequestedMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartRequestedMaxUnguaranteedParticipants(int value) {
        this.januaryStartRequestedMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartMaxguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartMaxguaranteedParticipants() {
        return januaryStartMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartMaxguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartMaxguaranteedParticipants(int value) {
        this.januaryStartMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartRequestedMaxguaranteedParticipants() {
        return januaryStartRequestedMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartRequestedMaxguaranteedParticipants(int value) {
        this.januaryStartRequestedMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartAcceptedParticipants property.
     * 
     */
    public int getJanuaryStartAcceptedParticipants() {
        return januaryStartAcceptedParticipants;
    }

    /**
     * Sets the value of the januaryStartAcceptedParticipants property.
     * 
     */
    public void setJanuaryStartAcceptedParticipants(int value) {
        this.januaryStartAcceptedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartUnderCCIReview property.
     * 
     */
    public int getJanuaryStartUnderCCIReview() {
        return januaryStartUnderCCIReview;
    }

    /**
     * Sets the value of the januaryStartUnderCCIReview property.
     * 
     */
    public void setJanuaryStartUnderCCIReview(int value) {
        this.januaryStartUnderCCIReview = value;
    }

    /**
     * Gets the value of the januaryStartOpenings property.
     * 
     */
    public int getJanuaryStartOpenings() {
        return januaryStartOpenings;
    }

    /**
     * Sets the value of the januaryStartOpenings property.
     * 
     */
    public void setJanuaryStartOpenings(int value) {
        this.januaryStartOpenings = value;
    }

    /**
     * Gets the value of the januaryStartStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJanuaryStartStatus() {
        return januaryStartStatus;
    }

    /**
     * Sets the value of the januaryStartStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJanuaryStartStatus(String value) {
        this.januaryStartStatus = value;
    }

    /**
     * Gets the value of the totalMaxUnguaranteedParticipants property.
     * 
     */
    public int getTotalMaxUnguaranteedParticipants() {
        return totalMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the totalMaxUnguaranteedParticipants property.
     * 
     */
    public void setTotalMaxUnguaranteedParticipants(int value) {
        this.totalMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the totalMaxguaranteedParticipants property.
     * 
     */
    public int getTotalMaxguaranteedParticipants() {
        return totalMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the totalMaxguaranteedParticipants property.
     * 
     */
    public void setTotalMaxguaranteedParticipants(int value) {
        this.totalMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the totalAcceptedParticipants property.
     * 
     */
    public int getTotalAcceptedParticipants() {
        return totalAcceptedParticipants;
    }

    /**
     * Sets the value of the totalAcceptedParticipants property.
     * 
     */
    public void setTotalAcceptedParticipants(int value) {
        this.totalAcceptedParticipants = value;
    }

    /**
     * Gets the value of the totalUnderCCIReview property.
     * 
     */
    public int getTotalUnderCCIReview() {
        return totalUnderCCIReview;
    }

    /**
     * Sets the value of the totalUnderCCIReview property.
     * 
     */
    public void setTotalUnderCCIReview(int value) {
        this.totalUnderCCIReview = value;
    }

    /**
     * Gets the value of the totalOpenings property.
     * 
     */
    public int getTotalOpenings() {
        return totalOpenings;
    }

    /**
     * Sets the value of the totalOpenings property.
     * 
     */
    public void setTotalOpenings(int value) {
        this.totalOpenings = value;
    }

}
