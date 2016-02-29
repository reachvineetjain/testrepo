//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.26 at 09:58:39 AM CST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.references;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for HostFamilyReferences complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostFamilyReferences">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostFamilyGenQuestionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fieldsFilled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="percentUpdate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="secondReferenceRelatedToFirst" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="references" type="{http://www.ccighgo.com/hfreferences}Reference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="previouslyHosted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="internet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherWebsites" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="community" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="event" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="magazine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherCommunity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostFamilyReferences", propOrder = {
    "loginId",
    "goId",
    "seasonId",
    "hostFamilyGenQuestionId",
    "fieldsFilled",
    "percentUpdate",
    "secondReferenceRelatedToFirst",
    "references",
    "previouslyHosted",
    "internet",
    "otherWebsites",
    "community",
    "event",
    "magazine",
    "otherCommunity"
})
public class HostFamilyReferences
    extends Response
{

    protected int loginId;
    protected int goId;
    protected int seasonId;
    protected int hostFamilyGenQuestionId;
    protected int fieldsFilled;
    protected double percentUpdate;
    protected boolean secondReferenceRelatedToFirst;
    protected List<Reference> references;
    protected boolean previouslyHosted;
    @XmlElement(required = true)
    protected String internet;
    @XmlElement(required = true)
    protected String otherWebsites;
    @XmlElement(required = true)
    protected String community;
    @XmlElement(required = true)
    protected String event;
    @XmlElement(required = true)
    protected String magazine;
    @XmlElement(required = true)
    protected String otherCommunity;

    /**
     * Gets the value of the loginId property.
     * 
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     */
    public void setLoginId(int value) {
        this.loginId = value;
    }

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
     * Gets the value of the seasonId property.
     * 
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     */
    public void setSeasonId(int value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the hostFamilyGenQuestionId property.
     * 
     */
    public int getHostFamilyGenQuestionId() {
        return hostFamilyGenQuestionId;
    }

    /**
     * Sets the value of the hostFamilyGenQuestionId property.
     * 
     */
    public void setHostFamilyGenQuestionId(int value) {
        this.hostFamilyGenQuestionId = value;
    }

    /**
     * Gets the value of the fieldsFilled property.
     * 
     */
    public int getFieldsFilled() {
        return fieldsFilled;
    }

    /**
     * Sets the value of the fieldsFilled property.
     * 
     */
    public void setFieldsFilled(int value) {
        this.fieldsFilled = value;
    }

    /**
     * Gets the value of the percentUpdate property.
     * 
     */
    public double getPercentUpdate() {
        return percentUpdate;
    }

    /**
     * Sets the value of the percentUpdate property.
     * 
     */
    public void setPercentUpdate(double value) {
        this.percentUpdate = value;
    }

    /**
     * Gets the value of the secondReferenceRelatedToFirst property.
     * 
     */
    public boolean isSecondReferenceRelatedToFirst() {
        return secondReferenceRelatedToFirst;
    }

    /**
     * Sets the value of the secondReferenceRelatedToFirst property.
     * 
     */
    public void setSecondReferenceRelatedToFirst(boolean value) {
        this.secondReferenceRelatedToFirst = value;
    }

    /**
     * Gets the value of the references property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the references property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     * 
     * 
     */
    public List<Reference> getReferences() {
        if (references == null) {
            references = new ArrayList<Reference>();
        }
        return this.references;
    }

    /**
     * Gets the value of the previouslyHosted property.
     * 
     */
    public boolean isPreviouslyHosted() {
        return previouslyHosted;
    }

    /**
     * Sets the value of the previouslyHosted property.
     * 
     */
    public void setPreviouslyHosted(boolean value) {
        this.previouslyHosted = value;
    }

    /**
     * Gets the value of the internet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternet() {
        return internet;
    }

    /**
     * Sets the value of the internet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternet(String value) {
        this.internet = value;
    }

    /**
     * Gets the value of the otherWebsites property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherWebsites() {
        return otherWebsites;
    }

    /**
     * Sets the value of the otherWebsites property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherWebsites(String value) {
        this.otherWebsites = value;
    }

    /**
     * Gets the value of the community property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunity() {
        return community;
    }

    /**
     * Sets the value of the community property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunity(String value) {
        this.community = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvent(String value) {
        this.event = value;
    }

    /**
     * Gets the value of the magazine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMagazine() {
        return magazine;
    }

    /**
     * Sets the value of the magazine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMagazine(String value) {
        this.magazine = value;
    }

    /**
     * Gets the value of the otherCommunity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherCommunity() {
        return otherCommunity;
    }

    /**
     * Sets the value of the otherCommunity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherCommunity(String value) {
        this.otherCommunity = value;
    }

}