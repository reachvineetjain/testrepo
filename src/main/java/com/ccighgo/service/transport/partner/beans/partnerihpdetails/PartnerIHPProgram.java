//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 03:11:17 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerihpdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerIHPProgram complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerIHPProgram">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="secondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerIHPProgram", propOrder = {
    "programName",
    "applicationDeadlineDate",
    "secondSemDeadlineDate",
    "seasonStatus"
})
public class PartnerIHPProgram {

    @XmlElement(required = true)
    protected String programName;
    @XmlElement(required = true)
    protected String applicationDeadlineDate;
    @XmlElement(required = true)
    protected String secondSemDeadlineDate;
    @XmlElement(required = true)
    protected String seasonStatus;

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

}
