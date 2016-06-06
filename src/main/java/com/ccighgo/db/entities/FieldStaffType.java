package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffType database table.
 * 
 */
@Entity
@Table(name="FieldStaffType")
@NamedQuery(name="FieldStaffType.findAll", query="SELECT f FROM FieldStaffType f")
public class FieldStaffType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffTypeId;

   @Column(length=45)
   private String fieldStaffTypeCode;

   @Column(length=45)
   private String fieldStaffTypeName;

   //bi-directional many-to-one association to FieldStaff
   @OneToMany(mappedBy="fieldStaffType")
   private List<FieldStaff> fieldStaffs;

   public FieldStaffType() {
   }

   public Integer getFieldStaffTypeId() {
      return this.fieldStaffTypeId;
   }

   public void setFieldStaffTypeId(Integer fieldStaffTypeId) {
      this.fieldStaffTypeId = fieldStaffTypeId;
   }

   public String getFieldStaffTypeCode() {
      return this.fieldStaffTypeCode;
   }

   public void setFieldStaffTypeCode(String fieldStaffTypeCode) {
      this.fieldStaffTypeCode = fieldStaffTypeCode;
   }

   public String getFieldStaffTypeName() {
      return this.fieldStaffTypeName;
   }

   public void setFieldStaffTypeName(String fieldStaffTypeName) {
      this.fieldStaffTypeName = fieldStaffTypeName;
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