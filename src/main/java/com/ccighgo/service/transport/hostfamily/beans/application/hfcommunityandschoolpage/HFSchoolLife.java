//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.27 at 12:48:35 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFSchoolLife complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFSchoolLife">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="studentWillGotoSchoolBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="distanceBetweenSchoolAndHome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="provideSpecialTransformation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="specialTransformationDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="familyChildEnrolledInTheSameSchool" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="activitiesChildrenInvolvedInAtSchool" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="contactedCoatchForParticularAthleticAbility" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="althleticAbilityDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="anyMemberTeachOrCoachAtSchool" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="hostFamilySeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="hostFamilySchoolLifeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFSchoolLife", propOrder = {
    "studentWillGotoSchoolBy",
    "distanceBetweenSchoolAndHome",
    "provideSpecialTransformation",
    "specialTransformationDetails",
    "familyChildEnrolledInTheSameSchool",
    "activitiesChildrenInvolvedInAtSchool",
    "contactedCoatchForParticularAthleticAbility",
    "althleticAbilityDetails",
    "anyMemberTeachOrCoachAtSchool",
    "hostFamilySeasonId",
    "hostFamilySchoolLifeId"
})
public class HFSchoolLife {

    @XmlElement(required = true)
    protected String studentWillGotoSchoolBy;
    @XmlElement(required = true)
    protected String distanceBetweenSchoolAndHome;
    protected boolean provideSpecialTransformation;
    @XmlElement(required = true)
    protected String specialTransformationDetails;
    @XmlElement(required = true)
    protected String familyChildEnrolledInTheSameSchool;
    @XmlElement(required = true)
    protected String activitiesChildrenInvolvedInAtSchool;
    protected boolean contactedCoatchForParticularAthleticAbility;
    @XmlElement(required = true)
    protected String althleticAbilityDetails;
    protected boolean anyMemberTeachOrCoachAtSchool;
    protected int hostFamilySeasonId;
    protected int hostFamilySchoolLifeId;

    /**
     * Gets the value of the studentWillGotoSchoolBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentWillGotoSchoolBy() {
        return studentWillGotoSchoolBy;
    }

    /**
     * Sets the value of the studentWillGotoSchoolBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentWillGotoSchoolBy(String value) {
        this.studentWillGotoSchoolBy = value;
    }

    /**
     * Gets the value of the distanceBetweenSchoolAndHome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistanceBetweenSchoolAndHome() {
        return distanceBetweenSchoolAndHome;
    }

    /**
     * Sets the value of the distanceBetweenSchoolAndHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistanceBetweenSchoolAndHome(String value) {
        this.distanceBetweenSchoolAndHome = value;
    }

    /**
     * Gets the value of the provideSpecialTransformation property.
     * 
     */
    public boolean isProvideSpecialTransformation() {
        return provideSpecialTransformation;
    }

    /**
     * Sets the value of the provideSpecialTransformation property.
     * 
     */
    public void setProvideSpecialTransformation(boolean value) {
        this.provideSpecialTransformation = value;
    }

    /**
     * Gets the value of the specialTransformationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialTransformationDetails() {
        return specialTransformationDetails;
    }

    /**
     * Sets the value of the specialTransformationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialTransformationDetails(String value) {
        this.specialTransformationDetails = value;
    }

    /**
     * Gets the value of the familyChildEnrolledInTheSameSchool property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyChildEnrolledInTheSameSchool() {
        return familyChildEnrolledInTheSameSchool;
    }

    /**
     * Sets the value of the familyChildEnrolledInTheSameSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyChildEnrolledInTheSameSchool(String value) {
        this.familyChildEnrolledInTheSameSchool = value;
    }

    /**
     * Gets the value of the activitiesChildrenInvolvedInAtSchool property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivitiesChildrenInvolvedInAtSchool() {
        return activitiesChildrenInvolvedInAtSchool;
    }

    /**
     * Sets the value of the activitiesChildrenInvolvedInAtSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivitiesChildrenInvolvedInAtSchool(String value) {
        this.activitiesChildrenInvolvedInAtSchool = value;
    }

    /**
     * Gets the value of the contactedCoatchForParticularAthleticAbility property.
     * 
     */
    public boolean isContactedCoatchForParticularAthleticAbility() {
        return contactedCoatchForParticularAthleticAbility;
    }

    /**
     * Sets the value of the contactedCoatchForParticularAthleticAbility property.
     * 
     */
    public void setContactedCoatchForParticularAthleticAbility(boolean value) {
        this.contactedCoatchForParticularAthleticAbility = value;
    }

    /**
     * Gets the value of the althleticAbilityDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlthleticAbilityDetails() {
        return althleticAbilityDetails;
    }

    /**
     * Sets the value of the althleticAbilityDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlthleticAbilityDetails(String value) {
        this.althleticAbilityDetails = value;
    }

    /**
     * Gets the value of the anyMemberTeachOrCoachAtSchool property.
     * 
     */
    public boolean isAnyMemberTeachOrCoachAtSchool() {
        return anyMemberTeachOrCoachAtSchool;
    }

    /**
     * Sets the value of the anyMemberTeachOrCoachAtSchool property.
     * 
     */
    public void setAnyMemberTeachOrCoachAtSchool(boolean value) {
        this.anyMemberTeachOrCoachAtSchool = value;
    }

    /**
     * Gets the value of the hostFamilySeasonId property.
     * 
     */
    public int getHostFamilySeasonId() {
        return hostFamilySeasonId;
    }

    /**
     * Sets the value of the hostFamilySeasonId property.
     * 
     */
    public void setHostFamilySeasonId(int value) {
        this.hostFamilySeasonId = value;
    }

    /**
     * Gets the value of the hostFamilySchoolLifeId property.
     * 
     */
    public int getHostFamilySchoolLifeId() {
        return hostFamilySchoolLifeId;
    }

    /**
     * Sets the value of the hostFamilySchoolLifeId property.
     * 
     */
    public void setHostFamilySchoolLifeId(int value) {
        this.hostFamilySchoolLifeId = value;
    }

}
