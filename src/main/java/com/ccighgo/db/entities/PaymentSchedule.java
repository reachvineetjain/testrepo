package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PaymentSchedule database table.
 * 
 */
@Entity
@NamedQuery(name="PaymentSchedule.findAll", query="SELECT p FROM PaymentSchedule p")
public class PaymentSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer paymentScheduleId;

	private String scheduleName;

	//bi-directional many-to-one association to FieldStaffSeason
	@OneToMany(mappedBy="paymentSchedule")
	private List<FieldStaffSeason> fieldStaffSeasons;

	//bi-directional many-to-one association to HostFamilySeason
	@OneToMany(mappedBy="paymentSchedule")
	private List<HostFamilySeason> hostFamilySeasons;

	//bi-directional many-to-one association to SeasonF1Detail
	@OneToMany(mappedBy="paymentSchedule")
	private List<SeasonF1Detail> seasonF1details;

	//bi-directional many-to-one association to SeasonJ1Detail
	@OneToMany(mappedBy="paymentSchedule")
	private List<SeasonJ1Detail> seasonJ1details;

	public PaymentSchedule() {
	}

	public Integer getPaymentScheduleId() {
		return this.paymentScheduleId;
	}

	public void setPaymentScheduleId(Integer paymentScheduleId) {
		this.paymentScheduleId = paymentScheduleId;
	}

	public String getScheduleName() {
		return this.scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public List<FieldStaffSeason> getFieldStaffSeasons() {
		return this.fieldStaffSeasons;
	}

	public void setFieldStaffSeasons(List<FieldStaffSeason> fieldStaffSeasons) {
		this.fieldStaffSeasons = fieldStaffSeasons;
	}

	public FieldStaffSeason addFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().add(fieldStaffSeason);
		fieldStaffSeason.setPaymentSchedule(this);

		return fieldStaffSeason;
	}

	public FieldStaffSeason removeFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().remove(fieldStaffSeason);
		fieldStaffSeason.setPaymentSchedule(null);

		return fieldStaffSeason;
	}

	public List<HostFamilySeason> getHostFamilySeasons() {
		return this.hostFamilySeasons;
	}

	public void setHostFamilySeasons(List<HostFamilySeason> hostFamilySeasons) {
		this.hostFamilySeasons = hostFamilySeasons;
	}

	public HostFamilySeason addHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().add(hostFamilySeason);
		hostFamilySeason.setPaymentSchedule(this);

		return hostFamilySeason;
	}

	public HostFamilySeason removeHostFamilySeason(HostFamilySeason hostFamilySeason) {
		getHostFamilySeasons().remove(hostFamilySeason);
		hostFamilySeason.setPaymentSchedule(null);

		return hostFamilySeason;
	}

	public List<SeasonF1Detail> getSeasonF1details() {
		return this.seasonF1details;
	}

	public void setSeasonF1details(List<SeasonF1Detail> seasonF1details) {
		this.seasonF1details = seasonF1details;
	}

	public SeasonF1Detail addSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().add(seasonF1detail);
		seasonF1detail.setPaymentSchedule(this);

		return seasonF1detail;
	}

	public SeasonF1Detail removeSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().remove(seasonF1detail);
		seasonF1detail.setPaymentSchedule(null);

		return seasonF1detail;
	}

	public List<SeasonJ1Detail> getSeasonJ1details() {
		return this.seasonJ1details;
	}

	public void setSeasonJ1details(List<SeasonJ1Detail> seasonJ1details) {
		this.seasonJ1details = seasonJ1details;
	}

	public SeasonJ1Detail addSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().add(seasonJ1detail);
		seasonJ1detail.setPaymentSchedule(this);

		return seasonJ1detail;
	}

	public SeasonJ1Detail removeSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().remove(seasonJ1detail);
		seasonJ1detail.setPaymentSchedule(null);

		return seasonJ1detail;
	}

}