//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.01 at 05:25:28 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HSPF1JanuaryStart2ndSemesterDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HSPF1JanuaryStart2ndSemesterDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earliestBirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestBirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="show2ndSemestertoNewHF" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="activateFullYearProgram" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="januaryStartFullYearDetail" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1JanuaryStartFullYearDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HSPF1JanuaryStart2ndSemesterDetails", propOrder = {
    "seasonId",
    "seasonProgramId",
    "startDate",
    "endDate",
    "applicationDeadlineDate",
    "earliestBirthDate",
    "latestBirthDate",
    "show2NdSemestertoNewHF",
    "activateFullYearProgram",
    "januaryStartFullYearDetail"
})
public class HSPF1JanuaryStart2NdSemesterDetails {

    protected int seasonId;
    protected int seasonProgramId;
    protected String startDate;
    protected String endDate;
    protected String applicationDeadlineDate;
    protected String earliestBirthDate;
    protected String latestBirthDate;
    @XmlElement(name = "show2ndSemestertoNewHF")
    protected Boolean show2NdSemestertoNewHF;
    protected Boolean activateFullYearProgram;
    protected HSPF1JanuaryStartFullYearDetail januaryStartFullYearDetail;

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
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the applicationDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationDeadlineDate() {
        return applicationDeadlineDate;
    }

    /**
     * Sets the value of the applicationDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationDeadlineDate(String value) {
        this.applicationDeadlineDate = value;
    }

    /**
     * Gets the value of the earliestBirthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestBirthDate() {
        return earliestBirthDate;
    }

    /**
     * Sets the value of the earliestBirthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestBirthDate(String value) {
        this.earliestBirthDate = value;
    }

    /**
     * Gets the value of the latestBirthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestBirthDate() {
        return latestBirthDate;
    }

    /**
     * Sets the value of the latestBirthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestBirthDate(String value) {
        this.latestBirthDate = value;
    }

    /**
     * Gets the value of the show2NdSemestertoNewHF property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShow2NdSemestertoNewHF() {
        return show2NdSemestertoNewHF;
    }

    /**
     * Sets the value of the show2NdSemestertoNewHF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShow2NdSemestertoNewHF(Boolean value) {
        this.show2NdSemestertoNewHF = value;
    }

    /**
     * Gets the value of the activateFullYearProgram property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActivateFullYearProgram() {
        return activateFullYearProgram;
    }

    /**
     * Sets the value of the activateFullYearProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActivateFullYearProgram(Boolean value) {
        this.activateFullYearProgram = value;
    }

    /**
     * Gets the value of the januaryStartFullYearDetail property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1JanuaryStartFullYearDetail }
     *     
     */
    public HSPF1JanuaryStartFullYearDetail getJanuaryStartFullYearDetail() {
        return januaryStartFullYearDetail;
    }

    /**
     * Sets the value of the januaryStartFullYearDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1JanuaryStartFullYearDetail }
     *     
     */
    public void setJanuaryStartFullYearDetail(HSPF1JanuaryStartFullYearDetail value) {
        this.januaryStartFullYearDetail = value;
    }

}
