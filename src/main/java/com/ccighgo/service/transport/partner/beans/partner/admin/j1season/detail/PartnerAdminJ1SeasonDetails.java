//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.18 at 02:02:17 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerAdminJ1SeasonDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminJ1SeasonDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="partnerAgencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="partnerSeasonProgramName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="partnerSeasonStatus" type="{http://www.ccighgo.com/partadminj1detail}PartnerSeasonStatus"/>
 *           &lt;element name="seasonStatus" type="{http://www.ccighgo.com/partadminj1detail}SeasonStatus"/>
 *           &lt;element name="partnerActiveForSeason" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="partnerSeasonDetails" type="{http://www.ccighgo.com/partadminj1detail}PartnerSeasonDetails"/>
 *           &lt;element name="programAllocations" type="{http://www.ccighgo.com/partadminj1detail}ProgramAllocations"/>
 *           &lt;element name="dates" type="{http://www.ccighgo.com/partadminj1detail}Dates"/>
 *           &lt;element name="operatingAgreements" type="{http://www.ccighgo.com/partadminj1detail}OperatingAgreements"/>
 *           &lt;element name="documents" type="{http://www.ccighgo.com/partadminj1detail}Documents"/>
 *           &lt;element name="partnerSeasonNotes" type="{http://www.ccighgo.com/partadminj1detail}NoteTopics"/>
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
@XmlType(name = "PartnerAdminJ1SeasonDetails", propOrder = {
    "partnerSeasonId",
    "partnerAgencyName",
    "partnerSeasonProgramName",
    "partnerSeasonStatus",
    "seasonStatus",
    "partnerActiveForSeason",
    "partnerSeasonDetails",
    "programAllocations",
    "dates",
    "operatingAgreements",
    "documents",
    "partnerSeasonNotes"
})
public class PartnerAdminJ1SeasonDetails
    extends Response
{

    protected int partnerSeasonId;
    @XmlElement(required = true)
    protected String partnerAgencyName;
    @XmlElement(required = true)
    protected String partnerSeasonProgramName;
    @XmlElement(required = true)
    protected PartnerSeasonStatus partnerSeasonStatus;
    @XmlElement(required = true)
    protected SeasonStatus seasonStatus;
    protected boolean partnerActiveForSeason;
    @XmlElement(required = true)
    protected PartnerSeasonDetails partnerSeasonDetails;
    @XmlElement(required = true)
    protected ProgramAllocations programAllocations;
    @XmlElement(required = true)
    protected Dates dates;
    @XmlElement(required = true)
    protected OperatingAgreements operatingAgreements;
    @XmlElement(required = true)
    protected Documents documents;
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
     * Gets the value of the partnerAgencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerAgencyName() {
        return partnerAgencyName;
    }

    /**
     * Sets the value of the partnerAgencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerAgencyName(String value) {
        this.partnerAgencyName = value;
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
     * Gets the value of the partnerActiveForSeason property.
     * 
     */
    public boolean isPartnerActiveForSeason() {
        return partnerActiveForSeason;
    }

    /**
     * Sets the value of the partnerActiveForSeason property.
     * 
     */
    public void setPartnerActiveForSeason(boolean value) {
        this.partnerActiveForSeason = value;
    }

    /**
     * Gets the value of the partnerSeasonDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerSeasonDetails }
     *     
     */
    public PartnerSeasonDetails getPartnerSeasonDetails() {
        return partnerSeasonDetails;
    }

    /**
     * Sets the value of the partnerSeasonDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerSeasonDetails }
     *     
     */
    public void setPartnerSeasonDetails(PartnerSeasonDetails value) {
        this.partnerSeasonDetails = value;
    }

    /**
     * Gets the value of the programAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link ProgramAllocations }
     *     
     */
    public ProgramAllocations getProgramAllocations() {
        return programAllocations;
    }

    /**
     * Sets the value of the programAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramAllocations }
     *     
     */
    public void setProgramAllocations(ProgramAllocations value) {
        this.programAllocations = value;
    }

    /**
     * Gets the value of the dates property.
     * 
     * @return
     *     possible object is
     *     {@link Dates }
     *     
     */
    public Dates getDates() {
        return dates;
    }

    /**
     * Sets the value of the dates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dates }
     *     
     */
    public void setDates(Dates value) {
        this.dates = value;
    }

    /**
     * Gets the value of the operatingAgreements property.
     * 
     * @return
     *     possible object is
     *     {@link OperatingAgreements }
     *     
     */
    public OperatingAgreements getOperatingAgreements() {
        return operatingAgreements;
    }

    /**
     * Sets the value of the operatingAgreements property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingAgreements }
     *     
     */
    public void setOperatingAgreements(OperatingAgreements value) {
        this.operatingAgreements = value;
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
