//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 02:48:16 PM CST 
//


package com.ccighgo.service.transport.season.beans.seasonhspihpdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IHPApplicationByRegion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IHPApplicationByRegion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="applicationRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="applicationRegionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acceptApplicationFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IHPApplicationByRegion", propOrder = {
    "seasonId",
    "seasonProgramId",
    "applicationRegionId",
    "applicationRegionName",
    "acceptApplicationFlag"
})
public class IHPApplicationByRegion {

    protected int seasonId;
    protected int seasonProgramId;
    protected int applicationRegionId;
    @XmlElement(required = true)
    protected String applicationRegionName;
    protected boolean acceptApplicationFlag;

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
     * Gets the value of the applicationRegionId property.
     * 
     */
    public int getApplicationRegionId() {
        return applicationRegionId;
    }

    /**
     * Sets the value of the applicationRegionId property.
     * 
     */
    public void setApplicationRegionId(int value) {
        this.applicationRegionId = value;
    }

    /**
     * Gets the value of the applicationRegionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationRegionName() {
        return applicationRegionName;
    }

    /**
     * Sets the value of the applicationRegionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationRegionName(String value) {
        this.applicationRegionName = value;
    }

    /**
     * Gets the value of the acceptApplicationFlag property.
     * 
     */
    public boolean isAcceptApplicationFlag() {
        return acceptApplicationFlag;
    }

    /**
     * Sets the value of the acceptApplicationFlag property.
     * 
     */
    public void setAcceptApplicationFlag(boolean value) {
        this.acceptApplicationFlag = value;
    }

}
