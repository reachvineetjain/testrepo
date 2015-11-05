//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:33:21 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for SubPartnersMailingAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnersMailingAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="mailingAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailingAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailingAddressCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailingAddressStateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailingAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailingAddressCountry" type="{http://www.ccighgo.com/subpartnerdetail}Country" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnersMailingAddress", propOrder = {
    "mailingAddress1",
    "mailingAddress2",
    "mailingAddressCity",
    "mailingAddressStateOrProvince",
    "mailingAddressZipCode",
    "mailingAddressCountry"
})
public class SubPartnersMailingAddress
    extends Response
{

    protected String mailingAddress1;
    protected String mailingAddress2;
    protected String mailingAddressCity;
    protected String mailingAddressStateOrProvince;
    protected String mailingAddressZipCode;
    protected Country mailingAddressCountry;

    /**
     * Gets the value of the mailingAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddress1() {
        return mailingAddress1;
    }

    /**
     * Sets the value of the mailingAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddress1(String value) {
        this.mailingAddress1 = value;
    }

    /**
     * Gets the value of the mailingAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddress2() {
        return mailingAddress2;
    }

    /**
     * Sets the value of the mailingAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddress2(String value) {
        this.mailingAddress2 = value;
    }

    /**
     * Gets the value of the mailingAddressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddressCity() {
        return mailingAddressCity;
    }

    /**
     * Sets the value of the mailingAddressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddressCity(String value) {
        this.mailingAddressCity = value;
    }

    /**
     * Gets the value of the mailingAddressStateOrProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddressStateOrProvince() {
        return mailingAddressStateOrProvince;
    }

    /**
     * Sets the value of the mailingAddressStateOrProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddressStateOrProvince(String value) {
        this.mailingAddressStateOrProvince = value;
    }

    /**
     * Gets the value of the mailingAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailingAddressZipCode() {
        return mailingAddressZipCode;
    }

    /**
     * Sets the value of the mailingAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailingAddressZipCode(String value) {
        this.mailingAddressZipCode = value;
    }

    /**
     * Gets the value of the mailingAddressCountry property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getMailingAddressCountry() {
        return mailingAddressCountry;
    }

    /**
     * Sets the value of the mailingAddressCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setMailingAddressCountry(Country value) {
        this.mailingAddressCountry = value;
    }

}
