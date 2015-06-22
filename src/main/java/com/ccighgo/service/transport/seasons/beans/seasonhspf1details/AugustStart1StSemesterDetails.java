//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.22 at 12:47:37 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AugustStart1stSemesterDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AugustStart1stSemesterDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earliestBirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestBirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="show1stSemesterToNewHF" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AugustStart1stSemesterDetails", propOrder = {
    "seasonId",
    "startDate",
    "endDate",
    "applicationDeadlineDate",
    "earliestBirthDate",
    "latestBirthDate",
    "show1StSemesterToNewHF"
})
public class AugustStart1StSemesterDetails {

    @XmlElement(required = true)
    protected BigInteger seasonId;
    protected String startDate;
    protected String endDate;
    protected String applicationDeadlineDate;
    protected String earliestBirthDate;
    protected String latestBirthDate;
    @XmlElement(name = "show1stSemesterToNewHF")
    protected Boolean show1StSemesterToNewHF;

    /**
     * Gets the value of the seasonId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeasonId(BigInteger value) {
        this.seasonId = value;
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
     * Gets the value of the show1StSemesterToNewHF property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShow1StSemesterToNewHF() {
        return show1StSemesterToNewHF;
    }

    /**
     * Sets the value of the show1StSemesterToNewHF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShow1StSemesterToNewHF(Boolean value) {
        this.show1StSemesterToNewHF = value;
    }

}
