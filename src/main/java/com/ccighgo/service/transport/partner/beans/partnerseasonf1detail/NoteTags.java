//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 12:57:32 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasonf1detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteTags complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NoteTags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="noteTagId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noteTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteTags", propOrder = {
    "noteTagId",
    "noteTag"
})
public class NoteTags {

    protected int noteTagId;
    @XmlElement(required = true)
    protected String noteTag;

    /**
     * Gets the value of the noteTagId property.
     * 
     */
    public int getNoteTagId() {
        return noteTagId;
    }

    /**
     * Sets the value of the noteTagId property.
     * 
     */
    public void setNoteTagId(int value) {
        this.noteTagId = value;
    }

    /**
     * Gets the value of the noteTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteTag() {
        return noteTag;
    }

    /**
     * Sets the value of the noteTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteTag(String value) {
        this.noteTag = value;
    }

}
