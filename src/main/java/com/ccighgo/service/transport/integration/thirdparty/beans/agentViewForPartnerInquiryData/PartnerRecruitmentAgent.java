//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.25 at 10:50:37 AM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.agentViewForPartnerInquiryData;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerRecruitmentAgent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAgent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="agentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningDetail"/>
 *         &lt;element name="programs" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningPrograms" maxOccurs="unbounded"/>
 *         &lt;element name="participantProgramOfferings" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningProgramOfferings"/>
 *         &lt;element name="descriptionsOfPrograms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalInformation" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningAdditionalInfo"/>
 *         &lt;element name="messagesToAgent" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningMessagesToAgent" maxOccurs="unbounded"/>
 *         &lt;element name="offices" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningOffices" maxOccurs="unbounded"/>
 *         &lt;element name="referenceCheck" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningReferenceCheck" maxOccurs="unbounded"/>
 *         &lt;element name="documents" type="{http://www.ccighgo.com/agentViewForPartnerInquiryData}PartnerRecruitmentAgentScreeningDocuments" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAgent", propOrder = {
    "agentStatus",
    "detail",
    "programs",
    "participantProgramOfferings",
    "descriptionsOfPrograms",
    "additionalInformation",
    "messagesToAgent",
    "offices",
    "referenceCheck",
    "documents"
})
public class PartnerRecruitmentAgent
    extends Response
{

    @XmlElement(required = true)
    protected String agentStatus;
    @XmlElement(required = true)
    protected PartnerRecruitmentAgentScreeningDetail detail;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAgentScreeningPrograms> programs;
    @XmlElement(required = true)
    protected PartnerRecruitmentAgentScreeningProgramOfferings participantProgramOfferings;
    @XmlElement(required = true)
    protected String descriptionsOfPrograms;
    @XmlElement(required = true)
    protected PartnerRecruitmentAgentScreeningAdditionalInfo additionalInformation;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAgentScreeningMessagesToAgent> messagesToAgent;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAgentScreeningOffices> offices;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAgentScreeningReferenceCheck> referenceCheck;
    @XmlElement(required = true)
    protected List<PartnerRecruitmentAgentScreeningDocuments> documents;

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
     *     {@link PartnerRecruitmentAgentScreeningDetail }
     *     
     */
    public PartnerRecruitmentAgentScreeningDetail getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAgentScreeningDetail }
     *     
     */
    public void setDetail(PartnerRecruitmentAgentScreeningDetail value) {
        this.detail = value;
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
     * {@link PartnerRecruitmentAgentScreeningPrograms }
     * 
     * 
     */
    public List<PartnerRecruitmentAgentScreeningPrograms> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<PartnerRecruitmentAgentScreeningPrograms>();
        }
        return this.programs;
    }

    /**
     * Gets the value of the participantProgramOfferings property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerRecruitmentAgentScreeningProgramOfferings }
     *     
     */
    public PartnerRecruitmentAgentScreeningProgramOfferings getParticipantProgramOfferings() {
        return participantProgramOfferings;
    }

    /**
     * Sets the value of the participantProgramOfferings property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAgentScreeningProgramOfferings }
     *     
     */
    public void setParticipantProgramOfferings(PartnerRecruitmentAgentScreeningProgramOfferings value) {
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
     *     {@link PartnerRecruitmentAgentScreeningAdditionalInfo }
     *     
     */
    public PartnerRecruitmentAgentScreeningAdditionalInfo getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Sets the value of the additionalInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerRecruitmentAgentScreeningAdditionalInfo }
     *     
     */
    public void setAdditionalInformation(PartnerRecruitmentAgentScreeningAdditionalInfo value) {
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
     * {@link PartnerRecruitmentAgentScreeningMessagesToAgent }
     * 
     * 
     */
    public List<PartnerRecruitmentAgentScreeningMessagesToAgent> getMessagesToAgent() {
        if (messagesToAgent == null) {
            messagesToAgent = new ArrayList<PartnerRecruitmentAgentScreeningMessagesToAgent>();
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
     * {@link PartnerRecruitmentAgentScreeningOffices }
     * 
     * 
     */
    public List<PartnerRecruitmentAgentScreeningOffices> getOffices() {
        if (offices == null) {
            offices = new ArrayList<PartnerRecruitmentAgentScreeningOffices>();
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
     * {@link PartnerRecruitmentAgentScreeningReferenceCheck }
     * 
     * 
     */
    public List<PartnerRecruitmentAgentScreeningReferenceCheck> getReferenceCheck() {
        if (referenceCheck == null) {
            referenceCheck = new ArrayList<PartnerRecruitmentAgentScreeningReferenceCheck>();
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
     * {@link PartnerRecruitmentAgentScreeningDocuments }
     * 
     * 
     */
    public List<PartnerRecruitmentAgentScreeningDocuments> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<PartnerRecruitmentAgentScreeningDocuments>();
        }
        return this.documents;
    }

}