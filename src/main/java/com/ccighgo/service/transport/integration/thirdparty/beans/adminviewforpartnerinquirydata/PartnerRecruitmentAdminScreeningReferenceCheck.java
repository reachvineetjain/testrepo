//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 02:55:50 PM IST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerRecruitmentAdminScreeningReferenceCheck complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdminScreeningReferenceCheck">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerCheckReferenceId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="completedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="completedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="approvedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="approvedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latestCopyOfBusinessExpires" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdminScreeningReferenceCheck", propOrder = {
    "partnerCheckReferenceId",
    "completedOn",
    "completedBy",
    "approvedOn",
    "approvedBy",
    "latestCopyOfBusinessExpires",
    "note"
})
public class PartnerRecruitmentAdminScreeningReferenceCheck {

    protected int partnerCheckReferenceId;
    @XmlElement(required = true)
    protected String completedOn;
    @XmlElement(required = true)
    protected String completedBy;
    @XmlElement(required = true)
    protected String approvedOn;
    @XmlElement(required = true)
    protected String approvedBy;
    @XmlElement(required = true)
    protected String latestCopyOfBusinessExpires;
    @XmlElement(required = true)
    protected String note;

    /**
     * Gets the value of the partnerCheckReferenceId property.
     * 
     */
    public int getPartnerCheckReferenceId() {
        return partnerCheckReferenceId;
    }

    /**
     * Sets the value of the partnerCheckReferenceId property.
     * 
     */
    public void setPartnerCheckReferenceId(int value) {
        this.partnerCheckReferenceId = value;
    }

    /**
     * Gets the value of the completedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompletedOn() {
        return completedOn;
    }

    /**
     * Sets the value of the completedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletedOn(String value) {
        this.completedOn = value;
    }

    /**
     * Gets the value of the completedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompletedBy() {
        return completedBy;
    }

    /**
     * Sets the value of the completedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletedBy(String value) {
        this.completedBy = value;
    }

    /**
     * Gets the value of the approvedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedOn() {
        return approvedOn;
    }

    /**
     * Sets the value of the approvedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedOn(String value) {
        this.approvedOn = value;
    }

    /**
     * Gets the value of the approvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Sets the value of the approvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedBy(String value) {
        this.approvedBy = value;
    }

    /**
     * Gets the value of the latestCopyOfBusinessExpires property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestCopyOfBusinessExpires() {
        return latestCopyOfBusinessExpires;
    }

    /**
     * Sets the value of the latestCopyOfBusinessExpires property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestCopyOfBusinessExpires(String value) {
        this.latestCopyOfBusinessExpires = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
