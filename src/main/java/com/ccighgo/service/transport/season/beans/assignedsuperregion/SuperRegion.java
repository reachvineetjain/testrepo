//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 05:54:59 PM IST 
//


package com.ccighgo.service.transport.season.beans.assignedsuperregion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SuperRegion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SuperRegion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="superRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="superRegionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonGeographyConfigurationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AssignedERDStaff" type="{http://www.ccighgo.com/assignedsuperregion}AssignedERDStaff" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuperRegion", propOrder = {
    "superRegionId",
    "superRegionName",
    "seasonGeographyConfigurationId",
    "assignedERDStaff"
})
public class SuperRegion {

    protected int superRegionId;
    @XmlElement(required = true)
    protected String superRegionName;
    protected int seasonGeographyConfigurationId;
    @XmlElement(name = "AssignedERDStaff")
    protected List<AssignedERDStaff> assignedERDStaff;

    /**
     * Gets the value of the superRegionId property.
     * 
     */
    public int getSuperRegionId() {
        return superRegionId;
    }

    /**
     * Sets the value of the superRegionId property.
     * 
     */
    public void setSuperRegionId(int value) {
        this.superRegionId = value;
    }

    /**
     * Gets the value of the superRegionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperRegionName() {
        return superRegionName;
    }

    /**
     * Sets the value of the superRegionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperRegionName(String value) {
        this.superRegionName = value;
    }

    /**
     * Gets the value of the seasonGeographyConfigurationId property.
     * 
     */
    public int getSeasonGeographyConfigurationId() {
        return seasonGeographyConfigurationId;
    }

    /**
     * Sets the value of the seasonGeographyConfigurationId property.
     * 
     */
    public void setSeasonGeographyConfigurationId(int value) {
        this.seasonGeographyConfigurationId = value;
    }

    /**
     * Gets the value of the assignedERDStaff property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assignedERDStaff property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssignedERDStaff().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssignedERDStaff }
     * 
     * 
     */
    public List<AssignedERDStaff> getAssignedERDStaff() {
        if (assignedERDStaff == null) {
            assignedERDStaff = new ArrayList<AssignedERDStaff>();
        }
        return this.assignedERDStaff;
    }

}
