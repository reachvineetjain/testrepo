//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.23 at 04:58:57 PM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewNotes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerAdminOverviewNotesDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminOverviewNotesDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerNoteId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonDepartmentNotetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noteValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="topic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdBy" type="{http://www.ccighgo.com/partnerAdminOverviewNotes}NoteUserCreator"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAdminOverviewNotesDetails", propOrder = {
    "goId",
    "partnerNoteId",
    "seasonId",
    "seasonDepartmentNotetId",
    "noteValue",
    "active",
    "topic",
    "createdOn",
    "createdBy"
})
public class PartnerAdminOverviewNotesDetails {

    protected int goId;
    protected int partnerNoteId;
    protected int seasonId;
    protected int seasonDepartmentNotetId;
    protected String noteValue;
    protected Boolean active;
    protected String topic;
    @XmlElement(required = true)
    protected String createdOn;
    @XmlElement(required = true)
    protected NoteUserCreator createdBy;

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
     * Gets the value of the partnerNoteId property.
     * 
     */
    public int getPartnerNoteId() {
        return partnerNoteId;
    }

    /**
     * Sets the value of the partnerNoteId property.
     * 
     */
    public void setPartnerNoteId(int value) {
        this.partnerNoteId = value;
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
     * Gets the value of the seasonDepartmentNotetId property.
     * 
     */
    public int getSeasonDepartmentNotetId() {
        return seasonDepartmentNotetId;
    }

    /**
     * Sets the value of the seasonDepartmentNotetId property.
     * 
     */
    public void setSeasonDepartmentNotetId(int value) {
        this.seasonDepartmentNotetId = value;
    }

    /**
     * Gets the value of the noteValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteValue() {
        return noteValue;
    }

    /**
     * Sets the value of the noteValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteValue(String value) {
        this.noteValue = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the topic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the value of the topic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
        this.topic = value;
    }

    /**
     * Gets the value of the createdOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the value of the createdOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedOn(String value) {
        this.createdOn = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link NoteUserCreator }
     *     
     */
    public NoteUserCreator getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoteUserCreator }
     *     
     */
    public void setCreatedBy(NoteUserCreator value) {
        this.createdBy = value;
    }

}
