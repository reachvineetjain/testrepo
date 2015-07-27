package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the UserType database table.
 * 
 */
@Entity
@Table(name = "UserType")
@NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
public class UserType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer userTypeId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(nullable = false, length = 20)
   private String userTypeCode;

   @Column(nullable = false, length = 50)
   private String userTypeName;

   // bi-directional many-to-one association to Login
   @OneToMany(mappedBy = "userType")
   private List<Login> logins;

   public UserType() {
   }

   public Integer getUserTypeId() {
      if (this.userTypeId != null)
         return this.userTypeId;
      return 0;
   }

   public void setUserTypeId(Integer userTypeId) {
      this.userTypeId = userTypeId;
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

   public List<Login> getLogins() {
      return this.logins;
   }

   public void setLogins(List<Login> logins) {
      this.logins = logins;
   }

   public Login addLogin(Login login) {
      getLogins().add(login);
      login.setUserType(this);

      return login;
   }

   public Login removeLogin(Login login) {
      getLogins().remove(login);
      login.setUserType(null);

      return login;
   }

}