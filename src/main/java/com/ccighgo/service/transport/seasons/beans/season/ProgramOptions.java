//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 11:12:03 AM CST 
//


package com.ccighgo.service.transport.seasons.beans.season;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProgramOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProgramOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programOptionsID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programOptionsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programOptionsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramOptions", propOrder = {
    "programOptionsID",
    "programOptionsCode",
    "programOptionsName"
})
public class ProgramOptions {

    protected int programOptionsID;
    @XmlElement(required = true)
    protected String programOptionsCode;
    @XmlElement(required = true)
    protected String programOptionsName;

    /**
     * Gets the value of the programOptionsID property.
     * 
     */
    public int getProgramOptionsID() {
        return programOptionsID;
    }

    /**
     * Sets the value of the programOptionsID property.
     * 
     */
    public void setProgramOptionsID(int value) {
        this.programOptionsID = value;
    }

    /**
     * Gets the value of the programOptionsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramOptionsCode() {
        return programOptionsCode;
    }

    /**
     * Sets the value of the programOptionsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramOptionsCode(String value) {
        this.programOptionsCode = value;
    }

    /**
     * Gets the value of the programOptionsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramOptionsName() {
        return programOptionsName;
    }

    /**
     * Sets the value of the programOptionsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramOptionsName(String value) {
        this.programOptionsName = value;
    }

}
