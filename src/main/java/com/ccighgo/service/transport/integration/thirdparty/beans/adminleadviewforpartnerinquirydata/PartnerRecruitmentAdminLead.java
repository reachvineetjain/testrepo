//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.19 at 09:59:09 AM CST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerRecruitmentAdminLead complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdminLead">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LeadStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="followUpDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="details" type="{http://www.ccighgo.com/adminLeadViewForPartnerInquiryData}PartnerRecruitmentAdminLeadScreeningDetail"/>
 *         &lt;element name="additionalInformation" type="{http://www.ccighgo.com/adminLeadViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningAdditionalInfo"/>
 *         &lt;element name="notes" type="{http://www.ccighgo.com/adminLeadViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningNotes" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdminLead", propOrder = {
    "goId",
    "leadStatus",
    "followUpDate",
    "details",
    "additionalInformation",
    "notes"
})
public class PartnerRecruitmentAdminLead
    extends Response
{

    protected int goId;
    @XmlElement(name = "LeadStatus", required = true)
    protected String leadStatus;
    @XmlElement(required = true)
    protected String followUpDate;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminLeadScreeningDetail details;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminScreeningAdditionalInfo additionalInformation;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningNotes> notes;

    /**
     * Gets the value of the goId property.
     * 
     */
    public int getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     */
    public void setGoId(int value) {
        this.goId = value;
    }

    /**
     * Gets the value of the leadStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadStatus() {
        return leadStatus;
    }

    /**
     * Sets the value of the leadStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadStatus(String value) {
        this.leadStatus = value;
    }

    /**
     * Gets the value of the followUpDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFollowUpDate() {
        return followUpDate;
    }

    /**
     * Sets the value of the followUpDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFollowUpDate(String value) {
        this.followUpDate = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAdminLeadScreeningDetail }
     *     
     */
    public PartnerRecruitmentAdminLeadScreeningDetail getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAdminLeadScreeningDetail }
     *     
     */
    public void setDetails(PartnerRecruitmentAdminLeadScreeningDetail value) {
        this.details = value;
    }

    /**
     * Gets the value of the additionalInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAdminScreeningAdditionalInfo }
     *     
     */
    public PartnerRecruitmentAdminScreeningAdditionalInfo getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Sets the value of the additionalInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAdminScreeningAdditionalInfo }
     *     
     */
    public void setAdditionalInformation(PartnerRecruitmentAdminScreeningAdditionalInfo value) {
        this.additionalInformation = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningNotes }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningNotes> getNotes() {
        if (notes == null) {
            notes = new ArrayList<PartnerRecruitmentAdminScreeningNotes>();
        }
        return this.notes;
    }

}
