//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.16 at 10:00:59 AM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hfStateId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hfState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFState", propOrder = {
    "hfStateId",
    "hfState"
})
public class HFState {

    protected int hfStateId;
    @XmlElement(required = true)
    protected String hfState;

    /**
     * Gets the value of the hfStateId property.
     * 
     */
    public int getHfStateId() {
        return hfStateId;
    }

    /**
     * Sets the value of the hfStateId property.
     * 
     */
    public void setHfStateId(int value) {
        this.hfStateId = value;
    }

    /**
     * Gets the value of the hfState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHfState() {
        return hfState;
    }

    /**
     * Sets the value of the hfState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHfState(String value) {
        this.hfState = value;
    }

}
