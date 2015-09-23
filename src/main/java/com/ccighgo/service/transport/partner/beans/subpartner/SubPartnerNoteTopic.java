package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerNoteTopic", propOrder = {
      "subPartnerNoteTopicId",
      "competitorInfo",
      "embassy_VisaInfo",
      "f1",
      "ght",
      "intern",
      "j1",
      "meeting_visit",
      "partnerNoteTopicName",
      "seasonInfo",
      "stInbound",
      "trainee",
      "w_t"
          
})
public class SubPartnerNoteTopic {

   protected Integer subPartnerNoteTopicId;
   protected Byte competitorInfo;
   protected Byte embassy_VisaInfo;
   protected Byte f1;
   protected Byte ght;
   protected Byte intern;
   protected Byte j1;
   protected Byte meeting_visit;
   protected String partnerNoteTopicName;
   protected Byte seasonInfo;
   protected Byte stInbound;
   protected Byte trainee;
   protected Byte w_t;
   
   
   public Integer getSubPartnerNoteTopicId() {
      return subPartnerNoteTopicId;
   }
   public void setSubPartnerNoteTopicId(Integer subPartnerNoteTopicId) {
      this.subPartnerNoteTopicId = subPartnerNoteTopicId;
   }
   public Byte getCompetitorInfo() {
      return competitorInfo;
   }
   public void setCompetitorInfo(Byte competitorInfo) {
      this.competitorInfo = competitorInfo;
   }
   public Byte getEmbassy_VisaInfo() {
      return embassy_VisaInfo;
   }
   public void setEmbassy_VisaInfo(Byte embassy_VisaInfo) {
      this.embassy_VisaInfo = embassy_VisaInfo;
   }
   public Byte getF1() {
      return f1;
   }
   public void setF1(Byte f1) {
      this.f1 = f1;
   }
   public Byte getGht() {
      return ght;
   }
   public void setGht(Byte ght) {
      this.ght = ght;
   }
   public Byte getIntern() {
      return intern;
   }
   public void setIntern(Byte intern) {
      this.intern = intern;
   }
   public Byte getJ1() {
      return j1;
   }
   public void setJ1(Byte j1) {
      this.j1 = j1;
   }
   public Byte getMeeting_visit() {
      return meeting_visit;
   }
   public void setMeeting_visit(Byte meeting_visit) {
      this.meeting_visit = meeting_visit;
   }
   public String getPartnerNoteTopicName() {
      return partnerNoteTopicName;
   }
   public void setPartnerNoteTopicName(String partnerNoteTopicName) {
      this.partnerNoteTopicName = partnerNoteTopicName;
   }
   public Byte getSeasonInfo() {
      return seasonInfo;
   }
   public void setSeasonInfo(Byte seasonInfo) {
      this.seasonInfo = seasonInfo;
   }
   public Byte getStInbound() {
      return stInbound;
   }
   public void setStInbound(Byte stInbound) {
      this.stInbound = stInbound;
   }
   public Byte getTrainee() {
      return trainee;
   }
   public void setTrainee(Byte trainee) {
      this.trainee = trainee;
   }
   public Byte getW_t() {
      return w_t;
   }
   public void setW_t(Byte w_t) {
      this.w_t = w_t;
   }
   
   
}
