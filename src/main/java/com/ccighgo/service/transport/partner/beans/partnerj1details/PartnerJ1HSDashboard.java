//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 02:05:57 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerj1details;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerJ1HSDashboard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerJ1HSDashboard">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerAnnouncements" type="{http://www.ccighgo.com/partnerj1hs}PartnerJ1HSAnnouncement" maxOccurs="unbounded"/>
 *         &lt;element name="cciContact" type="{http://www.ccighgo.com/partnerj1hs}PartnerJ1HSCCIContact"/>
 *         &lt;element name="partnerWorkQueueTypes" type="{http://www.ccighgo.com/partnerj1hs}PartnerJ1HSWorkQueueType" maxOccurs="unbounded"/>
 *         &lt;element name="partnerStatistics" type="{http://www.ccighgo.com/partnerj1hs}PartnerStatistics"/>
 *         &lt;element name="partnerJ1HSPrograms" type="{http://www.ccighgo.com/partnerj1hs}PartnerJ1HSProgram" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerJ1HSDashboard", propOrder = {
    "partnerGoId",
    "partnerLogo",
    "partnerCompany",
    "partnerAnnouncements",
    "cciContact",
    "partnerWorkQueueTypes",
    "partnerStatistics",
    "partnerJ1HSPrograms"
})
public class PartnerJ1HSDashboard
    extends Response
{

    protected int partnerGoId;
    @XmlElement(required = true)
    protected String partnerLogo;
    @XmlElement(required = true)
    protected String partnerCompany;
    @XmlElement(required = true)
    protected List<PartnerJ1HSAnnouncement> partnerAnnouncements;
    @XmlElement(required = true)
    protected PartnerJ1HSCCIContact cciContact;
    @XmlElement(required = true)
    protected List<PartnerJ1HSWorkQueueType> partnerWorkQueueTypes;
    @XmlElement(required = true)
    protected PartnerStatistics partnerStatistics;
    @XmlElement(required = true)
    protected List<PartnerJ1HSProgram> partnerJ1HSPrograms;

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
     * Gets the value of the partnerAnnouncements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerAnnouncements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerAnnouncements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerJ1HSAnnouncement }
     * 
     * 
     */
    public List<PartnerJ1HSAnnouncement> getPartnerAnnouncements() {
        if (partnerAnnouncements == null) {
            partnerAnnouncements = new ArrayList<PartnerJ1HSAnnouncement>();
        }
        return this.partnerAnnouncements;
    }

    /**
     * Gets the value of the cciContact property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerJ1HSCCIContact }
     *     
     */
    public PartnerJ1HSCCIContact getCciContact() {
        return cciContact;
    }

    /**
     * Sets the value of the cciContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerJ1HSCCIContact }
     *     
     */
    public void setCciContact(PartnerJ1HSCCIContact value) {
        this.cciContact = value;
    }

    /**
     * Gets the value of the partnerWorkQueueTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerWorkQueueTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerWorkQueueTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerJ1HSWorkQueueType }
     * 
     * 
     */
    public List<PartnerJ1HSWorkQueueType> getPartnerWorkQueueTypes() {
        if (partnerWorkQueueTypes == null) {
            partnerWorkQueueTypes = new ArrayList<PartnerJ1HSWorkQueueType>();
        }
        return this.partnerWorkQueueTypes;
    }

    /**
     * Gets the value of the partnerStatistics property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerStatistics }
     *     
     */
    public PartnerStatistics getPartnerStatistics() {
        return partnerStatistics;
    }

    /**
     * Sets the value of the partnerStatistics property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerStatistics }
     *     
     */
    public void setPartnerStatistics(PartnerStatistics value) {
        this.partnerStatistics = value;
    }

    /**
     * Gets the value of the partnerJ1HSPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerJ1HSPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerJ1HSPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerJ1HSProgram }
     * 
     * 
     */
    public List<PartnerJ1HSProgram> getPartnerJ1HSPrograms() {
        if (partnerJ1HSPrograms == null) {
            partnerJ1HSPrograms = new ArrayList<PartnerJ1HSProgram>();
        }
        return this.partnerJ1HSPrograms;
    }

}
