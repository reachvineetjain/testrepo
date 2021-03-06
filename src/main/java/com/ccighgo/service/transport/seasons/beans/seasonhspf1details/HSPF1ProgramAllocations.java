//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 02:23:29 PM CST 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for HSPF1ProgramAllocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HSPF1ProgramAllocations">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="augustStartMaximumParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="augustStartAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="augustStartRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="januaryStartMaximumParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="januaryStartAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="januaryStartRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalMaximumParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalAcceptedParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalRemainingParticipants" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HSPF1ProgramAllocations", propOrder = {
    "seasonId",
    "seasonProgramId",
    "augustStartMaximumParticipants",
    "augustStartAcceptedParticipants",
    "augustStartRemainingParticipants",
    "januaryStartMaximumParticipants",
    "januaryStartAcceptedParticipants",
    "januaryStartRemainingParticipants",
    "totalMaximumParticipants",
    "totalAcceptedParticipants",
    "totalRemainingParticipants"
})
public class HSPF1ProgramAllocations
    extends Response
{

    protected int seasonId;
    protected int seasonProgramId;
    protected Integer augustStartMaximumParticipants;
    protected Integer augustStartAcceptedParticipants;
    protected Integer augustStartRemainingParticipants;
    protected Integer januaryStartMaximumParticipants;
    protected Integer januaryStartAcceptedParticipants;
    protected Integer januaryStartRemainingParticipants;
    protected Integer totalMaximumParticipants;
    protected Integer totalAcceptedParticipants;
    protected Integer totalRemainingParticipants;

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
     * Gets the value of the augustStartMaximumParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAugustStartMaximumParticipants() {
        return augustStartMaximumParticipants;
    }

    /**
     * Sets the value of the augustStartMaximumParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAugustStartMaximumParticipants(Integer value) {
        this.augustStartMaximumParticipants = value;
    }

    /**
     * Gets the value of the augustStartAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAugustStartAcceptedParticipants() {
        return augustStartAcceptedParticipants;
    }

    /**
     * Sets the value of the augustStartAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAugustStartAcceptedParticipants(Integer value) {
        this.augustStartAcceptedParticipants = value;
    }

    /**
     * Gets the value of the augustStartRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAugustStartRemainingParticipants() {
        return augustStartRemainingParticipants;
    }

    /**
     * Sets the value of the augustStartRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAugustStartRemainingParticipants(Integer value) {
        this.augustStartRemainingParticipants = value;
    }

    /**
     * Gets the value of the januaryStartMaximumParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJanuaryStartMaximumParticipants() {
        return januaryStartMaximumParticipants;
    }

    /**
     * Sets the value of the januaryStartMaximumParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJanuaryStartMaximumParticipants(Integer value) {
        this.januaryStartMaximumParticipants = value;
    }

    /**
     * Gets the value of the januaryStartAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJanuaryStartAcceptedParticipants() {
        return januaryStartAcceptedParticipants;
    }

    /**
     * Sets the value of the januaryStartAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJanuaryStartAcceptedParticipants(Integer value) {
        this.januaryStartAcceptedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJanuaryStartRemainingParticipants() {
        return januaryStartRemainingParticipants;
    }

    /**
     * Sets the value of the januaryStartRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJanuaryStartRemainingParticipants(Integer value) {
        this.januaryStartRemainingParticipants = value;
    }

    /**
     * Gets the value of the totalMaximumParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalMaximumParticipants() {
        return totalMaximumParticipants;
    }

    /**
     * Sets the value of the totalMaximumParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalMaximumParticipants(Integer value) {
        this.totalMaximumParticipants = value;
    }

    /**
     * Gets the value of the totalAcceptedParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalAcceptedParticipants() {
        return totalAcceptedParticipants;
    }

    /**
     * Sets the value of the totalAcceptedParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalAcceptedParticipants(Integer value) {
        this.totalAcceptedParticipants = value;
    }

    /**
     * Gets the value of the totalRemainingParticipants property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalRemainingParticipants() {
        return totalRemainingParticipants;
    }

    /**
     * Sets the value of the totalRemainingParticipants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalRemainingParticipants(Integer value) {
        this.totalRemainingParticipants = value;
    }

}
