//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:33:36 AM CST 
//


package com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonsForParticipantDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonsForParticipantDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonsForParticipantDetails", propOrder = {
    "seasonId",
    "departmentProgramId",
    "seasonName"
})
public class SeasonsForParticipantDetails {

    protected int seasonId;
    protected int departmentProgramId;
    @XmlElement(required = true)
    protected String seasonName;

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
     * Gets the value of the seasonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonName() {
        return seasonName;
    }

    /**
     * Sets the value of the seasonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonName(String value) {
        this.seasonName = value;
    }

}
