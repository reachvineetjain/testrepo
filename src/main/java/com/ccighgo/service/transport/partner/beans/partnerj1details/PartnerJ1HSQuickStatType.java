//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.15 at 04:05:55 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerJ1HSQuickStatType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerJ1HSQuickStatType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerQuickStatTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerQuickStatTypeNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerQuickStatCategories" type="{http://www.ccighgo.com/partnerj1hs}PartnerJ1HSQuickStatCategory" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerJ1HSQuickStatType", propOrder = {
    "partnerQuickStatTypeName",
    "partnerQuickStatTypeNo",
    "partnerQuickStatCategories"
})
public class PartnerJ1HSQuickStatType {

    @XmlElement(required = true)
    protected String partnerQuickStatTypeName;
    protected int partnerQuickStatTypeNo;
    protected List<PartnerJ1HSQuickStatCategory> partnerQuickStatCategories;

    /**
     * Gets the value of the partnerQuickStatTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerQuickStatTypeName() {
        return partnerQuickStatTypeName;
    }

    /**
     * Sets the value of the partnerQuickStatTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerQuickStatTypeName(String value) {
        this.partnerQuickStatTypeName = value;
    }

    /**
     * Gets the value of the partnerQuickStatTypeNo property.
     * 
     */
    public int getPartnerQuickStatTypeNo() {
        return partnerQuickStatTypeNo;
    }

    /**
     * Sets the value of the partnerQuickStatTypeNo property.
     * 
     */
    public void setPartnerQuickStatTypeNo(int value) {
        this.partnerQuickStatTypeNo = value;
    }

    /**
     * Gets the value of the partnerQuickStatCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerQuickStatCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerQuickStatCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerJ1HSQuickStatCategory }
     * 
     * 
     */
    public List<PartnerJ1HSQuickStatCategory> getPartnerQuickStatCategories() {
        if (partnerQuickStatCategories == null) {
            partnerQuickStatCategories = new ArrayList<PartnerJ1HSQuickStatCategory>();
        }
        return this.partnerQuickStatCategories;
    }

}
