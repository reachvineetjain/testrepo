package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyStatus database table.
 * 
 */
@Entity
@Table(name="HostFamilyStatus")
@NamedQuery(name="HostFamilyStatus.findAll", query="SELECT h FROM HostFamilyStatus h")
public class HostFamilyStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyStatusId;

	@Column(length=30)
	private String hostFamilyStatus;

	//bi-directional many-to-one association to FieldStaffLeadershipSeasonDetail
	@OneToMany(mappedBy="hostFamilyStatus")
	private List<FieldStaffLeadershipSeasonDetail> fieldStaffLeadershipSeasonDetails;

	public HostFamilyStatus() {
	}

	public Integer getHostFamilyStatusId() {
		return this.hostFamilyStatusId;
	}

	public void setHostFamilyStatusId(Integer hostFamilyStatusId) {
		this.hostFamilyStatusId = hostFamilyStatusId;
	}

	public String getHostFamilyStatus() {
		return this.hostFamilyStatus;
	}

	public void setHostFamilyStatus(String hostFamilyStatus) {
		this.hostFamilyStatus = hostFamilyStatus;
	}

	public List<FieldStaffLeadershipSeasonDetail> getFieldStaffLeadershipSeasonDetails() {
		return this.fieldStaffLeadershipSeasonDetails;
	}

	public void setFieldStaffLeadershipSeasonDetails(List<FieldStaffLeadershipSeasonDetail> fieldStaffLeadershipSeasonDetails) {
		this.fieldStaffLeadershipSeasonDetails = fieldStaffLeadershipSeasonDetails;
	}

	public FieldStaffLeadershipSeasonDetail addFieldStaffLeadershipSeasonDetail(FieldStaffLeadershipSeasonDetail fieldStaffLeadershipSeasonDetail) {
		getFieldStaffLeadershipSeasonDetails().add(fieldStaffLeadershipSeasonDetail);
		fieldStaffLeadershipSeasonDetail.setHostFamilyStatus(this);

		return fieldStaffLeadershipSeasonDetail;
	}

	public FieldStaffLeadershipSeasonDetail removeFieldStaffLeadershipSeasonDetail(FieldStaffLeadershipSeasonDetail fieldStaffLeadershipSeasonDetail) {
		getFieldStaffLeadershipSeasonDetails().remove(fieldStaffLeadershipSeasonDetail);
		fieldStaffLeadershipSeasonDetail.setHostFamilyStatus(null);

		return fieldStaffLeadershipSeasonDetail;
	}

}