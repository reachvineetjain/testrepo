//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 02:06:07 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="augStartGuaranteedNumerator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augStartGuaranteedDenominator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartGuaranteedNumerator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartGuaranteedDenominator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalGuaranteedNumerator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalGuaranteedDenominator" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "augStartGuaranteedNumerator",
    "augStartGuaranteedDenominator",
    "janStartGuaranteedNumerator",
    "janStartGuaranteedDenominator",
    "totalGuaranteedNumerator",
    "totalGuaranteedDenominator"
})
public class F1Allocation {

    protected int augStartGuaranteedNumerator;
    protected int augStartGuaranteedDenominator;
    protected int janStartGuaranteedNumerator;
    protected int janStartGuaranteedDenominator;
    protected int totalGuaranteedNumerator;
    protected int totalGuaranteedDenominator;

    /**
     * Gets the value of the augStartGuaranteedNumerator property.
     * 
     */
    public int getAugStartGuaranteedNumerator() {
        return augStartGuaranteedNumerator;
    }

    /**
     * Sets the value of the augStartGuaranteedNumerator property.
     * 
     */
    public void setAugStartGuaranteedNumerator(int value) {
        this.augStartGuaranteedNumerator = value;
    }

    /**
     * Gets the value of the augStartGuaranteedDenominator property.
     * 
     */
    public int getAugStartGuaranteedDenominator() {
        return augStartGuaranteedDenominator;
    }

    /**
     * Sets the value of the augStartGuaranteedDenominator property.
     * 
     */
    public void setAugStartGuaranteedDenominator(int value) {
        this.augStartGuaranteedDenominator = value;
    }

    /**
     * Gets the value of the janStartGuaranteedNumerator property.
     * 
     */
    public int getJanStartGuaranteedNumerator() {
        return janStartGuaranteedNumerator;
    }

    /**
     * Sets the value of the janStartGuaranteedNumerator property.
     * 
     */
    public void setJanStartGuaranteedNumerator(int value) {
        this.janStartGuaranteedNumerator = value;
    }

    /**
     * Gets the value of the janStartGuaranteedDenominator property.
     * 
     */
    public int getJanStartGuaranteedDenominator() {
        return janStartGuaranteedDenominator;
    }

    /**
     * Sets the value of the janStartGuaranteedDenominator property.
     * 
     */
    public void setJanStartGuaranteedDenominator(int value) {
        this.janStartGuaranteedDenominator = value;
    }

    /**
     * Gets the value of the totalGuaranteedNumerator property.
     * 
     */
    public int getTotalGuaranteedNumerator() {
        return totalGuaranteedNumerator;
    }

    /**
     * Sets the value of the totalGuaranteedNumerator property.
     * 
     */
    public void setTotalGuaranteedNumerator(int value) {
        this.totalGuaranteedNumerator = value;
    }

    /**
     * Gets the value of the totalGuaranteedDenominator property.
     * 
     */
    public int getTotalGuaranteedDenominator() {
        return totalGuaranteedDenominator;
    }

    /**
     * Sets the value of the totalGuaranteedDenominator property.
     * 
     */
    public void setTotalGuaranteedDenominator(int value) {
        this.totalGuaranteedDenominator = value;
    }

}
