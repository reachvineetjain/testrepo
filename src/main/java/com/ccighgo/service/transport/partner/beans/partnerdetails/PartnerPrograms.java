//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.15 at 02:26:12 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerPrograms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerPrograms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerDepartmentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerDepartmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerProgramId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerPrograms", propOrder = {
    "partnerDepartmentId",
    "partnerDepartmentName",
    "partnerProgramId",
    "partnerProgramName"
})
public class PartnerPrograms {

    @XmlElement(required = true)
    protected String partnerDepartmentId;
    @XmlElement(required = true)
    protected String partnerDepartmentName;
    @XmlElement(required = true)
    protected String partnerProgramId;
    @XmlElement(required = true)
    protected String partnerProgramName;

    /**
     * Gets the value of the partnerDepartmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerDepartmentId() {
        return partnerDepartmentId;
    }

    /**
     * Sets the value of the partnerDepartmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerDepartmentId(String value) {
        this.partnerDepartmentId = value;
    }

    /**
     * Gets the value of the partnerDepartmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerDepartmentName() {
        return partnerDepartmentName;
    }

    /**
     * Sets the value of the partnerDepartmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerDepartmentName(String value) {
        this.partnerDepartmentName = value;
    }

    /**
     * Gets the value of the partnerProgramId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerProgramId() {
        return partnerProgramId;
    }

    /**
     * Sets the value of the partnerProgramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerProgramId(String value) {
        this.partnerProgramId = value;
    }

    /**
     * Gets the value of the partnerProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerProgramName() {
        return partnerProgramName;
    }

    /**
     * Sets the value of the partnerProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerProgramName(String value) {
        this.partnerProgramName = value;
    }

}
