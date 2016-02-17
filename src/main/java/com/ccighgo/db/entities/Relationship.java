package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Relationship database table.
 * 
 */
@Entity
@Table(name="Relationship")
@NamedQuery(name="Relationship.findAll", query="SELECT r FROM Relationship r")
public class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer relationshipId;

	@Column(length=25)
	private String relationshipType;

	public Relationship() {
	}

	public Integer getRelationshipId() {
		return this.relationshipId;
	}

	public void setRelationshipId(Integer relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

}