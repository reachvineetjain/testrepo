//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 05:04:10 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatingAgreement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperatingAgreement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatingAgreementdocumentIde" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="operatingAgreementdocumentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementdocumentUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementUploadedByFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementUploadedByLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementUploadedByPicUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementUploadedByDesignation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatingAgreementUpploadedOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatingAgreement", propOrder = {
    "operatingAgreementdocumentIde",
    "operatingAgreementdocumentName",
    "operatingAgreementdocumentUrl",
    "operatingAgreementUploadedByFirstName",
    "operatingAgreementUploadedByLastName",
    "operatingAgreementUploadedByPicUrl",
    "operatingAgreementUploadedByDesignation",
    "operatingAgreementUpploadedOn",
    "signed"
})
public class OperatingAgreement {

    protected int operatingAgreementdocumentIde;
    @XmlElement(required = true)
    protected String operatingAgreementdocumentName;
    @XmlElement(required = true)
    protected String operatingAgreementdocumentUrl;
    @XmlElement(required = true)
    protected String operatingAgreementUploadedByFirstName;
    @XmlElement(required = true)
    protected String operatingAgreementUploadedByLastName;
    @XmlElement(required = true)
    protected String operatingAgreementUploadedByPicUrl;
    @XmlElement(required = true)
    protected String operatingAgreementUploadedByDesignation;
    @XmlElement(required = true)
    protected String operatingAgreementUpploadedOn;
    protected boolean signed;

    /**
     * Gets the value of the operatingAgreementdocumentIde property.
     * 
     */
    public int getOperatingAgreementdocumentIde() {
        return operatingAgreementdocumentIde;
    }

    /**
     * Sets the value of the operatingAgreementdocumentIde property.
     * 
     */
    public void setOperatingAgreementdocumentIde(int value) {
        this.operatingAgreementdocumentIde = value;
    }

    /**
     * Gets the value of the operatingAgreementdocumentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementdocumentName() {
        return operatingAgreementdocumentName;
    }

    /**
     * Sets the value of the operatingAgreementdocumentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementdocumentName(String value) {
        this.operatingAgreementdocumentName = value;
    }

    /**
     * Gets the value of the operatingAgreementdocumentUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementdocumentUrl() {
        return operatingAgreementdocumentUrl;
    }

    /**
     * Sets the value of the operatingAgreementdocumentUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementdocumentUrl(String value) {
        this.operatingAgreementdocumentUrl = value;
    }

    /**
     * Gets the value of the operatingAgreementUploadedByFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementUploadedByFirstName() {
        return operatingAgreementUploadedByFirstName;
    }

    /**
     * Sets the value of the operatingAgreementUploadedByFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementUploadedByFirstName(String value) {
        this.operatingAgreementUploadedByFirstName = value;
    }

    /**
     * Gets the value of the operatingAgreementUploadedByLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementUploadedByLastName() {
        return operatingAgreementUploadedByLastName;
    }

    /**
     * Sets the value of the operatingAgreementUploadedByLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementUploadedByLastName(String value) {
        this.operatingAgreementUploadedByLastName = value;
    }

    /**
     * Gets the value of the operatingAgreementUploadedByPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementUploadedByPicUrl() {
        return operatingAgreementUploadedByPicUrl;
    }

    /**
     * Sets the value of the operatingAgreementUploadedByPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementUploadedByPicUrl(String value) {
        this.operatingAgreementUploadedByPicUrl = value;
    }

    /**
     * Gets the value of the operatingAgreementUploadedByDesignation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementUploadedByDesignation() {
        return operatingAgreementUploadedByDesignation;
    }

    /**
     * Sets the value of the operatingAgreementUploadedByDesignation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementUploadedByDesignation(String value) {
        this.operatingAgreementUploadedByDesignation = value;
    }

    /**
     * Gets the value of the operatingAgreementUpploadedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAgreementUpploadedOn() {
        return operatingAgreementUpploadedOn;
    }

    /**
     * Sets the value of the operatingAgreementUpploadedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAgreementUpploadedOn(String value) {
        this.operatingAgreementUpploadedOn = value;
    }

    /**
     * Gets the value of the signed property.
     * 
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * Sets the value of the signed property.
     * 
     */
    public void setSigned(boolean value) {
        this.signed = value;
    }

}
