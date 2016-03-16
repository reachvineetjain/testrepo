//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.15 at 04:13:26 PM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFDieTrayRestriction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFDieTrayRestriction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provideStudentWithThreeMeals" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="followDietrayRestriction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dietrayRestrictionExplanation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expectStudentFollowDietrayRestriction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="studentFollowDietrayRestrictionExplanation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostStudentWhoFollowDietrayRestriction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFDieTrayRestriction", propOrder = {
    "provideStudentWithThreeMeals",
    "followDietrayRestriction",
    "dietrayRestrictionExplanation",
    "expectStudentFollowDietrayRestriction",
    "studentFollowDietrayRestrictionExplanation",
    "hostStudentWhoFollowDietrayRestriction"
})
public class HFDieTrayRestriction {

    protected boolean provideStudentWithThreeMeals;
    protected boolean followDietrayRestriction;
    @XmlElement(required = true)
    protected String dietrayRestrictionExplanation;
    protected boolean expectStudentFollowDietrayRestriction;
    @XmlElement(required = true)
    protected String studentFollowDietrayRestrictionExplanation;
    protected boolean hostStudentWhoFollowDietrayRestriction;

    /**
     * Gets the value of the provideStudentWithThreeMeals property.
     * 
     */
    public boolean isProvideStudentWithThreeMeals() {
        return provideStudentWithThreeMeals;
    }

    /**
     * Sets the value of the provideStudentWithThreeMeals property.
     * 
     */
    public void setProvideStudentWithThreeMeals(boolean value) {
        this.provideStudentWithThreeMeals = value;
    }

    /**
     * Gets the value of the followDietrayRestriction property.
     * 
     */
    public boolean isFollowDietrayRestriction() {
        return followDietrayRestriction;
    }

    /**
     * Sets the value of the followDietrayRestriction property.
     * 
     */
    public void setFollowDietrayRestriction(boolean value) {
        this.followDietrayRestriction = value;
    }

    /**
     * Gets the value of the dietrayRestrictionExplanation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDietrayRestrictionExplanation() {
        return dietrayRestrictionExplanation;
    }

    /**
     * Sets the value of the dietrayRestrictionExplanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDietrayRestrictionExplanation(String value) {
        this.dietrayRestrictionExplanation = value;
    }

    /**
     * Gets the value of the expectStudentFollowDietrayRestriction property.
     * 
     */
    public boolean isExpectStudentFollowDietrayRestriction() {
        return expectStudentFollowDietrayRestriction;
    }

    /**
     * Sets the value of the expectStudentFollowDietrayRestriction property.
     * 
     */
    public void setExpectStudentFollowDietrayRestriction(boolean value) {
        this.expectStudentFollowDietrayRestriction = value;
    }

    /**
     * Gets the value of the studentFollowDietrayRestrictionExplanation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentFollowDietrayRestrictionExplanation() {
        return studentFollowDietrayRestrictionExplanation;
    }

    /**
     * Sets the value of the studentFollowDietrayRestrictionExplanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentFollowDietrayRestrictionExplanation(String value) {
        this.studentFollowDietrayRestrictionExplanation = value;
    }

    /**
     * Gets the value of the hostStudentWhoFollowDietrayRestriction property.
     * 
     */
    public boolean isHostStudentWhoFollowDietrayRestriction() {
        return hostStudentWhoFollowDietrayRestriction;
    }

    /**
     * Sets the value of the hostStudentWhoFollowDietrayRestriction property.
     * 
     */
    public void setHostStudentWhoFollowDietrayRestriction(boolean value) {
        this.hostStudentWhoFollowDietrayRestriction = value;
    }

}
