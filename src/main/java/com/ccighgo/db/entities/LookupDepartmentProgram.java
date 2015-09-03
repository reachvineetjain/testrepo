package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the LookupDepartmentProgram database table.
 * 
 */
@Entity
@Table(name="LookupDepartmentPrograms")
@NamedQuery(name="LookupDepartmentProgram.findAll", query="SELECT d FROM LookupDepartmentProgram d")
public class LookupDepartmentProgram  implements Serializable{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer lookupDepartmentProgramId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(length = 100)
   private String description;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(nullable = false, length = 50)
   private String programName;


   //bi-directional many-to-one association to CCIStaffUserProgram
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<CCIStaffUserProgram> ccistaffUserPrograms;
   
   // bi-directional many-to-one association to LookupDepartment
   @ManyToOne
   @JoinColumn(name = "departmentId", nullable = false)
   private LookupDepartment lookupDepartment;

   public LookupDepartmentProgram() {
   }

   public Integer getLookupDepartmentProgramId() {
      return lookupDepartmentProgramId;
   }

   public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
      this.lookupDepartmentProgramId = lookupDepartmentProgramId;
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

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public String getProgramName() {
      return this.programName;
   }

   public void setProgramName(String programName) {
      this.programName = programName;
   }

   public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
      return this.ccistaffUserPrograms;
   }

   public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
      this.ccistaffUserPrograms = ccistaffUserPrograms;
   }

   public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
      getCcistaffUserPrograms().add(ccistaffUserProgram);
      ccistaffUserProgram.setLookupDepartmentProgram(this);

      return ccistaffUserProgram;
   }

   public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
      getCcistaffUserPrograms().remove(ccistaffUserProgram);
      ccistaffUserProgram.setLookupDepartmentProgram(null);

      return ccistaffUserProgram;
   }  

   public LookupDepartment getLookupDepartment() {
      return this.lookupDepartment;
   }

   public void setLookupDepartment(LookupDepartment lookupDepartment) {
      this.lookupDepartment = lookupDepartment;
   }

}
