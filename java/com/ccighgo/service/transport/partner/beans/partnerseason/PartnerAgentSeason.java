package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonListObject", propOrder = {
    "seasonId",
    "seasonName",
    "seasonFullName"
})



public class PartnerAgentSeason {

   protected int seasonId;
   protected String seasonName;
   protected String seasonFullName;
   
   
   public int getSeasonId() {
      return seasonId;
   }
   public void setSeasonId(int seasonId) {
      this.seasonId = seasonId;
   }
   public String getSeasonName() {
      return seasonName;
   }
   public void setSeasonName(String seasonName) {
      this.seasonName = seasonName;
   }
   public String getSeasonFullName() {
      return seasonFullName;
   }
   public void setSeasonFullName(String seasonFullName) {
      this.seasonFullName = seasonFullName;
   }  
   
}
