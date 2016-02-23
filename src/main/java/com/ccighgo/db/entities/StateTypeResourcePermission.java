package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StateTypeResourcePermission database table.
 * 
 */
@Entity
@Table(name="StateTypeResourcePermission")
@NamedQuery(name="StateTypeResourcePermission.findAll", query="SELECT s FROM StateTypeResourcePermission s")
public class StateTypeResourcePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer stateTypeRPId;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to ResourcePermission
	@ManyToOne
	@JoinColumn(name="resourcePermissionId")
	private ResourcePermission resourcePermission;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="stateTypeId")
	private StateType stateType;

	public StateTypeResourcePermission() {
	}

	public Integer getStateTypeRPId() {
		return this.stateTypeRPId;
	}

	public void setStateTypeRPId(Integer stateTypeRPId) {
		this.stateTypeRPId = stateTypeRPId;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public ResourcePermission getResourcePermission() {
		return this.resourcePermission;
	}

	public void setResourcePermission(ResourcePermission resourcePermission) {
		this.resourcePermission = resourcePermission;
	}

	public StateType getStateType() {
		return this.stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

}