package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AdminWorkQueueCategories database table.
 * 
 */
@Entity
@Table(name="AdminWorkQueueCategories")
@NamedQuery(name="AdminWorkQueueCategory.findAll", query="SELECT a FROM AdminWorkQueueCategory a")
public class AdminWorkQueueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminWorkQueueCategoryId;

	private String adminWorkQueueCategoryName;

	private String roleType;

	//bi-directional many-to-one association to AdminWorkQueue
	@OneToMany(mappedBy="adminWorkQueueCategory")
	private List<AdminWorkQueue> adminWorkQueues;

	//bi-directional many-to-one association to AdminWorkQueueType
	@ManyToOne
	@JoinColumn(name="adminWQTypeId")
	private AdminWorkQueueType adminWorkQueueType;

	//bi-directional many-to-one association to AdminWorkQueueCategoryAggregate
	@OneToMany(mappedBy="adminWorkQueueCategory")
	private List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates;

	public AdminWorkQueueCategory() {
	}

	public Integer getAdminWorkQueueCategoryId() {
		return this.adminWorkQueueCategoryId;
	}

	public void setAdminWorkQueueCategoryId(Integer adminWorkQueueCategoryId) {
		this.adminWorkQueueCategoryId = adminWorkQueueCategoryId;
	}

	public String getAdminWorkQueueCategoryName() {
		return this.adminWorkQueueCategoryName;
	}

	public void setAdminWorkQueueCategoryName(String adminWorkQueueCategoryName) {
		this.adminWorkQueueCategoryName = adminWorkQueueCategoryName;
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
		adminWorkQueue.setAdminWorkQueueCategory(this);

		return adminWorkQueue;
	}

	public AdminWorkQueue removeAdminWorkQueue(AdminWorkQueue adminWorkQueue) {
		getAdminWorkQueues().remove(adminWorkQueue);
		adminWorkQueue.setAdminWorkQueueCategory(null);

		return adminWorkQueue;
	}

	public AdminWorkQueueType getAdminWorkQueueType() {
		return this.adminWorkQueueType;
	}

	public void setAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
		this.adminWorkQueueType = adminWorkQueueType;
	}

	public List<AdminWorkQueueCategoryAggregate> getAdminWorkQueueCategoryAggregates() {
		return this.adminWorkQueueCategoryAggregates;
	}

	public void setAdminWorkQueueCategoryAggregates(List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates) {
		this.adminWorkQueueCategoryAggregates = adminWorkQueueCategoryAggregates;
	}

	public AdminWorkQueueCategoryAggregate addAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().add(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setAdminWorkQueueCategory(this);

		return adminWorkQueueCategoryAggregate;
	}

	public AdminWorkQueueCategoryAggregate removeAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().remove(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setAdminWorkQueueCategory(null);

		return adminWorkQueueCategoryAggregate;
	}

}