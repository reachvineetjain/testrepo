package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyQuickStatsTypeAggregate database table.
 * 
 */
@Entity
@Table(name="HostFamilyQuickStatsTypeAggregate")
@NamedQuery(name="HostFamilyQuickStatsTypeAggregate.findAll", query="SELECT h FROM HostFamilyQuickStatsTypeAggregate h")
public class HostFamilyQuickStatsTypeAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyQuickStatsTypeAggregateId;

	@Column(length=50)
	private String hostFamilyQSTypeName;

	private Integer hostFamlyQSTypeAggregate;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostfamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilyQuickStatsType
	@ManyToOne
	@JoinColumn(name="hostFamilyQSTypeId")
	private HostFamilyQuickStatsType hostFamilyQuickStatsType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public HostFamilyQuickStatsTypeAggregate() {
	}

	public Integer getHostFamilyQuickStatsTypeAggregateId() {
		return this.hostFamilyQuickStatsTypeAggregateId;
	}

	public void setHostFamilyQuickStatsTypeAggregateId(Integer hostFamilyQuickStatsTypeAggregateId) {
		this.hostFamilyQuickStatsTypeAggregateId = hostFamilyQuickStatsTypeAggregateId;
	}

	public String getHostFamilyQSTypeName() {
		return this.hostFamilyQSTypeName;
	}

	public void setHostFamilyQSTypeName(String hostFamilyQSTypeName) {
		this.hostFamilyQSTypeName = hostFamilyQSTypeName;
	}

	public Integer getHostFamlyQSTypeAggregate() {
		return this.hostFamlyQSTypeAggregate;
	}

	public void setHostFamlyQSTypeAggregate(Integer hostFamlyQSTypeAggregate) {
		this.hostFamlyQSTypeAggregate = hostFamlyQSTypeAggregate;
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

	public HostFamilyQuickStatsType getHostFamilyQuickStatsType() {
		return this.hostFamilyQuickStatsType;
	}

	public void setHostFamilyQuickStatsType(HostFamilyQuickStatsType hostFamilyQuickStatsType) {
		this.hostFamilyQuickStatsType = hostFamilyQuickStatsType;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}