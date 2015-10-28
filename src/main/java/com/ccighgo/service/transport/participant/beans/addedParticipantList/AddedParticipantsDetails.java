//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.27 at 03:40:33 PM CDT 
//


package com.ccighgo.service.transport.participant.beans.addedParticipantList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddedParticipantsDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddedParticipantsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="participantGoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantlastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantPicUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantSeasonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantProgramOptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantProgramOption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantCountryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subPartnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantPlacementStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantPlacementStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantApplicationStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantApplicationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="participantGuranteed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="participantSubmittedFlightInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddedParticipantsDetails", propOrder = {
    "participantGoId",
    "participantType",
    "participantFirstName",
    "participantlastName",
    "participantPicUrl",
    "participantSeasonId",
    "participantSeasonName",
    "participantProgramOptionId",
    "participantProgramOption",
    "participantEmail",
    "participantStartDate",
    "participantEndDate",
    "participantCountryId",
    "participantCountry",
    "subPartnerGoId",
    "participantPlacementStatusId",
    "participantPlacementStatus",
    "participantApplicationStatusId",
    "participantApplicationStatus",
    "participantGuranteed",
    "participantSubmittedFlightInfo",
    "active"
})
public class AddedParticipantsDetails {

    @XmlElement(required = true)
    protected String participantGoId;
    @XmlElement(required = true)
    protected String participantType;
    @XmlElement(required = true)
    protected String participantFirstName;
    @XmlElement(required = true)
    protected String participantlastName;
    @XmlElement(required = true)
    protected String participantPicUrl;
    protected int participantSeasonId;
    @XmlElement(required = true)
    protected String participantSeasonName;
    protected int participantProgramOptionId;
    @XmlElement(required = true)
    protected String participantProgramOption;
    @XmlElement(required = true)
    protected String participantEmail;
    @XmlElement(required = true)
    protected String participantStartDate;
    @XmlElement(required = true)
    protected String participantEndDate;
    protected int participantCountryId;
    @XmlElement(required = true)
    protected String participantCountry;
    protected int subPartnerGoId;
    protected int participantPlacementStatusId;
    @XmlElement(required = true)
    protected String participantPlacementStatus;
    protected int participantApplicationStatusId;
    @XmlElement(required = true)
    protected String participantApplicationStatus;
    protected boolean participantGuranteed;
    protected boolean participantSubmittedFlightInfo;
    protected boolean active;

    /**
     * Gets the value of the participantGoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantGoId() {
        return participantGoId;
    }

    /**
     * Sets the value of the participantGoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantGoId(String value) {
        this.participantGoId = value;
    }

    /**
     * Gets the value of the participantType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantType() {
        return participantType;
    }

    /**
     * Sets the value of the participantType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantType(String value) {
        this.participantType = value;
    }

    /**
     * Gets the value of the participantFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantFirstName() {
        return participantFirstName;
    }

    /**
     * Sets the value of the participantFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantFirstName(String value) {
        this.participantFirstName = value;
    }

    /**
     * Gets the value of the participantlastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantlastName() {
        return participantlastName;
    }

    /**
     * Sets the value of the participantlastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantlastName(String value) {
        this.participantlastName = value;
    }

    /**
     * Gets the value of the participantPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantPicUrl() {
        return participantPicUrl;
    }

    /**
     * Sets the value of the participantPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantPicUrl(String value) {
        this.participantPicUrl = value;
    }

    /**
     * Gets the value of the participantSeasonId property.
     * 
     */
    public int getParticipantSeasonId() {
        return participantSeasonId;
    }

    /**
     * Sets the value of the participantSeasonId property.
     * 
     */
    public void setParticipantSeasonId(int value) {
        this.participantSeasonId = value;
    }

    /**
     * Gets the value of the participantSeasonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantSeasonName() {
        return participantSeasonName;
    }

    /**
     * Sets the value of the participantSeasonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantSeasonName(String value) {
        this.participantSeasonName = value;
    }

    /**
     * Gets the value of the participantProgramOptionId property.
     * 
     */
    public int getParticipantProgramOptionId() {
        return participantProgramOptionId;
    }

    /**
     * Sets the value of the participantProgramOptionId property.
     * 
     */
    public void setParticipantProgramOptionId(int value) {
        this.participantProgramOptionId = value;
    }

    /**
     * Gets the value of the participantProgramOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantProgramOption() {
        return participantProgramOption;
    }

    /**
     * Sets the value of the participantProgramOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantProgramOption(String value) {
        this.participantProgramOption = value;
    }

    /**
     * Gets the value of the participantEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantEmail() {
        return participantEmail;
    }

    /**
     * Sets the value of the participantEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantEmail(String value) {
        this.participantEmail = value;
    }

    /**
     * Gets the value of the participantStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantStartDate() {
        return participantStartDate;
    }

    /**
     * Sets the value of the participantStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantStartDate(String value) {
        this.participantStartDate = value;
    }

    /**
     * Gets the value of the participantEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantEndDate() {
        return participantEndDate;
    }

    /**
     * Sets the value of the participantEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantEndDate(String value) {
        this.participantEndDate = value;
    }

    /**
     * Gets the value of the participantCountryId property.
     * 
     */
    public int getParticipantCountryId() {
        return participantCountryId;
    }

    /**
     * Sets the value of the participantCountryId property.
     * 
     */
    public void setParticipantCountryId(int value) {
        this.participantCountryId = value;
    }

    /**
     * Gets the value of the participantCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantCountry() {
        return participantCountry;
    }

    /**
     * Sets the value of the participantCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantCountry(String value) {
        this.participantCountry = value;
    }

    /**
     * Gets the value of the subPartnerGoId property.
     * 
     */
    public int getSubPartnerGoId() {
        return subPartnerGoId;
    }

    /**
     * Sets the value of the subPartnerGoId property.
     * 
     */
    public void setSubPartnerGoId(int value) {
        this.subPartnerGoId = value;
    }

    /**
     * Gets the value of the participantPlacementStatusId property.
     * 
     */
    public int getParticipantPlacementStatusId() {
        return participantPlacementStatusId;
    }

    /**
     * Sets the value of the participantPlacementStatusId property.
     * 
     */
    public void setParticipantPlacementStatusId(int value) {
        this.participantPlacementStatusId = value;
    }

    /**
     * Gets the value of the participantPlacementStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantPlacementStatus() {
        return participantPlacementStatus;
    }

    /**
     * Sets the value of the participantPlacementStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantPlacementStatus(String value) {
        this.participantPlacementStatus = value;
    }

    /**
     * Gets the value of the participantApplicationStatusId property.
     * 
     */
    public int getParticipantApplicationStatusId() {
        return participantApplicationStatusId;
    }

    /**
     * Sets the value of the participantApplicationStatusId property.
     * 
     */
    public void setParticipantApplicationStatusId(int value) {
        this.participantApplicationStatusId = value;
    }

    /**
     * Gets the value of the participantApplicationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantApplicationStatus() {
        return participantApplicationStatus;
    }

    /**
     * Sets the value of the participantApplicationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantApplicationStatus(String value) {
        this.participantApplicationStatus = value;
    }

    /**
     * Gets the value of the participantGuranteed property.
     * 
     */
    public boolean isParticipantGuranteed() {
        return participantGuranteed;
    }

    /**
     * Sets the value of the participantGuranteed property.
     * 
     */
    public void setParticipantGuranteed(boolean value) {
        this.participantGuranteed = value;
    }

    /**
     * Gets the value of the participantSubmittedFlightInfo property.
     * 
     */
    public boolean isParticipantSubmittedFlightInfo() {
        return participantSubmittedFlightInfo;
    }

    /**
     * Sets the value of the participantSubmittedFlightInfo property.
     * 
     */
    public void setParticipantSubmittedFlightInfo(boolean value) {
        this.participantSubmittedFlightInfo = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

}