//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.07 at 11:56:43 AM CDT 
//


package com.ccighgo.service.transport.utility.beans.program;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Program complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Program">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Program", propOrder = {
    "departmentId",
    "departmentName",
    "acronym",
    "programId",
    "programName",
    "programDescription"
})
public class Program {

    protected int departmentId;
    @XmlElement(required = true)
    protected String departmentName;
    @XmlElement(required = true)
    protected String acronym;
    protected int programId;
    @XmlElement(required = true)
    protected String programName;
    protected String programDescription;

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
     * Gets the value of the programId property.
     * 
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     */
    public void setProgramId(int value) {
        this.programId = value;
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
     * Gets the value of the programDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Sets the value of the programDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramDescription(String value) {
        this.programDescription = value;
    }

}
