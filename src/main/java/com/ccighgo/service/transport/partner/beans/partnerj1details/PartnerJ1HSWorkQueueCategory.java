//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.11 at 12:02:48 PM IST 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerJ1HSWorkQueueCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerJ1HSWorkQueueCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerWorkQueueCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerWorkQueueCategoryNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerWorkQueueCategoryUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerJ1HSWorkQueueCategory", propOrder = {
    "partnerWorkQueueCategoryName",
    "partnerWorkQueueCategoryNo",
    "partnerWorkQueueCategoryUrl"
})
public class PartnerJ1HSWorkQueueCategory {

    @XmlElement(required = true)
    protected String partnerWorkQueueCategoryName;
    protected int partnerWorkQueueCategoryNo;
    @XmlElement(required = true)
    protected String partnerWorkQueueCategoryUrl;

    /**
     * Gets the value of the partnerWorkQueueCategoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerWorkQueueCategoryName() {
        return partnerWorkQueueCategoryName;
    }

    /**
     * Sets the value of the partnerWorkQueueCategoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerWorkQueueCategoryName(String value) {
        this.partnerWorkQueueCategoryName = value;
    }

    /**
     * Gets the value of the partnerWorkQueueCategoryNo property.
     * 
     */
    public int getPartnerWorkQueueCategoryNo() {
        return partnerWorkQueueCategoryNo;
    }

    /**
     * Sets the value of the partnerWorkQueueCategoryNo property.
     * 
     */
    public void setPartnerWorkQueueCategoryNo(int value) {
        this.partnerWorkQueueCategoryNo = value;
    }

    /**
     * Gets the value of the partnerWorkQueueCategoryUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerWorkQueueCategoryUrl() {
        return partnerWorkQueueCategoryUrl;
    }

    /**
     * Sets the value of the partnerWorkQueueCategoryUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerWorkQueueCategoryUrl(String value) {
        this.partnerWorkQueueCategoryUrl = value;
    }

}
