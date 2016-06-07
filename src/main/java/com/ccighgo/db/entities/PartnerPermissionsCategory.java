package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerPermissionsCategory database table.
 * 
 */
@Entity
@Table(name="PartnerPermissionsCategory")
@NamedQuery(name="PartnerPermissionsCategory.findAll", query="SELECT p FROM PartnerPermissionsCategory p")
public class PartnerPermissionsCategory implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerPermissionsCategoryId;

   @Column(length=50)
   private String partnerPermissionsCategory;

   //bi-directional many-to-one association to PartnerPermission
   @OneToMany(mappedBy="partnerPermissionsCategory")
   private List<PartnerPermission> partnerPermissions;

   public PartnerPermissionsCategory() {
   }

   public Integer getPartnerPermissionsCategoryId() {
      return this.partnerPermissionsCategoryId;
   }

   public void setPartnerPermissionsCategoryId(Integer partnerPermissionsCategoryId) {
      this.partnerPermissionsCategoryId = partnerPermissionsCategoryId;
   }

   public String getPartnerPermissionsCategory() {
      return this.partnerPermissionsCategory;
   }

   public void setPartnerPermissionsCategory(String partnerPermissionsCategory) {
      this.partnerPermissionsCategory = partnerPermissionsCategory;
   }

   public List<PartnerPermission> getPartnerPermissions() {
      return this.partnerPermissions;
   }

   public void setPartnerPermissions(List<PartnerPermission> partnerPermissions) {
      this.partnerPermissions = partnerPermissions;
   }

   public PartnerPermission addPartnerPermission(PartnerPermission partnerPermission) {
      getPartnerPermissions().add(partnerPermission);
      partnerPermission.setPartnerPermissionsCategory(this);

      return partnerPermission;
   }

   public PartnerPermission removePartnerPermission(PartnerPermission partnerPermission) {
      getPartnerPermissions().remove(partnerPermission);
      partnerPermission.setPartnerPermissionsCategory(null);

      return partnerPermission;
   }

}