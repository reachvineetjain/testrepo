package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffWorkQueueType database table.
 * 
 */
@Entity
@Table(name="FieldStaffWorkQueueType")
@NamedQuery(name="FieldStaffWorkQueueType.findAll", query="SELECT f FROM FieldStaffWorkQueueType f")
public class FieldStaffWorkQueueType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffWQTypeId;

	@Column(length=50)
	private String displayWQTypeName;

	@Column(length=50)
	private String fieldStaffWQTypeName;

	//bi-directional many-to-one association to FieldStaffWorkQueue
	@OneToMany(mappedBy="fieldStaffWorkQueueType")
	private List<FieldStaffWorkQueue> fieldStaffWorkQueues;

	//bi-directional many-to-one association to FieldStaffWorkQueueCategory
	@OneToMany(mappedBy="fieldStaffWorkQueueType")
	private List<FieldStaffWorkQueueCategory> fieldStaffWorkQueueCategories;

	//bi-directional many-to-one association to FieldStaffWorkQueueCategoryAggregate
	@OneToMany(mappedBy="fieldStaffWorkQueueType")
	private List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to StateProcess
	@ManyToOne
	@JoinColumn(name="stateProcessId")
	private StateProcess stateProcess;

	//bi-directional many-to-one association to FieldStaffWorkQueueTypeAggregate
	@OneToMany(mappedBy="fieldStaffWorkQueueType")
	private List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates;

	public FieldStaffWorkQueueType() {
	}

	public Integer getFieldStaffWQTypeId() {
		return this.fieldStaffWQTypeId;
	}

	public void setFieldStaffWQTypeId(Integer fieldStaffWQTypeId) {
		this.fieldStaffWQTypeId = fieldStaffWQTypeId;
	}

	public String getDisplayWQTypeName() {
		return this.displayWQTypeName;
	}

	public void setDisplayWQTypeName(String displayWQTypeName) {
		this.displayWQTypeName = displayWQTypeName;
	}

	public String getFieldStaffWQTypeName() {
		return this.fieldStaffWQTypeName;
	}

	public void setFieldStaffWQTypeName(String fieldStaffWQTypeName) {
		this.fieldStaffWQTypeName = fieldStaffWQTypeName;
	}

	public List<FieldStaffWorkQueue> getFieldStaffWorkQueues() {
		return this.fieldStaffWorkQueues;
	}

	public void setFieldStaffWorkQueues(List<FieldStaffWorkQueue> fieldStaffWorkQueues) {
		this.fieldStaffWorkQueues = fieldStaffWorkQueues;
	}

	public FieldStaffWorkQueue addFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().add(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaffWorkQueueType(this);

		return fieldStaffWorkQueue;
	}

	public FieldStaffWorkQueue removeFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().remove(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaffWorkQueueType(null);

		return fieldStaffWorkQueue;
	}

	public List<FieldStaffWorkQueueCategory> getFieldStaffWorkQueueCategories() {
		return this.fieldStaffWorkQueueCategories;
	}

	public void setFieldStaffWorkQueueCategories(List<FieldStaffWorkQueueCategory> fieldStaffWorkQueueCategories) {
		this.fieldStaffWorkQueueCategories = fieldStaffWorkQueueCategories;
	}

	public FieldStaffWorkQueueCategory addFieldStaffWorkQueueCategory(FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory) {
		getFieldStaffWorkQueueCategories().add(fieldStaffWorkQueueCategory);
		fieldStaffWorkQueueCategory.setFieldStaffWorkQueueType(this);

		return fieldStaffWorkQueueCategory;
	}

	public FieldStaffWorkQueueCategory removeFieldStaffWorkQueueCategory(FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory) {
		getFieldStaffWorkQueueCategories().remove(fieldStaffWorkQueueCategory);
		fieldStaffWorkQueueCategory.setFieldStaffWorkQueueType(null);

		return fieldStaffWorkQueueCategory;
	}

	public List<FieldStaffWorkQueueCategoryAggregate> getFieldStaffWorkQueueCategoryAggregates() {
		return this.fieldStaffWorkQueueCategoryAggregates;
	}

	public void setFieldStaffWorkQueueCategoryAggregates(List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates) {
		this.fieldStaffWorkQueueCategoryAggregates = fieldStaffWorkQueueCategoryAggregates;
	}

	public FieldStaffWorkQueueCategoryAggregate addFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().add(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaffWorkQueueType(this);

		return fieldStaffWorkQueueCategoryAggregate;
	}

	public FieldStaffWorkQueueCategoryAggregate removeFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().remove(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaffWorkQueueType(null);

		return fieldStaffWorkQueueCategoryAggregate;
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

	public List<FieldStaffWorkQueueTypeAggregate> getFieldStaffWorkQueueTypeAggregates() {
		return this.fieldStaffWorkQueueTypeAggregates;
	}

	public void setFieldStaffWorkQueueTypeAggregates(List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates) {
		this.fieldStaffWorkQueueTypeAggregates = fieldStaffWorkQueueTypeAggregates;
	}

	public FieldStaffWorkQueueTypeAggregate addFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
		getFieldStaffWorkQueueTypeAggregates().add(fieldStaffWorkQueueTypeAggregate);
		fieldStaffWorkQueueTypeAggregate.setFieldStaffWorkQueueType(this);

		return fieldStaffWorkQueueTypeAggregate;
	}

	public FieldStaffWorkQueueTypeAggregate removeFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
		getFieldStaffWorkQueueTypeAggregates().remove(fieldStaffWorkQueueTypeAggregate);
		fieldStaffWorkQueueTypeAggregate.setFieldStaffWorkQueueType(null);

		return fieldStaffWorkQueueTypeAggregate;
	}

}