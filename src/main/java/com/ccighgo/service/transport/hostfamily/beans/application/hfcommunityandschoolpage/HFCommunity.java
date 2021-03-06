//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.07 at 12:04:22 PM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFCommunity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFCommunity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="populationOfTheTown" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="cityOrTownWebSite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="nearestMajorCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="populationOfNearestCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="distanceFromCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="uniqueAboutYourCommunity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="placesAndEventsMightInterestTheStudent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="areasToBeAvoidedInTheNeighbourhood" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="volunteeringOpportunitiesInTheCommunity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="hostFamilySeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="hostFamilyCommunityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "HFCommunity", propOrder = {
    "populationOfTheTown",
    "cityOrTownWebSite",
    "nearestMajorCity",
    "populationOfNearestCity",
    "distanceFromCity",
    "uniqueAboutYourCommunity",
    "placesAndEventsMightInterestTheStudent",
    "areasToBeAvoidedInTheNeighbourhood",
    "volunteeringOpportunitiesInTheCommunity",
    "hostFamilySeasonId",
    "hostFamilyCommunityId"
})
public class HFCommunity {

    @XmlElement(required = true)
    protected String populationOfTheTown;
    @XmlElement(required = true)
    protected String cityOrTownWebSite;
    @XmlElement(required = true)
    protected String nearestMajorCity;
    @XmlElement(required = true)
    protected String populationOfNearestCity;
    @XmlElement(required = true)
    protected String distanceFromCity;
    @XmlElement(required = true)
    protected String uniqueAboutYourCommunity;
    @XmlElement(required = true)
    protected String placesAndEventsMightInterestTheStudent;
    @XmlElement(required = true)
    protected String areasToBeAvoidedInTheNeighbourhood;
    @XmlElement(required = true)
    protected String volunteeringOpportunitiesInTheCommunity;
    protected int hostFamilySeasonId;
    protected int hostFamilyCommunityId;

    /**
     * Gets the value of the populationOfTheTown property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopulationOfTheTown() {
        return populationOfTheTown;
    }

    /**
     * Sets the value of the populationOfTheTown property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopulationOfTheTown(String value) {
        this.populationOfTheTown = value;
    }

    /**
     * Gets the value of the cityOrTownWebSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityOrTownWebSite() {
        return cityOrTownWebSite;
    }

    /**
     * Sets the value of the cityOrTownWebSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityOrTownWebSite(String value) {
        this.cityOrTownWebSite = value;
    }

    /**
     * Gets the value of the nearestMajorCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNearestMajorCity() {
        return nearestMajorCity;
    }

    /**
     * Sets the value of the nearestMajorCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNearestMajorCity(String value) {
        this.nearestMajorCity = value;
    }

    /**
     * Gets the value of the populationOfNearestCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopulationOfNearestCity() {
        return populationOfNearestCity;
    }

    /**
     * Sets the value of the populationOfNearestCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopulationOfNearestCity(String value) {
        this.populationOfNearestCity = value;
    }

    /**
     * Gets the value of the distanceFromCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistanceFromCity() {
        return distanceFromCity;
    }

    /**
     * Sets the value of the distanceFromCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistanceFromCity(String value) {
        this.distanceFromCity = value;
    }

    /**
     * Gets the value of the uniqueAboutYourCommunity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueAboutYourCommunity() {
        return uniqueAboutYourCommunity;
    }

    /**
     * Sets the value of the uniqueAboutYourCommunity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueAboutYourCommunity(String value) {
        this.uniqueAboutYourCommunity = value;
    }

    /**
     * Gets the value of the placesAndEventsMightInterestTheStudent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacesAndEventsMightInterestTheStudent() {
        return placesAndEventsMightInterestTheStudent;
    }

    /**
     * Sets the value of the placesAndEventsMightInterestTheStudent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacesAndEventsMightInterestTheStudent(String value) {
        this.placesAndEventsMightInterestTheStudent = value;
    }

    /**
     * Gets the value of the areasToBeAvoidedInTheNeighbourhood property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreasToBeAvoidedInTheNeighbourhood() {
        return areasToBeAvoidedInTheNeighbourhood;
    }

    /**
     * Sets the value of the areasToBeAvoidedInTheNeighbourhood property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreasToBeAvoidedInTheNeighbourhood(String value) {
        this.areasToBeAvoidedInTheNeighbourhood = value;
    }

    /**
     * Gets the value of the volunteeringOpportunitiesInTheCommunity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolunteeringOpportunitiesInTheCommunity() {
        return volunteeringOpportunitiesInTheCommunity;
    }

    /**
     * Sets the value of the volunteeringOpportunitiesInTheCommunity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolunteeringOpportunitiesInTheCommunity(String value) {
        this.volunteeringOpportunitiesInTheCommunity = value;
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
     * Gets the value of the hostFamilyCommunityId property.
     * 
     */
    public int getHostFamilyCommunityId() {
        return hostFamilyCommunityId;
    }

    /**
     * Sets the value of the hostFamilyCommunityId property.
     * 
     */
    public void setHostFamilyCommunityId(int value) {
        this.hostFamilyCommunityId = value;
    }

}
