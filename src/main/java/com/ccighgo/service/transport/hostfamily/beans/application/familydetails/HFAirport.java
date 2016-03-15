//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.15 at 04:13:18 PM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="hostFamilyAirportId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="airportId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="airport" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="distanceToNearestAirport" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "hostFamilyAirportId",
    "airportId",
    "airport",
    "distanceToNearestAirport",
    "city"
})
public class HFAirport {

    protected int hostFamilyAirportId;
    protected int airportId;
    protected boolean airport;
    protected int distanceToNearestAirport;
    @XmlElement(required = true)
    protected String city;

    /**
     * Gets the value of the hostFamilyAirportId property.
     * 
     */
    public int getHostFamilyAirportId() {
        return hostFamilyAirportId;
    }

    /**
     * Sets the value of the hostFamilyAirportId property.
     * 
     */
    public void setHostFamilyAirportId(int value) {
        this.hostFamilyAirportId = value;
    }

    /**
     * Gets the value of the airportId property.
     * 
     */
    public int getAirportId() {
        return airportId;
    }

    /**
     * Sets the value of the airportId property.
     * 
     */
    public void setAirportId(int value) {
        this.airportId = value;
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

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

}
