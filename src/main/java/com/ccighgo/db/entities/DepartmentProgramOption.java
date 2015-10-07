package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the DepartmentProgramOptions database table.
 * 
 */
@Entity
@Table(name = "DepartmentProgramOptions")
@NamedQuery(name = "DepartmentProgramOption.findAll", query = "SELECT d FROM DepartmentProgramOption d")
public class DepartmentProgramOption implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(unique = true, nullable = false) private Integer departmentProgramOptionId;

   @Column(nullable = false) private Integer lookupDepartmentProgramId;

   @Column(nullable = false, length = 10) private String programOptionCode;

   @Column(nullable = false, length = 50) private String programOptionName;

   // bi-directional many-to-one association to DepartmentProgram
   @ManyToOne @JoinColumn(name = "departmentProgramId", nullable = false) private DepartmentProgram departmentProgram;

   // bi-directional many-to-one association to Participant
   @OneToMany(mappedBy = "departmentProgramOptionBean") private List<Participant> participants;

   // bi-directional many-to-one association to PartnerSeasonAllocation
   @OneToMany(mappedBy = "departmentProgramOption2") private List<PartnerSeasonAllocation> partnerSeasonAllocations2;

   // bi-directional many-to-one association to SeasonHSPAllocation
   @OneToMany(mappedBy = "departmentProgramOption") private List<SeasonHSPAllocation> seasonHspallocations;

   // bi-directional many-to-one association to SeasonWPAllocation
   @OneToMany(mappedBy = "departmentProgramOption") private List<SeasonWPAllocation> seasonWpallocations;

   // bi-directional many-to-one association to SeasonWPAllocation
   @OneToMany(mappedBy = "departmentProgramOption") private List<PartnerSeasonAllocation> partnerSeasonAllocations;

   public DepartmentProgramOption() {
   }

   public Integer getDepartmentProgramOptionId() {
      return this.departmentProgramOptionId;
   }

   public void setDepartmentProgramOptionId(Integer departmentProgramOptionId) {
      this.departmentProgramOptionId = departmentProgramOptionId;
   }

   public Integer getLookupDepartmentProgramId() {
      return this.lookupDepartmentProgramId;
   }

   public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
      this.lookupDepartmentProgramId = lookupDepartmentProgramId;
   }

   public String getProgramOptionCode() {
      return this.programOptionCode;
   }

   public void setProgramOptionCode(String programOptionCode) {
      this.programOptionCode = programOptionCode;
   }

   public String getProgramOptionName() {
      return this.programOptionName;
   }

   public void setProgramOptionName(String programOptionName) {
      this.programOptionName = programOptionName;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public List<Participant> getParticipants() {
      return this.participants;
   }

   public void setParticipants(List<Participant> participants) {
      this.participants = participants;
   }

   public Participant addParticipant(Participant participant) {
      getParticipants().add(participant);
      participant.setDepartmentProgramOptionBean(this);

      return participant;
   }

   public Participant removeParticipant(Participant participant) {
      getParticipants().remove(participant);
      participant.setDepartmentProgramOptionBean(null);

      return participant;
   }

   public List<PartnerSeasonAllocation> getPartnerSeasonAllocations2() {
      return this.partnerSeasonAllocations2;
   }

   public void setPartnerSeasonAllocations2(List<PartnerSeasonAllocation> partnerSeasonAllocations2) {
      this.partnerSeasonAllocations2 = partnerSeasonAllocations2;
   }

   public PartnerSeasonAllocation addPartnerSeasonAllocations2(PartnerSeasonAllocation partnerSeasonAllocations2) {
      getPartnerSeasonAllocations2().add(partnerSeasonAllocations2);
      partnerSeasonAllocations2.setDepartmentProgramOption2(this);

      return partnerSeasonAllocations2;
   }

   public PartnerSeasonAllocation removePartnerSeasonAllocations2(PartnerSeasonAllocation partnerSeasonAllocations2) {
      getPartnerSeasonAllocations2().remove(partnerSeasonAllocations2);
      partnerSeasonAllocations2.setDepartmentProgramOption2(null);

      return partnerSeasonAllocations2;
   }

   public List<SeasonHSPAllocation> getSeasonHspallocations() {
      return this.seasonHspallocations;
   }

   public void setSeasonHspallocations(List<SeasonHSPAllocation> seasonHspallocations) {
      this.seasonHspallocations = seasonHspallocations;
   }

   public SeasonHSPAllocation addSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
      getSeasonHspallocations().add(seasonHspallocation);
      seasonHspallocation.setDepartmentProgramOption(this);

      return seasonHspallocation;
   }

   public SeasonHSPAllocation removeSeasonHspallocation(SeasonHSPAllocation seasonHspallocation) {
      getSeasonHspallocations().remove(seasonHspallocation);
      seasonHspallocation.setDepartmentProgramOption(null);

      return seasonHspallocation;
   }

   public List<SeasonWPAllocation> getSeasonWpallocations() {
      return this.seasonWpallocations;
   }

   public void setSeasonWpallocations(List<SeasonWPAllocation> seasonWpallocations) {
      this.seasonWpallocations = seasonWpallocations;
   }

   public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
      return partnerSeasonAllocations;
   }

   public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
      this.partnerSeasonAllocations = partnerSeasonAllocations;
   }

   public PartnerSeasonAllocation addPartnerSeasonAllocations(PartnerSeasonAllocation partnerSeasonAllocations) {
      getPartnerSeasonAllocations().add(partnerSeasonAllocations);
      partnerSeasonAllocations.setDepartmentProgramOption(this);

      return partnerSeasonAllocations;
   }

   public PartnerSeasonAllocation removePartnerSeasonAllocations(PartnerSeasonAllocation partnerSeasonAllocations) {
      getPartnerSeasonAllocations().remove(partnerSeasonAllocations);
      partnerSeasonAllocations.setDepartmentProgramOption(null);

      return partnerSeasonAllocations;
   }

   public SeasonWPAllocation addSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
      getSeasonWpallocations().add(seasonWpallocation);
      seasonWpallocation.setDepartmentProgramOption(this);

      return seasonWpallocation;
   }

   public SeasonWPAllocation removeSeasonWpallocation(SeasonWPAllocation seasonWpallocation) {
      getSeasonWpallocations().remove(seasonWpallocation);
      seasonWpallocation.setDepartmentProgramOption(null);

      return seasonWpallocation;
   }

}