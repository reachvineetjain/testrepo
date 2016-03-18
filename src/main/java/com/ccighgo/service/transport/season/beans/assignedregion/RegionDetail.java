//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.10 at 11:17:38 AM IST 
//


package com.ccighgo.service.transport.season.beans.assignedregion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegionDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegionDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="regionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonGeographyConfigurationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="assignedRDStaff" type="{http://www.ccighgo.com/assignedregion}AssignedRDStaff" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionDetail", propOrder = {
    "regionId",
    "regionName",
    "seasonGeographyConfigurationId",
    "assignedRDStaff"
})
public class RegionDetail {

    protected int regionId;
    @XmlElement(required = true)
    protected String regionName;
    protected int seasonGeographyConfigurationId;
    protected List<AssignedRDStaff> assignedRDStaff;

    /**
     * Gets the value of the regionId property.
     * 
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * Sets the value of the regionId property.
     * 
     */
    public void setRegionId(int value) {
        this.regionId = value;
    }

    /**
     * Gets the value of the regionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Sets the value of the regionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionName(String value) {
        this.regionName = value;
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
     * Gets the value of the assignedRDStaff property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assignedRDStaff property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssignedRDStaff().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssignedRDStaff }
     * 
     * 
     */
    public List<AssignedRDStaff> getAssignedRDStaff() {
        if (assignedRDStaff == null) {
            assignedRDStaff = new ArrayList<AssignedRDStaff>();
        }
        return this.assignedRDStaff;
    }

}
