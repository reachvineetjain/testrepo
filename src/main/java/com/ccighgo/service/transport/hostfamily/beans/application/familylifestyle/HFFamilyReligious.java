//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.02 at 05:11:08 PM CST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familylifestyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFFamilyReligious complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFFamilyReligious">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="religious" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="explanation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oftenAttendReligiousMeetings" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferedTheStudentJoinYou" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="diffecultyHostingPersonWithDifferentReligious" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="inviteStudentForReligiousExperience" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFFamilyReligious", propOrder = {
    "religious",
    "explanation",
    "oftenAttendReligiousMeetings",
    "preferedTheStudentJoinYou",
    "diffecultyHostingPersonWithDifferentReligious",
    "inviteStudentForReligiousExperience"
})
public class HFFamilyReligious {

    @XmlElement(required = true)
    protected String religious;
    @XmlElement(required = true)
    protected String explanation;
    @XmlElement(required = true)
    protected String oftenAttendReligiousMeetings;
    @XmlElement(required = true)
    protected String preferedTheStudentJoinYou;
    protected boolean diffecultyHostingPersonWithDifferentReligious;
    protected boolean inviteStudentForReligiousExperience;

    /**
     * Gets the value of the religious property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReligious() {
        return religious;
    }

    /**
     * Sets the value of the religious property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReligious(String value) {
        this.religious = value;
    }

    /**
     * Gets the value of the explanation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Sets the value of the explanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplanation(String value) {
        this.explanation = value;
    }

    /**
     * Gets the value of the oftenAttendReligiousMeetings property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOftenAttendReligiousMeetings() {
        return oftenAttendReligiousMeetings;
    }

    /**
     * Sets the value of the oftenAttendReligiousMeetings property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOftenAttendReligiousMeetings(String value) {
        this.oftenAttendReligiousMeetings = value;
    }

    /**
     * Gets the value of the preferedTheStudentJoinYou property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferedTheStudentJoinYou() {
        return preferedTheStudentJoinYou;
    }

    /**
     * Sets the value of the preferedTheStudentJoinYou property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferedTheStudentJoinYou(String value) {
        this.preferedTheStudentJoinYou = value;
    }

    /**
     * Gets the value of the diffecultyHostingPersonWithDifferentReligious property.
     * 
     */
    public boolean isDiffecultyHostingPersonWithDifferentReligious() {
        return diffecultyHostingPersonWithDifferentReligious;
    }

    /**
     * Sets the value of the diffecultyHostingPersonWithDifferentReligious property.
     * 
     */
    public void setDiffecultyHostingPersonWithDifferentReligious(boolean value) {
        this.diffecultyHostingPersonWithDifferentReligious = value;
    }

    /**
     * Gets the value of the inviteStudentForReligiousExperience property.
     * 
     */
    public boolean isInviteStudentForReligiousExperience() {
        return inviteStudentForReligiousExperience;
    }

    /**
     * Sets the value of the inviteStudentForReligiousExperience property.
     * 
     */
    public void setInviteStudentForReligiousExperience(boolean value) {
        this.inviteStudentForReligiousExperience = value;
    }

}
