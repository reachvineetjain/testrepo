package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Participants database table.
 * 
 */
@Entity
@Table(name="Participants")
@NamedQuery(name="Participant.findAll", query="SELECT p FROM Participant p")
public class Participant implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer participantGoId;

   @Column(length=50)
   private String email;

   @Temporal(TemporalType.TIMESTAMP)
   private Date endDate;

   @Column(length=50)
   private String firstName;

   private byte guaranteed;

   private byte isLead;

   @Column(length=50)
   private String lastName;

   private Integer participantStatusId;

   @Temporal(TemporalType.TIMESTAMP)
   private Date startDate;

   private byte submittedFlightInfo;

   private Integer subPartner;

   //bi-directional many-to-one association to DepartmentProgramOption
   @ManyToOne
   @JoinColumn(name="departmentProgramOption")
   private DepartmentProgramOption departmentProgramOptionBean;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId")
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to LookupCountry
   @ManyToOne
   @JoinColumn(name="countryId")
   private LookupCountry lookupCountry;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner1;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="subPartnerId")
   private Partner partner2;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId")
   private Season season;

   public Participant() {
   }

   public Integer getParticipantGoId() {
      return this.participantGoId;
   }

   public void setParticipantGoId(Integer participantGoId) {
      this.participantGoId = participantGoId;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public byte getGuaranteed() {
      return this.guaranteed;
   }

   public void setGuaranteed(byte guaranteed) {
      this.guaranteed = guaranteed;
   }

   public byte getIsLead() {
      return this.isLead;
   }

   public void setIsLead(byte isLead) {
      this.isLead = isLead;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Integer getParticipantStatusId() {
      return this.participantStatusId;
   }

   public void setParticipantStatusId(Integer participantStatusId) {
      this.participantStatusId = participantStatusId;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public byte getSubmittedFlightInfo() {
      return this.submittedFlightInfo;
   }

   public void setSubmittedFlightInfo(byte submittedFlightInfo) {
      this.submittedFlightInfo = submittedFlightInfo;
   }

   public Integer getSubPartner() {
      return this.subPartner;
   }

   public void setSubPartner(Integer subPartner) {
      this.subPartner = subPartner;
   }

   public DepartmentProgramOption getDepartmentProgramOptionBean() {
      return this.departmentProgramOptionBean;
   }

   public void setDepartmentProgramOptionBean(DepartmentProgramOption departmentProgramOptionBean) {
      this.departmentProgramOptionBean = departmentProgramOptionBean;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public LookupCountry getLookupCountry() {
      return this.lookupCountry;
   }

   public void setLookupCountry(LookupCountry lookupCountry) {
      this.lookupCountry = lookupCountry;
   }

   public Partner getPartner1() {
      return this.partner1;
   }

   public void setPartner1(Partner partner1) {
      this.partner1 = partner1;
   }

   public Partner getPartner2() {
      return this.partner2;
   }

   public void setPartner2(Partner partner2) {
      this.partner2 = partner2;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}