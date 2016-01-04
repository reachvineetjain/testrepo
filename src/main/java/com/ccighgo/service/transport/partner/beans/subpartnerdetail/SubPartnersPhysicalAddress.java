//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.29 at 12:44:13 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for SubPartnersPhysicalAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnersPhysicalAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="physicalAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalAddressCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalAddressStateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalAddressCountry" type="{http://www.ccighgo.com/subpartnerdetail}Country" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnersPhysicalAddress", propOrder = {
    "physicalAddress1",
    "physicalAddress2",
    "physicalAddressCity",
    "physicalAddressStateOrProvince",
    "physicalAddressZipCode",
    "physicalAddressCountry"
})
public class SubPartnersPhysicalAddress
    extends Response
{

    protected String physicalAddress1;
    protected String physicalAddress2;
    protected String physicalAddressCity;
    protected String physicalAddressStateOrProvince;
    protected String physicalAddressZipCode;
    protected Country physicalAddressCountry;

    /**
     * Gets the value of the physicalAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddress1() {
        return physicalAddress1;
    }

    /**
     * Sets the value of the physicalAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddress1(String value) {
        this.physicalAddress1 = value;
    }

    /**
     * Gets the value of the physicalAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddress2() {
        return physicalAddress2;
    }

    /**
     * Sets the value of the physicalAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddress2(String value) {
        this.physicalAddress2 = value;
    }

    /**
     * Gets the value of the physicalAddressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddressCity() {
        return physicalAddressCity;
    }

    /**
     * Sets the value of the physicalAddressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddressCity(String value) {
        this.physicalAddressCity = value;
    }

    /**
     * Gets the value of the physicalAddressStateOrProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddressStateOrProvince() {
        return physicalAddressStateOrProvince;
    }

    /**
     * Sets the value of the physicalAddressStateOrProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddressStateOrProvince(String value) {
        this.physicalAddressStateOrProvince = value;
    }

    /**
     * Gets the value of the physicalAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalAddressZipCode() {
        return physicalAddressZipCode;
    }

    /**
     * Sets the value of the physicalAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalAddressZipCode(String value) {
        this.physicalAddressZipCode = value;
    }

    /**
     * Gets the value of the physicalAddressCountry property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getPhysicalAddressCountry() {
        return physicalAddressCountry;
    }

    /**
     * Sets the value of the physicalAddressCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setPhysicalAddressCountry(Country value) {
        this.physicalAddressCountry = value;
    }

}
