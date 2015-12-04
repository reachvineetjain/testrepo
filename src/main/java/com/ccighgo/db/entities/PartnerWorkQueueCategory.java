package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerWorkQueueCategories database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueueCategories")
@NamedQuery(name="PartnerWorkQueueCategory.findAll", query="SELECT p FROM PartnerWorkQueueCategory p")
public class PartnerWorkQueueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int partnerWQCategoryId;

	@Column(length=50)
	private String partnerWQCategoryName;

	//bi-directional many-to-one association to PartnerWorkQueue
	@OneToMany(mappedBy="partnerWorkQueueCategory")
	private List<PartnerWorkQueue> partnerWorkQueues;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@ManyToOne
	@JoinColumn(name="partnerWQTypeId", nullable=false)
	private PartnerWorkQueueType partnerWorkQueueType;

	//bi-directional many-to-one association to PartnerWorkQueueCategoryAggregate
	@OneToMany(mappedBy="partnerWorkQueueCategory")
	private List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates;

	public PartnerWorkQueueCategory() {
	}

	public int getPartnerWQCategoryId() {
		return this.partnerWQCategoryId;
	}

	public void setPartnerWQCategoryId(int partnerWQCategoryId) {
		this.partnerWQCategoryId = partnerWQCategoryId;
	}

	public String getPartnerWQCategoryName() {
		return this.partnerWQCategoryName;
	}

	public void setPartnerWQCategoryName(String partnerWQCategoryName) {
		this.partnerWQCategoryName = partnerWQCategoryName;
	}

	public List<PartnerWorkQueue> getPartnerWorkQueues() {
		return this.partnerWorkQueues;
	}

	public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
		this.partnerWorkQueues = partnerWorkQueues;
	}

	public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().add(partnerWorkQueue);
		partnerWorkQueue.setPartnerWorkQueueCategory(this);

		return partnerWorkQueue;
	}

	public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().remove(partnerWorkQueue);
		partnerWorkQueue.setPartnerWorkQueueCategory(null);

		return partnerWorkQueue;
	}

	public PartnerWorkQueueType getPartnerWorkQueueType() {
		return this.partnerWorkQueueType;
	}

	public void setPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		this.partnerWorkQueueType = partnerWorkQueueType;
	}

	public List<PartnerWorkQueueCategoryAggregate> getPartnerWorkQueueCategoryAggregates() {
		return this.partnerWorkQueueCategoryAggregates;
	}

	public void setPartnerWorkQueueCategoryAggregates(List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates) {
		this.partnerWorkQueueCategoryAggregates = partnerWorkQueueCategoryAggregates;
	}

	public PartnerWorkQueueCategoryAggregate addPartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().add(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setPartnerWorkQueueCategory(this);

		return partnerWorkQueueCategoryAggregate;
	}

	public PartnerWorkQueueCategoryAggregate removePartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().remove(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setPartnerWorkQueueCategory(null);

		return partnerWorkQueueCategoryAggregate;
	}

}