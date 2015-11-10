//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.10 at 01:44:15 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerSeasonDocumentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="documentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="documentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedByFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedByLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedByPicUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedByDesignation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentType" type="{http://www.ccighgo.com/partadminf1detail}DocumentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "partnerSeasonDocumentId",
    "documentId",
    "documentName",
    "uploadedByFirstName",
    "uploadedByLastName",
    "uploadedByPicUrl",
    "uploadedByDesignation",
    "documentDescription",
    "documentUrl",
    "documentType"
})
public class Document {

    protected int partnerSeasonDocumentId;
    protected int documentId;
    @XmlElement(required = true)
    protected String documentName;
    @XmlElement(required = true)
    protected String uploadedByFirstName;
    @XmlElement(required = true)
    protected String uploadedByLastName;
    @XmlElement(required = true)
    protected String uploadedByPicUrl;
    @XmlElement(required = true)
    protected String uploadedByDesignation;
    @XmlElement(required = true)
    protected String documentDescription;
    @XmlElement(required = true)
    protected String documentUrl;
    @XmlElement(required = true)
    protected DocumentType documentType;

    /**
     * Gets the value of the partnerSeasonDocumentId property.
     * 
     */
    public int getPartnerSeasonDocumentId() {
        return partnerSeasonDocumentId;
    }

    /**
     * Sets the value of the partnerSeasonDocumentId property.
     * 
     */
    public void setPartnerSeasonDocumentId(int value) {
        this.partnerSeasonDocumentId = value;
    }

    /**
     * Gets the value of the documentId property.
     * 
     */
    public int getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     * 
     */
    public void setDocumentId(int value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the documentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets the value of the documentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentName(String value) {
        this.documentName = value;
    }

    /**
     * Gets the value of the uploadedByFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedByFirstName() {
        return uploadedByFirstName;
    }

    /**
     * Sets the value of the uploadedByFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedByFirstName(String value) {
        this.uploadedByFirstName = value;
    }

    /**
     * Gets the value of the uploadedByLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedByLastName() {
        return uploadedByLastName;
    }

    /**
     * Sets the value of the uploadedByLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedByLastName(String value) {
        this.uploadedByLastName = value;
    }

    /**
     * Gets the value of the uploadedByPicUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedByPicUrl() {
        return uploadedByPicUrl;
    }

    /**
     * Sets the value of the uploadedByPicUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedByPicUrl(String value) {
        this.uploadedByPicUrl = value;
    }

    /**
     * Gets the value of the uploadedByDesignation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedByDesignation() {
        return uploadedByDesignation;
    }

    /**
     * Sets the value of the uploadedByDesignation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedByDesignation(String value) {
        this.uploadedByDesignation = value;
    }

    /**
     * Gets the value of the documentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentDescription() {
        return documentDescription;
    }

    /**
     * Sets the value of the documentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentDescription(String value) {
        this.documentDescription = value;
    }

    /**
     * Gets the value of the documentUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * Sets the value of the documentUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentUrl(String value) {
        this.documentUrl = value;
    }

    /**
     * Gets the value of the documentType property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setDocumentType(DocumentType value) {
        this.documentType = value;
    }

}
