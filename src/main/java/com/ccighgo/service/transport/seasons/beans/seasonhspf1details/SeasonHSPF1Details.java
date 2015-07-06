//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.06 at 09:59:31 AM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonHSPF1Details complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonHSPF1Details">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="details" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1BasicDetails" minOccurs="0"/>
 *         &lt;element name="januaryStart2ndSemesterDetails" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1JanuaryStart2ndSemesterDetails" minOccurs="0"/>
 *         &lt;element name="januaryStartFullYearDetail" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1JanuaryStartFullYearDetail" minOccurs="0"/>
 *         &lt;element name="augustStart1stSemesterDetails" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1AugustStart1stSemesterDetails" minOccurs="0"/>
 *         &lt;element name="augustStartFullYearDetails" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1AugustStartFullYearDetails" minOccurs="0"/>
 *         &lt;element name="fieldSettings" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1FieldSettings" minOccurs="0"/>
 *         &lt;element name="accounting" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1Accounting" minOccurs="0"/>
 *         &lt;element name="programAllocations" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1ProgramAllocations" minOccurs="0"/>
 *         &lt;element name="documents" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1SeasonHspF1Documents" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notes" type="{http://www.ccighgo.com/seasonhspf1details}HSPF1SeasonHspF1Notes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonHSPF1Details", propOrder = {
    "seasonId",
    "seasonProgramId",
    "details",
    "januaryStart2NdSemesterDetails",
    "januaryStartFullYearDetail",
    "augustStart1StSemesterDetails",
    "augustStartFullYearDetails",
    "fieldSettings",
    "accounting",
    "programAllocations",
    "documents",
    "notes"
})
public class SeasonHSPF1Details {

    protected int seasonId;
    protected int seasonProgramId;
    protected HSPF1BasicDetails details;
    @XmlElement(name = "januaryStart2ndSemesterDetails")
    protected HSPF1JanuaryStart2NdSemesterDetails januaryStart2NdSemesterDetails;
    protected HSPF1JanuaryStartFullYearDetail januaryStartFullYearDetail;
    @XmlElement(name = "augustStart1stSemesterDetails")
    protected HSPF1AugustStart1StSemesterDetails augustStart1StSemesterDetails;
    protected HSPF1AugustStartFullYearDetails augustStartFullYearDetails;
    protected HSPF1FieldSettings fieldSettings;
    protected HSPF1Accounting accounting;
    protected HSPF1ProgramAllocations programAllocations;
    protected List<HSPF1SeasonHspF1Documents> documents;
    protected List<HSPF1SeasonHspF1Notes> notes;

    /**
     * Gets the value of the seasonId property.
     * 
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     */
    public void setSeasonId(int value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the seasonProgramId property.
     * 
     */
    public int getSeasonProgramId() {
        return seasonProgramId;
    }

    /**
     * Sets the value of the seasonProgramId property.
     * 
     */
    public void setSeasonProgramId(int value) {
        this.seasonProgramId = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1BasicDetails }
     *     
     */
    public HSPF1BasicDetails getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1BasicDetails }
     *     
     */
    public void setDetails(HSPF1BasicDetails value) {
        this.details = value;
    }

    /**
     * Gets the value of the januaryStart2NdSemesterDetails property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1JanuaryStart2NdSemesterDetails }
     *     
     */
    public HSPF1JanuaryStart2NdSemesterDetails getJanuaryStart2NdSemesterDetails() {
        return januaryStart2NdSemesterDetails;
    }

    /**
     * Sets the value of the januaryStart2NdSemesterDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1JanuaryStart2NdSemesterDetails }
     *     
     */
    public void setJanuaryStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails value) {
        this.januaryStart2NdSemesterDetails = value;
    }

    /**
     * Gets the value of the januaryStartFullYearDetail property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1JanuaryStartFullYearDetail }
     *     
     */
    public HSPF1JanuaryStartFullYearDetail getJanuaryStartFullYearDetail() {
        return januaryStartFullYearDetail;
    }

    /**
     * Sets the value of the januaryStartFullYearDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1JanuaryStartFullYearDetail }
     *     
     */
    public void setJanuaryStartFullYearDetail(HSPF1JanuaryStartFullYearDetail value) {
        this.januaryStartFullYearDetail = value;
    }

    /**
     * Gets the value of the augustStart1StSemesterDetails property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1AugustStart1StSemesterDetails }
     *     
     */
    public HSPF1AugustStart1StSemesterDetails getAugustStart1StSemesterDetails() {
        return augustStart1StSemesterDetails;
    }

    /**
     * Sets the value of the augustStart1StSemesterDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1AugustStart1StSemesterDetails }
     *     
     */
    public void setAugustStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails value) {
        this.augustStart1StSemesterDetails = value;
    }

    /**
     * Gets the value of the augustStartFullYearDetails property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1AugustStartFullYearDetails }
     *     
     */
    public HSPF1AugustStartFullYearDetails getAugustStartFullYearDetails() {
        return augustStartFullYearDetails;
    }

    /**
     * Sets the value of the augustStartFullYearDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1AugustStartFullYearDetails }
     *     
     */
    public void setAugustStartFullYearDetails(HSPF1AugustStartFullYearDetails value) {
        this.augustStartFullYearDetails = value;
    }

    /**
     * Gets the value of the fieldSettings property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1FieldSettings }
     *     
     */
    public HSPF1FieldSettings getFieldSettings() {
        return fieldSettings;
    }

    /**
     * Sets the value of the fieldSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1FieldSettings }
     *     
     */
    public void setFieldSettings(HSPF1FieldSettings value) {
        this.fieldSettings = value;
    }

    /**
     * Gets the value of the accounting property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1Accounting }
     *     
     */
    public HSPF1Accounting getAccounting() {
        return accounting;
    }

    /**
     * Sets the value of the accounting property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1Accounting }
     *     
     */
    public void setAccounting(HSPF1Accounting value) {
        this.accounting = value;
    }

    /**
     * Gets the value of the programAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link HSPF1ProgramAllocations }
     *     
     */
    public HSPF1ProgramAllocations getProgramAllocations() {
        return programAllocations;
    }

    /**
     * Sets the value of the programAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link HSPF1ProgramAllocations }
     *     
     */
    public void setProgramAllocations(HSPF1ProgramAllocations value) {
        this.programAllocations = value;
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
     * {@link HSPF1SeasonHspF1Documents }
     * 
     * 
     */
    public List<HSPF1SeasonHspF1Documents> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<HSPF1SeasonHspF1Documents>();
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
     * {@link HSPF1SeasonHspF1Notes }
     * 
     * 
     */
    public List<HSPF1SeasonHspF1Notes> getNotes() {
        if (notes == null) {
            notes = new ArrayList<HSPF1SeasonHspF1Notes>();
        }
        return this.notes;
    }

}
