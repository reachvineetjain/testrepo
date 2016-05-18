//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.18 at 02:02:28 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Topic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Topic">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="topicId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="topicTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creator" type="{http://www.ccighgo.com/partadminf1detail}Creator"/>
 *         &lt;element name="addedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="privacy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noteTags" type="{http://www.ccighgo.com/partadminf1detail}NoteTags" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notesCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notes" type="{http://www.ccighgo.com/partadminf1detail}Note" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Topic", propOrder = {
    "topicId",
    "topicTitle",
    "creator",
    "addedOn",
    "privacy",
    "noteTags",
    "notesCount",
    "notes"
})
public class Topic {

    protected int topicId;
    @XmlElement(required = true)
    protected String topicTitle;
    @XmlElement(required = true)
    protected Creator creator;
    @XmlElement(required = true)
    protected String addedOn;
    @XmlElement(required = true)
    protected String privacy;
    protected List<NoteTags> noteTags;
    protected int notesCount;
    @XmlElement(required = true)
    protected List<Note> notes;

    /**
     * Gets the value of the topicId property.
     * 
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * Sets the value of the topicId property.
     * 
     */
    public void setTopicId(int value) {
        this.topicId = value;
    }

    /**
     * Gets the value of the topicTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * Sets the value of the topicTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopicTitle(String value) {
        this.topicTitle = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link Creator }
     *     
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Creator }
     *     
     */
    public void setCreator(Creator value) {
        this.creator = value;
    }

    /**
     * Gets the value of the addedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddedOn() {
        return addedOn;
    }

    /**
     * Sets the value of the addedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddedOn(String value) {
        this.addedOn = value;
    }

    /**
     * Gets the value of the privacy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Sets the value of the privacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacy(String value) {
        this.privacy = value;
    }

    /**
     * Gets the value of the noteTags property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noteTags property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoteTags().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NoteTags }
     * 
     * 
     */
    public List<NoteTags> getNoteTags() {
        if (noteTags == null) {
            noteTags = new ArrayList<NoteTags>();
        }
        return this.noteTags;
    }

    /**
     * Gets the value of the notesCount property.
     * 
     */
    public int getNotesCount() {
        return notesCount;
    }

    /**
     * Sets the value of the notesCount property.
     * 
     */
    public void setNotesCount(int value) {
        this.notesCount = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * 
     * 
     */
    public List<Note> getNotes() {
        if (notes == null) {
            notes = new ArrayList<Note>();
        }
        return this.notes;
    }

}
