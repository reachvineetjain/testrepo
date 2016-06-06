package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyReference database table.
 * 
 */
@Entity
@Table(name="HostFamilyReference")
@NamedQuery(name="HostFamilyReference.findAll", query="SELECT h FROM HostFamilyReference h")
public class HostFamilyReference implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyReferenceId;

   private Byte active;

   private Integer additionalSupport;

   @Column(length=100)
   private String address;

   private Byte allowOwnChildStay;

   @Column(length=50)
   private String city;

   @Column(length=30)
   private String closeness;

   @Lob
   private String comments;

   @Column(length=30)
   private String communityInvolvement;

   @Lob
   private String communityTies;

   @Column(length=50)
   private String completionMethod;

   private Integer createdBy;

   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date dateOfReference;

   @Column(length=100)
   private String firstName;

   @Column(length=30)
   private String flexibility;

   @Column(length=30)
   private String interestVariety;

   @Column(length=50)
   private String internationalAwareness;

   private Byte isThirdReferenceForSingleHost;

   @Column(length=30)
   private String knownFamilyMethod;

   @Column(length=100)
   private String lastName;

   @Column(length=100)
   private String lastVisit;

   @Column(length=50)
   private String lengthKnown;

   @Column(length=50)
   private String lengthKnownQuantity;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Lob
   private String ownChildStayReasons;

   private Byte personNotRelatedToBlood;

   @Column(length=30)
   private String phone;

   @Column(length=30)
   private String positiveExperiences;

   @Column(length=50)
   private String relationship;

   private Byte secondReferenceRelationtoFirst;

   @Column(length=30)
   private String stability;

   @Lob
   private String supportExplanation;

   @Column(length=100)
   private String visitFrequency;

   @Column(length=30)
   private String zipCode;

   //bi-directional many-to-one association to HostFamilySeason
   @ManyToOne
   @JoinColumn(name="hostFamilySeasonId")
   private HostFamilySeason hostFamilySeason;

   //bi-directional many-to-one association to LookupUSState
   @ManyToOne
   @JoinColumn(name="usStateId")
   private LookupUSState lookupUsstate;

   public HostFamilyReference() {
   }

   public Integer getHostFamilyReferenceId() {
      return this.hostFamilyReferenceId;
   }

   public void setHostFamilyReferenceId(Integer hostFamilyReferenceId) {
      this.hostFamilyReferenceId = hostFamilyReferenceId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public Integer getAdditionalSupport() {
      return this.additionalSupport;
   }

   public void setAdditionalSupport(Integer additionalSupport) {
      this.additionalSupport = additionalSupport;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Byte getAllowOwnChildStay() {
      return this.allowOwnChildStay;
   }

   public void setAllowOwnChildStay(Byte allowOwnChildStay) {
      this.allowOwnChildStay = allowOwnChildStay;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCloseness() {
      return this.closeness;
   }

   public void setCloseness(String closeness) {
      this.closeness = closeness;
   }

   public String getComments() {
      return this.comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public String getCommunityInvolvement() {
      return this.communityInvolvement;
   }

   public void setCommunityInvolvement(String communityInvolvement) {
      this.communityInvolvement = communityInvolvement;
   }

   public String getCommunityTies() {
      return this.communityTies;
   }

   public void setCommunityTies(String communityTies) {
      this.communityTies = communityTies;
   }

   public String getCompletionMethod() {
      return this.completionMethod;
   }

   public void setCompletionMethod(String completionMethod) {
      this.completionMethod = completionMethod;
   }

   public Integer getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(Integer createdBy) {
      this.createdBy = createdBy;
   }

   public Timestamp getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Timestamp createdOn) {
      this.createdOn = createdOn;
   }

   public Date getDateOfReference() {
      return this.dateOfReference;
   }

   public void setDateOfReference(Date dateOfReference) {
      this.dateOfReference = dateOfReference;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getFlexibility() {
      return this.flexibility;
   }

   public void setFlexibility(String flexibility) {
      this.flexibility = flexibility;
   }

   public String getInterestVariety() {
      return this.interestVariety;
   }

   public void setInterestVariety(String interestVariety) {
      this.interestVariety = interestVariety;
   }

   public String getInternationalAwareness() {
      return this.internationalAwareness;
   }

   public void setInternationalAwareness(String internationalAwareness) {
      this.internationalAwareness = internationalAwareness;
   }

   public Byte getIsThirdReferenceForSingleHost() {
      return this.isThirdReferenceForSingleHost;
   }

   public void setIsThirdReferenceForSingleHost(Byte isThirdReferenceForSingleHost) {
      this.isThirdReferenceForSingleHost = isThirdReferenceForSingleHost;
   }

   public String getKnownFamilyMethod() {
      return this.knownFamilyMethod;
   }

   public void setKnownFamilyMethod(String knownFamilyMethod) {
      this.knownFamilyMethod = knownFamilyMethod;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getLastVisit() {
      return this.lastVisit;
   }

   public void setLastVisit(String lastVisit) {
      this.lastVisit = lastVisit;
   }

   public String getLengthKnown() {
      return this.lengthKnown;
   }

   public void setLengthKnown(String lengthKnown) {
      this.lengthKnown = lengthKnown;
   }

   public String getLengthKnownQuantity() {
      return this.lengthKnownQuantity;
   }

   public void setLengthKnownQuantity(String lengthKnownQuantity) {
      this.lengthKnownQuantity = lengthKnownQuantity;
   }

   public Integer getModifiedBy() {
      return this.modifiedBy;
   }

   public void setModifiedBy(Integer modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Timestamp getModifiedOn() {
      return this.modifiedOn;
   }

   public void setModifiedOn(Timestamp modifiedOn) {
      this.modifiedOn = modifiedOn;
   }

   public String getOwnChildStayReasons() {
      return this.ownChildStayReasons;
   }

   public void setOwnChildStayReasons(String ownChildStayReasons) {
      this.ownChildStayReasons = ownChildStayReasons;
   }

   public Byte getPersonNotRelatedToBlood() {
      return this.personNotRelatedToBlood;
   }

   public void setPersonNotRelatedToBlood(Byte personNotRelatedToBlood) {
      this.personNotRelatedToBlood = personNotRelatedToBlood;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPositiveExperiences() {
      return this.positiveExperiences;
   }

   public void setPositiveExperiences(String positiveExperiences) {
      this.positiveExperiences = positiveExperiences;
   }

   public String getRelationship() {
      return this.relationship;
   }

   public void setRelationship(String relationship) {
      this.relationship = relationship;
   }

   public Byte getSecondReferenceRelationtoFirst() {
      return this.secondReferenceRelationtoFirst;
   }

   public void setSecondReferenceRelationtoFirst(Byte secondReferenceRelationtoFirst) {
      this.secondReferenceRelationtoFirst = secondReferenceRelationtoFirst;
   }

   public String getStability() {
      return this.stability;
   }

   public void setStability(String stability) {
      this.stability = stability;
   }

   public String getSupportExplanation() {
      return this.supportExplanation;
   }

   public void setSupportExplanation(String supportExplanation) {
      this.supportExplanation = supportExplanation;
   }

   public String getVisitFrequency() {
      return this.visitFrequency;
   }

   public void setVisitFrequency(String visitFrequency) {
      this.visitFrequency = visitFrequency;
   }

   public String getZipCode() {
      return this.zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

   public LookupUSState getLookupUsstate() {
      return this.lookupUsstate;
   }

   public void setLookupUsstate(LookupUSState lookupUsstate) {
      this.lookupUsstate = lookupUsstate;
   }

}