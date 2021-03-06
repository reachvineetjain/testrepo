//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 03:06:42 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasonf1detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerSeasonF1Detail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerSeasonF1Detail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="partnerSeasonProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="partnerSeasonAnnouncement" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerSeasonAnnouncements" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="partnerSeasonStatus" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerSeasonStatus"/>
 *           &lt;element name="seasonStatus" type="{http://www.ccighgo.com/partnerseasonf1detail}SeasonStatus"/>
 *           &lt;element name="partnerActiveSeason" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="partnerDepartment" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerDepartment"/>
 *           &lt;element name="partnerProgram" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerProgram"/>
 *           &lt;element name="partnerHLSeason" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerHLSeason"/>
 *           &lt;element name="insuranceProvidedBy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="sevisFeesPaidBy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="seasonStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="seasonEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="applicationDeadlineDatesAllocations" type="{http://www.ccighgo.com/partnerseasonf1detail}ApplicationDeadlilneDatesF1Allocations"/>
 *           &lt;element name="programAllocations" type="{http://www.ccighgo.com/partnerseasonf1detail}PartnerSeasonF1ProgramAllocations"/>
 *           &lt;element name="partnerSeasonNotes" type="{http://www.ccighgo.com/partnerseasonf1detail}NoteTopics"/>
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
@XmlType(name = "PartnerSeasonF1Detail", propOrder = {
    "partnerSeasonId",
    "partnerSeasonProgramName",
    "partnerSeasonAnnouncement",
    "partnerSeasonStatus",
    "seasonStatus",
    "partnerActiveSeason",
    "partnerDepartment",
    "partnerProgram",
    "partnerHLSeason",
    "insuranceProvidedBy",
    "sevisFeesPaidBy",
    "seasonStartDate",
    "seasonEndDate",
    "applicationDeadlineDatesAllocations",
    "programAllocations",
    "partnerSeasonNotes"
})
public class PartnerSeasonF1Detail
    extends Response
{

    protected int partnerSeasonId;
    @XmlElement(required = true)
    protected String partnerSeasonProgramName;
    protected List<PartnerSeasonAnnouncements> partnerSeasonAnnouncement;
    @XmlElement(required = true)
    protected PartnerSeasonStatus partnerSeasonStatus;
    @XmlElement(required = true)
    protected SeasonStatus seasonStatus;
    protected boolean partnerActiveSeason;
    @XmlElement(required = true)
    protected PartnerDepartment partnerDepartment;
    @XmlElement(required = true)
    protected PartnerProgram partnerProgram;
    @XmlElement(required = true)
    protected PartnerHLSeason partnerHLSeason;
    protected boolean insuranceProvidedBy;
    protected boolean sevisFeesPaidBy;
    @XmlElement(required = true)
    protected String seasonStartDate;
    @XmlElement(required = true)
    protected String seasonEndDate;
    @XmlElement(required = true)
    protected ApplicationDeadlilneDatesF1Allocations applicationDeadlineDatesAllocations;
    @XmlElement(required = true)
    protected PartnerSeasonF1ProgramAllocations programAllocations;
    @XmlElement(required = true)
    protected NoteTopics partnerSeasonNotes;

    /**
     * Gets the value of the partnerSeasonId property.
     * 
     */
    public int getPartnerSeasonId() {
        return partnerSeasonId;
    }

    /**
     * Sets the value of the partnerSeasonId property.
     * 
     */
    public void setPartnerSeasonId(int value) {
        this.partnerSeasonId = value;
    }

    /**
     * Gets the value of the partnerSeasonProgramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonProgramName() {
        return partnerSeasonProgramName;
    }

    /**
     * Sets the value of the partnerSeasonProgramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonProgramName(String value) {
        this.partnerSeasonProgramName = value;
    }

    /**
     * Gets the value of the partnerSeasonAnnouncement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerSeasonAnnouncement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerSeasonAnnouncement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerSeasonAnnouncements }
     * 
     * 
     */
    public List<PartnerSeasonAnnouncements> getPartnerSeasonAnnouncement() {
        if (partnerSeasonAnnouncement == null) {
            partnerSeasonAnnouncement = new ArrayList<PartnerSeasonAnnouncements>();
        }
        return this.partnerSeasonAnnouncement;
    }

    /**
     * Gets the value of the partnerSeasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerSeasonStatus }
     *     
     */
    public PartnerSeasonStatus getPartnerSeasonStatus() {
        return partnerSeasonStatus;
    }

    /**
     * Sets the value of the partnerSeasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerSeasonStatus }
     *     
     */
    public void setPartnerSeasonStatus(PartnerSeasonStatus value) {
        this.partnerSeasonStatus = value;
    }

    /**
     * Gets the value of the seasonStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SeasonStatus }
     *     
     */
    public SeasonStatus getSeasonStatus() {
        return seasonStatus;
    }

    /**
     * Sets the value of the seasonStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeasonStatus }
     *     
     */
    public void setSeasonStatus(SeasonStatus value) {
        this.seasonStatus = value;
    }

    /**
     * Gets the value of the partnerActiveSeason property.
     * 
     */
    public boolean isPartnerActiveSeason() {
        return partnerActiveSeason;
    }

    /**
     * Sets the value of the partnerActiveSeason property.
     * 
     */
    public void setPartnerActiveSeason(boolean value) {
        this.partnerActiveSeason = value;
    }

    /**
     * Gets the value of the partnerDepartment property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerDepartment }
     *     
     */
    public PartnerDepartment getPartnerDepartment() {
        return partnerDepartment;
    }

    /**
     * Sets the value of the partnerDepartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerDepartment }
     *     
     */
    public void setPartnerDepartment(PartnerDepartment value) {
        this.partnerDepartment = value;
    }

    /**
     * Gets the value of the partnerProgram property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerProgram }
     *     
     */
    public PartnerProgram getPartnerProgram() {
        return partnerProgram;
    }

    /**
     * Sets the value of the partnerProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerProgram }
     *     
     */
    public void setPartnerProgram(PartnerProgram value) {
        this.partnerProgram = value;
    }

    /**
     * Gets the value of the partnerHLSeason property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerHLSeason }
     *     
     */
    public PartnerHLSeason getPartnerHLSeason() {
        return partnerHLSeason;
    }

    /**
     * Sets the value of the partnerHLSeason property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerHLSeason }
     *     
     */
    public void setPartnerHLSeason(PartnerHLSeason value) {
        this.partnerHLSeason = value;
    }

    /**
     * Gets the value of the insuranceProvidedBy property.
     * 
     */
    public boolean isInsuranceProvidedBy() {
        return insuranceProvidedBy;
    }

    /**
     * Sets the value of the insuranceProvidedBy property.
     * 
     */
    public void setInsuranceProvidedBy(boolean value) {
        this.insuranceProvidedBy = value;
    }

    /**
     * Gets the value of the sevisFeesPaidBy property.
     * 
     */
    public boolean isSevisFeesPaidBy() {
        return sevisFeesPaidBy;
    }

    /**
     * Sets the value of the sevisFeesPaidBy property.
     * 
     */
    public void setSevisFeesPaidBy(boolean value) {
        this.sevisFeesPaidBy = value;
    }

    /**
     * Gets the value of the seasonStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    /**
     * Sets the value of the seasonStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonStartDate(String value) {
        this.seasonStartDate = value;
    }

    /**
     * Gets the value of the seasonEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonEndDate() {
        return seasonEndDate;
    }

    /**
     * Sets the value of the seasonEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonEndDate(String value) {
        this.seasonEndDate = value;
    }

    /**
     * Gets the value of the applicationDeadlineDatesAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationDeadlilneDatesF1Allocations }
     *     
     */
    public ApplicationDeadlilneDatesF1Allocations getApplicationDeadlineDatesAllocations() {
        return applicationDeadlineDatesAllocations;
    }

    /**
     * Sets the value of the applicationDeadlineDatesAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationDeadlilneDatesF1Allocations }
     *     
     */
    public void setApplicationDeadlineDatesAllocations(ApplicationDeadlilneDatesF1Allocations value) {
        this.applicationDeadlineDatesAllocations = value;
    }

    /**
     * Gets the value of the programAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerSeasonF1ProgramAllocations }
     *     
     */
    public PartnerSeasonF1ProgramAllocations getProgramAllocations() {
        return programAllocations;
    }

    /**
     * Sets the value of the programAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerSeasonF1ProgramAllocations }
     *     
     */
    public void setProgramAllocations(PartnerSeasonF1ProgramAllocations value) {
        this.programAllocations = value;
    }

    /**
     * Gets the value of the partnerSeasonNotes property.
     * 
     * @return
     *     possible object is
     *     {@link NoteTopics }
     *     
     */
    public NoteTopics getPartnerSeasonNotes() {
        return partnerSeasonNotes;
    }

    /**
     * Sets the value of the partnerSeasonNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoteTopics }
     *     
     */
    public void setPartnerSeasonNotes(NoteTopics value) {
        this.partnerSeasonNotes = value;
    }

}
