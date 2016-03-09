//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.03 at 03:57:38 PM CST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.hfcommunityandschoolpage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for HFCommunityAndSchoolPage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFCommunityAndSchoolPage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostFamilyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostFamilyCommunityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="community" type="{http://www.ccighgo.com/hfcommunityandschoolpage}HFCommunity"/>
 *         &lt;element name="schoolLife" type="{http://www.ccighgo.com/hfcommunityandschoolpage}HFSchoolLife"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFCommunityAndSchoolPage", propOrder = {
    "loginId",
    "seasonId",
    "programId",
    "hostFamilyId",
    "hostFamilyCommunityId",
    "community",
    "schoolLife"
})
public class HFCommunityAndSchoolPage
    extends Response
{

    protected int loginId;
    protected int seasonId;
    protected int programId;
    protected int hostFamilyId;
    protected int hostFamilyCommunityId;
    @XmlElement(required = true)
    protected HFCommunity community;
    @XmlElement(required = true)
    protected HFSchoolLife schoolLife;

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
     * Gets the value of the programId property.
     * 
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     */
    public void setProgramId(int value) {
        this.programId = value;
    }

    /**
     * Gets the value of the hostFamilyId property.
     * 
     */
    public int getHostFamilyId() {
        return hostFamilyId;
    }

    /**
     * Sets the value of the hostFamilyId property.
     * 
     */
    public void setHostFamilyId(int value) {
        this.hostFamilyId = value;
    }

    /**
     * Gets the value of the hostFamilyCommunityId property.
     * 
     */
    public int getHostFamilyCommunityId() {
        return hostFamilyCommunityId;
    }

    /**
     * Sets the value of the hostFamilyCommunityId property.
     * 
     */
    public void setHostFamilyCommunityId(int value) {
        this.hostFamilyCommunityId = value;
    }

    /**
     * Gets the value of the community property.
     * 
     * @return
     *     possible object is
     *     {@link HFCommunity }
     *     
     */
    public HFCommunity getCommunity() {
        return community;
    }

    /**
     * Sets the value of the community property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFCommunity }
     *     
     */
    public void setCommunity(HFCommunity value) {
        this.community = value;
    }

    /**
     * Gets the value of the schoolLife property.
     * 
     * @return
     *     possible object is
     *     {@link HFSchoolLife }
     *     
     */
    public HFSchoolLife getSchoolLife() {
        return schoolLife;
    }

    /**
     * Sets the value of the schoolLife property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFSchoolLife }
     *     
     */
    public void setSchoolLife(HFSchoolLife value) {
        this.schoolLife = value;
    }

}
