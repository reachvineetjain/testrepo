//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.15 at 12:13:56 AM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserPermissions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserPermissions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permisstionGroupId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="permisstionGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissionGroupOptions" type="{http://www.ccighgo.com/user}PermissionGroupOptions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserPermissions", propOrder = {
    "permisstionGroupId",
    "permisstionGroupName",
    "permissionGroupOptions"
})
public class UserPermissions {

    protected int permisstionGroupId;
    @XmlElement(required = true)
    protected String permisstionGroupName;
    protected List<PermissionGroupOptions> permissionGroupOptions;

    /**
     * Gets the value of the permisstionGroupId property.
     * 
     */
    public int getPermisstionGroupId() {
        return permisstionGroupId;
    }

    /**
     * Sets the value of the permisstionGroupId property.
     * 
     */
    public void setPermisstionGroupId(int value) {
        this.permisstionGroupId = value;
    }

    /**
     * Gets the value of the permisstionGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermisstionGroupName() {
        return permisstionGroupName;
    }

    /**
     * Sets the value of the permisstionGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermisstionGroupName(String value) {
        this.permisstionGroupName = value;
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
     * {@link PermissionGroupOptions }
     * 
     * 
     */
    public List<PermissionGroupOptions> getPermissionGroupOptions() {
        if (permissionGroupOptions == null) {
            permissionGroupOptions = new ArrayList<PermissionGroupOptions>();
        }
        return this.permissionGroupOptions;
    }

}
