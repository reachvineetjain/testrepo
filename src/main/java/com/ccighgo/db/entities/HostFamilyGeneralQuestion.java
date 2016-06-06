package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyGeneralQuestions database table.
 * 
 */
@Entity
@Table(name="HostFamilyGeneralQuestions")
@NamedQuery(name="HostFamilyGeneralQuestion.findAll", query="SELECT h FROM HostFamilyGeneralQuestion h")
public class HostFamilyGeneralQuestion implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyGeneralQuestionsId;

   @Column(length=1000)
   private String community;

   @Column(length=100)
   private String communityEvent;

   @Column(length=100)
   private String communityMagazine;

   @Column(length=100)
   private String communityOthers;

   @Column(length=50)
   private String internet;

   @Column(length=50)
   private String internetOthers;

   private Byte previousHostingWithCCI;

   //bi-directional many-to-one association to HostFamilySeason
   @ManyToOne
   @JoinColumn(name="hostFamilySeasonId")
   private HostFamilySeason hostFamilySeason;

   public HostFamilyGeneralQuestion() {
   }

   public Integer getHostFamilyGeneralQuestionsId() {
      return this.hostFamilyGeneralQuestionsId;
   }

   public void setHostFamilyGeneralQuestionsId(Integer hostFamilyGeneralQuestionsId) {
      this.hostFamilyGeneralQuestionsId = hostFamilyGeneralQuestionsId;
   }

   public String getCommunity() {
      return this.community;
   }

   public void setCommunity(String community) {
      this.community = community;
   }

   public String getCommunityEvent() {
      return this.communityEvent;
   }

   public void setCommunityEvent(String communityEvent) {
      this.communityEvent = communityEvent;
   }

   public String getCommunityMagazine() {
      return this.communityMagazine;
   }

   public void setCommunityMagazine(String communityMagazine) {
      this.communityMagazine = communityMagazine;
   }

   public String getCommunityOthers() {
      return this.communityOthers;
   }

   public void setCommunityOthers(String communityOthers) {
      this.communityOthers = communityOthers;
   }

   public String getInternet() {
      return this.internet;
   }

   public void setInternet(String internet) {
      this.internet = internet;
   }

   public String getInternetOthers() {
      return this.internetOthers;
   }

   public void setInternetOthers(String internetOthers) {
      this.internetOthers = internetOthers;
   }

   public Byte getPreviousHostingWithCCI() {
      return this.previousHostingWithCCI;
   }

   public void setPreviousHostingWithCCI(Byte previousHostingWithCCI) {
      this.previousHostingWithCCI = previousHostingWithCCI;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

}