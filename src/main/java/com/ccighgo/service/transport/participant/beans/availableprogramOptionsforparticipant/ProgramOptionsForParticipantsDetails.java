//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.02 at 02:47:47 PM CST 
//


package com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProgramOptionsForParticipantsDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProgramOptionsForParticipantsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programOptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramOption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramOptionsForParticipantsDetails", propOrder = {
    "departmentProgramId",
    "programOptionId",
    "departmentProgramOption"
})
public class ProgramOptionsForParticipantsDetails {

    protected int departmentProgramId;
    protected int programOptionId;
    @XmlElement(required = true)
    protected String departmentProgramOption;

    /**
     * Gets the value of the departmentProgramId property.
     * 
     */
    public int getDepartmentProgramId() {
        return departmentProgramId;
    }

    /**
     * Sets the value of the departmentProgramId property.
     * 
     */
    public void setDepartmentProgramId(int value) {
        this.departmentProgramId = value;
    }

    /**
     * Gets the value of the programOptionId property.
     * 
     */
    public int getProgramOptionId() {
        return programOptionId;
    }

    /**
     * Sets the value of the programOptionId property.
     * 
     */
    public void setProgramOptionId(int value) {
        this.programOptionId = value;
    }

    /**
     * Gets the value of the departmentProgramOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentProgramOption() {
        return departmentProgramOption;
    }

    /**
     * Sets the value of the departmentProgramOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentProgramOption(String value) {
        this.departmentProgramOption = value;
    }

}
