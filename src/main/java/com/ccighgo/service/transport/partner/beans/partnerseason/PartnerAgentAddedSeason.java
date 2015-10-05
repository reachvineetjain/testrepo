package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAgentAddedSeason", propOrder = {
    "partnerId",
    "partnerSeasonId",
    "partnerSeasonProgramName",
    "partnerSeasonDepartment",
    "partnerProgramOption",
    "partnerStartDate",
    "partnerEndDate",
    "partnerApplicationDeadlineDate",
    "seasonProgramStatus",
    "seasonStatus"
})
public class PartnerAgentAddedSeason {
   


   @XmlElement(required = true)
   protected String partnerId;
   protected int partnerSeasonId;
   @XmlElement(required = true)
   protected String partnerSeasonProgramName;
   @XmlElement(required = true)
   protected PartnerSeasonDepartment partnerSeasonDepartment;
   @XmlElement(required = true)
   protected PartnerSeasonProgramOption partnerProgramOption;
   @XmlElement(required = true)
   protected String partnerStartDate;
   @XmlElement(required = true)
   protected String partnerEndDate;
   @XmlElement(required = true)
   protected String partnerApplicationDeadlineDate;
   @XmlElement(required = true)
   protected PartnerSeasonProgramStatus seasonProgramStatus;
   protected SeasonStatus seasonStatus;

   /**
    * Gets the value of the partnerId property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getPartnerId() {
       return partnerId;
   }

   /**
    * Sets the value of the partnerId property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setPartnerId(String value) {
       this.partnerId = value;
   }

   /**
    * Gets the value of the partnerSeasonId property.
    * 
    */
   public int getPartnerSeasonId() {
       return partnerSeasonId;
   }

   /**
    * Sets the value of the partnerSeasonId property.
    * 
    */
   public void setPartnerSeasonId(int value) {
       this.partnerSeasonId = value;
   }

   /**
    * Gets the value of the partnerSeasonProgramName property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getPartnerSeasonProgramName() {
       return partnerSeasonProgramName;
   }

   /**
    * Sets the value of the partnerSeasonProgramName property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setPartnerSeasonProgramName(String value) {
       this.partnerSeasonProgramName = value;
   }

   /**
    * Gets the value of the partnerSeasonDepartment property.
    * 
    * @return
    *     possible object is
    *     {@link PartnerSeasonDepartment }
    *     
    */
   public PartnerSeasonDepartment getPartnerSeasonDepartment() {
       return partnerSeasonDepartment;
   }

   /**
    * Sets the value of the partnerSeasonDepartment property.
    * 
    * @param value
    *     allowed object is
    *     {@link PartnerSeasonDepartment }
    *     
    */
   public void setPartnerSeasonDepartment(PartnerSeasonDepartment value) {
       this.partnerSeasonDepartment = value;
   }

   /**
    * Gets the value of the partnerProgramOption property.
    * 
    * @return
    *     possible object is
    *     {@link PartnerSeasonProgramOption }
    *     
    */
   public PartnerSeasonProgramOption getPartnerProgramOption() {
       return partnerProgramOption;
   }

   /**
    * Sets the value of the partnerProgramOption property.
    * 
    * @param value
    *     allowed object is
    *     {@link PartnerSeasonProgramOption }
    *     
    */
   public void setPartnerProgramOption(PartnerSeasonProgramOption value) {
       this.partnerProgramOption = value;
   }

   /**
    * Gets the value of the partnerStartDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getPartnerStartDate() {
       return partnerStartDate;
   }

   /**
    * Sets the value of the partnerStartDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setPartnerStartDate(String value) {
       this.partnerStartDate = value;
   }

   /**
    * Gets the value of the partnerEndDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getPartnerEndDate() {
       return partnerEndDate;
   }

   /**
    * Sets the value of the partnerEndDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setPartnerEndDate(String value) {
       this.partnerEndDate = value;
   }

   /**
    * Gets the value of the partnerApplicationDeadlineDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getPartnerApplicationDeadlineDate() {
       return partnerApplicationDeadlineDate;
   }

   /**
    * Sets the value of the partnerApplicationDeadlineDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setPartnerApplicationDeadlineDate(String value) {
       this.partnerApplicationDeadlineDate = value;
   }


   /**
    * Gets the value of the seasonProgramStatus property.
    * 
    * @return
    *     possible object is
    *     {@link PartnerSeasonProgramStatus }
    *     
    */
   public PartnerSeasonProgramStatus getSeasonProgramStatus() {
       return seasonProgramStatus;
   }

   /**
    * Sets the value of the seasonProgramStatus property.
    * 
    * @param value
    *     allowed object is
    *     {@link PartnerSeasonProgramStatus }
    *     
    */
   public void setSeasonProgramStatus(PartnerSeasonProgramStatus value) {
       this.seasonProgramStatus = value;
   }

   public SeasonStatus getSeasonStatus() {
      return seasonStatus;
   }

   public void setSeasonStatus(SeasonStatus seasonStatus) {
      this.seasonStatus = seasonStatus;
   }


}
