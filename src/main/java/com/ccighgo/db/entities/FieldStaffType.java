package com.ccighgo.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the FieldStaffType database table.
 * 
 */
@Entity
@NamedQuery(name = "FieldStaffType.findAll", query = "SELECT f FROM FieldStaffType f")
public class FieldStaffType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer fieldStaffTypeId;

   private String fieldStaffType;

   private String fieldStaffTypeCode;

   // bi-directional many-to-one association to FieldStaff
   @OneToMany(mappedBy = "fieldStaffType")
   private List<FieldStaff> fieldStaffs;

   public FieldStaffType() {
   }

   public Integer getFieldStaffTypeId() {
      return this.fieldStaffTypeId;
   }

   public void setFieldStaffTypeId(Integer fieldStaffTypeId) {
      this.fieldStaffTypeId = fieldStaffTypeId;
   }

   public String getFieldStaffType() {
      return this.fieldStaffType;
   }

   public void setFieldStaffType(String fieldStaffType) {
      this.fieldStaffType = fieldStaffType;
   }

   public String getFieldStaffTypeCode() {
      return this.fieldStaffTypeCode;
   }

   public void setFieldStaffTypeCode(String fieldStaffTypeCode) {
      this.fieldStaffTypeCode = fieldStaffTypeCode;
   }

   public List<FieldStaff> getFieldStaffs() {
      return this.fieldStaffs;
   }

   public void setFieldStaffs(List<FieldStaff> fieldStaffs) {
      this.fieldStaffs = fieldStaffs;
   }

   public FieldStaff addFieldStaff(FieldStaff fieldStaff) {
      getFieldStaffs().add(fieldStaff);
      fieldStaff.setFieldStaffType(this);

      return fieldStaff;
   }

   public FieldStaff removeFieldStaff(FieldStaff fieldStaff) {
      getFieldStaffs().remove(fieldStaff);
      fieldStaff.setFieldStaffType(null);

      return fieldStaff;
   }

}