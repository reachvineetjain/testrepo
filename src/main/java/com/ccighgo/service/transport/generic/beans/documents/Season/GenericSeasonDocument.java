//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.04 at 09:52:53 AM CST 
//


package com.ccighgo.service.transport.generic.beans.documents.Season;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for GenericSeasonDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericSeasonDocument">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="documentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="documentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentType" type="{http://www.ccighgo.com/genericseasonsdocuments}DocumentType"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="docUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="upLoadedBy" type="{http://www.ccighgo.com/genericseasonsdocuments}GenericSeasonDocumentUpLoadedBy"/>
 *         &lt;element name="partnerSeasonParametrs" type="{http://www.ccighgo.com/genericseasonsdocuments}PartnerSeasonParametrs"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "GenericSeasonDocument", propOrder = {
    "documentId",
    "documentName",
    "documentDescription",
    "documentType",
    "fileName",
    "docName",
    "docUrl",
    "uploadDate",
    "upLoadedBy",
    "partnerSeasonParametrs",
    "active",
    "loginId"
})
public class GenericSeasonDocument
    extends Response
{

    protected int documentId;
    @XmlElement(required = true)
    protected String documentName;
    @XmlElement(required = true)
    protected String documentDescription;
    @XmlElement(required = true)
    protected DocumentType documentType;
    @XmlElement(required = true)
    protected String fileName;
    @XmlElement(required = true)
    protected String docName;
    @XmlElement(required = true)
    protected String docUrl;
    @XmlElement(required = true)
    protected String uploadDate;
    @XmlElement(required = true)
    protected GenericSeasonDocumentUpLoadedBy upLoadedBy;
    @XmlElement(required = true)
    protected PartnerSeasonParametrs partnerSeasonParametrs;
    protected boolean active;
    protected int loginId;

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

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the docName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocName() {
        return docName;
    }

    /**
     * Sets the value of the docName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocName(String value) {
        this.docName = value;
    }

    /**
     * Gets the value of the docUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocUrl() {
        return docUrl;
    }

    /**
     * Sets the value of the docUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocUrl(String value) {
        this.docUrl = value;
    }

    /**
     * Gets the value of the uploadDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * Sets the value of the uploadDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadDate(String value) {
        this.uploadDate = value;
    }

    /**
     * Gets the value of the upLoadedBy property.
     * 
     * @return
     *     possible object is
     *     {@link GenericSeasonDocumentUpLoadedBy }
     *     
     */
    public GenericSeasonDocumentUpLoadedBy getUpLoadedBy() {
        return upLoadedBy;
    }

    /**
     * Sets the value of the upLoadedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericSeasonDocumentUpLoadedBy }
     *     
     */
    public void setUpLoadedBy(GenericSeasonDocumentUpLoadedBy value) {
        this.upLoadedBy = value;
    }

    /**
     * Gets the value of the partnerSeasonParametrs property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerSeasonParametrs }
     *     
     */
    public PartnerSeasonParametrs getPartnerSeasonParametrs() {
        return partnerSeasonParametrs;
    }

    /**
     * Sets the value of the partnerSeasonParametrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerSeasonParametrs }
     *     
     */
    public void setPartnerSeasonParametrs(PartnerSeasonParametrs value) {
        this.partnerSeasonParametrs = value;
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
