//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 03:11:02 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerJ1HSProgram complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerJ1HSProgram">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="secondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="allocationStats" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="allocation" type="{http://www.ccighgo.com/partnerj1hs}J1HSAllocation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerJ1HSProgram", propOrder = {
    "programName",
    "applicationDeadlineDate",
    "secondSemDeadlineDate",
    "seasonStatus",
    "allocationStats",
    "allocation"
})
public class PartnerJ1HSProgram {

    @XmlElement(required = true)
    protected String programName;
    @XmlElement(required = true)
    protected String applicationDeadlineDate;
    @XmlElement(required = true)
    protected String secondSemDeadlineDate;
    @XmlElement(required = true)
    protected String seasonStatus;
    @XmlElement(required = true)
    protected String allocationStats;
    @XmlElement(required = true)
    protected J1HSAllocation allocation;

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
     * Gets the value of the secondSemDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondSemDeadlineDate() {
        return secondSemDeadlineDate;
    }

    /**
     * Sets the value of the secondSemDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondSemDeadlineDate(String value) {
        this.secondSemDeadlineDate = value;
    }

    /**
     * Gets the value of the seasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonStatus() {
        return seasonStatus;
    }

    /**
     * Sets the value of the seasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonStatus(String value) {
        this.seasonStatus = value;
    }

    /**
     * Gets the value of the allocationStats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllocationStats() {
        return allocationStats;
    }

    /**
     * Sets the value of the allocationStats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllocationStats(String value) {
        this.allocationStats = value;
    }

    /**
     * Gets the value of the allocation property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSAllocation }
     *     
     */
    public J1HSAllocation getAllocation() {
        return allocation;
    }

    /**
     * Sets the value of the allocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSAllocation }
     *     
     */
    public void setAllocation(J1HSAllocation value) {
        this.allocation = value;
    }

}
