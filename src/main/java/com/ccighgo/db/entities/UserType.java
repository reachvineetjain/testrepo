package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the UserType database table.
 * 
 */
@Entity
@Table(name="UserType")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer userTypeId;

   @Column(nullable=false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(nullable=false, length=20)
   private String userTypeCode;

   @Column(nullable=false, length=50)
   private String userTypeName;

   //bi-directional many-to-one association to LoginUserType
   @OneToMany(mappedBy="userType")
   private List<LoginUserType> loginUserTypes;

   public UserType() {
   }

   public Integer getUserTypeId() {
      return this.userTypeId;
   }

   public void setUserTypeId(Integer userTypeId) {
      this.userTypeId = userTypeId;
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

   public String getUserTypeCode() {
      return this.userTypeCode;
   }

   public void setUserTypeCode(String userTypeCode) {
      this.userTypeCode = userTypeCode;
   }

   public String getUserTypeName() {
      return this.userTypeName;
   }

   public void setUserTypeName(String userTypeName) {
      this.userTypeName = userTypeName;
   }

   public List<LoginUserType> getLoginUserTypes() {
      return this.loginUserTypes;
   }

   public void setLoginUserTypes(List<LoginUserType> loginUserTypes) {
      this.loginUserTypes = loginUserTypes;
   }

   public LoginUserType addLoginUserType(LoginUserType loginUserType) {
      getLoginUserTypes().add(loginUserType);
      loginUserType.setUserType(this);

      return loginUserType;
   }

   public LoginUserType removeLoginUserType(LoginUserType loginUserType) {
      getLoginUserTypes().remove(loginUserType);
      loginUserType.setUserType(null);

      return loginUserType;
   }

}