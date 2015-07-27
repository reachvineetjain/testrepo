package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the DepartmentFunctions database table.
 * 
 */
@Entity
@Table(name = "DepartmentFunctions")
@NamedQuery(name = "DepartmentFunction.findAll", query = "SELECT d FROM DepartmentFunction d")
public class DepartmentFunction implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer deptFunctionId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer departmentId;

   @Column(length = 200)
   private String functionDescription;

   @Column(length = 100)
   private String functionName;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   public DepartmentFunction() {
   }

   public Integer getDeptFunctionId() {
      if (this.deptFunctionId != null)
         return this.deptFunctionId;
      return 0;
   }

   public void setDeptFunctionId(Integer deptFunctionId) {
      this.deptFunctionId = deptFunctionId;
   }

   public Integer getCreatedBy() {
      if (this.createdBy != null)
         return this.createdBy;
      return 0;

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

   public Integer getDepartmentId() {
      if (this.departmentId != null)
         return this.departmentId;
      return 0;
   }

   public void setDepartmentId(Integer departmentId) {
      this.departmentId = departmentId;
   }

   public String getFunctionDescription() {
      return this.functionDescription;
   }

   public void setFunctionDescription(String functionDescription) {
      this.functionDescription = functionDescription;
   }

   public String getFunctionName() {
      return this.functionName;
   }

   public void setFunctionName(String functionName) {
      this.functionName = functionName;
   }

   public Integer getModifiedBy() {
      if (this.modifiedBy != null)
         return this.modifiedBy;
      return 0;
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

}