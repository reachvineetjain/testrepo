//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.02 at 04:53:19 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for topic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="topic">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isTopicPublic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="partnerSeasonNoteTopicId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="partnerSeasonNoteTopicName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="competitorInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="embassy_VisaInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="f1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ght" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="intern" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isPublic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="j1" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="meeting_visit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="seasonInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="stInbound" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="trainee" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="w_t" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="partnerSeasonNotes" type="{http://www.ccighgo.com/partnergenericnote}SubPartnerScreeningNotes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.ccighgo.com/partnergenericnote}TopicUserCreator" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topic", propOrder = {
    "loginId",
    "partnerSeasonId",
    "isTopicPublic",
    "partnerSeasonNoteTopicId",
    "partnerSeasonNoteTopicName",
    "competitorInfo",
    "embassyVisaInfo",
    "f1",
    "ght",
    "intern",
    "isPublic",
    "j1",
    "meetingVisit",
    "seasonInfo",
    "stInbound",
    "trainee",
    "wt",
    "partnerSeasonNotes",
    "createdBy",
    "createdOn"
})
public class Topic
    extends Response
{

    protected Integer loginId;
    protected Integer partnerSeasonId;
    protected Boolean isTopicPublic;
    protected Integer partnerSeasonNoteTopicId;
    protected String partnerSeasonNoteTopicName;
    protected Boolean competitorInfo;
    @XmlElement(name = "embassy_VisaInfo")
    protected Boolean embassyVisaInfo;
    protected Boolean f1;
    protected Boolean ght;
    protected Boolean intern;
    protected Boolean isPublic;
    protected Boolean j1;
    @XmlElement(name = "meeting_visit")
    protected Boolean meetingVisit;
    protected Boolean seasonInfo;
    protected Boolean stInbound;
    protected Boolean trainee;
    @XmlElement(name = "w_t")
    protected Boolean wt;
    protected List<SubPartnerScreeningNotes> partnerSeasonNotes;
    protected TopicUserCreator createdBy;
    protected String createdOn;

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLoginId(Integer value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the partnerSeasonId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPartnerSeasonId() {
        return partnerSeasonId;
    }

    /**
     * Sets the value of the partnerSeasonId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPartnerSeasonId(Integer value) {
        this.partnerSeasonId = value;
    }

    /**
     * Gets the value of the isTopicPublic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsTopicPublic() {
        return isTopicPublic;
    }

    /**
     * Sets the value of the isTopicPublic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsTopicPublic(Boolean value) {
        this.isTopicPublic = value;
    }

    /**
     * Gets the value of the partnerSeasonNoteTopicId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPartnerSeasonNoteTopicId() {
        return partnerSeasonNoteTopicId;
    }

    /**
     * Sets the value of the partnerSeasonNoteTopicId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPartnerSeasonNoteTopicId(Integer value) {
        this.partnerSeasonNoteTopicId = value;
    }

    /**
     * Gets the value of the partnerSeasonNoteTopicName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSeasonNoteTopicName() {
        return partnerSeasonNoteTopicName;
    }

    /**
     * Sets the value of the partnerSeasonNoteTopicName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSeasonNoteTopicName(String value) {
        this.partnerSeasonNoteTopicName = value;
    }

    /**
     * Gets the value of the competitorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompetitorInfo() {
        return competitorInfo;
    }

    /**
     * Sets the value of the competitorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompetitorInfo(Boolean value) {
        this.competitorInfo = value;
    }

    /**
     * Gets the value of the embassyVisaInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmbassyVisaInfo() {
        return embassyVisaInfo;
    }

    /**
     * Sets the value of the embassyVisaInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmbassyVisaInfo(Boolean value) {
        this.embassyVisaInfo = value;
    }

    /**
     * Gets the value of the f1 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isF1() {
        return f1;
    }

    /**
     * Sets the value of the f1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setF1(Boolean value) {
        this.f1 = value;
    }

    /**
     * Gets the value of the ght property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGht() {
        return ght;
    }

    /**
     * Sets the value of the ght property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGht(Boolean value) {
        this.ght = value;
    }

    /**
     * Gets the value of the intern property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntern() {
        return intern;
    }

    /**
     * Sets the value of the intern property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntern(Boolean value) {
        this.intern = value;
    }

    /**
     * Gets the value of the isPublic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPublic() {
        return isPublic;
    }

    /**
     * Sets the value of the isPublic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPublic(Boolean value) {
        this.isPublic = value;
    }

    /**
     * Gets the value of the j1 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isJ1() {
        return j1;
    }

    /**
     * Sets the value of the j1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setJ1(Boolean value) {
        this.j1 = value;
    }

    /**
     * Gets the value of the meetingVisit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMeetingVisit() {
        return meetingVisit;
    }

    /**
     * Sets the value of the meetingVisit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMeetingVisit(Boolean value) {
        this.meetingVisit = value;
    }

    /**
     * Gets the value of the seasonInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSeasonInfo() {
        return seasonInfo;
    }

    /**
     * Sets the value of the seasonInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSeasonInfo(Boolean value) {
        this.seasonInfo = value;
    }

    /**
     * Gets the value of the stInbound property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStInbound() {
        return stInbound;
    }

    /**
     * Sets the value of the stInbound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStInbound(Boolean value) {
        this.stInbound = value;
    }

    /**
     * Gets the value of the trainee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTrainee() {
        return trainee;
    }

    /**
     * Sets the value of the trainee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTrainee(Boolean value) {
        this.trainee = value;
    }

    /**
     * Gets the value of the wt property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWT() {
        return wt;
    }

    /**
     * Sets the value of the wt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWT(Boolean value) {
        this.wt = value;
    }

    /**
     * Gets the value of the partnerSeasonNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerSeasonNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerSeasonNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubPartnerScreeningNotes }
     * 
     * 
     */
    public List<SubPartnerScreeningNotes> getPartnerSeasonNotes() {
        if (partnerSeasonNotes == null) {
            partnerSeasonNotes = new ArrayList<SubPartnerScreeningNotes>();
        }
        return this.partnerSeasonNotes;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link TopicUserCreator }
     *     
     */
    public TopicUserCreator getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicUserCreator }
     *     
     */
    public void setCreatedBy(TopicUserCreator value) {
        this.createdBy = value;
    }

   public String getCreatedOn() {
      return createdOn;
   }

   public void setCreatedOn(String createdOn) {
      this.createdOn = createdOn;
   }
    
    

}
