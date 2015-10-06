package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonAgentDate", propOrder = {
    "options",
    "partnerSeasonStartDate",
    "partnerSeasonEndDate",
    "partnerSeasonAppDeadlineDate",
    "partnerSeasonExtAppDeadlineDate",
    "partnerSeasonSecSemDeadlineDate",
    "partnerSeasonExtSecSemDeadlineDate"
})
public class PartnerSeasonAgentDate {
   
   protected String options;
   protected String partnerSeasonStartDate;
   protected String partnerSeasonEndDate;
   protected String partnerSeasonAppDeadlineDate;
   protected String partnerSeasonExtAppDeadlineDate;
   protected String partnerSeasonSecSemDeadlineDate;
   protected String partnerSeasonExtSecSemDeadlineDate;
   
   
   public String getOptions() {
      return options;
   }
   public void setOptions(String options) {
      this.options = options;
   }
   public String getPartnerSeasonStartDate() {
      return partnerSeasonStartDate;
   }
   public void setPartnerSeasonStartDate(String partnerSeasonStartDate) {
      this.partnerSeasonStartDate = partnerSeasonStartDate;
   }
   public String getPartnerSeasonEndDate() {
      return partnerSeasonEndDate;
   }
   public void setPartnerSeasonEndDate(String partnerSeasonEndDate) {
      this.partnerSeasonEndDate = partnerSeasonEndDate;
   }
   public String getPartnerSeasonAppDeadlineDate() {
      return partnerSeasonAppDeadlineDate;
   }
   public void setPartnerSeasonAppDeadlineDate(String partnerSeasonAppDeadlineDate) {
      this.partnerSeasonAppDeadlineDate = partnerSeasonAppDeadlineDate;
   }
   public String getPartnerSeasonExtAppDeadlineDate() {
      return partnerSeasonExtAppDeadlineDate;
   }
   public void setPartnerSeasonExtAppDeadlineDate(String partnerSeasonExtAppDeadlineDate) {
      this.partnerSeasonExtAppDeadlineDate = partnerSeasonExtAppDeadlineDate;
   }
   public String getPartnerSeasonSecSemDeadlineDate() {
      return partnerSeasonSecSemDeadlineDate;
   }
   public void setPartnerSeasonSecSemDeadlineDate(String partnerSeasonSecSemDeadlineDate) {
      this.partnerSeasonSecSemDeadlineDate = partnerSeasonSecSemDeadlineDate;
   }
   public String getPartnerSeasonExtSecSemDeadlineDate() {
      return partnerSeasonExtSecSemDeadlineDate;
   }
   public void setPartnerSeasonExtSecSemDeadlineDate(String partnerSeasonExtSecSemDeadlineDate) {
      this.partnerSeasonExtSecSemDeadlineDate = partnerSeasonExtSecSemDeadlineDate;
   }
}
