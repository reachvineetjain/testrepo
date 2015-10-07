package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerWorkQueueType database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueueType")
@NamedQuery(name="PartnerWorkQueueType.findAll", query="SELECT p FROM PartnerWorkQueueType p")
public class PartnerWorkQueueType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerWQTypeId;

	@Column(length=50)
	private String displayWQTypeName;

	@Column(length=45)
	private String partnerWQTypeName;

	//bi-directional many-to-one association to PartnerWorkQueueCategory
	@OneToMany(mappedBy="partnerWorkQueueType")
	private List<PartnerWorkQueueCategory> partnerWorkQueueCategories;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to StateProcess
	@ManyToOne
	@JoinColumn(name="stateProcessId")
	private StateProcess stateProcess;

	//bi-directional many-to-one association to PartnerWorkQueueTypeAggregate
	@OneToMany(mappedBy="partnerWorkQueueType")
	private List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates;

	public PartnerWorkQueueType() {
	}

	public Integer getPartnerWQTypeId() {
		return this.partnerWQTypeId;
	}

	public void setPartnerWQTypeId(Integer partnerWQTypeId) {
		this.partnerWQTypeId = partnerWQTypeId;
	}

	public String getDisplayWQTypeName() {
		return this.displayWQTypeName;
	}

	public void setDisplayWQTypeName(String displayWQTypeName) {
		this.displayWQTypeName = displayWQTypeName;
	}

	public String getPartnerWQTypeName() {
		return this.partnerWQTypeName;
	}

	public void setPartnerWQTypeName(String partnerWQTypeName) {
		this.partnerWQTypeName = partnerWQTypeName;
	}

	public List<PartnerWorkQueueCategory> getPartnerWorkQueueCategories() {
		return this.partnerWorkQueueCategories;
	}

	public void setPartnerWorkQueueCategories(List<PartnerWorkQueueCategory> partnerWorkQueueCategories) {
		this.partnerWorkQueueCategories = partnerWorkQueueCategories;
	}

	public PartnerWorkQueueCategory addPartnerWorkQueueCategory(PartnerWorkQueueCategory partnerWorkQueueCategory) {
		getPartnerWorkQueueCategories().add(partnerWorkQueueCategory);
		partnerWorkQueueCategory.setPartnerWorkQueueType(this);

		return partnerWorkQueueCategory;
	}

	public PartnerWorkQueueCategory removePartnerWorkQueueCategory(PartnerWorkQueueCategory partnerWorkQueueCategory) {
		getPartnerWorkQueueCategories().remove(partnerWorkQueueCategory);
		partnerWorkQueueCategory.setPartnerWorkQueueType(null);

		return partnerWorkQueueCategory;
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

	public List<PartnerWorkQueueTypeAggregate> getPartnerWorkQueueTypeAggregates() {
		return this.partnerWorkQueueTypeAggregates;
	}

	public void setPartnerWorkQueueTypeAggregates(List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates) {
		this.partnerWorkQueueTypeAggregates = partnerWorkQueueTypeAggregates;
	}

	public PartnerWorkQueueTypeAggregate addPartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().add(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setPartnerWorkQueueType(this);

		return partnerWorkQueueTypeAggregate;
	}

	public PartnerWorkQueueTypeAggregate removePartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().remove(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setPartnerWorkQueueType(null);

		return partnerWorkQueueTypeAggregate;
	}

}