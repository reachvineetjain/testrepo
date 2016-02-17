package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffWorkQueueCategories database table.
 * 
 */
@Entity
@Table(name="FieldStaffWorkQueueCategories")
@NamedQuery(name="FieldStaffWorkQueueCategory.findAll", query="SELECT f FROM FieldStaffWorkQueueCategory f")
public class FieldStaffWorkQueueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffWQCategoryId;

	@Column(length=50)
	private String fieldStaffWQCategoryName;

	//bi-directional many-to-one association to FieldStaffWorkQueue
	@OneToMany(mappedBy="fieldStaffWorkQueueCategory")
	private List<FieldStaffWorkQueue> fieldStaffWorkQueues;

	//bi-directional many-to-one association to FieldStaffWorkQueueType
	@ManyToOne
	@JoinColumn(name="fieldStaffWQTypeId", nullable=false)
	private FieldStaffWorkQueueType fieldStaffWorkQueueType;

	//bi-directional many-to-one association to FieldStaffWorkQueueCategoryAggregate
	@OneToMany(mappedBy="fieldStaffWorkQueueCategory")
	private List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates;

	public FieldStaffWorkQueueCategory() {
	}

	public Integer getFieldStaffWQCategoryId() {
		return this.fieldStaffWQCategoryId;
	}

	public void setFieldStaffWQCategoryId(Integer fieldStaffWQCategoryId) {
		this.fieldStaffWQCategoryId = fieldStaffWQCategoryId;
	}

	public String getFieldStaffWQCategoryName() {
		return this.fieldStaffWQCategoryName;
	}

	public void setFieldStaffWQCategoryName(String fieldStaffWQCategoryName) {
		this.fieldStaffWQCategoryName = fieldStaffWQCategoryName;
	}

	public List<FieldStaffWorkQueue> getFieldStaffWorkQueues() {
		return this.fieldStaffWorkQueues;
	}

	public void setFieldStaffWorkQueues(List<FieldStaffWorkQueue> fieldStaffWorkQueues) {
		this.fieldStaffWorkQueues = fieldStaffWorkQueues;
	}

	public FieldStaffWorkQueue addFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().add(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaffWorkQueueCategory(this);

		return fieldStaffWorkQueue;
	}

	public FieldStaffWorkQueue removeFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
		getFieldStaffWorkQueues().remove(fieldStaffWorkQueue);
		fieldStaffWorkQueue.setFieldStaffWorkQueueCategory(null);

		return fieldStaffWorkQueue;
	}

	public FieldStaffWorkQueueType getFieldStaffWorkQueueType() {
		return this.fieldStaffWorkQueueType;
	}

	public void setFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
		this.fieldStaffWorkQueueType = fieldStaffWorkQueueType;
	}

	public List<FieldStaffWorkQueueCategoryAggregate> getFieldStaffWorkQueueCategoryAggregates() {
		return this.fieldStaffWorkQueueCategoryAggregates;
	}

	public void setFieldStaffWorkQueueCategoryAggregates(List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates) {
		this.fieldStaffWorkQueueCategoryAggregates = fieldStaffWorkQueueCategoryAggregates;
	}

	public FieldStaffWorkQueueCategoryAggregate addFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().add(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaffWorkQueueCategory(this);

		return fieldStaffWorkQueueCategoryAggregate;
	}

	public FieldStaffWorkQueueCategoryAggregate removeFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
		getFieldStaffWorkQueueCategoryAggregates().remove(fieldStaffWorkQueueCategoryAggregate);
		fieldStaffWorkQueueCategoryAggregate.setFieldStaffWorkQueueCategory(null);

		return fieldStaffWorkQueueCategoryAggregate;
	}

}