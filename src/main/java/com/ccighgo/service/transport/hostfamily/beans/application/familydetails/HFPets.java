//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.03 at 12:48:06 PM CST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFPets complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFPets">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hostFamilyPetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="petId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="typeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="indoor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="outDoor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="additionalInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFPets", propOrder = {
    "hostFamilyPetId",
    "petId",
    "typeId",
    "number",
    "indoor",
    "outDoor",
    "additionalInfo"
})
public class HFPets {

    protected int hostFamilyPetId;
    protected int petId;
    protected int typeId;
    protected int number;
    protected boolean indoor;
    protected boolean outDoor;
    @XmlElement(required = true)
    protected String additionalInfo;

    /**
     * Gets the value of the hostFamilyPetId property.
     * 
     */
    public int getHostFamilyPetId() {
        return hostFamilyPetId;
    }

    /**
     * Sets the value of the hostFamilyPetId property.
     * 
     */
    public void setHostFamilyPetId(int value) {
        this.hostFamilyPetId = value;
    }

    /**
     * Gets the value of the petId property.
     * 
     */
    public int getPetId() {
        return petId;
    }

    /**
     * Sets the value of the petId property.
     * 
     */
    public void setPetId(int value) {
        this.petId = value;
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
     * Gets the value of the number property.
     * 
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     */
    public void setNumber(int value) {
        this.number = value;
    }

    /**
     * Gets the value of the indoor property.
     * 
     */
    public boolean isIndoor() {
        return indoor;
    }

    /**
     * Sets the value of the indoor property.
     * 
     */
    public void setIndoor(boolean value) {
        this.indoor = value;
    }

    /**
     * Gets the value of the outDoor property.
     * 
     */
    public boolean isOutDoor() {
        return outDoor;
    }

    /**
     * Sets the value of the outDoor property.
     * 
     */
    public void setOutDoor(boolean value) {
        this.outDoor = value;
    }

    /**
     * Gets the value of the additionalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the value of the additionalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInfo(String value) {
        this.additionalInfo = value;
    }

}
