package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerQuickStatsCategories database table.
 * 
 */
@Entity
@Table(name="PartnerQuickStatsCategories")
@NamedQuery(name="PartnerQuickStatsCategory.findAll", query="SELECT p FROM PartnerQuickStatsCategory p")
public class PartnerQuickStatsCategory implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerQSCategoryId;

   @Column(length=50)
   private String partnerQSCategoryName;

   //bi-directional many-to-one association to PartnerQuickStatsType
   @ManyToOne
   @JoinColumn(name="partnerQSTypeId")
   private PartnerQuickStatsType partnerQuickStatsType;

   //bi-directional many-to-one association to PartnerQuickStatsCategoryAggregate
   @OneToMany(mappedBy="partnerQuickStatsCategory")
   private List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates;

   public PartnerQuickStatsCategory() {
   }

   public Integer getPartnerQSCategoryId() {
      return this.partnerQSCategoryId;
   }

   public void setPartnerQSCategoryId(Integer partnerQSCategoryId) {
      this.partnerQSCategoryId = partnerQSCategoryId;
   }

   public String getPartnerQSCategoryName() {
      return this.partnerQSCategoryName;
   }

   public void setPartnerQSCategoryName(String partnerQSCategoryName) {
      this.partnerQSCategoryName = partnerQSCategoryName;
   }

   public PartnerQuickStatsType getPartnerQuickStatsType() {
      return this.partnerQuickStatsType;
   }

   public void setPartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
      this.partnerQuickStatsType = partnerQuickStatsType;
   }

   public List<PartnerQuickStatsCategoryAggregate> getPartnerQuickStatsCategoryAggregates() {
      return this.partnerQuickStatsCategoryAggregates;
   }

   public void setPartnerQuickStatsCategoryAggregates(List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates) {
      this.partnerQuickStatsCategoryAggregates = partnerQuickStatsCategoryAggregates;
   }

   public PartnerQuickStatsCategoryAggregate addPartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
      getPartnerQuickStatsCategoryAggregates().add(partnerQuickStatsCategoryAggregate);
      partnerQuickStatsCategoryAggregate.setPartnerQuickStatsCategory(this);

      return partnerQuickStatsCategoryAggregate;
   }

   public PartnerQuickStatsCategoryAggregate removePartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
      getPartnerQuickStatsCategoryAggregates().remove(partnerQuickStatsCategoryAggregate);
      partnerQuickStatsCategoryAggregate.setPartnerQuickStatsCategory(null);

      return partnerQuickStatsCategoryAggregate;
   }

}