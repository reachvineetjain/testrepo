//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.05 at 06:07:53 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Photo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Photo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="photoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="typeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="filePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isOptional" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Photo", propOrder = {
    "photoId",
    "typeId",
    "filePath",
    "description",
    "isOptional"
})
public class Photo {

    protected int photoId;
    protected int typeId;
    @XmlElement(required = true)
    protected String filePath;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String isOptional;

    /**
     * Gets the value of the photoId property.
     * 
     */
    public int getPhotoId() {
        return photoId;
    }

    /**
     * Sets the value of the photoId property.
     * 
     */
    public void setPhotoId(int value) {
        this.photoId = value;
    }

    /**
     * Gets the value of the typeId property.
     * 
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets the value of the typeId property.
     * 
     */
    public void setTypeId(int value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the filePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the value of the filePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePath(String value) {
        this.filePath = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the isOptional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsOptional() {
        return isOptional;
    }

    /**
     * Sets the value of the isOptional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsOptional(String value) {
        this.isOptional = value;
    }

}
