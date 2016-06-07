package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyMileageCheck database table.
 * 
 */
@Entity
@Table(name="HostFamilyMileageCheck")
@NamedQuery(name="HostFamilyMileageCheck.findAll", query="SELECT h FROM HostFamilyMileageCheck h")
public class HostFamilyMileageCheck implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyMileageCheckId;

   private Byte active;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   private Integer fieldStaffGoId;

   private Byte isApproved;

   private Integer modifiedBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedOn;

   //bi-directional many-to-one association to HostFamilySeason
   @ManyToOne
   @JoinColumn(name="hostFamilySeasonId")
   private HostFamilySeason hostFamilySeason;

   public HostFamilyMileageCheck() {
   }

   public Integer getHostFamilyMileageCheckId() {
      return this.hostFamilyMileageCheckId;
   }

   public void setHostFamilyMileageCheckId(Integer hostFamilyMileageCheckId) {
      this.hostFamilyMileageCheckId = hostFamilyMileageCheckId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public Integer getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(Integer createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public Integer getFieldStaffGoId() {
      return this.fieldStaffGoId;
   }

   public void setFieldStaffGoId(Integer fieldStaffGoId) {
      this.fieldStaffGoId = fieldStaffGoId;
   }

   public Byte getIsApproved() {
      return this.isApproved;
   }

   public void setIsApproved(Byte isApproved) {
      this.isApproved = isApproved;
   }

   public Integer getModifiedBy() {
      return this.modifiedBy;
   }

   public void setModifiedBy(Integer modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Date getModifiedOn() {
      return this.modifiedOn;
   }

   public void setModifiedOn(Date modifiedOn) {
      this.modifiedOn = modifiedOn;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

}