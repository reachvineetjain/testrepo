package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyUpdateLog database table.
 * 
 */
@Entity
@Table(name="HostFamilyUpdateLog")
@NamedQuery(name="HostFamilyUpdateLog.findAll", query="SELECT h FROM HostFamilyUpdateLog h")
public class HostFamilyUpdateLog implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyUpdateLogId;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   @Lob
   private String updateLogObject;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   public HostFamilyUpdateLog() {
   }

   public Integer getHostFamilyUpdateLogId() {
      return this.hostFamilyUpdateLogId;
   }

   public void setHostFamilyUpdateLogId(Integer hostFamilyUpdateLogId) {
      this.hostFamilyUpdateLogId = hostFamilyUpdateLogId;
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

   public String getUpdateLogObject() {
      return this.updateLogObject;
   }

   public void setUpdateLogObject(String updateLogObject) {
      this.updateLogObject = updateLogObject;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

}