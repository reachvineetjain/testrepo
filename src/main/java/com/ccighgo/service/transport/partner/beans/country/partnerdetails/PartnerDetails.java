//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 10:05:12 AM CDT 
//


package com.ccighgo.service.transport.partner.beans.country.partnerdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerPrograms" type="{http://www.ccighgo.com/partnerdetails}PartnerPrograms" maxOccurs="unbounded"/>
 *         &lt;element name="dashboardSections" type="{http://www.ccighgo.com/partnerdetails}PartnerDashboardSections" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerDetails", propOrder = {
    "partnerId",
    "partnerFirstName",
    "partnerLastName",
    "partnerEmail",
    "partnerAddress",
    "partnerCountry",
    "partnerLogo",
    "partnerCompany",
    "partnerPrograms",
    "dashboardSections"
})
public class PartnerDetails
    extends Response
{

    protected int partnerId;
    @XmlElement(required = true)
    protected String partnerFirstName;
    @XmlElement(required = true)
    protected String partnerLastName;
    @XmlElement(required = true)
    protected String partnerEmail;
    @XmlElement(required = true)
    protected String partnerAddress;
    @XmlElement(required = true)
    protected String partnerCountry;
    @XmlElement(required = true)
    protected String partnerLogo;
    @XmlElement(required = true)
    protected String partnerCompany;
    @XmlElement(required = true)
    protected List<PartnerPrograms> partnerPrograms;
    @XmlElement(required = true)
    protected List<PartnerDashboardSections> dashboardSections;

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
     * Gets the value of the partnerFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerFirstName() {
        return partnerFirstName;
    }

    /**
     * Sets the value of the partnerFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerFirstName(String value) {
        this.partnerFirstName = value;
    }

    /**
     * Gets the value of the partnerLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerLastName() {
        return partnerLastName;
    }

    /**
     * Sets the value of the partnerLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerLastName(String value) {
        this.partnerLastName = value;
    }

    /**
     * Gets the value of the partnerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerEmail() {
        return partnerEmail;
    }

    /**
     * Sets the value of the partnerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerEmail(String value) {
        this.partnerEmail = value;
    }

    /**
     * Gets the value of the partnerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerAddress() {
        return partnerAddress;
    }

    /**
     * Sets the value of the partnerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerAddress(String value) {
        this.partnerAddress = value;
    }

    /**
     * Gets the value of the partnerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCountry() {
        return partnerCountry;
    }

    /**
     * Sets the value of the partnerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCountry(String value) {
        this.partnerCountry = value;
    }

    /**
     * Gets the value of the partnerLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerLogo() {
        return partnerLogo;
    }

    /**
     * Sets the value of the partnerLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerLogo(String value) {
        this.partnerLogo = value;
    }

    /**
     * Gets the value of the partnerCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCompany() {
        return partnerCompany;
    }

    /**
     * Sets the value of the partnerCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCompany(String value) {
        this.partnerCompany = value;
    }

    /**
     * Gets the value of the partnerPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerPrograms }
     * 
     * 
     */
    public List<PartnerPrograms> getPartnerPrograms() {
        if (partnerPrograms == null) {
            partnerPrograms = new ArrayList<PartnerPrograms>();
        }
        return this.partnerPrograms;
    }

    /**
     * Gets the value of the dashboardSections property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dashboardSections property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDashboardSections().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerDashboardSections }
     * 
     * 
     */
    public List<PartnerDashboardSections> getDashboardSections() {
        if (dashboardSections == null) {
            dashboardSections = new ArrayList<PartnerDashboardSections>();
        }
        return this.dashboardSections;
    }

}
