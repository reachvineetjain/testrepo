//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.30 at 12:02:32 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerdashboard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerProgram complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerProgram">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerDepartmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerDepartmentProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programDetailsUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerProgram", propOrder = {
    "partnerSeasonId",
    "partnerDepartmentProgramId",
    "partnerDepartmentProgramName",
    "programDetailsUrl"
})
public class PartnerProgram {

    protected int partnerSeasonId;
    protected int partnerDepartmentProgramId;
    @XmlElement(required = true)
    protected String partnerDepartmentProgramName;
    @XmlElement(required = true)
    protected String programDetailsUrl;

    /**
     * Gets the value of the partnerSeasonId property.
     * 
     */
    public int getPartnerSeasonId() {
        return partnerSeasonId;
    }

    /**
     * Sets the value of the partnerSeasonId property.
     * 
     */
    public void setPartnerSeasonId(int value) {
        this.partnerSeasonId = value;
    }

    /**
     * Gets the value of the partnerDepartmentProgramId property.
     * 
     */
    public int getPartnerDepartmentProgramId() {
        return partnerDepartmentProgramId;
    }

    /**
     * Sets the value of the partnerDepartmentProgramId property.
     * 
     */
    public void setPartnerDepartmentProgramId(int value) {
        this.partnerDepartmentProgramId = value;
    }

    /**
     * Gets the value of the partnerDepartmentProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerDepartmentProgramName() {
        return partnerDepartmentProgramName;
    }

    /**
     * Sets the value of the partnerDepartmentProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerDepartmentProgramName(String value) {
        this.partnerDepartmentProgramName = value;
    }

    /**
     * Gets the value of the programDetailsUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramDetailsUrl() {
        return programDetailsUrl;
    }

    /**
     * Sets the value of the programDetailsUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramDetailsUrl(String value) {
        this.programDetailsUrl = value;
    }

}
