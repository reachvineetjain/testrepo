//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 01:59:10 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeofPartnerUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypeofPartnerUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerUserRoleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerUserRoleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeofPartnerUser", propOrder = {
    "partnerUserRoleId",
    "partnerUserRoleName"
})
public class TypeofPartnerUser {

    protected int partnerUserRoleId;
    @XmlElement(required = true)
    protected String partnerUserRoleName;

    /**
     * Gets the value of the partnerUserRoleId property.
     * 
     */
    public int getPartnerUserRoleId() {
        return partnerUserRoleId;
    }

    /**
     * Sets the value of the partnerUserRoleId property.
     * 
     */
    public void setPartnerUserRoleId(int value) {
        this.partnerUserRoleId = value;
    }

    /**
     * Gets the value of the partnerUserRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUserRoleName() {
        return partnerUserRoleName;
    }

    /**
     * Sets the value of the partnerUserRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUserRoleName(String value) {
        this.partnerUserRoleName = value;
    }

}
