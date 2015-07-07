//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.07 at 03:09:12 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonHspJ1HSDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonHspJ1HSDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="j1hsBasicDetail" type="{http://www.ccighgo.com/j1hs}J1HSBasicDetail"/>
 *         &lt;element name="j1hsJanStart" type="{http://www.ccighgo.com/j1hs}J1HSJanStart"/>
 *         &lt;element name="j1hsAugStart" type="{http://www.ccighgo.com/j1hs}J1HSAugStart"/>
 *         &lt;element name="j1hsFieldSettings" type="{http://www.ccighgo.com/j1hs}J1HSFieldSettings"/>
 *         &lt;element name="j1hsProgramAllocations" type="{http://www.ccighgo.com/j1hs}J1HSProgramAllocations"/>
 *         &lt;element name="j1hsDocuments" type="{http://www.ccighgo.com/j1hs}J1HSDocuments" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="j1hsNotes" type="{http://www.ccighgo.com/j1hs}J1HSNotes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonHspJ1HSDetails", propOrder = {
    "seasonId",
    "seasonProgramId",
    "departmentProgramId",
    "j1HsBasicDetail",
    "j1HsJanStart",
    "j1HsAugStart",
    "j1HsFieldSettings",
    "j1HsProgramAllocations",
    "j1HsDocuments",
    "j1HsNotes"
})
public class SeasonHspJ1HSDetails {

    protected int seasonId;
    protected int seasonProgramId;
    protected Integer departmentProgramId;
    @XmlElement(name = "j1hsBasicDetail", required = true)
    protected J1HSBasicDetail j1HsBasicDetail;
    @XmlElement(name = "j1hsJanStart", required = true)
    protected J1HSJanStart j1HsJanStart;
    @XmlElement(name = "j1hsAugStart", required = true)
    protected J1HSAugStart j1HsAugStart;
    @XmlElement(name = "j1hsFieldSettings", required = true)
    protected J1HSFieldSettings j1HsFieldSettings;
    @XmlElement(name = "j1hsProgramAllocations", required = true)
    protected J1HSProgramAllocations j1HsProgramAllocations;
    @XmlElement(name = "j1hsDocuments")
    protected List<J1HSDocuments> j1HsDocuments;
    @XmlElement(name = "j1hsNotes")
    protected List<J1HSNotes> j1HsNotes;

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
     * Gets the value of the departmentProgramId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDepartmentProgramId() {
        return departmentProgramId;
    }

    /**
     * Sets the value of the departmentProgramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDepartmentProgramId(Integer value) {
        this.departmentProgramId = value;
    }

    /**
     * Gets the value of the j1HsBasicDetail property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSBasicDetail }
     *     
     */
    public J1HSBasicDetail getJ1HsBasicDetail() {
        return j1HsBasicDetail;
    }

    /**
     * Sets the value of the j1HsBasicDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSBasicDetail }
     *     
     */
    public void setJ1HsBasicDetail(J1HSBasicDetail value) {
        this.j1HsBasicDetail = value;
    }

    /**
     * Gets the value of the j1HsJanStart property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSJanStart }
     *     
     */
    public J1HSJanStart getJ1HsJanStart() {
        return j1HsJanStart;
    }

    /**
     * Sets the value of the j1HsJanStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSJanStart }
     *     
     */
    public void setJ1HsJanStart(J1HSJanStart value) {
        this.j1HsJanStart = value;
    }

    /**
     * Gets the value of the j1HsAugStart property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSAugStart }
     *     
     */
    public J1HSAugStart getJ1HsAugStart() {
        return j1HsAugStart;
    }

    /**
     * Sets the value of the j1HsAugStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSAugStart }
     *     
     */
    public void setJ1HsAugStart(J1HSAugStart value) {
        this.j1HsAugStart = value;
    }

    /**
     * Gets the value of the j1HsFieldSettings property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSFieldSettings }
     *     
     */
    public J1HSFieldSettings getJ1HsFieldSettings() {
        return j1HsFieldSettings;
    }

    /**
     * Sets the value of the j1HsFieldSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSFieldSettings }
     *     
     */
    public void setJ1HsFieldSettings(J1HSFieldSettings value) {
        this.j1HsFieldSettings = value;
    }

    /**
     * Gets the value of the j1HsProgramAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link J1HSProgramAllocations }
     *     
     */
    public J1HSProgramAllocations getJ1HsProgramAllocations() {
        return j1HsProgramAllocations;
    }

    /**
     * Sets the value of the j1HsProgramAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link J1HSProgramAllocations }
     *     
     */
    public void setJ1HsProgramAllocations(J1HSProgramAllocations value) {
        this.j1HsProgramAllocations = value;
    }

    /**
     * Gets the value of the j1HsDocuments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the j1HsDocuments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJ1HsDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link J1HSDocuments }
     * 
     * 
     */
    public List<J1HSDocuments> getJ1HsDocuments() {
        if (j1HsDocuments == null) {
            j1HsDocuments = new ArrayList<J1HSDocuments>();
        }
        return this.j1HsDocuments;
    }

    /**
     * Gets the value of the j1HsNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the j1HsNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJ1HsNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link J1HSNotes }
     * 
     * 
     */
    public List<J1HSNotes> getJ1HsNotes() {
        if (j1HsNotes == null) {
            j1HsNotes = new ArrayList<J1HSNotes>();
        }
        return this.j1HsNotes;
    }

}
