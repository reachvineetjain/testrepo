package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AdminQuickStatsTypeAggregate database table.
 * 
 */
@Entity
@NamedQuery(name="AdminQuickStatsTypeAggregate.findAll", query="SELECT a FROM AdminQuickStatsTypeAggregate a")
public class AdminQuickStatsTypeAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminQSTypeAggregateId;

	private Integer adminQSTypeAggregate;

	private String adminQSTypeName;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to AdminQuickStatsType
	@ManyToOne
	@JoinColumn(name="adminQSTypeId")
	private AdminQuickStatsType adminQuickStatsType;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="adminGoId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public AdminQuickStatsTypeAggregate() {
	}

	public Integer getAdminQSTypeAggregateId() {
		return this.adminQSTypeAggregateId;
	}

	public void setAdminQSTypeAggregateId(Integer adminQSTypeAggregateId) {
		this.adminQSTypeAggregateId = adminQSTypeAggregateId;
	}

	public Integer getAdminQSTypeAggregate() {
		return this.adminQSTypeAggregate;
	}

	public void setAdminQSTypeAggregate(Integer adminQSTypeAggregate) {
		this.adminQSTypeAggregate = adminQSTypeAggregate;
	}

	public String getAdminQSTypeName() {
		return this.adminQSTypeName;
	}

	public void setAdminQSTypeName(String adminQSTypeName) {
		this.adminQSTypeName = adminQSTypeName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public AdminQuickStatsType getAdminQuickStatsType() {
		return this.adminQuickStatsType;
	}

	public void setAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
		this.adminQuickStatsType = adminQuickStatsType;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}