//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.09 at 10:54:59 AM CST 
//


package com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for fieldStaffStatuses complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fieldStaffStatuses">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="fieldStaffStatuses" type="{http://www.ccighgo.com/fieldstaffoverview}fieldStaffStatus" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fieldStaffStatuses", propOrder = {
    "fieldStaffStatuses"
})
public class FieldStaffStatuses
    extends Response
{

    @XmlElement(required = true)
    protected List<FieldStaffStatus> fieldStaffStatuses;

    /**
     * Gets the value of the fieldStaffStatuses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldStaffStatuses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldStaffStatuses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldStaffStatus }
     * 
     * 
     */
    public List<FieldStaffStatus> getFieldStaffStatuses() {
        if (fieldStaffStatuses == null) {
            fieldStaffStatuses = new ArrayList<FieldStaffStatus>();
        }
        return this.fieldStaffStatuses;
    }

}