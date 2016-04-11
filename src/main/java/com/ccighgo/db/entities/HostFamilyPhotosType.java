package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyPhotosType database table.
 * 
 */
@Entity
@Table(name="HostFamilyPhotosType")
@NamedQuery(name="HostFamilyPhotosType.findAll", query="SELECT h FROM HostFamilyPhotosType h")
public class HostFamilyPhotosType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyPhotoTypeId;

	@Column(length=50)
	private String hostFamilyPhotoTypeName;

	private Byte isOptional;

	//bi-directional many-to-one association to HostFamilyPhoto
	@OneToMany(mappedBy="hostFamilyPhotosType")
	private List<HostFamilyPhoto> hostFamilyPhotos;

	public HostFamilyPhotosType() {
	}

	public Integer getHostFamilyPhotoTypeId() {
		return this.hostFamilyPhotoTypeId;
	}

	public void setHostFamilyPhotoTypeId(Integer hostFamilyPhotoTypeId) {
		this.hostFamilyPhotoTypeId = hostFamilyPhotoTypeId;
	}

	public String getHostFamilyPhotoTypeName() {
		return this.hostFamilyPhotoTypeName;
	}

	public void setHostFamilyPhotoTypeName(String hostFamilyPhotoTypeName) {
		this.hostFamilyPhotoTypeName = hostFamilyPhotoTypeName;
	}

	public Byte getIsOptional() {
		return this.isOptional;
	}

	public void setIsOptional(Byte isOptional) {
		this.isOptional = isOptional;
	}

	public List<HostFamilyPhoto> getHostFamilyPhotos() {
		return this.hostFamilyPhotos;
	}

	public void setHostFamilyPhotos(List<HostFamilyPhoto> hostFamilyPhotos) {
		this.hostFamilyPhotos = hostFamilyPhotos;
	}

	public HostFamilyPhoto addHostFamilyPhoto(HostFamilyPhoto hostFamilyPhoto) {
		getHostFamilyPhotos().add(hostFamilyPhoto);
		hostFamilyPhoto.setHostFamilyPhotosType(this);

		return hostFamilyPhoto;
	}

	public HostFamilyPhoto removeHostFamilyPhoto(HostFamilyPhoto hostFamilyPhoto) {
		getHostFamilyPhotos().remove(hostFamilyPhoto);
		hostFamilyPhoto.setHostFamilyPhotosType(null);

		return hostFamilyPhoto;
	}

}