//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.01 at 04:08:53 PM IST 
//


package com.ccighgo.service.transport.partner.beans.partner.user.details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserAddressCountry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserAddressCountry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="officeAddressCountryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="officeAddressCountryISOCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="officeAddressCountryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAddressCountry", propOrder = {
    "officeAddressCountryId",
    "officeAddressCountryISOCode",
    "officeAddressCountryName"
})
public class UserAddressCountry {

    protected int officeAddressCountryId;
    @XmlElement(required = true)
    protected String officeAddressCountryISOCode;
    @XmlElement(required = true)
    protected String officeAddressCountryName;

    /**
     * Gets the value of the officeAddressCountryId property.
     * 
     */
    public int getOfficeAddressCountryId() {
        return officeAddressCountryId;
    }

    /**
     * Sets the value of the officeAddressCountryId property.
     * 
     */
    public void setOfficeAddressCountryId(int value) {
        this.officeAddressCountryId = value;
    }

    /**
     * Gets the value of the officeAddressCountryISOCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeAddressCountryISOCode() {
        return officeAddressCountryISOCode;
    }

    /**
     * Sets the value of the officeAddressCountryISOCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeAddressCountryISOCode(String value) {
        this.officeAddressCountryISOCode = value;
    }

    /**
     * Gets the value of the officeAddressCountryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeAddressCountryName() {
        return officeAddressCountryName;
    }

    /**
     * Sets the value of the officeAddressCountryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeAddressCountryName(String value) {
        this.officeAddressCountryName = value;
    }

}
