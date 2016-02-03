package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AdminWorkQueueType database table.
 * 
 */
@Entity
@NamedQuery(name="AdminWorkQueueType.findAll", query="SELECT a FROM AdminWorkQueueType a")
public class AdminWorkQueueType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminWQTypeId;

	private String adminWQTypeName;

	private String roleType;

	//bi-directional many-to-one association to AdminWorkQueue
	@OneToMany(mappedBy="adminWorkQueueType")
	private List<AdminWorkQueue> adminWorkQueues;

	//bi-directional many-to-one association to AdminWorkQueueCategory
	@OneToMany(mappedBy="adminWorkQueueType")
	private List<AdminWorkQueueCategory> adminWorkQueueCategories;

	//bi-directional many-to-one association to AdminWorkQueueCategoryAggregate
	@OneToMany(mappedBy="adminWorkQueueType")
	private List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to AdminWorkQueueTypeAggregate
	@OneToMany(mappedBy="adminWorkQueueType")
	private List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates;

	public AdminWorkQueueType() {
	}

	public Integer getAdminWQTypeId() {
		return this.adminWQTypeId;
	}

	public void setAdminWQTypeId(Integer adminWQTypeId) {
		this.adminWQTypeId = adminWQTypeId;
	}

	public String getAdminWQTypeName() {
		return this.adminWQTypeName;
	}

	public void setAdminWQTypeName(String adminWQTypeName) {
		this.adminWQTypeName = adminWQTypeName;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<AdminWorkQueue> getAdminWorkQueues() {
		return this.adminWorkQueues;
	}

	public void setAdminWorkQueues(List<AdminWorkQueue> adminWorkQueues) {
		this.adminWorkQueues = adminWorkQueues;
	}

	public AdminWorkQueue addAdminWorkQueue(AdminWorkQueue adminWorkQueue) {
		getAdminWorkQueues().add(adminWorkQueue);
		adminWorkQueue.setAdminWorkQueueType(this);

		return adminWorkQueue;
	}

	public AdminWorkQueue removeAdminWorkQueue(AdminWorkQueue adminWorkQueue) {
		getAdminWorkQueues().remove(adminWorkQueue);
		adminWorkQueue.setAdminWorkQueueType(null);

		return adminWorkQueue;
	}

	public List<AdminWorkQueueCategory> getAdminWorkQueueCategories() {
		return this.adminWorkQueueCategories;
	}

	public void setAdminWorkQueueCategories(List<AdminWorkQueueCategory> adminWorkQueueCategories) {
		this.adminWorkQueueCategories = adminWorkQueueCategories;
	}

	public AdminWorkQueueCategory addAdminWorkQueueCategory(AdminWorkQueueCategory adminWorkQueueCategory) {
		getAdminWorkQueueCategories().add(adminWorkQueueCategory);
		adminWorkQueueCategory.setAdminWorkQueueType(this);

		return adminWorkQueueCategory;
	}

	public AdminWorkQueueCategory removeAdminWorkQueueCategory(AdminWorkQueueCategory adminWorkQueueCategory) {
		getAdminWorkQueueCategories().remove(adminWorkQueueCategory);
		adminWorkQueueCategory.setAdminWorkQueueType(null);

		return adminWorkQueueCategory;
	}

	public List<AdminWorkQueueCategoryAggregate> getAdminWorkQueueCategoryAggregates() {
		return this.adminWorkQueueCategoryAggregates;
	}

	public void setAdminWorkQueueCategoryAggregates(List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates) {
		this.adminWorkQueueCategoryAggregates = adminWorkQueueCategoryAggregates;
	}

	public AdminWorkQueueCategoryAggregate addAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().add(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setAdminWorkQueueType(this);

		return adminWorkQueueCategoryAggregate;
	}

	public AdminWorkQueueCategoryAggregate removeAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().remove(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setAdminWorkQueueType(null);

		return adminWorkQueueCategoryAggregate;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public List<AdminWorkQueueTypeAggregate> getAdminWorkQueueTypeAggregates() {
		return this.adminWorkQueueTypeAggregates;
	}

	public void setAdminWorkQueueTypeAggregates(List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates) {
		this.adminWorkQueueTypeAggregates = adminWorkQueueTypeAggregates;
	}

	public AdminWorkQueueTypeAggregate addAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().add(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setAdminWorkQueueType(this);

		return adminWorkQueueTypeAggregate;
	}

	public AdminWorkQueueTypeAggregate removeAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().remove(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setAdminWorkQueueType(null);

		return adminWorkQueueTypeAggregate;
	}

}