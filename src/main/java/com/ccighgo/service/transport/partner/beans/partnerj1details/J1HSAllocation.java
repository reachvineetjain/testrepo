//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.21 at 01:46:54 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for J1HSAllocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="J1HSAllocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="augStartUnguaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartUnguaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalUnguaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augStartGuaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartGuaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalGuaranteed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "J1HSAllocation", propOrder = {
    "augStartUnguaranteed",
    "janStartUnguaranteed",
    "totalUnguaranteed",
    "augStartGuaranteed",
    "janStartGuaranteed",
    "totalGuaranteed"
})
public class J1HSAllocation {

    protected int augStartUnguaranteed;
    protected int janStartUnguaranteed;
    protected int totalUnguaranteed;
    protected int augStartGuaranteed;
    protected int janStartGuaranteed;
    protected int totalGuaranteed;

    /**
     * Gets the value of the augStartUnguaranteed property.
     * 
     */
    public int getAugStartUnguaranteed() {
        return augStartUnguaranteed;
    }

    /**
     * Sets the value of the augStartUnguaranteed property.
     * 
     */
    public void setAugStartUnguaranteed(int value) {
        this.augStartUnguaranteed = value;
    }

    /**
     * Gets the value of the janStartUnguaranteed property.
     * 
     */
    public int getJanStartUnguaranteed() {
        return janStartUnguaranteed;
    }

    /**
     * Sets the value of the janStartUnguaranteed property.
     * 
     */
    public void setJanStartUnguaranteed(int value) {
        this.janStartUnguaranteed = value;
    }

    /**
     * Gets the value of the totalUnguaranteed property.
     * 
     */
    public int getTotalUnguaranteed() {
        return totalUnguaranteed;
    }

    /**
     * Sets the value of the totalUnguaranteed property.
     * 
     */
    public void setTotalUnguaranteed(int value) {
        this.totalUnguaranteed = value;
    }

    /**
     * Gets the value of the augStartGuaranteed property.
     * 
     */
    public int getAugStartGuaranteed() {
        return augStartGuaranteed;
    }

    /**
     * Sets the value of the augStartGuaranteed property.
     * 
     */
    public void setAugStartGuaranteed(int value) {
        this.augStartGuaranteed = value;
    }

    /**
     * Gets the value of the janStartGuaranteed property.
     * 
     */
    public int getJanStartGuaranteed() {
        return janStartGuaranteed;
    }

    /**
     * Sets the value of the janStartGuaranteed property.
     * 
     */
    public void setJanStartGuaranteed(int value) {
        this.janStartGuaranteed = value;
    }

    /**
     * Gets the value of the totalGuaranteed property.
     * 
     */
    public int getTotalGuaranteed() {
        return totalGuaranteed;
    }

    /**
     * Sets the value of the totalGuaranteed property.
     * 
     */
    public void setTotalGuaranteed(int value) {
        this.totalGuaranteed = value;
    }

}
