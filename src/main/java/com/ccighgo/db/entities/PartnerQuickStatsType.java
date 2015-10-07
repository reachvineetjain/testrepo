package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerQuickStatsType database table.
 * 
 */
@Entity
@Table(name="PartnerQuickStatsType")
@NamedQuery(name="PartnerQuickStatsType.findAll", query="SELECT p FROM PartnerQuickStatsType p")
public class PartnerQuickStatsType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerQSTypeId;

	@Column(length=50)
	private String partnerQSTypeName;

	//bi-directional many-to-one association to PartnerQuickStatsCategory
	@OneToMany(mappedBy="partnerQuickStatsType")
	private List<PartnerQuickStatsCategory> partnerQuickStatsCategories;

	//bi-directional many-to-one association to PartnerQuickStatsCategoryAggregate
	@OneToMany(mappedBy="partnerQuickStatsType")
	private List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to PartnerQuickStatsTypeAggregate
	@OneToMany(mappedBy="partnerQuickStatsType")
	private List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates;

	public PartnerQuickStatsType() {
	}

	public Integer getPartnerQSTypeId() {
		return this.partnerQSTypeId;
	}

	public void setPartnerQSTypeId(Integer partnerQSTypeId) {
		this.partnerQSTypeId = partnerQSTypeId;
	}

	public String getPartnerQSTypeName() {
		return this.partnerQSTypeName;
	}

	public void setPartnerQSTypeName(String partnerQSTypeName) {
		this.partnerQSTypeName = partnerQSTypeName;
	}

	public List<PartnerQuickStatsCategory> getPartnerQuickStatsCategories() {
		return this.partnerQuickStatsCategories;
	}

	public void setPartnerQuickStatsCategories(List<PartnerQuickStatsCategory> partnerQuickStatsCategories) {
		this.partnerQuickStatsCategories = partnerQuickStatsCategories;
	}

	public PartnerQuickStatsCategory addPartnerQuickStatsCategory(PartnerQuickStatsCategory partnerQuickStatsCategory) {
		getPartnerQuickStatsCategories().add(partnerQuickStatsCategory);
		partnerQuickStatsCategory.setPartnerQuickStatsType(this);

		return partnerQuickStatsCategory;
	}

	public PartnerQuickStatsCategory removePartnerQuickStatsCategory(PartnerQuickStatsCategory partnerQuickStatsCategory) {
		getPartnerQuickStatsCategories().remove(partnerQuickStatsCategory);
		partnerQuickStatsCategory.setPartnerQuickStatsType(null);

		return partnerQuickStatsCategory;
	}

	public List<PartnerQuickStatsCategoryAggregate> getPartnerQuickStatsCategoryAggregates() {
		return this.partnerQuickStatsCategoryAggregates;
	}

	public void setPartnerQuickStatsCategoryAggregates(List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates) {
		this.partnerQuickStatsCategoryAggregates = partnerQuickStatsCategoryAggregates;
	}

	public PartnerQuickStatsCategoryAggregate addPartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().add(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setPartnerQuickStatsType(this);

		return partnerQuickStatsCategoryAggregate;
	}

	public PartnerQuickStatsCategoryAggregate removePartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().remove(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setPartnerQuickStatsType(null);

		return partnerQuickStatsCategoryAggregate;
	}

	public List<PartnerQuickStatsTypeAggregate> getPartnerQuickStatsTypeAggregates() {
		return this.partnerQuickStatsTypeAggregates;
	}

	public void setPartnerQuickStatsTypeAggregates(List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates) {
		this.partnerQuickStatsTypeAggregates = partnerQuickStatsTypeAggregates;
	}

	public PartnerQuickStatsTypeAggregate addPartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().add(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setPartnerQuickStatsType(this);

		return partnerQuickStatsTypeAggregate;
	}

	public PartnerQuickStatsTypeAggregate removePartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().remove(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setPartnerQuickStatsType(null);

		return partnerQuickStatsTypeAggregate;
	}

}