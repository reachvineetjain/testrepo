//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 11:01:32 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubPartners complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubPartners">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subPartnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartnerFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subPartnerLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subPartnerSeasons" type="{http://www.ccighgo.com/subpartner}SubPartnerSeasons" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subPartnerCountry" type="{http://www.ccighgo.com/subpartner}SubPartnerCountry"/>
 *         &lt;element name="subPartnerStatus" type="{http://www.ccighgo.com/subpartner}SubPartnerStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartners", propOrder = {
    "subPartnerId",
    "subPartnerFirstName",
    "subPartnerLastName",
    "subPartnerSeasons",
    "subPartnerCountry",
    "subPartnerStatus"
})
public class SubPartners {

    protected int subPartnerId;
    @XmlElement(required = true)
    protected String subPartnerFirstName;
    @XmlElement(required = true)
    protected String subPartnerLastName;
    protected List<SubPartnerSeasons> subPartnerSeasons;
    @XmlElement(required = true)
    protected SubPartnerCountry subPartnerCountry;
    @XmlElement(required = true)
    protected SubPartnerStatus subPartnerStatus;

    /**
     * Gets the value of the subPartnerId property.
     * 
     */
    public int getSubPartnerId() {
        return subPartnerId;
    }

    /**
     * Sets the value of the subPartnerId property.
     * 
     */
    public void setSubPartnerId(int value) {
        this.subPartnerId = value;
    }

    /**
     * Gets the value of the subPartnerFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerFirstName() {
        return subPartnerFirstName;
    }

    /**
     * Sets the value of the subPartnerFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerFirstName(String value) {
        this.subPartnerFirstName = value;
    }

    /**
     * Gets the value of the subPartnerLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubPartnerLastName() {
        return subPartnerLastName;
    }

    /**
     * Sets the value of the subPartnerLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubPartnerLastName(String value) {
        this.subPartnerLastName = value;
    }

    /**
     * Gets the value of the subPartnerSeasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subPartnerSeasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubPartnerSeasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubPartnerSeasons }
     * 
     * 
     */
    public List<SubPartnerSeasons> getSubPartnerSeasons() {
        if (subPartnerSeasons == null) {
            subPartnerSeasons = new ArrayList<SubPartnerSeasons>();
        }
        return this.subPartnerSeasons;
    }

    /**
     * Gets the value of the subPartnerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link SubPartnerCountry }
     *     
     */
    public SubPartnerCountry getSubPartnerCountry() {
        return subPartnerCountry;
    }

    /**
     * Sets the value of the subPartnerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubPartnerCountry }
     *     
     */
    public void setSubPartnerCountry(SubPartnerCountry value) {
        this.subPartnerCountry = value;
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

}
