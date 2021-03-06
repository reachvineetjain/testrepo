//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.07 at 11:56:47 AM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.deafultpermissions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StaffUserDefaultPermissions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StaffUserDefaultPermissions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permissionGroupId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="permissionGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissionGroupOptions" type="{http://www.ccighgo.com/rolepermissions}StaffUserDefaultPermissionGroupOptions" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaffUserDefaultPermissions", propOrder = {
      "departmentId",
      "acronym",
      "departmentName",
      "active",
      "permissionGroupId",
      "permissionGroupName",
      "permissionGroupOptions"
    
})
public class StaffUserDefaultPermissions {

    protected int permissionGroupId;
    @XmlElement(required = true)
    protected String permissionGroupName;
    @XmlElement(required = true)
    protected List<StaffUserDefaultPermissionGroupOptions> permissionGroupOptions;
    @XmlElement(required = true)
    protected int departmentId;
    @XmlElement(required = true)
    protected String acronym;
    @XmlElement(required = true)
    protected String departmentName;
    @XmlElement(required = true)
    protected boolean active;
    /**
     * Gets the value of the permissionGroupId property.
     * 
     */
    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    /**
     * Sets the value of the permissionGroupId property.
     * 
     */
    public void setPermissionGroupId(int value) {
        this.permissionGroupId = value;
    }

    /**
     * Gets the value of the permissionGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    /**
     * Sets the value of the permissionGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionGroupName(String value) {
        this.permissionGroupName = value;
    }

    /**
     * Gets the value of the permissionGroupOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissionGroupOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissionGroupOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffUserDefaultPermissionGroupOptions }
     * 
     * 
     */
    public List<StaffUserDefaultPermissionGroupOptions> getPermissionGroupOptions() {
        if (permissionGroupOptions == null) {
            permissionGroupOptions = new ArrayList<StaffUserDefaultPermissionGroupOptions>();
        }
        return this.permissionGroupOptions;
    }

   public int getDepartmentId() {
      return departmentId;
   }

   public void setDepartmentId(int departmentId) {
      this.departmentId = departmentId;
   }

   public String getAcronym() {
      return acronym;
   }

   public void setAcronym(String acronym) {
      this.acronym = acronym;
   }

   public String getDepartmentName() {
      return departmentName;
   }

   public void setDepartmentName(String departmentName) {
      this.departmentName = departmentName;
   }

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

}
