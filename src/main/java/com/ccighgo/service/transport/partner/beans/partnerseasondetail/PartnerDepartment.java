//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.03 at 10:25:37 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasondetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerDepartment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerDepartment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerSeasonDepartmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerSeasonDepartmentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerSeasonDepartmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerDepartment", propOrder = {
    "partnerSeasonDepartmentId",
    "partnerSeasonDepartmentCode",
    "partnerSeasonDepartmentName"
})
public class PartnerDepartment {

    protected int partnerSeasonDepartmentId;
    @XmlElement(required = true)
    protected String partnerSeasonDepartmentCode;
    @XmlElement(required = true)
    protected String partnerSeasonDepartmentName;

    /**
     * Gets the value of the partnerSeasonDepartmentId property.
     * 
     */
    public int getPartnerSeasonDepartmentId() {
        return partnerSeasonDepartmentId;
    }

    /**
     * Sets the value of the partnerSeasonDepartmentId property.
     * 
     */
    public void setPartnerSeasonDepartmentId(int value) {
        this.partnerSeasonDepartmentId = value;
    }

    /**
     * Gets the value of the partnerSeasonDepartmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonDepartmentCode() {
        return partnerSeasonDepartmentCode;
    }

    /**
     * Sets the value of the partnerSeasonDepartmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonDepartmentCode(String value) {
        this.partnerSeasonDepartmentCode = value;
    }

    /**
     * Gets the value of the partnerSeasonDepartmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonDepartmentName() {
        return partnerSeasonDepartmentName;
    }

    /**
     * Sets the value of the partnerSeasonDepartmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonDepartmentName(String value) {
        this.partnerSeasonDepartmentName = value;
    }

}
