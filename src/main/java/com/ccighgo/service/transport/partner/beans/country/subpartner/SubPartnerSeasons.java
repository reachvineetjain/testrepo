//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 10:05:23 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.country.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubPartnerSeasons complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnerSeasons">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subPartnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerSeasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerSeasonProgram" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerSeasons", propOrder = {
    "subPartnerSeasonId",
    "subPartnerSeasonProgramId",
    "subPartnerSeasonProgram"
})
public class SubPartnerSeasons {

    protected int subPartnerSeasonId;
    protected int subPartnerSeasonProgramId;
    @XmlElement(required = true)
    protected String subPartnerSeasonProgram;

    /**
     * Gets the value of the subPartnerSeasonId property.
     * 
     */
    public int getSubPartnerSeasonId() {
        return subPartnerSeasonId;
    }

    /**
     * Sets the value of the subPartnerSeasonId property.
     * 
     */
    public void setSubPartnerSeasonId(int value) {
        this.subPartnerSeasonId = value;
    }

    /**
     * Gets the value of the subPartnerSeasonProgramId property.
     * 
     */
    public int getSubPartnerSeasonProgramId() {
        return subPartnerSeasonProgramId;
    }

    /**
     * Sets the value of the subPartnerSeasonProgramId property.
     * 
     */
    public void setSubPartnerSeasonProgramId(int value) {
        this.subPartnerSeasonProgramId = value;
    }

    /**
     * Gets the value of the subPartnerSeasonProgram property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerSeasonProgram() {
        return subPartnerSeasonProgram;
    }

    /**
     * Sets the value of the subPartnerSeasonProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerSeasonProgram(String value) {
        this.subPartnerSeasonProgram = value;
    }

}
