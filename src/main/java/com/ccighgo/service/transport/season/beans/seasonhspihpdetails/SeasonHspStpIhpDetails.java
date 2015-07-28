//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:47 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspihpdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonHspStpIhpDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonHspStpIhpDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ihpNameAndStatus" type="{http://www.ccighgo.com/stpihp}IHPNameAndStatus"/>
 *         &lt;element name="ihpDates" type="{http://www.ccighgo.com/stpihp}IHPDates"/>
 *         &lt;element name="ihpProgramConfiguration" type="{http://www.ccighgo.com/stpihp}IHPProgramConfiguration"/>
 *         &lt;element name="ihpDocuments" type="{http://www.ccighgo.com/stpihp}IHPDocuments" maxOccurs="unbounded"/>
 *         &lt;element name="ihpNotes" type="{http://www.ccighgo.com/stpihp}IHPNotes" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonHspStpIhpDetails", propOrder = {
    "seasonId",
    "seasonProgramId",
    "departmentProgramId",
    "ihpNameAndStatus",
    "ihpDates",
    "ihpProgramConfiguration",
    "ihpDocuments",
    "ihpNotes"
})
public class SeasonHspStpIhpDetails {

    protected int seasonId;
    protected int seasonProgramId;
    protected Integer departmentProgramId;
    @XmlElement(required = true)
    protected IHPNameAndStatus ihpNameAndStatus;
    @XmlElement(required = true)
    protected IHPDates ihpDates;
    @XmlElement(required = true)
    protected IHPProgramConfiguration ihpProgramConfiguration;
    @XmlElement(required = true)
    protected List<IHPDocuments> ihpDocuments;
    @XmlElement(required = true)
    protected List<IHPNotes> ihpNotes;

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
     * Gets the value of the ihpNameAndStatus property.
     * 
     * @return
     *     possible object is
     *     {@link IHPNameAndStatus }
     *     
     */
    public IHPNameAndStatus getIhpNameAndStatus() {
        return ihpNameAndStatus;
    }

    /**
     * Sets the value of the ihpNameAndStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link IHPNameAndStatus }
     *     
     */
    public void setIhpNameAndStatus(IHPNameAndStatus value) {
        this.ihpNameAndStatus = value;
    }

    /**
     * Gets the value of the ihpDates property.
     * 
     * @return
     *     possible object is
     *     {@link IHPDates }
     *     
     */
    public IHPDates getIhpDates() {
        return ihpDates;
    }

    /**
     * Sets the value of the ihpDates property.
     * 
     * @param value
     *     allowed object is
     *     {@link IHPDates }
     *     
     */
    public void setIhpDates(IHPDates value) {
        this.ihpDates = value;
    }

    /**
     * Gets the value of the ihpProgramConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link IHPProgramConfiguration }
     *     
     */
    public IHPProgramConfiguration getIhpProgramConfiguration() {
        return ihpProgramConfiguration;
    }

    /**
     * Sets the value of the ihpProgramConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link IHPProgramConfiguration }
     *     
     */
    public void setIhpProgramConfiguration(IHPProgramConfiguration value) {
        this.ihpProgramConfiguration = value;
    }

    /**
     * Gets the value of the ihpDocuments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ihpDocuments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIhpDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IHPDocuments }
     * 
     * 
     */
    public List<IHPDocuments> getIhpDocuments() {
        if (ihpDocuments == null) {
            ihpDocuments = new ArrayList<IHPDocuments>();
        }
        return this.ihpDocuments;
    }

    /**
     * Gets the value of the ihpNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ihpNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIhpNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IHPNotes }
     * 
     * 
     */
    public List<IHPNotes> getIhpNotes() {
        if (ihpNotes == null) {
            ihpNotes = new ArrayList<IHPNotes>();
        }
        return this.ihpNotes;
    }

}
