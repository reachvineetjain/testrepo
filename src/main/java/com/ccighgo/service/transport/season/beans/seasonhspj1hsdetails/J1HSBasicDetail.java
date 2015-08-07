//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:47 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for J1HSBasicDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="J1HSBasicDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programStatusValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "J1HSBasicDetail", propOrder = {
    "seasonId",
    "seasonProgramId",
    "programName",
    "programStatusId",
    "programStatusValue"
})
public class J1HSBasicDetail extends Response{

    protected int seasonId;
    protected int seasonProgramId;
    @XmlElement(required = true)
    protected String programName;
    protected int programStatusId;
    @XmlElement(required = true)
    protected String programStatusValue;

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
     * Gets the value of the programStatusId property.
     * 
     */
    public int getProgramStatusId() {
        return programStatusId;
    }

    /**
     * Sets the value of the programStatusId property.
     * 
     */
    public void setProgramStatusId(int value) {
        this.programStatusId = value;
    }

    /**
     * Gets the value of the programStatusValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramStatusValue() {
        return programStatusValue;
    }

    /**
     * Sets the value of the programStatusValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramStatusValue(String value) {
        this.programStatusValue = value;
    }

}
