//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:38 PM CDT 
//


package com.ccighgo.service.transport.utility.beans.documenttype;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for DocumentTypes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentTypes" type="{http://www.ccighgo.com/doctype}DocumentType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentTypes", propOrder = {
    "documentTypes"
})
public class DocumentTypes extends Response{

    @XmlElement(required = true)
    protected List<DocumentType> documentTypes;

    /**
     * Gets the value of the documentTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocumentTypes() {
        if (documentTypes == null) {
            documentTypes = new ArrayList<DocumentType>();
        }
        return this.documentTypes;
    }

}
