//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.01 at 01:38:35 PM CST 
//


package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerSubPartners complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerSubPartners">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subPartners" type="{http://www.ccighgo.com/subpartner}SubPartners" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSubPartners", propOrder = {
    "count",
    "partnerGoId",
    "subPartners"
})
public class PartnerSubPartners
    extends Response
{

    protected int count;
    protected int partnerGoId;
    protected List<SubPartners> subPartners;

    /**
     * Gets the value of the count property.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the partnerGoId property.
     * 
     */
    public int getPartnerGoId() {
        return partnerGoId;
    }

    /**
     * Sets the value of the partnerGoId property.
     * 
     */
    public void setPartnerGoId(int value) {
        this.partnerGoId = value;
    }

    /**
     * Gets the value of the subPartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subPartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubPartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubPartners }
     * 
     * 
     */
    public List<SubPartners> getSubPartners() {
        if (subPartners == null) {
            subPartners = new ArrayList<SubPartners>();
        }
        return this.subPartners;
    }

}
