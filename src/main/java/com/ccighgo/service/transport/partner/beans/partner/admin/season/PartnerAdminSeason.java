//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 03:00:14 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.season;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerAdminSeason complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminSeason">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerSeasonStatus" type="{http://www.ccighgo.com/partneradminseason}PartnerSeasonStatus"/>
 *         &lt;element name="seasonStatus" type="{http://www.ccighgo.com/partneradminseason}SeasonStatus"/>
 *         &lt;element name="partnerActiveForSeason" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="signedContract" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAdminSeason", propOrder = {
    "partnerGoId",
    "seasonId",
    "partnerSeasonId",
    "departmentId",
    "acronym",
    "departmentName",
    "departmentProgramId",
    "departmentProgramName",
    "programName",
    "programStartDate",
    "programEndDate",
    "appDeadlineDate",
    "partnerSeasonStatus",
    "seasonStatus",
    "partnerActiveForSeason",
    "signedContract"
})
public class PartnerAdminSeason {

    @XmlElement(required = true)
    protected String partnerGoId;
    protected int seasonId;
    protected int partnerSeasonId;
    protected int departmentId;
    @XmlElement(required = true)
    protected String acronym;
    @XmlElement(required = true)
    protected String departmentName;
    protected int departmentProgramId;
    @XmlElement(required = true)
    protected String departmentProgramName;
    @XmlElement(required = true)
    protected String programName;
    @XmlElement(required = true)
    protected String programStartDate;
    @XmlElement(required = true)
    protected String programEndDate;
    @XmlElement(required = true)
    protected String appDeadlineDate;
    @XmlElement(required = true)
    protected PartnerSeasonStatus partnerSeasonStatus;
    @XmlElement(required = true)
    protected SeasonStatus seasonStatus;
    protected boolean partnerActiveForSeason;
    protected boolean signedContract;

    /**
     * Gets the value of the partnerGoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerGoId() {
        return partnerGoId;
    }

    /**
     * Sets the value of the partnerGoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerGoId(String value) {
        this.partnerGoId = value;
    }

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
     * Gets the value of the partnerSeasonId property.
     * 
     */
    public int getPartnerSeasonId() {
        return partnerSeasonId;
    }

    /**
     * Sets the value of the partnerSeasonId property.
     * 
     */
    public void setPartnerSeasonId(int value) {
        this.partnerSeasonId = value;
    }

    /**
     * Gets the value of the departmentId property.
     * 
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     */
    public void setDepartmentId(int value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the acronym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Sets the value of the acronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcronym(String value) {
        this.acronym = value;
    }

    /**
     * Gets the value of the departmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the value of the departmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
    }

    /**
     * Gets the value of the departmentProgramId property.
     * 
     */
    public int getDepartmentProgramId() {
        return departmentProgramId;
    }

    /**
     * Sets the value of the departmentProgramId property.
     * 
     */
    public void setDepartmentProgramId(int value) {
        this.departmentProgramId = value;
    }

    /**
     * Gets the value of the departmentProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentProgramName() {
        return departmentProgramName;
    }

    /**
     * Sets the value of the departmentProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentProgramName(String value) {
        this.departmentProgramName = value;
    }

    /**
     * Gets the value of the programName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * Sets the value of the programName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramName(String value) {
        this.programName = value;
    }

    /**
     * Gets the value of the programStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramStartDate() {
        return programStartDate;
    }

    /**
     * Sets the value of the programStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramStartDate(String value) {
        this.programStartDate = value;
    }

    /**
     * Gets the value of the programEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramEndDate() {
        return programEndDate;
    }

    /**
     * Sets the value of the programEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramEndDate(String value) {
        this.programEndDate = value;
    }

    /**
     * Gets the value of the appDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppDeadlineDate() {
        return appDeadlineDate;
    }

    /**
     * Sets the value of the appDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppDeadlineDate(String value) {
        this.appDeadlineDate = value;
    }

    /**
     * Gets the value of the partnerSeasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerSeasonStatus }
     *     
     */
    public PartnerSeasonStatus getPartnerSeasonStatus() {
        return partnerSeasonStatus;
    }

    /**
     * Sets the value of the partnerSeasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerSeasonStatus }
     *     
     */
    public void setPartnerSeasonStatus(PartnerSeasonStatus value) {
        this.partnerSeasonStatus = value;
    }

    /**
     * Gets the value of the seasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SeasonStatus }
     *     
     */
    public SeasonStatus getSeasonStatus() {
        return seasonStatus;
    }

    /**
     * Sets the value of the seasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeasonStatus }
     *     
     */
    public void setSeasonStatus(SeasonStatus value) {
        this.seasonStatus = value;
    }

    /**
     * Gets the value of the partnerActiveForSeason property.
     * 
     */
    public boolean isPartnerActiveForSeason() {
        return partnerActiveForSeason;
    }

    /**
     * Sets the value of the partnerActiveForSeason property.
     * 
     */
    public void setPartnerActiveForSeason(boolean value) {
        this.partnerActiveForSeason = value;
    }

    /**
     * Gets the value of the signedContract property.
     * 
     */
    public boolean isSignedContract() {
        return signedContract;
    }

    /**
     * Sets the value of the signedContract property.
     * 
     */
    public void setSignedContract(boolean value) {
        this.signedContract = value;
    }

}
