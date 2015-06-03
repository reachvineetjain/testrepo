package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SysDiagrams database table.
 * 
 */
@Entity
@Table(name="SysDiagrams")
@NamedQuery(name="SysDiagram.findAll", query="SELECT s FROM SysDiagram s")
public class SysDiagram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int diagramID;

	@Lob
	private byte[] definition;

	@Column(nullable=false, length=160)
	private String name;

	@Column(nullable=false)
	private int principalID;

	private int version;

	public SysDiagram() {
	}

	public int getDiagramID() {
		return this.diagramID;
	}

	public void setDiagramID(int diagramID) {
		this.diagramID = diagramID;
	}

	public byte[] getDefinition() {
		return this.definition;
	}

	public void setDefinition(byte[] definition) {
		this.definition = definition;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrincipalID() {
		return this.principalID;
	}

	public void setPrincipalID(int principalID) {
		this.principalID = principalID;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}