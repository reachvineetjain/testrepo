package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyWorkQueueCategories database table.
 * 
 */
@Entity
@Table(name="HostFamilyWorkQueueCategories")
@NamedQuery(name="HostFamilyWorkQueueCategory.findAll", query="SELECT h FROM HostFamilyWorkQueueCategory h")
public class HostFamilyWorkQueueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyWorkQueueCategoriesId;

	@Column(length=50)
	private String hostFamilyWQCategoryName;

	//bi-directional many-to-one association to HostFamilyWorkQueue
	@OneToMany(mappedBy="hostFamilyWorkQueueCategory")
	private List<HostFamilyWorkQueue> hostFamilyWorkQueues;

	//bi-directional many-to-one association to HostFamilyWorkQueueType
	@ManyToOne
	@JoinColumn(name="hostFamilyWQTypeId")
	private HostFamilyWorkQueueType hostFamilyWorkQueueType;

	//bi-directional many-to-one association to HostFamilyWorkQueueCategoryAggregate
	@OneToMany(mappedBy="hostFamilyWorkQueueCategory")
	private List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates;

	public HostFamilyWorkQueueCategory() {
	}

	public Integer getHostFamilyWorkQueueCategoriesId() {
		return this.hostFamilyWorkQueueCategoriesId;
	}

	public void setHostFamilyWorkQueueCategoriesId(Integer hostFamilyWorkQueueCategoriesId) {
		this.hostFamilyWorkQueueCategoriesId = hostFamilyWorkQueueCategoriesId;
	}

	public String getHostFamilyWQCategoryName() {
		return this.hostFamilyWQCategoryName;
	}

	public void setHostFamilyWQCategoryName(String hostFamilyWQCategoryName) {
		this.hostFamilyWQCategoryName = hostFamilyWQCategoryName;
	}

	public List<HostFamilyWorkQueue> getHostFamilyWorkQueues() {
		return this.hostFamilyWorkQueues;
	}

	public void setHostFamilyWorkQueues(List<HostFamilyWorkQueue> hostFamilyWorkQueues) {
		this.hostFamilyWorkQueues = hostFamilyWorkQueues;
	}

	public HostFamilyWorkQueue addHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
		getHostFamilyWorkQueues().add(hostFamilyWorkQueue);
		hostFamilyWorkQueue.setHostFamilyWorkQueueCategory(this);

		return hostFamilyWorkQueue;
	}

	public HostFamilyWorkQueue removeHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
		getHostFamilyWorkQueues().remove(hostFamilyWorkQueue);
		hostFamilyWorkQueue.setHostFamilyWorkQueueCategory(null);

		return hostFamilyWorkQueue;
	}

	public HostFamilyWorkQueueType getHostFamilyWorkQueueType() {
		return this.hostFamilyWorkQueueType;
	}

	public void setHostFamilyWorkQueueType(HostFamilyWorkQueueType hostFamilyWorkQueueType) {
		this.hostFamilyWorkQueueType = hostFamilyWorkQueueType;
	}

	public List<HostFamilyWorkQueueCategoryAggregate> getHostFamilyWorkQueueCategoryAggregates() {
		return this.hostFamilyWorkQueueCategoryAggregates;
	}

	public void setHostFamilyWorkQueueCategoryAggregates(List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates) {
		this.hostFamilyWorkQueueCategoryAggregates = hostFamilyWorkQueueCategoryAggregates;
	}

	public HostFamilyWorkQueueCategoryAggregate addHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
		getHostFamilyWorkQueueCategoryAggregates().add(hostFamilyWorkQueueCategoryAggregate);
		hostFamilyWorkQueueCategoryAggregate.setHostFamilyWorkQueueCategory(this);

		return hostFamilyWorkQueueCategoryAggregate;
	}

	public HostFamilyWorkQueueCategoryAggregate removeHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
		getHostFamilyWorkQueueCategoryAggregates().remove(hostFamilyWorkQueueCategoryAggregate);
		hostFamilyWorkQueueCategoryAggregate.setHostFamilyWorkQueueCategory(null);

		return hostFamilyWorkQueueCategoryAggregate;
	}

}