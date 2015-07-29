//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 01:56:14 PM CDT 
//


package com.ccighgo.service.transport.season.beans.assignedstates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StateInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stateId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AssignedStateStaff" type="{http://www.ccighgo.com/assignedstates}AssignedStateStaff" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateInfo", propOrder = {
    "stateId",
    "stateCode",
    "assignedStateStaff"
})
public class StateInfo {

    protected int stateId;
    @XmlElement(required = true)
    protected String stateCode;
    @XmlElement(name = "AssignedStateStaff")
    protected List<AssignedStateStaff> assignedStateStaff;

    /**
     * Gets the value of the stateId property.
     * 
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * Sets the value of the stateId property.
     * 
     */
    public void setStateId(int value) {
        this.stateId = value;
    }

    /**
     * Gets the value of the stateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateCode(String value) {
        this.stateCode = value;
    }

    /**
     * Gets the value of the assignedStateStaff property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assignedStateStaff property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssignedStateStaff().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssignedStateStaff }
     * 
     * 
     */
    public List<AssignedStateStaff> getAssignedStateStaff() {
        if (assignedStateStaff == null) {
            assignedStateStaff = new ArrayList<AssignedStateStaff>();
        }
        return this.assignedStateStaff;
    }

}
