//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.30 at 05:32:56 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonslist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProgramSeason complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProgramSeason">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonProgramUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramSeason", propOrder = {
    "seasonProgramId",
    "seasonProgramName",
    "seasonProgramUrl"
})
public class ProgramSeason {

    protected int seasonProgramId;
    @XmlElement(required = true)
    protected String seasonProgramName;
    @XmlElement(required = true)
    protected String seasonProgramUrl;

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
     * Gets the value of the seasonProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonProgramName() {
        return seasonProgramName;
    }

    /**
     * Sets the value of the seasonProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonProgramName(String value) {
        this.seasonProgramName = value;
    }

    /**
     * Gets the value of the seasonProgramUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonProgramUrl() {
        return seasonProgramUrl;
    }

    /**
     * Sets the value of the seasonProgramUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonProgramUrl(String value) {
        this.seasonProgramUrl = value;
    }

}
