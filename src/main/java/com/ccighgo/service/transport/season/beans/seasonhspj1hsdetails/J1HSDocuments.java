//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 01:49:20 PM CST 
//


package com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for J1HSDocuments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="J1HSDocuments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="docType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="docUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "J1HSDocuments", propOrder = {
    "seasonId",
    "seasonProgramId",
    "departmentProgramId",
    "docType",
    "fileType",
    "fileName",
    "docName",
    "docUrl",
    "uploadDate",
    "uploadedBy",
    "active"
})
public class J1HSDocuments {

    protected int seasonId;
    protected int seasonProgramId;
    protected int departmentProgramId;
    @XmlElement(required = true)
    protected String docType;
    @XmlElement(required = true)
    protected String fileType;
    @XmlElement(required = true)
    protected String fileName;
    @XmlElement(required = true)
    protected String docName;
    @XmlElement(required = true)
    protected String docUrl;
    @XmlElement(required = true)
    protected String uploadDate;
    @XmlElement(required = true)
    protected String uploadedBy;
    protected boolean active;

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
     * Gets the value of the docType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocType() {
        return docType;
    }

    /**
     * Sets the value of the docType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocType(String value) {
        this.docType = value;
    }

    /**
     * Gets the value of the fileType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileType(String value) {
        this.fileType = value;
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
     * Gets the value of the uploadedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedBy() {
        return uploadedBy;
    }

    /**
     * Sets the value of the uploadedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedBy(String value) {
        this.uploadedBy = value;
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

}
