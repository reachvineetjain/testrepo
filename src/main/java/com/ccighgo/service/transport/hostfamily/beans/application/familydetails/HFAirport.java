//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.22 at 05:43:06 PM CST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFAirport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFAirport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="airport" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="distanceToNearestAirport" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFAirport", propOrder = {
    "goId",
    "airport",
    "distanceToNearestAirport"
})
public class HFAirport {

    protected int goId;
    protected boolean airport;
    protected int distanceToNearestAirport;

    /**
     * Gets the value of the goId property.
     * 
     */
    public int getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     */
    public void setGoId(int value) {
        this.goId = value;
    }

    /**
     * Gets the value of the airport property.
     * 
     */
    public boolean isAirport() {
        return airport;
    }

    /**
     * Sets the value of the airport property.
     * 
     */
    public void setAirport(boolean value) {
        this.airport = value;
    }

    /**
     * Gets the value of the distanceToNearestAirport property.
     * 
     */
    public int getDistanceToNearestAirport() {
        return distanceToNearestAirport;
    }

    /**
     * Sets the value of the distanceToNearestAirport property.
     * 
     */
    public void setDistanceToNearestAirport(int value) {
        this.distanceToNearestAirport = value;
    }

}
