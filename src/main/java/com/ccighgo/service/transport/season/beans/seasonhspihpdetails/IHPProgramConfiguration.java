//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.24 at 07:16:05 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspihpdetails;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IHPProgramConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IHPProgramConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxNoOfParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lcHoldTimeDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfLcCanRequestHold" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="splitPlacementInPending" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationCutOffPriorToProgStart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stopAcceptingApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingApplicationByGender" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gender" type="{http://www.ccighgo.com/stpihp}IHPApplicationByGender" minOccurs="0"/>
 *         &lt;element name="stopAcceptingApplicationByRegion" type="{http://www.ccighgo.com/stpihp}IHPApplicationByRegion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="stopAcceptingIhpStandardSettings" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingVolunteerHomeStayApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingLanguageBuddyApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingHolidayHomeStayApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingHighSchoolApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IHPProgramConfiguration", propOrder = {
    "seasonId",
    "seasonProgramId",
    "maxNoOfParticipants",
    "lcHoldTimeDays",
    "noOfLcCanRequestHold",
    "splitPlacementInPending",
    "applicationCutOffPriorToProgStart",
    "stopAcceptingApplications",
    "stopAcceptingApplicationByGender",
    "gender",
    "stopAcceptingApplicationByRegion",
    "stopAcceptingIhpStandardSettings",
    "stopAcceptingVolunteerHomeStayApplications",
    "stopAcceptingLanguageBuddyApplications",
    "stopAcceptingHolidayHomeStayApplications",
    "stopAcceptingHighSchoolApplications"
})
public class IHPProgramConfiguration {

    protected int seasonId;
    protected int seasonProgramId;
    protected int maxNoOfParticipants;
    protected int lcHoldTimeDays;
    protected int noOfLcCanRequestHold;
    @XmlElement(required = true)
    protected String splitPlacementInPending;
    @XmlElement(required = true)
    protected String applicationCutOffPriorToProgStart;
    protected boolean stopAcceptingApplications;
    protected boolean stopAcceptingApplicationByGender;
    protected IHPApplicationByGender gender;
    protected List<IHPApplicationByRegion> stopAcceptingApplicationByRegion;
    protected boolean stopAcceptingIhpStandardSettings;
    protected boolean stopAcceptingVolunteerHomeStayApplications;
    protected boolean stopAcceptingLanguageBuddyApplications;
    protected boolean stopAcceptingHolidayHomeStayApplications;
    protected boolean stopAcceptingHighSchoolApplications;

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
     * Gets the value of the maxNoOfParticipants property.
     * 
     */
    public int getMaxNoOfParticipants() {
        return maxNoOfParticipants;
    }

    /**
     * Sets the value of the maxNoOfParticipants property.
     * 
     */
    public void setMaxNoOfParticipants(int value) {
        this.maxNoOfParticipants = value;
    }

    /**
     * Gets the value of the lcHoldTimeDays property.
     * 
     */
    public int getLcHoldTimeDays() {
        return lcHoldTimeDays;
    }

    /**
     * Sets the value of the lcHoldTimeDays property.
     * 
     */
    public void setLcHoldTimeDays(int value) {
        this.lcHoldTimeDays = value;
    }

    /**
     * Gets the value of the noOfLcCanRequestHold property.
     * 
     */
    public int getNoOfLcCanRequestHold() {
        return noOfLcCanRequestHold;
    }

    /**
     * Sets the value of the noOfLcCanRequestHold property.
     * 
     */
    public void setNoOfLcCanRequestHold(int value) {
        this.noOfLcCanRequestHold = value;
    }

    /**
     * Gets the value of the splitPlacementInPending property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitPlacementInPending() {
        return splitPlacementInPending;
    }

    /**
     * Sets the value of the splitPlacementInPending property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitPlacementInPending(String value) {
        this.splitPlacementInPending = value;
    }

    /**
     * Gets the value of the applicationCutOffPriorToProgStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationCutOffPriorToProgStart() {
        return applicationCutOffPriorToProgStart;
    }

    /**
     * Sets the value of the applicationCutOffPriorToProgStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationCutOffPriorToProgStart(String value) {
        this.applicationCutOffPriorToProgStart = value;
    }

    /**
     * Gets the value of the stopAcceptingApplications property.
     * 
     */
    public boolean isStopAcceptingApplications() {
        return stopAcceptingApplications;
    }

    /**
     * Sets the value of the stopAcceptingApplications property.
     * 
     */
    public void setStopAcceptingApplications(boolean value) {
        this.stopAcceptingApplications = value;
    }

    /**
     * Gets the value of the stopAcceptingApplicationByGender property.
     * 
     */
    public boolean isStopAcceptingApplicationByGender() {
        return stopAcceptingApplicationByGender;
    }

    /**
     * Sets the value of the stopAcceptingApplicationByGender property.
     * 
     */
    public void setStopAcceptingApplicationByGender(boolean value) {
        this.stopAcceptingApplicationByGender = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link IHPApplicationByGender }
     *     
     */
    public IHPApplicationByGender getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link IHPApplicationByGender }
     *     
     */
    public void setGender(IHPApplicationByGender value) {
        this.gender = value;
    }

    /**
     * Gets the value of the stopAcceptingApplicationByRegion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stopAcceptingApplicationByRegion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStopAcceptingApplicationByRegion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IHPApplicationByRegion }
     * 
     * 
     */
    public List<IHPApplicationByRegion> getStopAcceptingApplicationByRegion() {
        if (stopAcceptingApplicationByRegion == null) {
            stopAcceptingApplicationByRegion = new ArrayList<IHPApplicationByRegion>();
        }
        return this.stopAcceptingApplicationByRegion;
    }

    /**
     * Gets the value of the stopAcceptingIhpStandardSettings property.
     * 
     */
    public boolean isStopAcceptingIhpStandardSettings() {
        return stopAcceptingIhpStandardSettings;
    }

    /**
     * Sets the value of the stopAcceptingIhpStandardSettings property.
     * 
     */
    public void setStopAcceptingIhpStandardSettings(boolean value) {
        this.stopAcceptingIhpStandardSettings = value;
    }

    /**
     * Gets the value of the stopAcceptingVolunteerHomeStayApplications property.
     * 
     */
    public boolean isStopAcceptingVolunteerHomeStayApplications() {
        return stopAcceptingVolunteerHomeStayApplications;
    }

    /**
     * Sets the value of the stopAcceptingVolunteerHomeStayApplications property.
     * 
     */
    public void setStopAcceptingVolunteerHomeStayApplications(boolean value) {
        this.stopAcceptingVolunteerHomeStayApplications = value;
    }

    /**
     * Gets the value of the stopAcceptingLanguageBuddyApplications property.
     * 
     */
    public boolean isStopAcceptingLanguageBuddyApplications() {
        return stopAcceptingLanguageBuddyApplications;
    }

    /**
     * Sets the value of the stopAcceptingLanguageBuddyApplications property.
     * 
     */
    public void setStopAcceptingLanguageBuddyApplications(boolean value) {
        this.stopAcceptingLanguageBuddyApplications = value;
    }

    /**
     * Gets the value of the stopAcceptingHolidayHomeStayApplications property.
     * 
     */
    public boolean isStopAcceptingHolidayHomeStayApplications() {
        return stopAcceptingHolidayHomeStayApplications;
    }

    /**
     * Sets the value of the stopAcceptingHolidayHomeStayApplications property.
     * 
     */
    public void setStopAcceptingHolidayHomeStayApplications(boolean value) {
        this.stopAcceptingHolidayHomeStayApplications = value;
    }

    /**
     * Gets the value of the stopAcceptingHighSchoolApplications property.
     * 
     */
    public boolean isStopAcceptingHighSchoolApplications() {
        return stopAcceptingHighSchoolApplications;
    }

    /**
     * Sets the value of the stopAcceptingHighSchoolApplications property.
     * 
     */
    public void setStopAcceptingHighSchoolApplications(boolean value) {
        this.stopAcceptingHighSchoolApplications = value;
    }

}
