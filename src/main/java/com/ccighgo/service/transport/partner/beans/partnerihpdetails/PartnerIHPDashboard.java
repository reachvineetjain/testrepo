//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.15 at 04:14:59 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerihpdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerIHPDashboard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerIHPDashboard">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="partnerGoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerAnnouncements" type="{http://www.ccighgo.com/partnerihp}PartnerIHPAnnouncement" maxOccurs="unbounded"/>
 *         &lt;element name="cciContact" type="{http://www.ccighgo.com/partnerihp}PartnerIHPCCIContact"/>
 *         &lt;element name="partnerWorkQueueTypes" type="{http://www.ccighgo.com/partnerihp}PartnerIHPWorkQueueType" maxOccurs="unbounded"/>
 *         &lt;element name="partnerQuickLinks" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="partnerQuickStatTypes" type="{http://www.ccighgo.com/partnerihp}PartnerIHPQuickStatType" maxOccurs="unbounded"/>
 *         &lt;element name="partnerIHPPrograms" type="{http://www.ccighgo.com/partnerihp}PartnerIHPProgram" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerIHPDashboard", propOrder = {
    "partnerGoId",
    "partnerLogo",
    "partnerCompany",
    "partnerAnnouncements",
    "cciContact",
    "partnerWorkQueueTypes",
    "partnerQuickLinks",
    "partnerQuickStatTypes",
    "partnerIHPPrograms"
})
public class PartnerIHPDashboard
    extends Response
{

    protected int partnerGoId;
    @XmlElement(required = true)
    protected String partnerLogo;
    @XmlElement(required = true)
    protected String partnerCompany;
    @XmlElement(required = true)
    protected List<PartnerIHPAnnouncement> partnerAnnouncements;
    @XmlElement(required = true)
    protected PartnerIHPCCIContact cciContact;
    @XmlElement(required = true)
    protected List<PartnerIHPWorkQueueType> partnerWorkQueueTypes;
    @XmlElement(required = true)
    protected List<String> partnerQuickLinks;
    @XmlElement(required = true)
    protected List<PartnerIHPQuickStatType> partnerQuickStatTypes;
    @XmlElement(required = true)
    protected List<PartnerIHPProgram> partnerIHPPrograms;

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
     * {@link PartnerIHPAnnouncement }
     * 
     * 
     */
    public List<PartnerIHPAnnouncement> getPartnerAnnouncements() {
        if (partnerAnnouncements == null) {
            partnerAnnouncements = new ArrayList<PartnerIHPAnnouncement>();
        }
        return this.partnerAnnouncements;
    }

    /**
     * Gets the value of the cciContact property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerIHPCCIContact }
     *     
     */
    public PartnerIHPCCIContact getCciContact() {
        return cciContact;
    }

    /**
     * Sets the value of the cciContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerIHPCCIContact }
     *     
     */
    public void setCciContact(PartnerIHPCCIContact value) {
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
     * {@link PartnerIHPWorkQueueType }
     * 
     * 
     */
    public List<PartnerIHPWorkQueueType> getPartnerWorkQueueTypes() {
        if (partnerWorkQueueTypes == null) {
            partnerWorkQueueTypes = new ArrayList<PartnerIHPWorkQueueType>();
        }
        return this.partnerWorkQueueTypes;
    }

    /**
     * Gets the value of the partnerQuickLinks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerQuickLinks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerQuickLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPartnerQuickLinks() {
        if (partnerQuickLinks == null) {
            partnerQuickLinks = new ArrayList<String>();
        }
        return this.partnerQuickLinks;
    }

    /**
     * Gets the value of the partnerQuickStatTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerQuickStatTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerQuickStatTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerIHPQuickStatType }
     * 
     * 
     */
    public List<PartnerIHPQuickStatType> getPartnerQuickStatTypes() {
        if (partnerQuickStatTypes == null) {
            partnerQuickStatTypes = new ArrayList<PartnerIHPQuickStatType>();
        }
        return this.partnerQuickStatTypes;
    }

    /**
     * Gets the value of the partnerIHPPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerIHPPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerIHPPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerIHPProgram }
     * 
     * 
     */
    public List<PartnerIHPProgram> getPartnerIHPPrograms() {
        if (partnerIHPPrograms == null) {
            partnerIHPPrograms = new ArrayList<PartnerIHPProgram>();
        }
        return this.partnerIHPPrograms;
    }

}