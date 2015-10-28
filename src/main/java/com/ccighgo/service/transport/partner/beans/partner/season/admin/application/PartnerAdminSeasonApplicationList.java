//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 10:07:49 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.season.admin.application;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerAdminSeasonApplicationList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminSeasonApplicationList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="partnerSeasonApplication" type="{http://www.ccighgo.com/seasonadminapplication}PartnerAdminSeasonApplication" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAdminSeasonApplicationList", propOrder = {
    "partnerId",
    "partnerSeasonApplication",
    "comments"
})
public class PartnerAdminSeasonApplicationList
    extends Response
{

    protected int partnerId;
    protected List<PartnerAdminSeasonApplication> partnerSeasonApplication;
    @XmlElement(required = true)
    protected String comments;

    /**
     * Gets the value of the partnerId property.
     * 
     */
    public int getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(int value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the partnerSeasonApplication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerSeasonApplication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerSeasonApplication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerAdminSeasonApplication }
     * 
     * 
     */
    public List<PartnerAdminSeasonApplication> getPartnerSeasonApplication() {
        if (partnerSeasonApplication == null) {
            partnerSeasonApplication = new ArrayList<PartnerAdminSeasonApplication>();
        }
        return this.partnerSeasonApplication;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

}
