//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 04:03:10 PM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerRecruitmentAdminScreeningNotes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerRecruitmentAdminScreeningNotes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonDepartmentProgramNotetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noteId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noteValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="public" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerRecruitmentAdminScreeningNotes", propOrder = {
    "seasonId",
    "seasonDepartmentProgramNotetId",
    "noteId",
    "noteValue",
    "_public",
    "createdOn",
    "createdBy"
})
public class PartnerRecruitmentAdminScreeningNotes
    extends Response
{

    protected int seasonId;
    protected int seasonDepartmentProgramNotetId;
    protected int noteId;
    protected String noteValue;
    @XmlElement(name = "public")
    protected Boolean _public;
    @XmlElement(required = true)
    protected String createdOn;
    @XmlElement(required = true)
    protected String createdBy;

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
     * Gets the value of the seasonDepartmentProgramNotetId property.
     * 
     */
    public int getSeasonDepartmentProgramNotetId() {
        return seasonDepartmentProgramNotetId;
    }

    /**
     * Sets the value of the seasonDepartmentProgramNotetId property.
     * 
     */
    public void setSeasonDepartmentProgramNotetId(int value) {
        this.seasonDepartmentProgramNotetId = value;
    }

    /**
     * Gets the value of the noteId property.
     * 
     */
    public int getNoteId() {
        return noteId;
    }

    /**
     * Sets the value of the noteId property.
     * 
     */
    public void setNoteId(int value) {
        this.noteId = value;
    }

    /**
     * Gets the value of the noteValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteValue() {
        return noteValue;
    }

    /**
     * Sets the value of the noteValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteValue(String value) {
        this.noteValue = value;
    }

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPublic(Boolean value) {
        this._public = value;
    }

    /**
     * Gets the value of the createdOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the value of the createdOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedOn(String value) {
        this.createdOn = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

}
