package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyQuickStatsType database table.
 * 
 */
@Entity
@Table(name="HostFamilyQuickStatsType")
@NamedQuery(name="HostFamilyQuickStatsType.findAll", query="SELECT h FROM HostFamilyQuickStatsType h")
public class HostFamilyQuickStatsType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyQuickStatsTypeId;

	@Column(length=50)
	private String hostFamilyQSTypeName;

	//bi-directional many-to-one association to HostFamilyQuickStatsCategory
	@OneToMany(mappedBy="hostFamilyQuickStatsType")
	private List<HostFamilyQuickStatsCategory> hostFamilyQuickStatsCategories;

	//bi-directional many-to-one association to HostFamilyQuickStatsCategoryAggregate
	@OneToMany(mappedBy="hostFamilyQuickStatsType")
	private List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to HostFamilyQuickStatsTypeAggregate
	@OneToMany(mappedBy="hostFamilyQuickStatsType")
	private List<HostFamilyQuickStatsTypeAggregate> hostFamilyQuickStatsTypeAggregates;

	public HostFamilyQuickStatsType() {
	}

	public Integer getHostFamilyQuickStatsTypeId() {
		return this.hostFamilyQuickStatsTypeId;
	}

	public void setHostFamilyQuickStatsTypeId(Integer hostFamilyQuickStatsTypeId) {
		this.hostFamilyQuickStatsTypeId = hostFamilyQuickStatsTypeId;
	}

	public String getHostFamilyQSTypeName() {
		return this.hostFamilyQSTypeName;
	}

	public void setHostFamilyQSTypeName(String hostFamilyQSTypeName) {
		this.hostFamilyQSTypeName = hostFamilyQSTypeName;
	}

	public List<HostFamilyQuickStatsCategory> getHostFamilyQuickStatsCategories() {
		return this.hostFamilyQuickStatsCategories;
	}

	public void setHostFamilyQuickStatsCategories(List<HostFamilyQuickStatsCategory> hostFamilyQuickStatsCategories) {
		this.hostFamilyQuickStatsCategories = hostFamilyQuickStatsCategories;
	}

	public HostFamilyQuickStatsCategory addHostFamilyQuickStatsCategory(HostFamilyQuickStatsCategory hostFamilyQuickStatsCategory) {
		getHostFamilyQuickStatsCategories().add(hostFamilyQuickStatsCategory);
		hostFamilyQuickStatsCategory.setHostFamilyQuickStatsType(this);

		return hostFamilyQuickStatsCategory;
	}

	public HostFamilyQuickStatsCategory removeHostFamilyQuickStatsCategory(HostFamilyQuickStatsCategory hostFamilyQuickStatsCategory) {
		getHostFamilyQuickStatsCategories().remove(hostFamilyQuickStatsCategory);
		hostFamilyQuickStatsCategory.setHostFamilyQuickStatsType(null);

		return hostFamilyQuickStatsCategory;
	}

	public List<HostFamilyQuickStatsCategoryAggregate> getHostFamilyQuickStatsCategoryAggregates() {
		return this.hostFamilyQuickStatsCategoryAggregates;
	}

	public void setHostFamilyQuickStatsCategoryAggregates(List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates) {
		this.hostFamilyQuickStatsCategoryAggregates = hostFamilyQuickStatsCategoryAggregates;
	}

	public HostFamilyQuickStatsCategoryAggregate addHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
		getHostFamilyQuickStatsCategoryAggregates().add(hostFamilyQuickStatsCategoryAggregate);
		hostFamilyQuickStatsCategoryAggregate.setHostFamilyQuickStatsType(this);

		return hostFamilyQuickStatsCategoryAggregate;
	}

	public HostFamilyQuickStatsCategoryAggregate removeHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
		getHostFamilyQuickStatsCategoryAggregates().remove(hostFamilyQuickStatsCategoryAggregate);
		hostFamilyQuickStatsCategoryAggregate.setHostFamilyQuickStatsType(null);

		return hostFamilyQuickStatsCategoryAggregate;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public List<HostFamilyQuickStatsTypeAggregate> getHostFamilyQuickStatsTypeAggregates() {
		return this.hostFamilyQuickStatsTypeAggregates;
	}

	public void setHostFamilyQuickStatsTypeAggregates(List<HostFamilyQuickStatsTypeAggregate> hostFamilyQuickStatsTypeAggregates) {
		this.hostFamilyQuickStatsTypeAggregates = hostFamilyQuickStatsTypeAggregates;
	}

	public HostFamilyQuickStatsTypeAggregate addHostFamilyQuickStatsTypeAggregate(HostFamilyQuickStatsTypeAggregate hostFamilyQuickStatsTypeAggregate) {
		getHostFamilyQuickStatsTypeAggregates().add(hostFamilyQuickStatsTypeAggregate);
		hostFamilyQuickStatsTypeAggregate.setHostFamilyQuickStatsType(this);

		return hostFamilyQuickStatsTypeAggregate;
	}

	public HostFamilyQuickStatsTypeAggregate removeHostFamilyQuickStatsTypeAggregate(HostFamilyQuickStatsTypeAggregate hostFamilyQuickStatsTypeAggregate) {
		getHostFamilyQuickStatsTypeAggregates().remove(hostFamilyQuickStatsTypeAggregate);
		hostFamilyQuickStatsTypeAggregate.setHostFamilyQuickStatsType(null);

		return hostFamilyQuickStatsTypeAggregate;
	}

}