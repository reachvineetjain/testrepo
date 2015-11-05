//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:33:21 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for SubPartnerDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartnerDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *           &lt;element name="partnerDetail" type="{http://www.ccighgo.com/subpartnerdetail}Details" minOccurs="0"/>
 *           &lt;element name="subPartnerPrimaryContact" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersPrimaryContact" minOccurs="0"/>
 *           &lt;element name="subPartnerPhysicalAddress" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersPhysicalAddress" minOccurs="0"/>
 *           &lt;element name="subPartnerMailingAddress" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersMailingAddress" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerDetail", propOrder = {
    "goId",
    "active",
    "partnerDetail",
    "subPartnerPrimaryContact",
    "subPartnerPhysicalAddress",
    "subPartnerMailingAddress"
})
public class SubPartnerDetail
    extends Response
{

    protected String goId;
    protected Boolean active;
    protected Details partnerDetail;
    protected SubPartnersPrimaryContact subPartnerPrimaryContact;
    protected SubPartnersPhysicalAddress subPartnerPhysicalAddress;
    protected SubPartnersMailingAddress subPartnerMailingAddress;

    /**
     * Gets the value of the goId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoId(String value) {
        this.goId = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the partnerDetail property.
     * 
     * @return
     *     possible object is
     *     {@link Details }
     *     
     */
    public Details getPartnerDetail() {
        return partnerDetail;
    }

    /**
     * Sets the value of the partnerDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Details }
     *     
     */
    public void setPartnerDetail(Details value) {
        this.partnerDetail = value;
    }

    /**
     * Gets the value of the subPartnerPrimaryContact property.
     * 
     * @return
     *     possible object is
     *     {@link SubPartnersPrimaryContact }
     *     
     */
    public SubPartnersPrimaryContact getSubPartnerPrimaryContact() {
        return subPartnerPrimaryContact;
    }

    /**
     * Sets the value of the subPartnerPrimaryContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubPartnersPrimaryContact }
     *     
     */
    public void setSubPartnerPrimaryContact(SubPartnersPrimaryContact value) {
        this.subPartnerPrimaryContact = value;
    }

    /**
     * Gets the value of the subPartnerPhysicalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link SubPartnersPhysicalAddress }
     *     
     */
    public SubPartnersPhysicalAddress getSubPartnerPhysicalAddress() {
        return subPartnerPhysicalAddress;
    }

    /**
     * Sets the value of the subPartnerPhysicalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubPartnersPhysicalAddress }
     *     
     */
    public void setSubPartnerPhysicalAddress(SubPartnersPhysicalAddress value) {
        this.subPartnerPhysicalAddress = value;
    }

    /**
     * Gets the value of the subPartnerMailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link SubPartnersMailingAddress }
     *     
     */
    public SubPartnersMailingAddress getSubPartnerMailingAddress() {
        return subPartnerMailingAddress;
    }

    /**
     * Sets the value of the subPartnerMailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubPartnersMailingAddress }
     *     
     */
    public void setSubPartnerMailingAddress(SubPartnersMailingAddress value) {
        this.subPartnerMailingAddress = value;
    }

}
