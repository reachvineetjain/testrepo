//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 09:38:16 AM CST 
//


package com.ccighgo.service.transport.season.beans.seasonprgnote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for SeasonProgramNote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonProgramNote">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramNotesId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noteValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonProgramNote", propOrder = {
    "seasonId",
    "seasonProgramId",
    "seasonProgramNotesId",
    "departmentProgramId",
    "noteValue",
    "active",
    "createdOn",
    "createdBy",
    "loginId"
})
public class SeasonProgramNote
    extends Response
{

    protected int seasonId;
    protected int seasonProgramId;
    protected int seasonProgramNotesId;
    protected int departmentProgramId;
    protected String noteValue;
    protected boolean active;
    @XmlElement(required = true)
    protected String createdOn;
    @XmlElement(required = true)
    protected String createdBy;
    protected int loginId;

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
     * Gets the value of the seasonProgramNotesId property.
     * 
     */
    public int getSeasonProgramNotesId() {
        return seasonProgramNotesId;
    }

    /**
     * Sets the value of the seasonProgramNotesId property.
     * 
     */
    public void setSeasonProgramNotesId(int value) {
        this.seasonProgramNotesId = value;
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
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
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

    /**
     * Gets the value of the loginId property.
     * 
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     */
    public void setLoginId(int value) {
        this.loginId = value;
    }

}
