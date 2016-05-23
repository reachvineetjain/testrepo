//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.18 at 02:02:28 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProgramAllocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProgramAllocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramOptionJANFYId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramOptionAUGFYId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augStartMaxGuaranteedPax" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augStartTotalAllocated" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augStartPaxApproved" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartMaxGuaranteedPax" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartTotalAllocated" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="janStartPaxApproved" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramAllocations", propOrder = {
    "seasonId",
    "seasonProgramId",
    "departmentProgramOptionJANFYId",
    "departmentProgramOptionAUGFYId",
    "augStartMaxGuaranteedPax",
    "augStartTotalAllocated",
    "augStartPaxApproved",
    "janStartMaxGuaranteedPax",
    "janStartTotalAllocated",
    "janStartPaxApproved"
})
public class ProgramAllocations {

    protected int seasonId;
    protected int seasonProgramId;
    protected int departmentProgramOptionJANFYId;
    protected int departmentProgramOptionAUGFYId;
    protected int augStartMaxGuaranteedPax;
    protected int augStartTotalAllocated;
    protected int augStartPaxApproved;
    protected int janStartMaxGuaranteedPax;
    protected int janStartTotalAllocated;
    protected int janStartPaxApproved;

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
     * Gets the value of the departmentProgramOptionJANFYId property.
     * 
     */
    public int getDepartmentProgramOptionJANFYId() {
        return departmentProgramOptionJANFYId;
    }

    /**
     * Sets the value of the departmentProgramOptionJANFYId property.
     * 
     */
    public void setDepartmentProgramOptionJANFYId(int value) {
        this.departmentProgramOptionJANFYId = value;
    }

    /**
     * Gets the value of the departmentProgramOptionAUGFYId property.
     * 
     */
    public int getDepartmentProgramOptionAUGFYId() {
        return departmentProgramOptionAUGFYId;
    }

    /**
     * Sets the value of the departmentProgramOptionAUGFYId property.
     * 
     */
    public void setDepartmentProgramOptionAUGFYId(int value) {
        this.departmentProgramOptionAUGFYId = value;
    }

    /**
     * Gets the value of the augStartMaxGuaranteedPax property.
     * 
     */
    public int getAugStartMaxGuaranteedPax() {
        return augStartMaxGuaranteedPax;
    }

    /**
     * Sets the value of the augStartMaxGuaranteedPax property.
     * 
     */
    public void setAugStartMaxGuaranteedPax(int value) {
        this.augStartMaxGuaranteedPax = value;
    }

    /**
     * Gets the value of the augStartTotalAllocated property.
     * 
     */
    public int getAugStartTotalAllocated() {
        return augStartTotalAllocated;
    }

    /**
     * Sets the value of the augStartTotalAllocated property.
     * 
     */
    public void setAugStartTotalAllocated(int value) {
        this.augStartTotalAllocated = value;
    }

    /**
     * Gets the value of the augStartPaxApproved property.
     * 
     */
    public int getAugStartPaxApproved() {
        return augStartPaxApproved;
    }

    /**
     * Sets the value of the augStartPaxApproved property.
     * 
     */
    public void setAugStartPaxApproved(int value) {
        this.augStartPaxApproved = value;
    }

    /**
     * Gets the value of the janStartMaxGuaranteedPax property.
     * 
     */
    public int getJanStartMaxGuaranteedPax() {
        return janStartMaxGuaranteedPax;
    }

    /**
     * Sets the value of the janStartMaxGuaranteedPax property.
     * 
     */
    public void setJanStartMaxGuaranteedPax(int value) {
        this.janStartMaxGuaranteedPax = value;
    }

    /**
     * Gets the value of the janStartTotalAllocated property.
     * 
     */
    public int getJanStartTotalAllocated() {
        return janStartTotalAllocated;
    }

    /**
     * Sets the value of the janStartTotalAllocated property.
     * 
     */
    public void setJanStartTotalAllocated(int value) {
        this.janStartTotalAllocated = value;
    }

    /**
     * Gets the value of the janStartPaxApproved property.
     * 
     */
    public int getJanStartPaxApproved() {
        return janStartPaxApproved;
    }

    /**
     * Sets the value of the janStartPaxApproved property.
     * 
     */
    public void setJanStartPaxApproved(int value) {
        this.janStartPaxApproved = value;
    }

}
