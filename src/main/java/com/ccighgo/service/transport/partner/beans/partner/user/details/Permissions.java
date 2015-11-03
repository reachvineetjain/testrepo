//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.02 at 11:43:11 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.user.details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Permissions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Permissions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="applications" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="flights" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="placementInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="monitoring" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="accounting" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="studentsPreProgram" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="contracting" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="insurance" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Permissions", propOrder = {
    "admin",
    "applications",
    "flights",
    "placementInfo",
    "monitoring",
    "accounting",
    "studentsPreProgram",
    "contracting",
    "insurance"
})
public class Permissions {

    protected boolean admin;
    protected boolean applications;
    protected boolean flights;
    protected boolean placementInfo;
    protected boolean monitoring;
    protected boolean accounting;
    protected boolean studentsPreProgram;
    protected boolean contracting;
    protected boolean insurance;

    /**
     * Gets the value of the admin property.
     * 
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the value of the admin property.
     * 
     */
    public void setAdmin(boolean value) {
        this.admin = value;
    }

    /**
     * Gets the value of the applications property.
     * 
     */
    public boolean isApplications() {
        return applications;
    }

    /**
     * Sets the value of the applications property.
     * 
     */
    public void setApplications(boolean value) {
        this.applications = value;
    }

    /**
     * Gets the value of the flights property.
     * 
     */
    public boolean isFlights() {
        return flights;
    }

    /**
     * Sets the value of the flights property.
     * 
     */
    public void setFlights(boolean value) {
        this.flights = value;
    }

    /**
     * Gets the value of the placementInfo property.
     * 
     */
    public boolean isPlacementInfo() {
        return placementInfo;
    }

    /**
     * Sets the value of the placementInfo property.
     * 
     */
    public void setPlacementInfo(boolean value) {
        this.placementInfo = value;
    }

    /**
     * Gets the value of the monitoring property.
     * 
     */
    public boolean isMonitoring() {
        return monitoring;
    }

    /**
     * Sets the value of the monitoring property.
     * 
     */
    public void setMonitoring(boolean value) {
        this.monitoring = value;
    }

    /**
     * Gets the value of the accounting property.
     * 
     */
    public boolean isAccounting() {
        return accounting;
    }

    /**
     * Sets the value of the accounting property.
     * 
     */
    public void setAccounting(boolean value) {
        this.accounting = value;
    }

    /**
     * Gets the value of the studentsPreProgram property.
     * 
     */
    public boolean isStudentsPreProgram() {
        return studentsPreProgram;
    }

    /**
     * Sets the value of the studentsPreProgram property.
     * 
     */
    public void setStudentsPreProgram(boolean value) {
        this.studentsPreProgram = value;
    }

    /**
     * Gets the value of the contracting property.
     * 
     */
    public boolean isContracting() {
        return contracting;
    }

    /**
     * Sets the value of the contracting property.
     * 
     */
    public void setContracting(boolean value) {
        this.contracting = value;
    }

    /**
     * Gets the value of the insurance property.
     * 
     */
    public boolean isInsurance() {
        return insurance;
    }

    /**
     * Sets the value of the insurance property.
     * 
     */
    public void setInsurance(boolean value) {
        this.insurance = value;
    }

}
