//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.07 at 04:43:31 PM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.deafultpermissions;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StaffUserRolePermissions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StaffUserRolePermissions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="staffUserDefaultPermissions" type="{http://www.ccighgo.com/rolepermissions}StaffUserDefaultPermissions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaffUserRolePermissions", propOrder = {
    "staffUserDefaultPermissions"
})
public class StaffUserRolePermissions {

    protected List<StaffUserDefaultPermissions> staffUserDefaultPermissions;

    /**
     * Gets the value of the staffUserDefaultPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffUserDefaultPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffUserDefaultPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffUserDefaultPermissions }
     * 
     * 
     */
    public List<StaffUserDefaultPermissions> getStaffUserDefaultPermissions() {
        if (staffUserDefaultPermissions == null) {
            staffUserDefaultPermissions = new ArrayList<StaffUserDefaultPermissions>();
        }
        return this.staffUserDefaultPermissions;
    }

}
