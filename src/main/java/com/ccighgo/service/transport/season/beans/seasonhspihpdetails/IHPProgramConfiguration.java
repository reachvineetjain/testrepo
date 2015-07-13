//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.13 at 03:57:41 PM CDT 
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
 *         &lt;element name="stopAceptingApplications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stopAcceptingApplicationByGender" type="{http://www.ccighgo.com/stpihp}IHPApplicationByGender" minOccurs="0"/>
 *         &lt;element name="stopAcceptingApplicationByRegion" type="{http://www.ccighgo.com/stpihp}IHPApplicationByRegion" maxOccurs="unbounded" minOccurs="0"/>
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
    "stopAceptingApplications",
    "stopAcceptingApplicationByGender",
    "stopAcceptingApplicationByRegion"
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
    protected boolean stopAceptingApplications;
    protected IHPApplicationByGender stopAcceptingApplicationByGender;
    protected List<IHPApplicationByRegion> stopAcceptingApplicationByRegion;

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
     * Gets the value of the stopAceptingApplications property.
     * 
     */
    public boolean isStopAceptingApplications() {
        return stopAceptingApplications;
    }

    /**
     * Sets the value of the stopAceptingApplications property.
     * 
     */
    public void setStopAceptingApplications(boolean value) {
        this.stopAceptingApplications = value;
    }

    /**
     * Gets the value of the stopAcceptingApplicationByGender property.
     * 
     * @return
     *     possible object is
     *     {@link IHPApplicationByGender }
     *     
     */
    public IHPApplicationByGender getStopAcceptingApplicationByGender() {
        return stopAcceptingApplicationByGender;
    }

    /**
     * Sets the value of the stopAcceptingApplicationByGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link IHPApplicationByGender }
     *     
     */
    public void setStopAcceptingApplicationByGender(IHPApplicationByGender value) {
        this.stopAcceptingApplicationByGender = value;
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

}
