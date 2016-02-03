package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AdminQuickStatsCategories database table.
 * 
 */
@Entity
@Table(name="AdminQuickStatsCategories")
@NamedQuery(name="AdminQuickStatsCategory.findAll", query="SELECT a FROM AdminQuickStatsCategory a")
public class AdminQuickStatsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminQSCategoryId;

	private String adminQSCategoryName;

	//bi-directional many-to-one association to AdminQuickStatsType
	@ManyToOne
	@JoinColumn(name="adminQSTypeId")
	private AdminQuickStatsType adminQuickStatsType;

	//bi-directional many-to-one association to AdminQuickStatsCategoryAggregate
	@OneToMany(mappedBy="adminQuickStatsCategory")
	private List<AdminQuickStatsCategoryAggregate> adminQuickStatsCategoryAggregates;

	public AdminQuickStatsCategory() {
	}

	public Integer getAdminQSCategoryId() {
		return this.adminQSCategoryId;
	}

	public void setAdminQSCategoryId(Integer adminQSCategoryId) {
		this.adminQSCategoryId = adminQSCategoryId;
	}

	public String getAdminQSCategoryName() {
		return this.adminQSCategoryName;
	}

	public void setAdminQSCategoryName(String adminQSCategoryName) {
		this.adminQSCategoryName = adminQSCategoryName;
	}

	public AdminQuickStatsType getAdminQuickStatsType() {
		return this.adminQuickStatsType;
	}

	public void setAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
		this.adminQuickStatsType = adminQuickStatsType;
	}

	public List<AdminQuickStatsCategoryAggregate> getAdminQuickStatsCategoryAggregates() {
		return this.adminQuickStatsCategoryAggregates;
	}

	public void setAdminQuickStatsCategoryAggregates(List<AdminQuickStatsCategoryAggregate> adminQuickStatsCategoryAggregates) {
		this.adminQuickStatsCategoryAggregates = adminQuickStatsCategoryAggregates;
	}

	public AdminQuickStatsCategoryAggregate addAdminQuickStatsCategoryAggregate(AdminQuickStatsCategoryAggregate adminQuickStatsCategoryAggregate) {
		getAdminQuickStatsCategoryAggregates().add(adminQuickStatsCategoryAggregate);
		adminQuickStatsCategoryAggregate.setAdminQuickStatsCategory(this);

		return adminQuickStatsCategoryAggregate;
	}

	public AdminQuickStatsCategoryAggregate removeAdminQuickStatsCategoryAggregate(AdminQuickStatsCategoryAggregate adminQuickStatsCategoryAggregate) {
		getAdminQuickStatsCategoryAggregates().remove(adminQuickStatsCategoryAggregate);
		adminQuickStatsCategoryAggregate.setAdminQuickStatsCategory(null);

		return adminQuickStatsCategoryAggregate;
	}

}