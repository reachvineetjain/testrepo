//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 11:47:40 AM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.allhostfamily;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllHostFamilyForFieldStaffDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllHostFamilyForFieldStaffDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hfGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email " type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localCoordinatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localCoordinatorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participants" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasons" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllHostFamilyForFieldStaffDetail", propOrder = {
    "hfGoId",
    "category",
    "address",
    "phone",
    "status",
    "email0020",
    "localCoordinatorName",
    "localCoordinatorId",
    "participants",
    "seasons"
})
public class AllHostFamilyForFieldStaffDetail {

    protected int hfGoId;
    @XmlElement(required = true)
    protected String category;
    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(name = "email ", required = true)
    protected String email0020;
    @XmlElement(required = true)
    protected String localCoordinatorName;
    protected int localCoordinatorId;
    @XmlElement(required = true)
    protected String participants;
    @XmlElement(required = true)
    protected String seasons;

    /**
     * Gets the value of the hfGoId property.
     * 
     */
    public int getHfGoId() {
        return hfGoId;
    }

    /**
     * Sets the value of the hfGoId property.
     * 
     */
    public void setHfGoId(int value) {
        this.hfGoId = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the email0020 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail_0020() {
        return email0020;
    }

    /**
     * Sets the value of the email0020 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail_0020(String value) {
        this.email0020 = value;
    }

    /**
     * Gets the value of the localCoordinatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalCoordinatorName() {
        return localCoordinatorName;
    }

    /**
     * Sets the value of the localCoordinatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalCoordinatorName(String value) {
        this.localCoordinatorName = value;
    }

    /**
     * Gets the value of the localCoordinatorId property.
     * 
     */
    public int getLocalCoordinatorId() {
        return localCoordinatorId;
    }

    /**
     * Sets the value of the localCoordinatorId property.
     * 
     */
    public void setLocalCoordinatorId(int value) {
        this.localCoordinatorId = value;
    }

    /**
     * Gets the value of the participants property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipants() {
        return participants;
    }

    /**
     * Sets the value of the participants property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipants(String value) {
        this.participants = value;
    }

    /**
     * Gets the value of the seasons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasons() {
        return seasons;
    }

    /**
     * Sets the value of the seasons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasons(String value) {
        this.seasons = value;
    }

}
