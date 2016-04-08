package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyQuickStatsCategories database table.
 * 
 */
@Entity
@Table(name="HostFamilyQuickStatsCategories")
@NamedQuery(name="HostFamilyQuickStatsCategory.findAll", query="SELECT h FROM HostFamilyQuickStatsCategory h")
public class HostFamilyQuickStatsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyQuickStatsCategoriesId;

	@Column(length=50)
	private String hostFamilyQSCategoryName;

	//bi-directional many-to-one association to HostFamilyQuickStatsType
	@ManyToOne
	@JoinColumn(name="hostFamilyQSTypeId")
	private HostFamilyQuickStatsType hostFamilyQuickStatsType;

	//bi-directional many-to-one association to HostFamilyQuickStatsCategoryAggregate
	@OneToMany(mappedBy="hostFamilyQuickStatsCategory")
	private List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates;

	public HostFamilyQuickStatsCategory() {
	}

	public Integer getHostFamilyQuickStatsCategoriesId() {
		return this.hostFamilyQuickStatsCategoriesId;
	}

	public void setHostFamilyQuickStatsCategoriesId(Integer hostFamilyQuickStatsCategoriesId) {
		this.hostFamilyQuickStatsCategoriesId = hostFamilyQuickStatsCategoriesId;
	}

	public String getHostFamilyQSCategoryName() {
		return this.hostFamilyQSCategoryName;
	}

	public void setHostFamilyQSCategoryName(String hostFamilyQSCategoryName) {
		this.hostFamilyQSCategoryName = hostFamilyQSCategoryName;
	}

	public HostFamilyQuickStatsType getHostFamilyQuickStatsType() {
		return this.hostFamilyQuickStatsType;
	}

	public void setHostFamilyQuickStatsType(HostFamilyQuickStatsType hostFamilyQuickStatsType) {
		this.hostFamilyQuickStatsType = hostFamilyQuickStatsType;
	}

	public List<HostFamilyQuickStatsCategoryAggregate> getHostFamilyQuickStatsCategoryAggregates() {
		return this.hostFamilyQuickStatsCategoryAggregates;
	}

	public void setHostFamilyQuickStatsCategoryAggregates(List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates) {
		this.hostFamilyQuickStatsCategoryAggregates = hostFamilyQuickStatsCategoryAggregates;
	}

	public HostFamilyQuickStatsCategoryAggregate addHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
		getHostFamilyQuickStatsCategoryAggregates().add(hostFamilyQuickStatsCategoryAggregate);
		hostFamilyQuickStatsCategoryAggregate.setHostFamilyQuickStatsCategory(this);

		return hostFamilyQuickStatsCategoryAggregate;
	}

	public HostFamilyQuickStatsCategoryAggregate removeHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
		getHostFamilyQuickStatsCategoryAggregates().remove(hostFamilyQuickStatsCategoryAggregate);
		hostFamilyQuickStatsCategoryAggregate.setHostFamilyQuickStatsCategory(null);

		return hostFamilyQuickStatsCategoryAggregate;
	}

}