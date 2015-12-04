package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LookupUSStates database table.
 * 
 */
@Entity
@Table(name="LookupUSStates")
@NamedQuery(name="LookupUSState.findAll", query="SELECT l FROM LookupUSState l")
public class LookupUSState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer usStatesId;

	@Column(nullable=false, length=5)
	private String stateCode;

	@Column(nullable=false, length=50)
	private String stateName;

	//bi-directional many-to-one association to CCIStaffUser
	@OneToMany(mappedBy="lookupUsstate")
	private List<CCIStaffUser> ccistaffUsers;

	//bi-directional many-to-one association to FieldStaff
	@OneToMany(mappedBy="lookupUsstate1")
	private List<FieldStaff> fieldStaffs1;

	//bi-directional many-to-one association to FieldStaff
	@OneToMany(mappedBy="lookupUsstate2")
	private List<FieldStaff> fieldStaffs2;

	//bi-directional many-to-one association to FieldStaffReference
	@OneToMany(mappedBy="lookupUsstate")
	private List<FieldStaffReference> fieldStaffReferences;

	//bi-directional many-to-one association to SeasonGeographyConfiguration
	@OneToMany(mappedBy="lookupUsstate")
	private List<SeasonGeographyConfiguration> seasonGeographyConfigurations;

	//bi-directional many-to-one association to SeasonIHPGeographyConfiguration
	@OneToMany(mappedBy="lookupUsstate")
	private List<SeasonIHPGeographyConfiguration> seasonIhpgeographyConfigurations;

	public LookupUSState() {
	}

	public Integer getUsStatesId() {
		return this.usStatesId;
	}

	public void setUsStatesId(Integer usStatesId) {
		this.usStatesId = usStatesId;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<CCIStaffUser> getCcistaffUsers() {
		return this.ccistaffUsers;
	}

	public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
		this.ccistaffUsers = ccistaffUsers;
	}

	public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().add(ccistaffUser);
		ccistaffUser.setLookupUsstate(this);

		return ccistaffUser;
	}

	public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
		getCcistaffUsers().remove(ccistaffUser);
		ccistaffUser.setLookupUsstate(null);

		return ccistaffUser;
	}

	public List<FieldStaff> getFieldStaffs1() {
		return this.fieldStaffs1;
	}

	public void setFieldStaffs1(List<FieldStaff> fieldStaffs1) {
		this.fieldStaffs1 = fieldStaffs1;
	}

	public FieldStaff addFieldStaffs1(FieldStaff fieldStaffs1) {
		getFieldStaffs1().add(fieldStaffs1);
		fieldStaffs1.setLookupUsstate1(this);

		return fieldStaffs1;
	}

	public FieldStaff removeFieldStaffs1(FieldStaff fieldStaffs1) {
		getFieldStaffs1().remove(fieldStaffs1);
		fieldStaffs1.setLookupUsstate1(null);

		return fieldStaffs1;
	}

	public List<FieldStaff> getFieldStaffs2() {
		return this.fieldStaffs2;
	}

	public void setFieldStaffs2(List<FieldStaff> fieldStaffs2) {
		this.fieldStaffs2 = fieldStaffs2;
	}

	public FieldStaff addFieldStaffs2(FieldStaff fieldStaffs2) {
		getFieldStaffs2().add(fieldStaffs2);
		fieldStaffs2.setLookupUsstate2(this);

		return fieldStaffs2;
	}

	public FieldStaff removeFieldStaffs2(FieldStaff fieldStaffs2) {
		getFieldStaffs2().remove(fieldStaffs2);
		fieldStaffs2.setLookupUsstate2(null);

		return fieldStaffs2;
	}

	public List<FieldStaffReference> getFieldStaffReferences() {
		return this.fieldStaffReferences;
	}

	public void setFieldStaffReferences(List<FieldStaffReference> fieldStaffReferences) {
		this.fieldStaffReferences = fieldStaffReferences;
	}

	public FieldStaffReference addFieldStaffReference(FieldStaffReference fieldStaffReference) {
		getFieldStaffReferences().add(fieldStaffReference);
		fieldStaffReference.setLookupUsstate(this);

		return fieldStaffReference;
	}

	public FieldStaffReference removeFieldStaffReference(FieldStaffReference fieldStaffReference) {
		getFieldStaffReferences().remove(fieldStaffReference);
		fieldStaffReference.setLookupUsstate(null);

		return fieldStaffReference;
	}

	public List<SeasonGeographyConfiguration> getSeasonGeographyConfigurations() {
		return this.seasonGeographyConfigurations;
	}

	public void setSeasonGeographyConfigurations(List<SeasonGeographyConfiguration> seasonGeographyConfigurations) {
		this.seasonGeographyConfigurations = seasonGeographyConfigurations;
	}

	public SeasonGeographyConfiguration addSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		getSeasonGeographyConfigurations().add(seasonGeographyConfiguration);
		seasonGeographyConfiguration.setLookupUsstate(this);

		return seasonGeographyConfiguration;
	}

	public SeasonGeographyConfiguration removeSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
		getSeasonGeographyConfigurations().remove(seasonGeographyConfiguration);
		seasonGeographyConfiguration.setLookupUsstate(null);

		return seasonGeographyConfiguration;
	}

	public List<SeasonIHPGeographyConfiguration> getSeasonIhpgeographyConfigurations() {
		return this.seasonIhpgeographyConfigurations;
	}

	public void setSeasonIhpgeographyConfigurations(List<SeasonIHPGeographyConfiguration> seasonIhpgeographyConfigurations) {
		this.seasonIhpgeographyConfigurations = seasonIhpgeographyConfigurations;
	}

	public SeasonIHPGeographyConfiguration addSeasonIhpgeographyConfiguration(SeasonIHPGeographyConfiguration seasonIhpgeographyConfiguration) {
		getSeasonIhpgeographyConfigurations().add(seasonIhpgeographyConfiguration);
		seasonIhpgeographyConfiguration.setLookupUsstate(this);

		return seasonIhpgeographyConfiguration;
	}

	public SeasonIHPGeographyConfiguration removeSeasonIhpgeographyConfiguration(SeasonIHPGeographyConfiguration seasonIhpgeographyConfiguration) {
		getSeasonIhpgeographyConfigurations().remove(seasonIhpgeographyConfiguration);
		seasonIhpgeographyConfiguration.setLookupUsstate(null);

		return seasonIhpgeographyConfiguration;
	}

}