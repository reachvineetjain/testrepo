package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SysDiagrams database table.
 * 
 */
@Entity
@Table(name = "SysDiagrams")
@NamedQuery(name = "SysDiagram.findAll", query = "SELECT s FROM SysDiagram s")
public class SysDiagram implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer diagramId;

   @Lob
   private byte[] definition;

   @Column(nullable = false, length = 160)
   private String name;

   @Column(nullable = false)
   private Integer principalId;

   private Integer version;

   public SysDiagram() {
   }

   public Integer getDiagramId() {
      if (this.diagramId != null)
         return this.diagramId;
      return 0;
   }

   public void setDiagramId(Integer diagramId) {
      this.diagramId = diagramId;
   }

   public byte[] getDefinition() {
      return this.definition;
   }

   public void setDefinition(byte[] definition) {
      this.definition = definition;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getPrincipalId() {
      if (this.principalId != null)
         return this.principalId;
      return 0;
   }

   public void setPrincipalId(Integer principalId) {
      this.principalId = principalId;
   }

   public Integer getVersion() {
      if (this.version != null)
         return this.version;
      return 0;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

}