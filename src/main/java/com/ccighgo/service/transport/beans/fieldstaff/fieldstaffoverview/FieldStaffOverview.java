//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.17 at 04:31:48 PM IST 
//


package com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for FieldStaffOverview complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldStaffOverview">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="lastLoginDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fieldStaffStatus" type="{http://www.ccighgo.com/fsoverview}FieldStaffStatus"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fieldStaffDetails" type="{http://www.ccighgo.com/fsoverview}FieldStaffDetails"/>
 *         &lt;element name="documents" type="{http://www.ccighgo.com/fsoverview}Documents"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldStaffOverview", propOrder = {
    "lastLoginDate",
    "fieldStaffStatus",
    "active",
    "fieldStaffDetails",
    "documents"
})
public class FieldStaffOverview
    extends Response
{

    @XmlElement(required = true)
    protected String lastLoginDate;
    @XmlElement(required = true)
    protected FieldStaffStatus fieldStaffStatus;
    protected boolean active;
    @XmlElement(required = true)
    protected FieldStaffDetails fieldStaffDetails;
    @XmlElement(required = true)
    protected Documents documents;

    /**
     * Gets the value of the lastLoginDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets the value of the lastLoginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastLoginDate(String value) {
        this.lastLoginDate = value;
    }

    /**
     * Gets the value of the fieldStaffStatus property.
     * 
     * @return
     *     possible object is
     *     {@link FieldStaffStatus }
     *     
     */
    public FieldStaffStatus getFieldStaffStatus() {
        return fieldStaffStatus;
    }

    /**
     * Sets the value of the fieldStaffStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldStaffStatus }
     *     
     */
    public void setFieldStaffStatus(FieldStaffStatus value) {
        this.fieldStaffStatus = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the fieldStaffDetails property.
     * 
     * @return
     *     possible object is
     *     {@link FieldStaffDetails }
     *     
     */
    public FieldStaffDetails getFieldStaffDetails() {
        return fieldStaffDetails;
    }

    /**
     * Sets the value of the fieldStaffDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldStaffDetails }
     *     
     */
    public void setFieldStaffDetails(FieldStaffDetails value) {
        this.fieldStaffDetails = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link Documents }
     *     
     */
    public Documents getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documents }
     *     
     */
    public void setDocuments(Documents value) {
        this.documents = value;
    }

}
