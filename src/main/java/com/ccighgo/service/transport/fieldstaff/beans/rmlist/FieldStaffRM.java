//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.05 at 03:11:06 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.rmlist;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldStaffRM complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldStaffRM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="picture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fsStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rmSeasonContacts" type="{http://www.ccighgo.com/fsrmlist}RMSeasonContact" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldStaffRM", propOrder = {
    "goId",
    "picture",
    "firstName",
    "lastName",
    "phone",
    "email",
    "city",
    "state",
    "zip",
    "active",
    "fsStatus",
    "rmSeasonContacts"
})
public class FieldStaffRM {

    protected int goId;
    @XmlElement(required = true)
    protected String picture;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String phone;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    protected String zip;
    protected int active;
    @XmlElement(required = true)
    protected String fsStatus;
    protected List<RMSeasonContact> rmSeasonContacts;

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
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicture(String value) {
        this.picture = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public int getActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(int value) {
        this.active = value;
    }

    /**
     * Gets the value of the fsStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsStatus() {
        return fsStatus;
    }

    /**
     * Sets the value of the fsStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsStatus(String value) {
        this.fsStatus = value;
    }

    /**
     * Gets the value of the rmSeasonContacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rmSeasonContacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRmSeasonContacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RMSeasonContact }
     * 
     * 
     */
    public List<RMSeasonContact> getRmSeasonContacts() {
        if (rmSeasonContacts == null) {
            rmSeasonContacts = new ArrayList<RMSeasonContact>();
        }
        return this.rmSeasonContacts;
    }

}
