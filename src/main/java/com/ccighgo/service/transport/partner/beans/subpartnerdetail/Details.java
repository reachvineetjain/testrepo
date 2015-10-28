//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.28 at 04:08:14 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Details complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Details">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logoImageURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="logoUserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="needsPartnerReview" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="subPartnerStatus" type="{http://www.ccighgo.com/subpartnerdetail}SubPartnerStatus" minOccurs="0"/>
 *         &lt;element name="recivevisaforms" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="payGreenHeartDirectly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReTypedPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Details", propOrder = {
    "logoImageURL",
    "logoUserName",
    "agencyName",
    "needsPartnerReview",
    "subPartnerStatus",
    "recivevisaforms",
    "payGreenHeartDirectly",
    "username",
    "newPassword",
    "reTypedPassword"
})
public class Details {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String logoImageURL;
    @XmlElement(required = true)
    protected String logoUserName;
    @XmlElement(required = true)
    protected String agencyName;
    protected boolean needsPartnerReview;
    protected SubPartnerStatus subPartnerStatus;
    protected Boolean recivevisaforms;
    protected Boolean payGreenHeartDirectly;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String newPassword;
    @XmlElement(name = "ReTypedPassword", required = true)
    protected String reTypedPassword;

    /**
     * Gets the value of the logoImageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoImageURL() {
        return logoImageURL;
    }

    /**
     * Sets the value of the logoImageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoImageURL(String value) {
        this.logoImageURL = value;
    }

    /**
     * Gets the value of the logoUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUserName() {
        return logoUserName;
    }

    /**
     * Sets the value of the logoUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoUserName(String value) {
        this.logoUserName = value;
    }

    /**
     * Gets the value of the agencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Sets the value of the agencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyName(String value) {
        this.agencyName = value;
    }

    /**
     * Gets the value of the needsPartnerReview property.
     * 
     */
    public boolean isNeedsPartnerReview() {
        return needsPartnerReview;
    }

    /**
     * Sets the value of the needsPartnerReview property.
     * 
     */
    public void setNeedsPartnerReview(boolean value) {
        this.needsPartnerReview = value;
    }

    /**
     * Gets the value of the subPartnerStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SubPartnerStatus }
     *     
     */
    public SubPartnerStatus getSubPartnerStatus() {
        return subPartnerStatus;
    }

    /**
     * Sets the value of the subPartnerStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubPartnerStatus }
     *     
     */
    public void setSubPartnerStatus(SubPartnerStatus value) {
        this.subPartnerStatus = value;
    }

    /**
     * Gets the value of the recivevisaforms property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecivevisaforms() {
        return recivevisaforms;
    }

    /**
     * Sets the value of the recivevisaforms property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecivevisaforms(Boolean value) {
        this.recivevisaforms = value;
    }

    /**
     * Gets the value of the payGreenHeartDirectly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPayGreenHeartDirectly() {
        return payGreenHeartDirectly;
    }

    /**
     * Sets the value of the payGreenHeartDirectly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPayGreenHeartDirectly(Boolean value) {
        this.payGreenHeartDirectly = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
    }

    /**
     * Gets the value of the reTypedPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReTypedPassword() {
        return reTypedPassword;
    }

    /**
     * Sets the value of the reTypedPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReTypedPassword(String value) {
        this.reTypedPassword = value;
    }

}