package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyWorkQueueType database table.
 * 
 */
@Entity
@Table(name="HostFamilyWorkQueueType")
@NamedQuery(name="HostFamilyWorkQueueType.findAll", query="SELECT h FROM HostFamilyWorkQueueType h")
public class HostFamilyWorkQueueType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyWorkQueueTypeId;

	@Column(length=50)
	private String displayWQTypeName;

	@Column(length=50)
	private String hostFamilyWQTypeName;

	//bi-directional many-to-one association to HostFamilyWorkQueue
	@OneToMany(mappedBy="hostFamilyWorkQueueType")
	private List<HostFamilyWorkQueue> hostFamilyWorkQueues;

	//bi-directional many-to-one association to HostFamilyWorkQueueCategory
	@OneToMany(mappedBy="hostFamilyWorkQueueType")
	private List<HostFamilyWorkQueueCategory> hostFamilyWorkQueueCategories;

	//bi-directional many-to-one association to HostFamilyWorkQueueCategoryAggregate
	@OneToMany(mappedBy="hostFamilyWorkQueueType")
	private List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to StateProcess
	@ManyToOne
	@JoinColumn(name="stateProcessId")
	private StateProcess stateProcess;

	//bi-directional many-to-one association to HostFamilyWorkQueueTypeAggregate
	@OneToMany(mappedBy="hostFamilyWorkQueueType")
	private List<HostFamilyWorkQueueTypeAggregate> hostFamilyWorkQueueTypeAggregates;

	public HostFamilyWorkQueueType() {
	}

	public Integer getHostFamilyWorkQueueTypeId() {
		return this.hostFamilyWorkQueueTypeId;
	}

	public void setHostFamilyWorkQueueTypeId(Integer hostFamilyWorkQueueTypeId) {
		this.hostFamilyWorkQueueTypeId = hostFamilyWorkQueueTypeId;
	}

	public String getDisplayWQTypeName() {
		return this.displayWQTypeName;
	}

	public void setDisplayWQTypeName(String displayWQTypeName) {
		this.displayWQTypeName = displayWQTypeName;
	}

	public String getHostFamilyWQTypeName() {
		return this.hostFamilyWQTypeName;
	}

	public void setHostFamilyWQTypeName(String hostFamilyWQTypeName) {
		this.hostFamilyWQTypeName = hostFamilyWQTypeName;
	}

	public List<HostFamilyWorkQueue> getHostFamilyWorkQueues() {
		return this.hostFamilyWorkQueues;
	}

	public void setHostFamilyWorkQueues(List<HostFamilyWorkQueue> hostFamilyWorkQueues) {
		this.hostFamilyWorkQueues = hostFamilyWorkQueues;
	}

	public HostFamilyWorkQueue addHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
		getHostFamilyWorkQueues().add(hostFamilyWorkQueue);
		hostFamilyWorkQueue.setHostFamilyWorkQueueType(this);

		return hostFamilyWorkQueue;
	}

	public HostFamilyWorkQueue removeHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
		getHostFamilyWorkQueues().remove(hostFamilyWorkQueue);
		hostFamilyWorkQueue.setHostFamilyWorkQueueType(null);

		return hostFamilyWorkQueue;
	}

	public List<HostFamilyWorkQueueCategory> getHostFamilyWorkQueueCategories() {
		return this.hostFamilyWorkQueueCategories;
	}

	public void setHostFamilyWorkQueueCategories(List<HostFamilyWorkQueueCategory> hostFamilyWorkQueueCategories) {
		this.hostFamilyWorkQueueCategories = hostFamilyWorkQueueCategories;
	}

	public HostFamilyWorkQueueCategory addHostFamilyWorkQueueCategory(HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory) {
		getHostFamilyWorkQueueCategories().add(hostFamilyWorkQueueCategory);
		hostFamilyWorkQueueCategory.setHostFamilyWorkQueueType(this);

		return hostFamilyWorkQueueCategory;
	}

	public HostFamilyWorkQueueCategory removeHostFamilyWorkQueueCategory(HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory) {
		getHostFamilyWorkQueueCategories().remove(hostFamilyWorkQueueCategory);
		hostFamilyWorkQueueCategory.setHostFamilyWorkQueueType(null);

		return hostFamilyWorkQueueCategory;
	}

	public List<HostFamilyWorkQueueCategoryAggregate> getHostFamilyWorkQueueCategoryAggregates() {
		return this.hostFamilyWorkQueueCategoryAggregates;
	}

	public void setHostFamilyWorkQueueCategoryAggregates(List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates) {
		this.hostFamilyWorkQueueCategoryAggregates = hostFamilyWorkQueueCategoryAggregates;
	}

	public HostFamilyWorkQueueCategoryAggregate addHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
		getHostFamilyWorkQueueCategoryAggregates().add(hostFamilyWorkQueueCategoryAggregate);
		hostFamilyWorkQueueCategoryAggregate.setHostFamilyWorkQueueType(this);

		return hostFamilyWorkQueueCategoryAggregate;
	}

	public HostFamilyWorkQueueCategoryAggregate removeHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
		getHostFamilyWorkQueueCategoryAggregates().remove(hostFamilyWorkQueueCategoryAggregate);
		hostFamilyWorkQueueCategoryAggregate.setHostFamilyWorkQueueType(null);

		return hostFamilyWorkQueueCategoryAggregate;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public StateProcess getStateProcess() {
		return this.stateProcess;
	}

	public void setStateProcess(StateProcess stateProcess) {
		this.stateProcess = stateProcess;
	}

	public List<HostFamilyWorkQueueTypeAggregate> getHostFamilyWorkQueueTypeAggregates() {
		return this.hostFamilyWorkQueueTypeAggregates;
	}

	public void setHostFamilyWorkQueueTypeAggregates(List<HostFamilyWorkQueueTypeAggregate> hostFamilyWorkQueueTypeAggregates) {
		this.hostFamilyWorkQueueTypeAggregates = hostFamilyWorkQueueTypeAggregates;
	}

	public HostFamilyWorkQueueTypeAggregate addHostFamilyWorkQueueTypeAggregate(HostFamilyWorkQueueTypeAggregate hostFamilyWorkQueueTypeAggregate) {
		getHostFamilyWorkQueueTypeAggregates().add(hostFamilyWorkQueueTypeAggregate);
		hostFamilyWorkQueueTypeAggregate.setHostFamilyWorkQueueType(this);

		return hostFamilyWorkQueueTypeAggregate;
	}

	public HostFamilyWorkQueueTypeAggregate removeHostFamilyWorkQueueTypeAggregate(HostFamilyWorkQueueTypeAggregate hostFamilyWorkQueueTypeAggregate) {
		getHostFamilyWorkQueueTypeAggregates().remove(hostFamilyWorkQueueTypeAggregate);
		hostFamilyWorkQueueTypeAggregate.setHostFamilyWorkQueueType(null);

		return hostFamilyWorkQueueTypeAggregate;
	}

}