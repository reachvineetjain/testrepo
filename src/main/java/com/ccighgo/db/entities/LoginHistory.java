package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the LoginHistory database table.
 * 
 */
@Entity
@Table(name = "LoginHistory")
@NamedQuery(name = "LoginHistory.findAll", query = "SELECT l FROM LoginHistory l")
public class LoginHistory implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer loginHistoryId;

   @Column(length = 20)
   private String ipAddress;

   @Column(nullable = false)
   private Timestamp loggedOn;

   // bi-directional many-to-one association to Login
   @ManyToOne
   @JoinColumn(name = "loginId", nullable = false)
   private Login login;

   public LoginHistory() {
   }

   public Integer getLoginHistoryId() {
      if (this.loginHistoryId != null)
         return this.loginHistoryId;
      return 0;
   }

   public void setLoginHistoryId(Integer loginHistoryId) {
      this.loginHistoryId = loginHistoryId;
   }

   public String getIpAddress() {
      return this.ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public Timestamp getLoggedOn() {
      return this.loggedOn;
   }

   public void setLoggedOn(Timestamp loggedOn) {
      this.loggedOn = loggedOn;
   }

   public Login getLogin() {
      return this.login;
   }

   public void setLogin(Login login) {
      this.login = login;
   }

}