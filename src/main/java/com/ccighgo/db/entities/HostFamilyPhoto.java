package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyPhotos database table.
 * 
 */
@Entity
@Table(name="HostFamilyPhotos")
@NamedQuery(name="HostFamilyPhoto.findAll", query="SELECT h FROM HostFamilyPhoto h")
public class HostFamilyPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyPhotoId;

	private Byte active;

	private Byte approvedByCCI;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(length=1000)
	private String description;

	@Column(length=50)
	private String fileName;

	@Column(length=50)
	private String filePath;

	@Column(length=45)
	private String isOptional;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Column(length=50)
	private String photoName;

	private Byte rejectedByCCI;

	private Byte submittedToCCI;

	//bi-directional many-to-one association to HostFamilyPhotosType
	@ManyToOne
	@JoinColumn(name="hostFamilyPhotoTypeId", nullable=false)
	private HostFamilyPhotosType hostFamilyPhotosType;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyPhoto() {
	}

	public Integer getHostFamilyPhotoId() {
		return this.hostFamilyPhotoId;
	}

	public void setHostFamilyPhotoId(Integer hostFamilyPhotoId) {
		this.hostFamilyPhotoId = hostFamilyPhotoId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getApprovedByCCI() {
		return this.approvedByCCI;
	}

	public void setApprovedByCCI(Byte approvedByCCI) {
		this.approvedByCCI = approvedByCCI;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getIsOptional() {
		return this.isOptional;
	}

	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Byte getRejectedByCCI() {
		return this.rejectedByCCI;
	}

	public void setRejectedByCCI(Byte rejectedByCCI) {
		this.rejectedByCCI = rejectedByCCI;
	}

	public Byte getSubmittedToCCI() {
		return this.submittedToCCI;
	}

	public void setSubmittedToCCI(Byte submittedToCCI) {
		this.submittedToCCI = submittedToCCI;
	}

	public HostFamilyPhotosType getHostFamilyPhotosType() {
		return this.hostFamilyPhotosType;
	}

	public void setHostFamilyPhotosType(HostFamilyPhotosType hostFamilyPhotosType) {
		this.hostFamilyPhotosType = hostFamilyPhotosType;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}