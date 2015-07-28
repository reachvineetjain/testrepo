//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:48 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonwpdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonWPDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonWPDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="wpBasicDetail" type="{http://www.ccighgo.com/wp}WPBasicDetail"/>
 *         &lt;element name="wpSectionOne" type="{http://www.ccighgo.com/wp}WPSectionOne"/>
 *         &lt;element name="wpProgramAllocations" type="{http://www.ccighgo.com/wp}WPProgramAllocations"/>
 *         &lt;element name="wpDocuments" type="{http://www.ccighgo.com/wp}WPDocuments" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wpNotes" type="{http://www.ccighgo.com/wp}WPNotes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonWPDetails", propOrder = {
    "seasonId",
    "seasonProgramId",
    "departmentProgramId",
    "wpBasicDetail",
    "wpSectionOne",
    "wpProgramAllocations",
    "wpDocuments",
    "wpNotes"
})
public class SeasonWPDetails {

    protected int seasonId;
    protected int seasonProgramId;
    protected int departmentProgramId;
    @XmlElement(required = true)
    protected WPBasicDetail wpBasicDetail;
    @XmlElement(required = true)
    protected WPSectionOne wpSectionOne;
    @XmlElement(required = true)
    protected WPProgramAllocations wpProgramAllocations;
    protected List<WPDocuments> wpDocuments;
    protected List<WPNotes> wpNotes;

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
     */
    public int getDepartmentProgramId() {
        return departmentProgramId;
    }

    /**
     * Sets the value of the departmentProgramId property.
     * 
     */
    public void setDepartmentProgramId(int value) {
        this.departmentProgramId = value;
    }

    /**
     * Gets the value of the wpBasicDetail property.
     * 
     * @return
     *     possible object is
     *     {@link WPBasicDetail }
     *     
     */
    public WPBasicDetail getWpBasicDetail() {
        return wpBasicDetail;
    }

    /**
     * Sets the value of the wpBasicDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPBasicDetail }
     *     
     */
    public void setWpBasicDetail(WPBasicDetail value) {
        this.wpBasicDetail = value;
    }

    /**
     * Gets the value of the wpSectionOne property.
     * 
     * @return
     *     possible object is
     *     {@link WPSectionOne }
     *     
     */
    public WPSectionOne getWpSectionOne() {
        return wpSectionOne;
    }

    /**
     * Sets the value of the wpSectionOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPSectionOne }
     *     
     */
    public void setWpSectionOne(WPSectionOne value) {
        this.wpSectionOne = value;
    }

    /**
     * Gets the value of the wpProgramAllocations property.
     * 
     * @return
     *     possible object is
     *     {@link WPProgramAllocations }
     *     
     */
    public WPProgramAllocations getWpProgramAllocations() {
        return wpProgramAllocations;
    }

    /**
     * Sets the value of the wpProgramAllocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link WPProgramAllocations }
     *     
     */
    public void setWpProgramAllocations(WPProgramAllocations value) {
        this.wpProgramAllocations = value;
    }

    /**
     * Gets the value of the wpDocuments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wpDocuments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWpDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WPDocuments }
     * 
     * 
     */
    public List<WPDocuments> getWpDocuments() {
        if (wpDocuments == null) {
            wpDocuments = new ArrayList<WPDocuments>();
        }
        return this.wpDocuments;
    }

    /**
     * Gets the value of the wpNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wpNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWpNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WPNotes }
     * 
     * 
     */
    public List<WPNotes> getWpNotes() {
        if (wpNotes == null) {
            wpNotes = new ArrayList<WPNotes>();
        }
        return this.wpNotes;
    }

}
