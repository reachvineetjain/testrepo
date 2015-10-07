//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.25 at 10:50:25 AM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerRecruitmentAdmin complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdmin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="LeadStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningDetail"/>
 *         &lt;element name="followUpDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programs" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningPrograms" maxOccurs="unbounded"/>
 *         &lt;element name="participantProgramOfferings" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningProgramOfferings"/>
 *         &lt;element name="descriptionsOfPrograms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalInformation" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningAdditionalInfo"/>
 *         &lt;element name="messagesToAgent" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningMessagesToAgent" maxOccurs="unbounded"/>
 *         &lt;element name="offices" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningOffices" maxOccurs="unbounded"/>
 *         &lt;element name="referenceCheck" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningReferenceCheck" maxOccurs="unbounded"/>
 *         &lt;element name="documents" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningDocuments" maxOccurs="unbounded"/>
 *         &lt;element name="notes" type="{http://www.ccighgo.com/adminViewForPartnerInquiryData}PartnerRecruitmentAdminScreeningNotes" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdmin", propOrder = {
    "leadStatus",
    "agentStatus",
    "detail",
    "followUpDate",
    "programs",
    "participantProgramOfferings",
    "descriptionsOfPrograms",
    "additionalInformation",
    "messagesToAgent",
    "offices",
    "referenceCheck",
    "documents",
    "notes"
})
public class PartnerRecruitmentAdmin
    extends Response
{

    @XmlElement(name = "LeadStatus", required = true)
    protected String leadStatus;
    @XmlElement(required = true)
    protected String agentStatus;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminScreeningDetail detail;
    @XmlElement(required = true)
    protected String followUpDate;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningPrograms> programs;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminScreeningProgramOfferings participantProgramOfferings;
    @XmlElement(required = true)
    protected String descriptionsOfPrograms;
    @XmlElement(required = true)
    protected PartnerRecruitmentAdminScreeningAdditionalInfo additionalInformation;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningMessagesToAgent> messagesToAgent;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningOffices> offices;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningReferenceCheck> referenceCheck;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningDocuments> documents;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAdminScreeningNotes> notes;

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
     * Gets the value of the agentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentStatus() {
        return agentStatus;
    }

    /**
     * Sets the value of the agentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentStatus(String value) {
        this.agentStatus = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAdminScreeningDetail }
     *     
     */
    public PartnerRecruitmentAdminScreeningDetail getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAdminScreeningDetail }
     *     
     */
    public void setDetail(PartnerRecruitmentAdminScreeningDetail value) {
        this.detail = value;
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
     * Gets the value of the programs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningPrograms }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningPrograms> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<PartnerRecruitmentAdminScreeningPrograms>();
        }
        return this.programs;
    }

    /**
     * Gets the value of the participantProgramOfferings property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAdminScreeningProgramOfferings }
     *     
     */
    public PartnerRecruitmentAdminScreeningProgramOfferings getParticipantProgramOfferings() {
        return participantProgramOfferings;
    }

    /**
     * Sets the value of the participantProgramOfferings property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAdminScreeningProgramOfferings }
     *     
     */
    public void setParticipantProgramOfferings(PartnerRecruitmentAdminScreeningProgramOfferings value) {
        this.participantProgramOfferings = value;
    }

    /**
     * Gets the value of the descriptionsOfPrograms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionsOfPrograms() {
        return descriptionsOfPrograms;
    }

    /**
     * Sets the value of the descriptionsOfPrograms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionsOfPrograms(String value) {
        this.descriptionsOfPrograms = value;
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
     * Gets the value of the messagesToAgent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messagesToAgent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessagesToAgent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningMessagesToAgent }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningMessagesToAgent> getMessagesToAgent() {
        if (messagesToAgent == null) {
            messagesToAgent = new ArrayList<PartnerRecruitmentAdminScreeningMessagesToAgent>();
        }
        return this.messagesToAgent;
    }

    /**
     * Gets the value of the offices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOffices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningOffices }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningOffices> getOffices() {
        if (offices == null) {
            offices = new ArrayList<PartnerRecruitmentAdminScreeningOffices>();
        }
        return this.offices;
    }

    /**
     * Gets the value of the referenceCheck property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceCheck property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceCheck().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningReferenceCheck }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningReferenceCheck> getReferenceCheck() {
        if (referenceCheck == null) {
            referenceCheck = new ArrayList<PartnerRecruitmentAdminScreeningReferenceCheck>();
        }
        return this.referenceCheck;
    }

    /**
     * Gets the value of the documents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRecruitmentAdminScreeningDocuments }
     * 
     * 
     */
    public List<PartnerRecruitmentAdminScreeningDocuments> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<PartnerRecruitmentAdminScreeningDocuments>();
        }
        return this.documents;
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
