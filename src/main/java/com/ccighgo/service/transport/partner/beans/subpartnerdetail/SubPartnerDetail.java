//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 04:38:27 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *           &lt;element name="subPartnerStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="partnerDetail" type="{http://www.ccighgo.com/subpartnerdetail}Details"/>
 *           &lt;element name="subPartnerPrimaryContact" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersPrimaryContact"/>
 *           &lt;element name="subPartnerPhysicalAddress" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersPhysicalAddress"/>
 *           &lt;element name="subPartnerMailingAddress" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnersMailingAddress"/>
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
    "subPartnerStatus",
    "partnerDetail",
    "subPartnerPrimaryContact",
    "subPartnerPhysicalAddress",
    "subPartnerMailingAddress"
})
public class SubPartnerDetail
    extends Response
{

    @XmlElement(required = true)
    protected String subPartnerStatus;
    @XmlElement(required = true)
    protected Details partnerDetail;
    @XmlElement(required = true)
    protected SubPartnersPrimaryContact subPartnerPrimaryContact;
    @XmlElement(required = true)
    protected SubPartnersPhysicalAddress subPartnerPhysicalAddress;
    @XmlElement(required = true)
    protected SubPartnersMailingAddress subPartnerMailingAddress;

    /**
     * Gets the value of the subPartnerStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerStatus() {
        return subPartnerStatus;
    }

    /**
     * Sets the value of the subPartnerStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerStatus(String value) {
        this.subPartnerStatus = value;
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
