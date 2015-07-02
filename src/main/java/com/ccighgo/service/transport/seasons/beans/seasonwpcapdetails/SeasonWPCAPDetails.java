//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.01 at 05:25:48 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonWPCAPDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonWPCAPDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="details" type="{http://www.ccighgo.com/seasonwpcapdetails}WPCAPBasicDetails" minOccurs="0"/>
 *         &lt;element name="internshipDetails" type="{http://www.ccighgo.com/seasonwpcapdetails}WPCAPInternshipDetails" minOccurs="0"/>
 *         &lt;element name="traineeDetails" type="{http://www.ccighgo.com/seasonwpcapdetails}WPCAPTraineeDetails" minOccurs="0"/>
 *         &lt;element name="generalSettings" type="{http://www.ccighgo.com/seasonwpcapdetails}WPCAPGeneralSettings" minOccurs="0"/>
 *         &lt;element name="programAllocations" type="{http://www.ccighgo.com/seasonwpcapdetails}WPCAPProgramAllocations" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonWPCAPDetails", propOrder = {
    "seasonId",
    "seasonProgramId",
    "details",
    "internshipDetails",
    "traineeDetails",
    "generalSettings",
    "programAllocations"
})
public class SeasonWPCAPDetails {

    protected int seasonId;
    protected int seasonProgramId;
    protected WPCAPBasicDetails details;
    protected WPCAPInternshipDetails internshipDetails;
    protected WPCAPTraineeDetails traineeDetails;
    protected WPCAPGeneralSettings generalSettings;
    protected WPCAPProgramAllocations programAllocations;

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
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link WPCAPBasicDetails }
     *     
     */
    public WPCAPBasicDetails getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPCAPBasicDetails }
     *     
     */
    public void setDetails(WPCAPBasicDetails value) {
        this.details = value;
    }

    /**
     * Gets the value of the internshipDetails property.
     * 
     * @return
     *     possible object is
     *     {@link WPCAPInternshipDetails }
     *     
     */
    public WPCAPInternshipDetails getInternshipDetails() {
        return internshipDetails;
    }

    /**
     * Sets the value of the internshipDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPCAPInternshipDetails }
     *     
     */
    public void setInternshipDetails(WPCAPInternshipDetails value) {
        this.internshipDetails = value;
    }

    /**
     * Gets the value of the traineeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link WPCAPTraineeDetails }
     *     
     */
    public WPCAPTraineeDetails getTraineeDetails() {
        return traineeDetails;
    }

    /**
     * Sets the value of the traineeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPCAPTraineeDetails }
     *     
     */
    public void setTraineeDetails(WPCAPTraineeDetails value) {
        this.traineeDetails = value;
    }

    /**
     * Gets the value of the generalSettings property.
     * 
     * @return
     *     possible object is
     *     {@link WPCAPGeneralSettings }
     *     
     */
    public WPCAPGeneralSettings getGeneralSettings() {
        return generalSettings;
    }

    /**
     * Sets the value of the generalSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPCAPGeneralSettings }
     *     
     */
    public void setGeneralSettings(WPCAPGeneralSettings value) {
        this.generalSettings = value;
    }

    /**
     * Gets the value of the programAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link WPCAPProgramAllocations }
     *     
     */
    public WPCAPProgramAllocations getProgramAllocations() {
        return programAllocations;
    }

    /**
     * Sets the value of the programAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPCAPProgramAllocations }
     *     
     */
    public void setProgramAllocations(WPCAPProgramAllocations value) {
        this.programAllocations = value;
    }

}
