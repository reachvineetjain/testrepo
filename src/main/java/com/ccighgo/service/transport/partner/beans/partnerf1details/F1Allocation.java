//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.13 at 03:48:12 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for F1Allocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="F1Allocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="augStartGuaranteed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="janStartGuaranteed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalGuaranteed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "F1Allocation", propOrder = {
    "augStartGuaranteed",
    "janStartGuaranteed",
    "totalGuaranteed"
})
public class F1Allocation {

    @XmlElement(required = true)
    protected String augStartGuaranteed;
    @XmlElement(required = true)
    protected String janStartGuaranteed;
    @XmlElement(required = true)
    protected String totalGuaranteed;

    /**
     * Gets the value of the augStartGuaranteed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAugStartGuaranteed() {
        return augStartGuaranteed;
    }

    /**
     * Sets the value of the augStartGuaranteed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAugStartGuaranteed(String value) {
        this.augStartGuaranteed = value;
    }

    /**
     * Gets the value of the janStartGuaranteed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJanStartGuaranteed() {
        return janStartGuaranteed;
    }

    /**
     * Sets the value of the janStartGuaranteed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJanStartGuaranteed(String value) {
        this.janStartGuaranteed = value;
    }

    /**
     * Gets the value of the totalGuaranteed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalGuaranteed() {
        return totalGuaranteed;
    }

    /**
     * Sets the value of the totalGuaranteed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalGuaranteed(String value) {
        this.totalGuaranteed = value;
    }

}
