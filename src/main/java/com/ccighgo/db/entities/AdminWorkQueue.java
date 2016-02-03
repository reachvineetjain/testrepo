package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AdminWorkQueue database table.
 * 
 */
@Entity
@NamedQuery(name="AdminWorkQueue.findAll", query="SELECT a FROM AdminWorkQueue a")
public class AdminWorkQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminWQId;

	private String cciStaaffUserRole;

	private Integer cciStaffUserGoId;

	private Timestamp createdDate;

	private Integer departmentProgramId;

	private Integer lookupDepartmentProgramId;

	@Lob
	private String queueData;

	private Integer seasonId;

	private Integer targetGoId;

	private String targetRoleType;

	//bi-directional many-to-one association to AdminWorkQueueCategory
	@ManyToOne
	@JoinColumn(name="adminWQCategoryId")
	private AdminWorkQueueCategory adminWorkQueueCategory;

	//bi-directional many-to-one association to AdminWorkQueueType
	@ManyToOne
	@JoinColumn(name="adminWQTypeId")
	private AdminWorkQueueType adminWorkQueueType;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="stateTypeId")
	private StateType stateType;

	public AdminWorkQueue() {
	}

	public Integer getAdminWQId() {
		return this.adminWQId;
	}

	public void setAdminWQId(Integer adminWQId) {
		this.adminWQId = adminWQId;
	}

	public String getCciStaaffUserRole() {
		return this.cciStaaffUserRole;
	}

	public void setCciStaaffUserRole(String cciStaaffUserRole) {
		this.cciStaaffUserRole = cciStaaffUserRole;
	}

	public Integer getCciStaffUserGoId() {
		return this.cciStaffUserGoId;
	}

	public void setCciStaffUserGoId(Integer cciStaffUserGoId) {
		this.cciStaffUserGoId = cciStaffUserGoId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public Integer getLookupDepartmentProgramId() {
		return this.lookupDepartmentProgramId;
	}

	public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
		this.lookupDepartmentProgramId = lookupDepartmentProgramId;
	}

	public String getQueueData() {
		return this.queueData;
	}

	public void setQueueData(String queueData) {
		this.queueData = queueData;
	}

	public Integer getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getTargetGoId() {
		return this.targetGoId;
	}

	public void setTargetGoId(Integer targetGoId) {
		this.targetGoId = targetGoId;
	}

	public String getTargetRoleType() {
		return this.targetRoleType;
	}

	public void setTargetRoleType(String targetRoleType) {
		this.targetRoleType = targetRoleType;
	}

	public AdminWorkQueueCategory getAdminWorkQueueCategory() {
		return this.adminWorkQueueCategory;
	}

	public void setAdminWorkQueueCategory(AdminWorkQueueCategory adminWorkQueueCategory) {
		this.adminWorkQueueCategory = adminWorkQueueCategory;
	}

	public AdminWorkQueueType getAdminWorkQueueType() {
		return this.adminWorkQueueType;
	}

	public void setAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
		this.adminWorkQueueType = adminWorkQueueType;
	}

	public StateType getStateType() {
		return this.stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

}