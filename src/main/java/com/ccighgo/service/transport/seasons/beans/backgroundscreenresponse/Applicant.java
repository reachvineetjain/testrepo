//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.19 at 11:11:14 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Applicant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Applicant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicantID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Applicant", propOrder = {
    "applicantID",
    "fileNo",
    "fileURL",
    "responseCode",
    "errorMessage"
})
public class Applicant {

    @XmlElement(name = "ApplicantID")
    protected String applicantID;
    @XmlElement(name = "FileNo")
    protected String fileNo;
    @XmlElement(name = "FileURL")
    protected String fileURL;
    @XmlElement(name = "ResponseCode")
    protected String responseCode;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;

    /**
     * Gets the value of the applicantID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantID() {
        return applicantID;
    }

    /**
     * Sets the value of the applicantID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantID(String value) {
        this.applicantID = value;
    }

    /**
     * Gets the value of the fileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * Sets the value of the fileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNo(String value) {
        this.fileNo = value;
    }

    /**
     * Gets the value of the fileURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileURL() {
        return fileURL;
    }

    /**
     * Sets the value of the fileURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileURL(String value) {
        this.fileURL = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

}
