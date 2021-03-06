//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.06 at 03:33:34 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familydetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for HFApplicationFamilyDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFApplicationFamilyDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostFamilyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="singleHost" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="singleHostDetail" type="{http://www.ccighgo.com/hfappfamilydetails}HFSingleHostDetails"/>
 *         &lt;element name="photo" type="{http://www.ccighgo.com/hfappfamilydetails}Photo"/>
 *         &lt;element name="adults" type="{http://www.ccighgo.com/hfappfamilydetails}HFAdultDetails" maxOccurs="unbounded"/>
 *         &lt;element name="contactInfo" type="{http://www.ccighgo.com/hfappfamilydetails}HFContactInfo"/>
 *         &lt;element name="physicalAddress" type="{http://www.ccighgo.com/hfappfamilydetails}HFPhysicalAddress"/>
 *         &lt;element name="mailingAddress" type="{http://www.ccighgo.com/hfappfamilydetails}HFMailingAddress"/>
 *         &lt;element name="hostFamilySeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="airports" type="{http://www.ccighgo.com/hfappfamilydetails}HFAirport" maxOccurs="unbounded"/>
 *         &lt;element name="children" type="{http://www.ccighgo.com/hfappfamilydetails}HFChildren" maxOccurs="unbounded"/>
 *         &lt;element name="pets" type="{http://www.ccighgo.com/hfappfamilydetails}HFPets" maxOccurs="unbounded"/>
 *         &lt;element name="isTheirChildrenUnder18LivesAtHome" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fieldsFilled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="percentUpdate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFApplicationFamilyDetails", propOrder = {
    "loginId",
    "seasonId",
    "programId",
    "hostFamilyId",
    "singleHost",
    "singleHostDetail",
    "photo",
    "adults",
    "contactInfo",
    "physicalAddress",
    "mailingAddress",
    "hostFamilySeasonId",
    "airports",
    "children",
    "pets",
    "isTheirChildrenUnder18LivesAtHome",
    "fieldsFilled",
    "percentUpdate"
})
public class HFApplicationFamilyDetails
    extends Response
{

    protected int loginId;
    protected int seasonId;
    protected int programId;
    protected int hostFamilyId;
    protected boolean singleHost;
    @XmlElement(required = true)
    protected HFSingleHostDetails singleHostDetail;
    @XmlElement(required = true)
    protected Photo photo;
    @XmlElement(required = true)
    protected List<HFAdultDetails> adults;
    @XmlElement(required = true)
    protected HFContactInfo contactInfo;
    @XmlElement(required = true)
    protected HFPhysicalAddress physicalAddress;
    @XmlElement(required = true)
    protected HFMailingAddress mailingAddress;
    protected int hostFamilySeasonId;
    @XmlElement(required = true)
    protected List<HFAirport> airports;
    @XmlElement(required = true)
    protected List<HFChildren> children;
    @XmlElement(required = true)
    protected List<HFPets> pets;
    protected boolean isTheirChildrenUnder18LivesAtHome;
    protected int fieldsFilled;
    protected double percentUpdate;

    /**
     * Gets the value of the loginId property.
     * 
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     */
    public void setLoginId(int value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the seasonId property.
     * 
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     */
    public void setSeasonId(int value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the programId property.
     * 
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     */
    public void setProgramId(int value) {
        this.programId = value;
    }

    /**
     * Gets the value of the hostFamilyId property.
     * 
     */
    public int getHostFamilyId() {
        return hostFamilyId;
    }

    /**
     * Sets the value of the hostFamilyId property.
     * 
     */
    public void setHostFamilyId(int value) {
        this.hostFamilyId = value;
    }

    /**
     * Gets the value of the singleHost property.
     * 
     */
    public boolean isSingleHost() {
        return singleHost;
    }

    /**
     * Sets the value of the singleHost property.
     * 
     */
    public void setSingleHost(boolean value) {
        this.singleHost = value;
    }

    /**
     * Gets the value of the singleHostDetail property.
     * 
     * @return
     *     possible object is
     *     {@link HFSingleHostDetails }
     *     
     */
    public HFSingleHostDetails getSingleHostDetail() {
        return singleHostDetail;
    }

    /**
     * Sets the value of the singleHostDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFSingleHostDetails }
     *     
     */
    public void setSingleHostDetail(HFSingleHostDetails value) {
        this.singleHostDetail = value;
    }

    /**
     * Gets the value of the photo property.
     * 
     * @return
     *     possible object is
     *     {@link Photo }
     *     
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Sets the value of the photo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Photo }
     *     
     */
    public void setPhoto(Photo value) {
        this.photo = value;
    }

    /**
     * Gets the value of the adults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HFAdultDetails }
     * 
     * 
     */
    public List<HFAdultDetails> getAdults() {
        if (adults == null) {
            adults = new ArrayList<HFAdultDetails>();
        }
        return this.adults;
    }

    /**
     * Gets the value of the contactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HFContactInfo }
     *     
     */
    public HFContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the value of the contactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFContactInfo }
     *     
     */
    public void setContactInfo(HFContactInfo value) {
        this.contactInfo = value;
    }

    /**
     * Gets the value of the physicalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link HFPhysicalAddress }
     *     
     */
    public HFPhysicalAddress getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Sets the value of the physicalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFPhysicalAddress }
     *     
     */
    public void setPhysicalAddress(HFPhysicalAddress value) {
        this.physicalAddress = value;
    }

    /**
     * Gets the value of the mailingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link HFMailingAddress }
     *     
     */
    public HFMailingAddress getMailingAddress() {
        return mailingAddress;
    }

    /**
     * Sets the value of the mailingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link HFMailingAddress }
     *     
     */
    public void setMailingAddress(HFMailingAddress value) {
        this.mailingAddress = value;
    }

    /**
     * Gets the value of the hostFamilySeasonId property.
     * 
     */
    public int getHostFamilySeasonId() {
        return hostFamilySeasonId;
    }

    /**
     * Sets the value of the hostFamilySeasonId property.
     * 
     */
    public void setHostFamilySeasonId(int value) {
        this.hostFamilySeasonId = value;
    }

    /**
     * Gets the value of the airports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HFAirport }
     * 
     * 
     */
    public List<HFAirport> getAirports() {
        if (airports == null) {
            airports = new ArrayList<HFAirport>();
        }
        return this.airports;
    }

    /**
     * Gets the value of the children property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the children property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildren().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HFChildren }
     * 
     * 
     */
    public List<HFChildren> getChildren() {
        if (children == null) {
            children = new ArrayList<HFChildren>();
        }
        return this.children;
    }

    /**
     * Gets the value of the pets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HFPets }
     * 
     * 
     */
    public List<HFPets> getPets() {
        if (pets == null) {
            pets = new ArrayList<HFPets>();
        }
        return this.pets;
    }

    /**
     * Gets the value of the isTheirChildrenUnder18LivesAtHome property.
     * 
     */
    public boolean isIsTheirChildrenUnder18LivesAtHome() {
        return isTheirChildrenUnder18LivesAtHome;
    }

    /**
     * Sets the value of the isTheirChildrenUnder18LivesAtHome property.
     * 
     */
    public void setIsTheirChildrenUnder18LivesAtHome(boolean value) {
        this.isTheirChildrenUnder18LivesAtHome = value;
    }

    /**
     * Gets the value of the fieldsFilled property.
     * 
     */
    public int getFieldsFilled() {
        return fieldsFilled;
    }

    /**
     * Sets the value of the fieldsFilled property.
     * 
     */
    public void setFieldsFilled(int value) {
        this.fieldsFilled = value;
    }

    /**
     * Gets the value of the percentUpdate property.
     * 
     */
    public double getPercentUpdate() {
        return percentUpdate;
    }

    /**
     * Sets the value of the percentUpdate property.
     * 
     */
    public void setPercentUpdate(double value) {
        this.percentUpdate = value;
    }

}
