//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.25 at 10:50:25 AM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerRecruitmentAdminScreeningPrograms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdminScreeningPrograms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applied" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="notify" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="markedBy" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningMarkedByUser"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdminScreeningPrograms", propOrder = {
    "programName",
    "applied",
    "notify",
    "action",
    "markedBy"
})
public class PartnerRecruitmentAdminScreeningPrograms {

    @XmlElement(required = true)
    protected String programName;
    protected boolean applied;
    protected boolean notify;
    @XmlElement(required = true)
    protected String action;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminScreeningMarkedByUser markedBy;

    /**
     * Gets the value of the programName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * Sets the value of the programName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramName(String value) {
        this.programName = value;
    }

    /**
     * Gets the value of the applied property.
     * 
     */
    public boolean isApplied() {
        return applied;
    }

    /**
     * Sets the value of the applied property.
     * 
     */
    public void setApplied(boolean value) {
        this.applied = value;
    }

    /**
     * Gets the value of the notify property.
     * 
     */
    public boolean isNotify() {
        return notify;
    }

    /**
     * Sets the value of the notify property.
     * 
     */
    public void setNotify(boolean value) {
        this.notify = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the markedBy property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAdminScreeningMarkedByUser }
     *     
     */
    public PartnerRecruitmentAdminScreeningMarkedByUser getMarkedBy() {
        return markedBy;
    }

    /**
     * Sets the value of the markedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAdminScreeningMarkedByUser }
     *     
     */
    public void setMarkedBy(PartnerRecruitmentAdminScreeningMarkedByUser value) {
        this.markedBy = value;
    }

}
