package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyWorkQueueCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="HostFamilyWorkQueueCategoryAggregate")
@NamedQuery(name="HostFamilyWorkQueueCategoryAggregate.findAll", query="SELECT h FROM HostFamilyWorkQueueCategoryAggregate h")
public class HostFamilyWorkQueueCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyWorkQueueCategoryAggregateId;

	private Integer hostFamilyWQCategoryAggregate;

	@Column(length=50)
	private String hostFamilyWQCategoryName;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilyWorkQueueCategory
	@ManyToOne
	@JoinColumn(name="hostFamilyWQCategoryId")
	private HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory;

	//bi-directional many-to-one association to HostFamilyWorkQueueType
	@ManyToOne
	@JoinColumn(name="hostFamilyWQTypeId")
	private HostFamilyWorkQueueType hostFamilyWorkQueueType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public HostFamilyWorkQueueCategoryAggregate() {
	}

	public Integer getHostFamilyWorkQueueCategoryAggregateId() {
		return this.hostFamilyWorkQueueCategoryAggregateId;
	}

	public void setHostFamilyWorkQueueCategoryAggregateId(Integer hostFamilyWorkQueueCategoryAggregateId) {
		this.hostFamilyWorkQueueCategoryAggregateId = hostFamilyWorkQueueCategoryAggregateId;
	}

	public Integer getHostFamilyWQCategoryAggregate() {
		return this.hostFamilyWQCategoryAggregate;
	}

	public void setHostFamilyWQCategoryAggregate(Integer hostFamilyWQCategoryAggregate) {
		this.hostFamilyWQCategoryAggregate = hostFamilyWQCategoryAggregate;
	}

	public String getHostFamilyWQCategoryName() {
		return this.hostFamilyWQCategoryName;
	}

	public void setHostFamilyWQCategoryName(String hostFamilyWQCategoryName) {
		this.hostFamilyWQCategoryName = hostFamilyWQCategoryName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

	public HostFamilyWorkQueueCategory getHostFamilyWorkQueueCategory() {
		return this.hostFamilyWorkQueueCategory;
	}

	public void setHostFamilyWorkQueueCategory(HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory) {
		this.hostFamilyWorkQueueCategory = hostFamilyWorkQueueCategory;
	}

	public HostFamilyWorkQueueType getHostFamilyWorkQueueType() {
		return this.hostFamilyWorkQueueType;
	}

	public void setHostFamilyWorkQueueType(HostFamilyWorkQueueType hostFamilyWorkQueueType) {
		this.hostFamilyWorkQueueType = hostFamilyWorkQueueType;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}