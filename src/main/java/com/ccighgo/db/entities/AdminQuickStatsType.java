package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AdminQuickStatsType database table.
 * 
 */
@Entity
@NamedQuery(name="AdminQuickStatsType.findAll", query="SELECT a FROM AdminQuickStatsType a")
public class AdminQuickStatsType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminQSTypeId;

	private String adminQSTypeName;

	//bi-directional many-to-one association to AdminQuickStatsCategory
	@OneToMany(mappedBy="adminQuickStatsType")
	private List<AdminQuickStatsCategory> adminQuickStatsCategories;

	//bi-directional many-to-one association to AdminQuickStatsCategoryAggregate
	@OneToMany(mappedBy="adminQuickStatsType")
	private List<AdminQuickStatsCategoryAggregate> adminQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to AdminQuickStatsTypeAggregate
	@OneToMany(mappedBy="adminQuickStatsType")
	private List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates;

	public AdminQuickStatsType() {
	}

	public Integer getAdminQSTypeId() {
		return this.adminQSTypeId;
	}

	public void setAdminQSTypeId(Integer adminQSTypeId) {
		this.adminQSTypeId = adminQSTypeId;
	}

	public String getAdminQSTypeName() {
		return this.adminQSTypeName;
	}

	public void setAdminQSTypeName(String adminQSTypeName) {
		this.adminQSTypeName = adminQSTypeName;
	}

	public List<AdminQuickStatsCategory> getAdminQuickStatsCategories() {
		return this.adminQuickStatsCategories;
	}

	public void setAdminQuickStatsCategories(List<AdminQuickStatsCategory> adminQuickStatsCategories) {
		this.adminQuickStatsCategories = adminQuickStatsCategories;
	}

	public AdminQuickStatsCategory addAdminQuickStatsCategory(AdminQuickStatsCategory adminQuickStatsCategory) {
		getAdminQuickStatsCategories().add(adminQuickStatsCategory);
		adminQuickStatsCategory.setAdminQuickStatsType(this);

		return adminQuickStatsCategory;
	}

	public AdminQuickStatsCategory removeAdminQuickStatsCategory(AdminQuickStatsCategory adminQuickStatsCategory) {
		getAdminQuickStatsCategories().remove(adminQuickStatsCategory);
		adminQuickStatsCategory.setAdminQuickStatsType(null);

		return adminQuickStatsCategory;
	}

	public List<AdminQuickStatsCategoryAggregate> getAdminQuickStatsCategoryAggregates() {
		return this.adminQuickStatsCategoryAggregates;
	}

	public void setAdminQuickStatsCategoryAggregates(List<AdminQuickStatsCategoryAggregate> adminQuickStatsCategoryAggregates) {
		this.adminQuickStatsCategoryAggregates = adminQuickStatsCategoryAggregates;
	}

	public AdminQuickStatsCategoryAggregate addAdminQuickStatsCategoryAggregate(AdminQuickStatsCategoryAggregate adminQuickStatsCategoryAggregate) {
		getAdminQuickStatsCategoryAggregates().add(adminQuickStatsCategoryAggregate);
		adminQuickStatsCategoryAggregate.setAdminQuickStatsType(this);

		return adminQuickStatsCategoryAggregate;
	}

	public AdminQuickStatsCategoryAggregate removeAdminQuickStatsCategoryAggregate(AdminQuickStatsCategoryAggregate adminQuickStatsCategoryAggregate) {
		getAdminQuickStatsCategoryAggregates().remove(adminQuickStatsCategoryAggregate);
		adminQuickStatsCategoryAggregate.setAdminQuickStatsType(null);

		return adminQuickStatsCategoryAggregate;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public List<AdminQuickStatsTypeAggregate> getAdminQuickStatsTypeAggregates() {
		return this.adminQuickStatsTypeAggregates;
	}

	public void setAdminQuickStatsTypeAggregates(List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates) {
		this.adminQuickStatsTypeAggregates = adminQuickStatsTypeAggregates;
	}

	public AdminQuickStatsTypeAggregate addAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().add(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setAdminQuickStatsType(this);

		return adminQuickStatsTypeAggregate;
	}

	public AdminQuickStatsTypeAggregate removeAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().remove(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setAdminQuickStatsType(null);

		return adminQuickStatsTypeAggregate;
	}

}