//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 01:56:08 PM CDT 
//


package com.ccighgo.service.transport.season.beans.assignedregion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignedRDStaff complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedRDStaff">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldStaffId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="assignedArea" type="{http://www.ccighgo.com/assignedregion}RegionAssignedArea"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="photo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedRDStaff", propOrder = {
    "fieldStaffId",
    "assignedArea",
    "firstName",
    "lastName",
    "photo"
})
public class AssignedRDStaff {

    protected int fieldStaffId;
    @XmlElement(required = true)
    protected RegionAssignedArea assignedArea;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String photo;

    /**
     * Gets the value of the fieldStaffId property.
     * 
     */
    public int getFieldStaffId() {
        return fieldStaffId;
    }

    /**
     * Sets the value of the fieldStaffId property.
     * 
     */
    public void setFieldStaffId(int value) {
        this.fieldStaffId = value;
    }

    /**
     * Gets the value of the assignedArea property.
     * 
     * @return
     *     possible object is
     *     {@link RegionAssignedArea }
     *     
     */
    public RegionAssignedArea getAssignedArea() {
        return assignedArea;
    }

    /**
     * Sets the value of the assignedArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionAssignedArea }
     *     
     */
    public void setAssignedArea(RegionAssignedArea value) {
        this.assignedArea = value;
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
     * Gets the value of the photo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the value of the photo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoto(String value) {
        this.photo = value;
    }

}