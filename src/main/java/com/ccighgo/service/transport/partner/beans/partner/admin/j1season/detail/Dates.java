//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.18 at 02:02:17 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Dates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonDefaultStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonDefaultEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonDefaultAppDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonDefaultExtAppDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonDefaultSecondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonDefaultExtSecondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValAppDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValExtAppDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValSecondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partValExtSecondSemDeadlineDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dates", propOrder = {
    "seasonDefaultStartDate",
    "seasonDefaultEndDate",
    "seasonDefaultAppDeadlineDate",
    "seasonDefaultExtAppDeadlineDate",
    "seasonDefaultSecondSemDeadlineDate",
    "seasonDefaultExtSecondSemDeadlineDate",
    "partValStartDate",
    "partValEndDate",
    "partValAppDeadlineDate",
    "partValExtAppDeadlineDate",
    "partValSecondSemDeadlineDate",
    "partValExtSecondSemDeadlineDate"
})
public class Dates {

    @XmlElement(required = true)
    protected String seasonDefaultStartDate;
    @XmlElement(required = true)
    protected String seasonDefaultEndDate;
    @XmlElement(required = true)
    protected String seasonDefaultAppDeadlineDate;
    @XmlElement(required = true)
    protected String seasonDefaultExtAppDeadlineDate;
    @XmlElement(required = true)
    protected String seasonDefaultSecondSemDeadlineDate;
    @XmlElement(required = true)
    protected String seasonDefaultExtSecondSemDeadlineDate;
    @XmlElement(required = true)
    protected String partValStartDate;
    @XmlElement(required = true)
    protected String partValEndDate;
    @XmlElement(required = true)
    protected String partValAppDeadlineDate;
    @XmlElement(required = true)
    protected String partValExtAppDeadlineDate;
    @XmlElement(required = true)
    protected String partValSecondSemDeadlineDate;
    @XmlElement(required = true)
    protected String partValExtSecondSemDeadlineDate;

    /**
     * Gets the value of the seasonDefaultStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultStartDate() {
        return seasonDefaultStartDate;
    }

    /**
     * Sets the value of the seasonDefaultStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultStartDate(String value) {
        this.seasonDefaultStartDate = value;
    }

    /**
     * Gets the value of the seasonDefaultEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultEndDate() {
        return seasonDefaultEndDate;
    }

    /**
     * Sets the value of the seasonDefaultEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultEndDate(String value) {
        this.seasonDefaultEndDate = value;
    }

    /**
     * Gets the value of the seasonDefaultAppDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultAppDeadlineDate() {
        return seasonDefaultAppDeadlineDate;
    }

    /**
     * Sets the value of the seasonDefaultAppDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultAppDeadlineDate(String value) {
        this.seasonDefaultAppDeadlineDate = value;
    }

    /**
     * Gets the value of the seasonDefaultExtAppDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultExtAppDeadlineDate() {
        return seasonDefaultExtAppDeadlineDate;
    }

    /**
     * Sets the value of the seasonDefaultExtAppDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultExtAppDeadlineDate(String value) {
        this.seasonDefaultExtAppDeadlineDate = value;
    }

    /**
     * Gets the value of the seasonDefaultSecondSemDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultSecondSemDeadlineDate() {
        return seasonDefaultSecondSemDeadlineDate;
    }

    /**
     * Sets the value of the seasonDefaultSecondSemDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultSecondSemDeadlineDate(String value) {
        this.seasonDefaultSecondSemDeadlineDate = value;
    }

    /**
     * Gets the value of the seasonDefaultExtSecondSemDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonDefaultExtSecondSemDeadlineDate() {
        return seasonDefaultExtSecondSemDeadlineDate;
    }

    /**
     * Sets the value of the seasonDefaultExtSecondSemDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonDefaultExtSecondSemDeadlineDate(String value) {
        this.seasonDefaultExtSecondSemDeadlineDate = value;
    }

    /**
     * Gets the value of the partValStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValStartDate() {
        return partValStartDate;
    }

    /**
     * Sets the value of the partValStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValStartDate(String value) {
        this.partValStartDate = value;
    }

    /**
     * Gets the value of the partValEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValEndDate() {
        return partValEndDate;
    }

    /**
     * Sets the value of the partValEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValEndDate(String value) {
        this.partValEndDate = value;
    }

    /**
     * Gets the value of the partValAppDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValAppDeadlineDate() {
        return partValAppDeadlineDate;
    }

    /**
     * Sets the value of the partValAppDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValAppDeadlineDate(String value) {
        this.partValAppDeadlineDate = value;
    }

    /**
     * Gets the value of the partValExtAppDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValExtAppDeadlineDate() {
        return partValExtAppDeadlineDate;
    }

    /**
     * Sets the value of the partValExtAppDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValExtAppDeadlineDate(String value) {
        this.partValExtAppDeadlineDate = value;
    }

    /**
     * Gets the value of the partValSecondSemDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValSecondSemDeadlineDate() {
        return partValSecondSemDeadlineDate;
    }

    /**
     * Sets the value of the partValSecondSemDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValSecondSemDeadlineDate(String value) {
        this.partValSecondSemDeadlineDate = value;
    }

    /**
     * Gets the value of the partValExtSecondSemDeadlineDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartValExtSecondSemDeadlineDate() {
        return partValExtSecondSemDeadlineDate;
    }

    /**
     * Sets the value of the partValExtSecondSemDeadlineDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartValExtSecondSemDeadlineDate(String value) {
        this.partValExtSecondSemDeadlineDate = value;
    }

}
